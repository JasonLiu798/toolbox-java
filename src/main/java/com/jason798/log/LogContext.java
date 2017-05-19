package com.jason798.log;

import com.jason798.collection.CollectionUtil;

/**
 * store log enable,level
 */
public class LogContext {
	
	/**
	 * 是否打开日志，初始化打开
	 */
	public static boolean enable = true;
	/**
	 * 打印日志级别，初始化为info
	 */
	public static LogLevel level = LogLevel.INFO;
	
	
	/**
	 * AOP 日志黑名单
	 * <p>
	 * LogServiceImpl,ThreadManager,LogController,LoginController
	 */
	private static String[] BLACK = new String[]{
			"LogService", "LogServiceImpl",
			"ThreadManager", "LogController",
			"LoginController"
	};
	
	/**
	 * 日志 白名单
	 CommonController
	 DictionaryController
	 LoginController
	 ModuleController
	 TsRoleController
	 TsRoleModuleController
	 TsUserController
	 TsUserRoleController
	 */
	private static String[] WHITE = new String[]{
			"CommonController", "DictionaryController",
			"LoginController",
			"ModuleController",
			"TsRoleController",
			"TsRoleModuleController",
			"TsUserController",
			"TsUserRoleController"
	};
	
	
	/**
	 * 开发环境必开，否则看不到异常
	 */
	public static void enableDev() {
		enable = true;
		level = LogLevel.DEBUG;
	}
	
	/**
	 * 设置黑名单
	 *
	 * @param blackList
	 */
	public static void setBlackList(String[] blackList) {
		if (!CollectionUtil.isEmpty(blackList)) {
			BLACK = blackList;
		}
	}
	

	
	public static LogLevel getLevel() {
		return level;
	}
	
	public static void debugEnable() {
		enable();
		setLevel(LogLevel.DEBUG);
	}
	
	public static void infoEnable() {
		enable();
		setLevel(LogLevel.INFO);
	}
	
	public static void errorEnable() {
		enable();
		setLevel(LogLevel.ERROR);
	}
	
	public static void setLevel(LogLevel level) {
		level = level;
	}
	
	public static void setEnable(boolean on) {
		enable = on;
	}
	
	public static void enable() {
		enable = true;
	}
	
	public static void disable() {
		enable = false;
	}
	
	
}
