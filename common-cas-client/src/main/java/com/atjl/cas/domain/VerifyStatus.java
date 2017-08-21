package com.atjl.cas.domain;

public class VerifyStatus {

	/**
	 * 校验状态
	 */
	private boolean status;
	
	/**
	 * Issuer
	 */
	private String iss;
	
	/**
	 * 描述信息
	 */
	private String msg;

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getIss() {
		return iss;
	}

	public void setIss(String iss) {
		this.iss = iss;
	}
}
