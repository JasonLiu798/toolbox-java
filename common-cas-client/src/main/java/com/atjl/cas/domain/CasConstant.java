package com.atjl.cas.domain;

public class CasConstant {
	
	private CasConstant() {
		throw new IllegalAccessError("Utility class");
	}
	
	/**
	 *  token验证成功后，写到COOKIE的参数
	 */
	public static final String TOKEN_COOKIE_KEY = "_TOKEN_KEY_";


	/**
	 * Defines the parameter to look for for the artifact.
	 */
	public static final String TICKET_NAME = "ticket";
}
