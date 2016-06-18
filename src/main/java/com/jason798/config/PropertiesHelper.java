package com.jason798.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * properties helper
 */
public class PropertiesHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesHelper.class);

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
            LOGGER.error("load properties for key = " + key + "not found!");
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
            LOGGER.error("load properties for key = " + key + "not found!");
            return "";
        }
    }

    public static void initProperties(String filePath) throws IOException {
        InputStream in = new BufferedInputStream(new FileInputStream(filePath));
        EXIT_RESOURCE_BUNDLE = new PropertyResourceBundle(in);
    }
}
