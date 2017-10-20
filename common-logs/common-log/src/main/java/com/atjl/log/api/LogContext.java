package com.atjl.log.api;

/**
 * 日志上下文
 */
public class LogContext {
    private LogContext() {
        throw new UnsupportedOperationException();
    }

    /**
     * 是否打开日志，初始化打开
     */
    public static boolean enable = true;
    /**
     * 打印日志级别，初始化为info
     */
    public static LogLevel level = LogLevel.INFO;

    public static boolean simplify = true;//是否简写类名
    public static int simplifyLen = 60;//超过20则简写
    public static boolean showLv = true;


    public static boolean isEnable() {
        return enable;
    }

    public static void setEnable(boolean enable) {
        LogContext.enable = enable;
    }

    public static LogLevel getLevel() {
        return level;
    }

    public static void setLevel(LogLevel level) {
        LogContext.level = level;
    }

    /**
     * 查看 日志状态
     */
    public static String getContextStatusJson() {
        StringBuilder sb = new StringBuilder();
        return sb.append("{\"on\":").append(enable).append(",").append("\"level\":").append(level).append("}").toString();
    }

}
