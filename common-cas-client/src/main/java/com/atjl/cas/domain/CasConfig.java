package com.atjl.cas.domain;

import java.util.Map;


public class CasConfig {
	
	private Map<String, String> properties;

	public String getConfig(String key) {
		return properties.get(key);
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}
	 
}
