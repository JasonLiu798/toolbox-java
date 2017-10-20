package com.atjl.util.config.util;


import com.atjl.util.config.ConfigPropConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

/**
 * 加载配置文件 辅助类
 * 内部用
 */
public class ConfigLoadUtil {
    private ConfigLoadUtil() {
        super();
    }

    private static final Logger logger = LoggerFactory.getLogger(ConfigLoadUtil.class);


    /**
     * load properties to Properties
     *
     * @param propertiesFile properteies path
     * @return properties
     */
    public static Properties loadProperties(String propertiesFile, int type) throws IOException {
        switch (type) {
            case ConfigPropConstant.TP_FILE:
                return loadPropertiesFromFile(propertiesFile);
            case ConfigPropConstant.TP_CLASSPATH:
                return loadPropertiesFromClasspathFile(propertiesFile);
            default:
                logger.error("loadProperties unsupported type,{}", type);
                return null;
        }
    }

    public static Properties loadPropertiesFromClasspathFile(String file) throws IOException {
        Properties properties = new Properties();
        properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(file));
        return properties;
    }

    public static Properties loadPropertiesFromFile(String fileWholePath) {
        Properties properties = new Properties();
        File file = new File(fileWholePath);
        if (!file.exists()) {
            return null;
        }
        InputStream is = null;
        try {
            is = new BufferedInputStream(new FileInputStream(file));
            properties.load(is);
            return properties;
        } catch (IOException e) {
            logger.error("loadPropertiesFromFile read fail {}", e.getMessage());
            return null;
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    logger.error("loadPropertiesFromFile close fail {}", e.getMessage());
                }
            }
        }
    }

}
