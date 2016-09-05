package com.jason798.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * properties helper
 */
public class PropertiesHelper {

    private static final Logger LOG = LoggerFactory.getLogger(PropertiesHelper.class);

    /**
     * classpath:app.properties
     */
    private static final String DFT_FILE_NAME = "config";

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(DFT_FILE_NAME);

    private static ResourceBundle EXIT_RESOURCE_BUNDLE = null;

    public static String[] jsStrings = new String[1];

    private PropertiesHelper() {
    }


    public static String getString(String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
			LOG.error("load properties for key = " + key + "not found!");
            return "";
        }
    }

    public static String getByKey(String key) {
        try {
            if (EXIT_RESOURCE_BUNDLE == null) {
                return "";
            }
            return EXIT_RESOURCE_BUNDLE.getString(key);
        } catch (Exception e) {
			LOG.error("load properties for key = " + key + "not found!");
            return "";
        }
    }

    public static void initProperties(String filePath) throws IOException {
        InputStream in = new BufferedInputStream(new FileInputStream(filePath));
        EXIT_RESOURCE_BUNDLE = new PropertyResourceBundle(in);
    }

	public static Properties loadProperties(String propertiesFile){
		Properties properties = new Properties();
		try {
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(propertiesFile));
		}catch (Throwable t) {
			throw new IllegalStateException("read consumer properties file fail", t);
		}
		return properties;
	}


	public static boolean getBooleanFromPropertiesDefault(Properties prop,String key,boolean defaultVal){
		boolean res = defaultVal;
		Boolean tmp = null;
		try{
			Object obj = prop.get(key);
			if(obj!=null){
				String tmpStr = String.valueOf(obj);
				tmp = Boolean.parseBoolean(tmpStr);
			}
		}catch (Exception e){
			LOG.error("read config {} error,use default value {}",key,defaultVal);
		}

		if(tmp!=null){
			if(LOG.isDebugEnabled()) {
				LOG.debug("read config {} value {}", key, tmp);
			}
			return tmp;
		}
		return res;
	}

	public static int getIntFromPropertiesDefault(Properties prop,String key, int defaultVal){
		int res = defaultVal;
		Integer tmp = null;
		try{
			Object obj = prop.get(key);
			String tmpStr = null;
			if(obj!=null){
				tmpStr = String.valueOf(prop.get(key));
				tmp =Integer.parseInt(tmpStr) ;
			}
		}catch (Exception e){
			LOG.error("read config {} error,use default value {}",key,defaultVal);
		}
		if(tmp!=null){
			if(LOG.isDebugEnabled()) {
				LOG.debug("read config {} value {}", key, tmp);
			}
			return tmp;
		}
		return res;
	}

	public static String getStringFromPropertiesDefault(Properties prop,String key,String defaultVal){
		String res = defaultVal;
		String tmp = null;
		try{
			Object obj = prop.get(key);
			if(obj!=null){
				tmp = String.valueOf(prop.get(key));
			}
		}catch (Exception e){
			LOG.error("read config {} error,use default value {}",key,defaultVal);
		}
		if(tmp!=null){
			if(LOG.isDebugEnabled()) {
				LOG.debug("read config {} value {}", key, tmp);
			}
			return tmp;
		}
		return res;
	}






}
