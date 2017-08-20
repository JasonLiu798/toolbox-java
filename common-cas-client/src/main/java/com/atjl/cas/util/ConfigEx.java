package com.atjl.cas.util;


/**
 * 
 *
 */
public class ConfigEx {
	
	private ConfigEx() {}
	
    public static String getConfigValue(String propertyValue, String defaultValue) {
        return propertyValue == null ?  defaultValue : propertyValue;
    }
}
