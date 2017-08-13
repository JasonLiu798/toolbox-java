package com.atjl.util.config;

import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.common.ReflectUtil;
import com.atjl.util.character.RegexUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * properties helper
 */
public class ConfigUtil {
	private ConfigUtil(){
		throw new UnsupportedOperationException();
	}

    private static final Logger logger = LoggerFactory.getLogger(ConfigUtil.class);

    private static final Map<String, ResourceBundle> resourceBundles = new HashMap<>();
    private static final String DFT_PREFIX = "";
    public static final String TP_CLASSPATH = "";
    //private static final String DFT_FILE = "config";
    //private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(DFT_FILE);

    private static ResourceBundle EXIT_RESOURCE_BUNDLE = null;

    public static String[] jsStrings = new String[1];



    public static void initProperties(String filePath) throws IOException {
        InputStream in = new BufferedInputStream(new FileInputStream(filePath));
        EXIT_RESOURCE_BUNDLE = new PropertyResourceBundle(in);
    }

    public static String getByKey(String key) {
        try {
            if (EXIT_RESOURCE_BUNDLE == null) {
                return "";
            }
            return EXIT_RESOURCE_BUNDLE.getString(key);
        } catch (Exception e) {
            logger.error("load properties for key = " + key + "not found!");
            return "";
        }
    }



    /**
     * init resource bundle
     *
     * @param fileName file path
     */
    private static void initResourceBundle(String fileName) {
        if (resourceBundles.get(fileName) == null) {
            synchronized (ConfigUtil.class) {
                resourceBundles.put(fileName, ResourceBundle.getBundle(fileName));
            }
        }
    }

    /**
     * get from bundle
     *
     * @param key      prop key
     * @param fileName filepath
     * @return value
     */
    public static String get(String key, String fileName) {
        if (resourceBundles.get(fileName) == null) {
            initResourceBundle(fileName);
        }
        ResourceBundle rb = resourceBundles.get(fileName);
        try {
            return rb.getString(key);
        } catch (MissingResourceException e) {
            logger.info(e.getMessage());
            return "";
        }
    }

    /**
     * check properties file exist
     * @param filename filename
     * @return exist or not
     */
    public static boolean checkPropertiesExist(String filename){
        if(Thread.currentThread().getContextClassLoader().getResource(filename)==null){
            return false;
        }
        return true;
    }

