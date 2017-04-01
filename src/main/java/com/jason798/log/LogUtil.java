package com.jason798.log;

import com.jason798.collection.CollectionUtil;
import com.jason798.config.ConfigUtil;
import com.jason798.json.JSONFastJsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 日志工具类，统一日志打印对象实例
 * 动态修改日志级别，测试/生产环境临时 打开，查看debug日志
 *
 * log util,use one log instance
 */
public class LogUtil {
    private static Logger LOG = LoggerFactory.getLogger(LogUtil.class);
    /**
     * 是否打开日志，初始化打开
     */
    public static boolean enable = true;
    /**
     * 打印日志级别，初始化为info
     */
    public static LogLevel level = LogLevel.INFO;

    /**
     * 开发环境务必开启，否则看不到异常
     * must call this function in development environment,or no error will show
     */
    public static void enableDev() {
        enable = true;
        level = LogLevel.DEBUG;
    }

    /**
     * 调用级别
     * call level
     */
    public static final int OUTER_CALLER_LEVEL = 4;

    /**
     * 日志级别常量
     */
    public enum LogLevel {
        DEBUG, INFO, WARN, ERROR
    }

    /**
     * 初始化
     * 读取配置文件，初始化日志级别
     */
    static {
        /**
         * 初始化,读取sysconfig的 键 loginit
         * 如果存在，enable = true
         * 不存在，enable = false
         */
        boolean got = false;
        String value = "";
        try {
            value = ConfigUtil.get("loginit", "config");
            got = true;
        } catch (Exception e) {
        }
        if (got) {
            if (value.equals(String.valueOf(LogLevel.DEBUG))) {
                enable = true;
                level = LogLevel.DEBUG;
            }
            if (value.equals(String.valueOf(LogLevel.INFO))) {
                enable = true;
                level = LogLevel.INFO;
            }
            if (value.equals(String.valueOf(LogLevel.WARN))) {
                enable = true;
                level = LogLevel.WARN;
            }
            if (value.equals(String.valueOf(LogLevel.ERROR))) {
                enable = true;
                level = LogLevel.ERROR;
            }
            //else value error
        }
    }

    /**
     * 查看 日志状态
     *
     * @return
     */
    public static String getJson() {
        StringBuilder sb = new StringBuilder();
        return sb.append("{\"on\":").append(enable).append(",").append("\"level\":").append(level).append("}").toString();
    }


    /**
     * level 3
     *
     * @param msg
     * @param param
     */
    public static void debug(String msg, Object... param) {
        if (isDebugEnable()) {
            debug(msg, OUTER_CALLER_LEVEL, null, param);
        }
    }

    public static void debug(String msg, Throwable e, Object... param) {
        if (isDebugEnable()) {
            debug(msg, OUTER_CALLER_LEVEL, e, param);
        }
    }

    /**
     * 判断日志级别并打印
     * level 2
     *
     * @param msg
     * @param e
     * @param param
     */
    public static void debug(String msg, int level, Throwable e, Object... param) {
        if (isDebugEnable()) {
            String msgStack = getStackAndMsg(msg, level);
            LOG.info(msgStack, param);//注： 测试环境logback使用info级别，设为debu，将不会打印
            if (e != null) {
                e.printStackTrace();
            }
        }
    }


    private static Object[] filter2Json(Object... params) {
        if (CollectionUtil.isEmpty(params)) {
            return params;
        }
        Object[] strParam = new Object[params.length];
        for (int i = 0; i < params.length; i++) {
            String objStr = null;
            try{
                objStr = "[J]" + JSONFastJsonUtil.objectToJson(params[i]);
            } catch (Exception e) {
                //发送exception，使用string
                objStr = "[S]" + params[i].toString();
            }
            strParam[i] = objStr;
        }
        return strParam;
    }

    /**
     * call level 3
     *
     * @param msg
     * @param param
     */
    public static void info(String msg, Object... param) {
        if (isInfoEnable()) {
            info(msg, OUTER_CALLER_LEVEL, null, param);
        }
    }

    public static void infoJson(String msg, Object... param) {
        if (isInfoEnable()) {
            Object[] jparam = filter2Json(param);
            info(msg, OUTER_CALLER_LEVEL, null, jparam);
        }
    }


    public static void infoJson(String msg, Throwable e, Object... param) {
        if (isInfoEnable()) {
            Object[] jparam = filter2Json(param);
            info(msg, OUTER_CALLER_LEVEL, e, jparam);
        }
    }

    public static void info(String msg, Throwable e, Object... param) {
        if (isInfoEnable()) {
            info(msg, OUTER_CALLER_LEVEL, e, param);
        }
    }

    /**
     * info日志打印，如果开启debug，则打印堆栈
     * level 2
     *
     * @param msg
     * @param e
     * @param param
     */
    public static void info(String msg, int level, Throwable e, Object... param) {
        if (isInfoEnable()) {
            String msgStack = getStackAndMsg(msg, level);
            LOG.info(msgStack, param);//注： 测试环境logback使用info级别，如使用debug()，将不会打印
            if (e != null && isDebugEnable()) {
                e.printStackTrace();//开发环境打印堆栈，错误更快暴露
            }
        }
    }


