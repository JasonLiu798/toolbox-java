package com.atjl.logdb.api.domain;

import com.atjl.log.api.LogException;
import com.atjl.log.api.LogLevel;
import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.queue.IQueue;
import com.atjl.util.queue.QueueManager;

import java.util.concurrent.atomic.AtomicLong;

/**
 * db日志上下文
 */
public class LogDbContext {
    private LogDbContext() {
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

    /**
     *
     */
    public final static AtomicLong WRITE_ERROR = new AtomicLong();


    /**
     * 异步日志队列
     */
    private static IQueue logQueue;

    // 日志黑名单 AOP日志用
    private static String[] BLACK = new String[]{};
    // 日志白名单 AOP日志用，同时存在，优先级高于黑名单
    private static String[] WHITE = new String[]{};

    /**
     * 初始化
     */
    static {
        logQueue = QueueManager.getQueue(LogDbConstant.LOG_QUEUE);
        if (logQueue == null) {
            throw new LogException("创建队列异常");
        }
    }


    public static boolean isWrite(LogLevel lv) {
        if (!isEnable()) {
            return false;
        }
        boolean enable = false;
        if (level == LogLevel.DEBUG) {
            enable = true;
        } else if (level == LogLevel.INFO) {
            if (lv == LogLevel.ERROR || lv == LogLevel.WARN || lv == LogLevel.INFO) {
                enable = true;
            }
        } else if (level == LogLevel.WARN) {
            if (lv == LogLevel.ERROR || lv == LogLevel.WARN) {
                enable = true;
            }
        } else {
            if (lv == LogLevel.ERROR) {
                enable = true;
            }
        }
        return enable;
    }

    public static boolean isEnable() {
        return enable;
    }

    public static void setEnable(boolean enable) {
        LogDbContext.enable = enable;
    }

    public static LogLevel getLevel() {
        return level;
    }

    public static void setLevel(LogLevel level) {
        LogDbContext.level = level;
    }

    /**
     * 设置黑名单
     */
    public static void setBlackList(String[] blackList) {
        if (!CollectionUtil.isEmpty(blackList)) {
            BLACK = blackList;
        }
    }


    /**
     * 是否在白名单
     */
    public static boolean isClzInWhiteList(String clz) {
        for (String s : WHITE) {
            if (StringCheckUtil.equal(clz, s)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isClzInBlackList(String clz) {
        for (String s : BLACK) {
            if (StringCheckUtil.equal(clz, s)) {
                return true;
            }
        }
        return false;
    }


    public static IQueue getLogQueue() {
        return logQueue;
    }

    /**
     * 查看 日志状态
     *
     * @return
     */
    public static String getStatusJson() {
        StringBuilder sb = new StringBuilder();
        return sb.append("{\"on\":").append(enable).append(",").append("\"level\":").append(level).append("}").toString();
    }

}
