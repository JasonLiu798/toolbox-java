package com.atjl.cas.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;


public class CasSysConfig {

	private static final Logger logger = LoggerFactory.getLogger(CasSysConfig.class);
	public static final String SYS_PATH = "cas.properties";

	private Properties sysconfig = new Properties();

	public String getKey(String key) {
		if (sysconfig.size() == 0) {
			sysconfig=loadConfig();
		}
		Object val = sysconfig.get(key);
		return val == null ? null : val.toString();
	}

	public String getKey(String key, String fileName) {
		sysconfig= loadConfigFromProp(fileName);
		Object val = sysconfig.get(key);
		return val == null ? null : val.toString();
	}

	 
	public Properties loadConfig() {
		return loadConfigFromProp(SYS_PATH);
	}

	
	public static Properties loadConfigFromProp(String propertiesName) {
		Properties sysconfig = new Properties();
		try {
			sysconfig.load(CasSysConfig.class.getClassLoader()
					.getResourceAsStream(propertiesName));
		} catch (IOException e) {
			logger.error("context", e);
		}
		return sysconfig;
	}



}