    /**
     * level 3
     */
    public static void warn(String msg, Object... param) {
        if (isWarnEnable()) {
            warn(msg, OUTER_CALLER_LEVEL, null, param);
        }
    }

    public static void warn(String msg, Throwable e, Object... param) {
        if (isWarnEnable()) {
            warn(msg, OUTER_CALLER_LEVEL, e, param);
        }
    }

    /**
     * warn日志打印，如果开启debug，则打印堆栈
     * level 2
     *
     * @param msg
     * @param e
     * @param param
     */
    public static void warn(String msg, int level, Throwable e, Object... param) {
        if (isWarnEnable()) {
            String msgStack = getStackAndMsg(msg, level);
            LOG.warn(msgStack, param);//注： 测试环境logback使用info级别，设为debu，将不会打印
            if (e != null && isDebugEnable()) {
                e.printStackTrace();//开发环境打印堆栈，错误更快暴露
            }
        }
    }

    /**
     * level 3
     */
    public static void error(String msg, Object... param) {
        if (isErrorEnable()) {
            error(msg, OUTER_CALLER_LEVEL, null, param);
        }
    }

    public static void errorJson(String msg, Object... param) {
        if (isErrorEnable()) {
            Object[] jparam = filter2Json(param);
            error(msg, OUTER_CALLER_LEVEL, null, jparam);
        }
    }

    /**
     * level 3
     */
    public static void error(String msg, Throwable e, Object... param) {
        if (isErrorEnable()) {
            error(msg, 4, e, param);
        }
    }

    public static void errorJson(String msg, Throwable e, Object... param) {
        if (isErrorEnable()) {
            Object[] jparam = filter2Json(param);
            error(msg, OUTER_CALLER_LEVEL, e, jparam);
        }
    }


    /**
     * 错误日志打印，如果开启debug，则打印堆栈
     * level 2
     *
     * @param msg
     * @param e     需要打印 异常堆栈的 异常
     * @param param
     */
    public static void error(String msg, int level, Throwable e, Object... param) {
        if (isErrorEnable()) {
            String msgStack = getStackAndMsg(msg, level);
            LOG.error(msgStack, param);//注： 测试环境logback使用info级别，设为debu，将不会打印
            if (e != null && isDebugEnable()) {
                e.printStackTrace();//开发环境打印堆栈，错误更快暴露
            }
        }
    }

    /**
     * level 1
     *
     * @param msg
     * @return
     */
    private static String getStackAndMsg(String msg, int level) {
        String msgStack = "";
        msgStack = getStack(level);//直接调用，本层为1层，本类调用方还有2层，类外调用方1层 = 4
        return msgStack + " " + msg;
    }

    /**
     * 获取第几层 caller
     * level 0
     *
     * @param preLevel
     * @return
     */
    private static String getStack(int preLevel) {
        if (preLevel < 0) {
            preLevel = 0;
        }
        Throwable throwable = new Throwable();
        StackTraceElement[] stes = throwable.getStackTrace();
        if (stes.length > preLevel) {
            StringBuilder sb = new StringBuilder();
            //get upper caller stack trace
            sb.append(stes[preLevel].toString());
            return sb.toString();
        }
        return "";
    }

    public static boolean isDebugEnable() {
        if (enable && level == LogLevel.DEBUG) {
            return true;
        }
        return false;
    }

    public static boolean isInfoEnable() {
        if (enable && (level == LogLevel.INFO || level == LogLevel.DEBUG)) {
            return true;
        }
        return false;
    }

    public static boolean isWarnEnable() {
        if (enable && (level == LogLevel.INFO || level == LogLevel.DEBUG || level == LogLevel.WARN)) {
            return true;
        }
        return false;
    }

    public static boolean isErrorEnable() {
        if (enable && (level == LogLevel.INFO || level == LogLevel.DEBUG || level == LogLevel.ERROR || level == LogLevel.WARN)) {
            return true;
        }
        return false;
    }

    public static LogLevel getLevel() {
        return level;
    }

    public static void debugEnable() {
        LogUtil.enable();
        setLevel(LogLevel.DEBUG);
    }

    public static void infoEnable() {
        LogUtil.enable();
        setLevel(LogLevel.INFO);
    }

    public static void errorEnable() {
        LogUtil.enable();
        setLevel(LogLevel.ERROR);
    }

    public static void setLevel(LogLevel level) {
        LogUtil.level = level;
    }

    public static void setEnable(boolean on) {
        LogUtil.enable = on;
    }

    public static void enable() {
        enable = true;
    }

    public static void disable() {
        enable = false;
    }

    public static String format(String locate, String exception) {
        return format(locate, exception, null);
    }

    public static String format(String locate, String exception, String data) {
        if (data == null) {
            return String.format("%s,%s", locate, exception);
        }
        return String.format("%s,%s,data %s", locate, exception, data);
    }


}
