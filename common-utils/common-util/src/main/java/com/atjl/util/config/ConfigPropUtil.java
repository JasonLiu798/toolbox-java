package com.atjl.util.config;

import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.collection.CollectionUtilEx;
import com.atjl.util.file.PathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * properties读取工具类
 * 默认
 */
public final class ConfigPropUtil {
    private ConfigPropUtil() {
        super();
    }

    private static final Logger logger = LoggerFactory.getLogger(ConfigPropUtil.class);

    /**
     * key: filename
     * value: resourceBundle
     */
    private static final Map<String, ResourceBundle> RESOURCE_BUNDLES = new ConcurrentHashMap<>();
    private static final Map<String, String> FILE_PATH_TO_FILE = new ConcurrentHashMap<>();

    /**
     * 默认从 classpath properties文件初始化
     */
    static {
        init(ConfigPropConstant.DFT_FILE_NAME, ConfigPropConstant.TP_CLASSPATH);
    }

    /**
     * @param filename not inclue ext .properties
     * @return
     */
    public static boolean checkPropertiesExist(String filename) {
        if (Thread.currentThread().getContextClassLoader().getResource(filename + ConfigPropConstant.PROP_EXT) == null) {
            return false;
        }
        return true;
    }

    public static boolean init(String filePath, int type) {
        if (StringCheckUtil.isEmpty(filePath)) {
            logger.error("init prop,filepath null");
            return false;
        }
        boolean initRes = false;
        if (type == ConfigPropConstant.TP_CLASSPATH) {
            initRes = initPropFromClasspath(filePath);
        } else if (type == ConfigPropConstant.TP_FILE) {
            initRes = initPropFromFile(filePath);
        } else {
            logger.error("init prop,unknow type {}", type);
            return initRes;
        }
        return initRes;
    }

    /**
     * init resource bundle from classpath
     *
     * @param fileName
     * @return
     */
    public static boolean initPropFromClasspath(String fileName) {
        if (checkPropertiesExist(fileName)) {
            RESOURCE_BUNDLES.put(fileName, ResourceBundle.getBundle(fileName));
        } else {
            logger.warn("properties file {} not exist", fileName);
            return false;
        }
        return true;
    }

    /**
     * init resource bundle from file
     *
     * @param fileWholePath
     * @return
     */
    public static boolean initPropFromFile(String fileWholePath) {
        String fileName = PathUtil.getFileName(fileWholePath);
        if (StringCheckUtil.isEmpty(fileName)) {
            logger.error("init prop from file,get file name null {}", fileWholePath);
            return false;
        }
        if (RESOURCE_BUNDLES.containsKey(fileName)) {
            logger.warn("init prop from file,file already init,file name {},file path {}", fileName, fileWholePath);
            return true;
        }

        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(fileWholePath));
            RESOURCE_BUNDLES.put(fileWholePath, new PropertyResourceBundle(in));
            FILE_PATH_TO_FILE.put(fileWholePath, fileName);
        } catch (IOException e) {
            logger.error("init prop from file,read fail {}", e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 获取文件对应的原始路径
     *
     * @param fileName
     * @return
     */
    public static String getFilePath(String fileName) {
        return FILE_PATH_TO_FILE.get(fileName);
    }

    /**
     * 获取使用绝对文件路径 初始化的所有 文件名
     *
     * @return
     */
    public static Set<String> getPropUserFiles() {
        return FILE_PATH_TO_FILE.keySet();
    }

    /**
     * 初始化并 获取 配置项
     *
     * @param fileName
     * @param key
     * @return
     */
    public static String getAndInit(String fileName, String key) {
        if (!initPropFromClasspath(fileName)) {
            logger.error("init fail,file {}", fileName);
            return "";
        }
        return get(fileName, key);
    }

    /**
     * get from bundle
     *
     * @param key      prop key
     * @param fileName filepath
     * @return value
     */
    public static String get(String fileName, String key) {
        if (StringCheckUtil.isEmpty(key)) {
            logger.error("get key null or empty");
            return "";
        }
        if (!RESOURCE_BUNDLES.containsKey(fileName)) {
            logger.error("get,get resource bundle fail,{}", fileName);
            return "";
        }
        ResourceBundle rb = RESOURCE_BUNDLES.get(fileName);
        try {
            return rb.getString(key);
        } catch (MissingResourceException e) {
            logger.error("get {}", e.getMessage());
            return "";
        }
    }

    public static String get(String key) {
        return get(ConfigPropConstant.DFT_FILE_NAME, key);
    }

    /**
     * @param fileName
     * @param keys
     * @return
     */
    public static Map<String, String> getBatch(String fileName, List<String> keys) {
        if (CollectionUtilEx.isEmpty(keys)) {
            return new HashMap<>();
        }
        if (!RESOURCE_BUNDLES.containsKey(fileName)) {
            logger.error("getBatch,get resource bundle fail,{}", fileName);
            return new HashMap<>();
        }
        ResourceBundle rb = RESOURCE_BUNDLES.get(fileName);
        Map<String, String> res = new HashMap<>();
        for (String key : keys) {
            try {
                res.put(key, rb.getString(key));
            } catch (Exception e) {
                logger.error("getBatch exception {}", e.getMessage());
            }
        }
        return res;
    }

    public static Map<String, String> getBatch(List<String> keys) {
        return getBatch(ConfigPropConstant.DFT_FILE_NAME, keys);
    }

}
