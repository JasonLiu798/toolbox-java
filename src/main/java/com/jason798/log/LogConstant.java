package com.jason798.log;


import java.util.HashMap;
import java.util.Map;

public class LogConstant {
	
	public static final String LV_DEBUG = "D";
	public static final String LV_INFO = "I";
	public static final String LV_WARN = "W";
	public static final String LV_ERROR = "E";
	
	public static final String DICT_LOG_BLACK_LIST_KEY = "blacklist";
	
	/**
	 * ######################## content length max size ########################
	 */
	public static final int RES_MAX_SIZE = 4000;
	public static final int PARAM_MAX_SIZE = 2000;
	public static final int REF_MAX_SIZE = 1000;
	
	public static final String LOG_RES_EXCEPTION = "发送异常";
	/**
	 * ####################### log types ########################
	 */
	public static final String CREATE = "CRT";//create/insert data
	public static final String DEL = "DEL";//delete data
	public static final String UPD = "UPD";//update data
	public static final String GET = "GET";//get data
	public static final String LOGIN = "LOGIN";//login
	public static final String LOGOUT = "LOGOUT";//logout
	public static final String DATAIN = "DATAIN";//import data
	public static final String DATAOUT = "DATAOUT";//export data
	public static final String OTHER = "OT";//other data
	
	/**
	 * ####################### modules ########################
	 */
	public static final String MODULE_SYS = "sys";
	// timing module
	public static final String MODULE_TIMING = "timing";
	
	public static final String MODULE_LOG = "log";
	public static final String MODULE_UNKNOWN = "unknown";
	
	/**
	 * defualt values
	 */
	public static final String DFT_USER = "SYS";
	public static final String DFT_USER_NAME = "SYS";
	
	
	public static final String METHOD_PREFIX_TO_TYPE = "";
	
	public static final int OUTER_CALLER_LEVEL = 4;
	
	public static final Map<String, String> METHOD_TO_LOG_TYPE = new HashMap<>();
	
	static {
		METHOD_TO_LOG_TYPE.put("find", GET);
		METHOD_TO_LOG_TYPE.put("get", GET);
		METHOD_TO_LOG_TYPE.put("query", GET);
		
		METHOD_TO_LOG_TYPE.put("delete", DEL);
		METHOD_TO_LOG_TYPE.put("del", DEL);
		
		METHOD_TO_LOG_TYPE.put("add", CREATE);
		METHOD_TO_LOG_TYPE.put("create", DEL);
		
		METHOD_TO_LOG_TYPE.put("export", DATAOUT);
		METHOD_TO_LOG_TYPE.put("import", DATAIN);
		
		//METHOD_TO_LOG_TYPE.put("ecp",ECP);
		
		METHOD_TO_LOG_TYPE.put("login", LOGIN);
		METHOD_TO_LOG_TYPE.put("logout", LOGOUT);
		
		METHOD_TO_LOG_TYPE.put("update", UPD);
		METHOD_TO_LOG_TYPE.put("set", UPD);
	}
	
	public static String getMethodType(String methodName) {
		return METHOD_TO_LOG_TYPE.get(methodName);
	}
	

	public static final String [] WHITE = {"A","B"};
	
	public static final String [] BLACK = {"A","B"};

}
