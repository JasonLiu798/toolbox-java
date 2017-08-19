package com.atjl.log.api;

import com.atjl.log.domain.LogConstantInner;
import com.atjl.log.util.LogFmtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志工具类，统一日志打印
 */
public class LogUtil {
    private LogUtil() {
        throw new UnsupportedOperationException();
    }

    private static final Logger logger = LoggerFactory.getLogger(LogUtil.class);

    public static void setLevel(LogLevel lv) {
        LogContext.setLevel(lv);
    }

    public static LogLevel getLevel() {
        return LogContext.getLevel();
    }

    public static void enable() {
        LogContext.setEnable(true);
    }

    public static void disable() {
        LogContext.setEnable(false);
    }


    public static boolean isDebugEnabled() {
        return LogContext.enable && LogContext.level == LogLevel.DEBUG;
    }

    public static boolean isInfoEnabled() {
        return LogContext.enable && (LogContext.level == LogLevel.INFO || LogContext.level == LogLevel.DEBUG);
    }

    public static boolean isWarnEnabled() {
        return LogContext.enable && (LogContext.level == LogLevel.INFO || LogContext.level == LogLevel.DEBUG || LogContext.level == LogLevel.WARN);
    }

    public static boolean isErrorEnabled() {
        return LogContext.enable && (LogContext.level == LogLevel.INFO || LogContext.level == LogLevel.DEBUG || LogContext.level == LogLevel.WARN || LogContext.level == LogLevel.ERROR);
    }

    /**
     * debug日志，只写文件
     *
     * @param msg
     * @param param
     */
    public static void debug(String msg, Object... param) {
        if (isDebugEnabled()) {
            String stack = LogFmtUtil.getStackAndMsg(LogLevel.DEBUG, msg, LogConstantInner.STACK_PRE_LV);
            logger.info(stack, param);
        }
    }

    public static void info(String msg, Object... param) {
        if (isInfoEnabled()) {
            String stack = LogFmtUtil.getStackAndMsg(LogLevel.INFO, msg, LogConstantInner.STACK_PRE_LV);
            logger.info(stack, param);
        }
    }

    public static void warn(String msg, Object... param) {
        if (isWarnEnabled()) {
            String stack = LogFmtUtil.getStackAndMsg(LogLevel.WARN, msg, LogConstantInner.STACK_PRE_LV);
            logger.warn(stack, param);
        }
    }

    public static void error(String msg, Object... param) {
        if (isErrorEnabled()) {
            String stack = LogFmtUtil.getStackAndMsg(LogLevel.ERROR, msg, LogConstantInner.STACK_PRE_LV);
            logger.error(stack, param);
        }
    }


}
