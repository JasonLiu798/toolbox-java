package com.atjl.validate.core;

import java.util.HashMap;

/**
 * url-参数-校验规则
 */
public class ActionValidate {
	// 要验证的url
	private String actionUrl;
	// 当前Action的验证规则
	private HashMap<String,ParamValidator> param2rules = new HashMap<>();

	public String getActionUrl() {
		return actionUrl;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}

    public void setParam2rules(HashMap<String, ParamValidator> param2rules) {
        this.param2rules = param2rules;
    }

    public HashMap<String, ParamValidator> getParam2rules() {
        return param2rules;
    }

    @Override
    public String toString() {
        return "ActionValidate{" +
                "actionUrl='" + actionUrl + '\'' +
                ", paramList=" + param2rules +
                '}';
    }
}
