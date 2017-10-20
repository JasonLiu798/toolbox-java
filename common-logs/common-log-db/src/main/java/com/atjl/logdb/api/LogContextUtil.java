package com.atjl.logdb.api;

import com.atjl.log.api.LogContext;
import com.atjl.log.api.LogLevel;
import com.atjl.log.api.LogUtil;
import com.atjl.logdb.api.domain.LogDbContext;

/**
 *
 */
public class LogContextUtil {

    public static void envDev() {
        LogUtil.envDev();
        LogDbUtil.envDev();
    }

    public static void envSit() {
        LogContext.setEnable(true);
        LogContext.setLevel(LogLevel.INFO);

        LogDbContext.setEnable(true);
        LogDbContext.setLevel(LogLevel.INFO);
    }

    public static void envSitDebug() {
        LogContext.setEnable(true);
        LogContext.setLevel(LogLevel.DEBUG);

        LogDbContext.setEnable(true);
        LogDbContext.setLevel(LogLevel.DEBUG);
    }


    public static void envPrd() {
        LogContext.setEnable(true);
        LogContext.setLevel(LogLevel.ERROR);

        LogDbContext.setEnable(true);
        LogDbContext.setLevel(LogLevel.INFO);
    }

}