    /**
     * load properties to Properties
     *
     * @param propertiesFile properteies path
     * @return properties
     */
    public static Properties loadProperties(String propertiesFile) {
        Properties properties = new Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(propertiesFile));
        } catch (Exception e) {
            logger.error("read properties file fail {}", e.getMessage());
            throw new IllegalStateException("loadProperties load error");
        }
        return properties;
    }


    /**
     * generate config model
     *
     * @param clz            config model class
     * @param prefixKey      prefix key in properties file
     * @param propertiesFile properties file
     * @return config model
     */
    public static <T> T generateConfigModelUsePrefixKey(Class<T> clz, String prefixKey, String propertiesFile) {
        Properties prop = loadProperties(propertiesFile);
        String prefix = getStringFromProperties(prop, prefixKey);
        if (prefix == null) {
            generateConfigModel(clz, DFT_PREFIX, propertiesFile);
        }
        return generateConfigModel(clz, prefix, prop);
    }

    /**
     * generate config model
     *
     * @param clz            config model class
     * @param prefix         prefix
     * @param propertiesFile properties file
     * @return config model
     */
    public static <T> T generateConfigModel(Class<T> clz, String prefix, String propertiesFile) {
        Properties prop = loadProperties(propertiesFile);
        return generateConfigModel(clz, prefix, prop);
    }

    /**
     * auto generateConfigModel config object
     *
     * @param clz  config model class
     * @param prop preoperteis
     * @return generated config model object
     */
    public static <T> T generateConfigModel(Class<T> clz, String prefix, Properties prop) {
        T res = null;
        try {
            res = clz.newInstance();
            Set<String> fields = ReflectUtil.getAllFieldsHaveSetter(clz);
            for (String field : fields) {
                Object val = null;
                Class<?> fieldType = ReflectUtil.getFieldTypeAll(res, field);
                if (logger.isDebugEnabled()) {
                    logger.debug("{} fieldType {}", field, fieldType);
                }
                Object defaultVal = ReflectUtil.getter(res, field);
                Object propVal = prop.get(filterPrefix(prefix, field));

                if (defaultVal == null) {
                    //value not set,must config value
                    if (fieldType == String.class) {
                        val = getStringFromProperties(prop, prefix, field);
                    } else if (fieldType == Boolean.class || fieldType == boolean.class) {
                        val = getBooleanFromProperties(prop, prefix, field);
                    } else if (fieldType == Integer.class || fieldType == int.class) {
                        val = getIntFromProperties(prop, prefix, field);
                    } else if (fieldType == Long.class || fieldType == long.class) {
                        val = getLongFromProperties(prop, prefix, field);
                    } else {
                        logger.error("config model class:{},field {} unknown type,will set to null", clz.toString(), field);
                    }
                } else {
                    //value got default
                    if (propVal != null) {
                        //properties file set value
                        Object tmp = null;
                        if (fieldType == String.class) {
                            tmp = getStringFromPropertiesDefault(prop, prefix, field, String.valueOf(defaultVal));
                        } else if (fieldType == Boolean.class || fieldType == boolean.class) {
                            tmp = getBooleanFromPropertiesDefault(prop, prefix, field, (Boolean) defaultVal);
                        } else if (fieldType == Integer.class || fieldType == int.class) {
                            tmp = getIntFromPropertiesDefault(prop, prefix, field, (Integer) defaultVal);
                        } else if (fieldType == Long.class || fieldType == long.class) {
                            tmp = getLongFromPropertiesDefault(prop, prefix, field, (Integer) defaultVal);
                        } else {
                            logger.error("default value unknown type,field {} will set to null", field);
                        }
                        if (tmp != null) {
                            val = tmp;
                        }
                    }
                }
                if (val != null) {
                    ReflectUtil.setter(res, field, fieldType, val);
                }
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static boolean filter2bool(Object obj) {
        if (obj.getClass() == boolean.class) {
            return (boolean) obj;
        } else if (obj.getClass() == Boolean.class) {
            return (Boolean) obj;
        }
        throw new IllegalArgumentException("filter 2 bool,obj type not int or Integer");
    }


    public static int filter2int(Object obj) {
        if (obj.getClass() == int.class) {
            return (int) obj;
        } else if (obj.getClass() == Integer.class) {
            return (Integer) obj;
        }
        throw new IllegalArgumentException("filter 2 int,obj type not int or Integer");
    }


    /**
     * filter of key prefix
     *
     * @param prefix key prefix
     * @param key    key
     * @return filted key
     */
    private static String filterPrefix(String prefix, String key) {
        if (!StringCheckUtil.isEmpty(prefix)) {
            key = prefix + "." + key;
        }
        return key;
    }

    /**
     * get boolean from properties ,not exist or null,use default
     *
     * @param prop       properties
     * @param prefix     key prefix
     * @param key        key
     * @param defaultVal default value
     * @return boolean value
     */
    private static boolean getBooleanFromPropertiesDefault(Properties prop, String prefix, String key, boolean defaultVal) {
        Boolean tmp = getBooleanFromProperties(prop, prefix, key);
        if (tmp == null) {
            return defaultVal;
        }
        return tmp;
    }

    static boolean getBooleanFromPropertiesDefault(Properties prop, String key, boolean defaultVal) {
        return getBooleanFromPropertiesDefault(prop, DFT_PREFIX, key, defaultVal);
    }

    /**
     * get boolean from properties
     *
     * @param prop   properties
     * @param prefix key prefix
     * @param key    key
     * @return boolean value
     */
    private static Boolean getBooleanFromProperties(Properties prop, String prefix, String key) {
        Object tmp = prop.getProperty(filterPrefix(prefix, key));
        if (tmp == null) {
            return null;
        }
        String stmp = String.valueOf(tmp);
        if (RegexUtil.isBoolean(stmp)) {
            return Boolean.parseBoolean(stmp);
        }
        return null;
    }

    private static Boolean getBooleanFromProperties(Properties prop, String key) {
        return getBooleanFromProperties(prop, DFT_PREFIX, key);
    }


    /**
     * get int from properties,not exist use default
     *
     * @param prop       properties
     * @param prefix     prefix
     * @param key        key
     * @param defaultVal default value
     * @return int properties
     */
    private static int getIntFromPropertiesDefault(Properties prop, String prefix, String key, int defaultVal) {
        Integer val = getIntFromProperties(prop, prefix, key);
        if (val == null) {
            return defaultVal;
        }
        return val;
    }

    private static int getIntFromPropertiesDefault(Properties prop, String key, int defaultVal) {
        return getIntFromPropertiesDefault(prop, DFT_PREFIX, key, defaultVal);
    }

    /**
     * get int from properties
     *
     * @param prop   properteis
     * @param prefix key prefix
     * @param key    key
     * @return interge value
     */
    private static Integer getIntFromProperties(Properties prop, String prefix, String key) {
        Object val = prop.getProperty(filterPrefix(prefix, key));
        if (val == null) {
            return null;
        }
        if (!RegexUtil.isInteger(String.valueOf(val))) {
            return null;
        }
        return Integer.parseInt(String.valueOf(val));
    }

    private static Integer getIntFromProperties(Properties prop, String key) {
        return Integer.parseInt(String.valueOf(prop.getProperty(filterPrefix(DFT_PREFIX, key))));
    }


    private static long getLongFromPropertiesDefault(Properties prop, String prefix, String key, long defaultVal) {
        Long val = getLongFromProperties(prop, prefix, key);
        if (val == null) {
            return defaultVal;
        }
        return val;
    }

    private static Long getLongFromProperties(Properties prop, String prefix, String key) {
        Object val = prop.getProperty(filterPrefix(prefix, key));
        if (val == null) {
            return null;
        }
        if (!RegexUtil.isInteger(String.valueOf(val))) {
            return null;
        }
        return Long.parseLong(String.valueOf(val));
    }


    /**
     * get string from properties
     *
     * @param prop   properteies
     * @param prefix prefix
     * @param key    key
     * @return String value
     */
    private static String getStringFromProperties(Properties prop, String prefix, String key) {
        Object obj = prop.get(filterPrefix(prefix, key));
        if (obj == null) {
            return null;
        }
        return String.valueOf(obj);
    }

    private static String getStringFromProperties(Properties prop, String key) {
        return getStringFromProperties(prop, DFT_PREFIX, key);
    }

    /**
     * get string from perperties,if null,use default
     *
     * @param prop       properties
     * @param prefix     prefix
     * @param key        key
     * @param defaultVal default value
     * @return string value
     */
    private static String getStringFromPropertiesDefault(Properties prop, String prefix, String key, String defaultVal) {
        String res = defaultVal;
        try {
            key = filterPrefix(prefix, key);
            res = prop.getProperty(key, defaultVal);
        } catch (Exception e) {
            logger.error("read config {} error,use default value {}", key, defaultVal);
        }
        return res;
    }

    private static String getStringFromPropertiesDefault(Properties prop, String key, String defaultVal) {
        return getStringFromPropertiesDefault(prop, DFT_PREFIX, key, defaultVal);
    }
}

