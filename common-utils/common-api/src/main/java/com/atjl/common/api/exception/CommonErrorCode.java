package com.atjl.common.api.exception;

/**
 * common errors
 */
public class CommonErrorCode {
	
	public static final String SUCCESS_CODE = "0";
	public static final String SUCCESS_MSG = "成功";
	public static final String NO_ACTION = "";
	public static ErrorCode SUCCESS = new ErrorCode(SUCCESS_CODE, SUCCESS_MSG, NO_ACTION);
	
	public static final String UNKNOWN_ERROR_CODE = "S00001";
	public static final String UNKNOWN_ERROR_MSG = "未知错误";
	public static final String UNKNOWN_ERROR_ACTION = "请联系系统管理员";
	
	public static ErrorCode UNKNOW_ERROR = new ErrorCode(UNKNOWN_ERROR_CODE, UNKNOWN_ERROR_MSG, UNKNOWN_ERROR_ACTION);
	
	public static final String CONFIGURE_ERROR_CODE = "S00002";
	public static final String CONFIGURE_ERROR_MSG = "配置读取错误";
	public static final String CONFIGURE_ERROR_ACTION = "请检查配置";
	
	public static ErrorCode CONFIGURE_ERROR = new ErrorCode(CONFIGURE_ERROR_CODE,
			CONFIGURE_ERROR_MSG,CONFIGURE_ERROR_ACTION);
	
	public static final String PARAM_NULL_CODE = "S00003";
	public static final String PARAM_NULL_MSG = "参数为空";
	public static final String PARAM_NULL_ACTION = "请检查参数";
	public static ErrorCode PARAM_NULL = new ErrorCode(PARAM_NULL_CODE,
			PARAM_NULL_MSG,PARAM_NULL_ACTION);
	


}
