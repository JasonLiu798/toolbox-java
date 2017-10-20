package com.atjl.logdb.api;

import com.atjl.log.api.LogLevel;
import com.atjl.log.api.LogUtil;
import com.atjl.log.util.LogFmtUtil;
import com.atjl.logdb.api.domain.LogDbConstant;
import com.atjl.logdb.api.domain.LogOpType;
import com.atjl.logdb.api.domain.LogDbContext;
import com.atjl.logdb.api.domain.OpLog;
import com.atjl.util.character.StringUtil;
import com.atjl.util.json.JSONFastJsonUtil;
import com.atjl.utilex.ApplicationContextHepler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.atjl.log.api.LogLevel.DEBUG;

/**
 * 写日志到db工具类
 */
public class LogDbUtil {
    private static final Logger logger = LoggerFactory.getLogger(LogDbUtil.class);

    static {
//        envDev();
//        envPrd();
    }

    /**
     * 开发环境必开，否则看不到异常
     */
    public static void envDev() {
        LogDbContext.setEnable(true);
        LogDbContext.setLevel(DEBUG);
    }

    public static void envPrd() {
        LogDbContext.setEnable(true);
        LogDbContext.setLevel(LogLevel.INFO);
    }


    public static boolean isDebugEnabled() {
        return LogDbContext.enable && LogDbContext.level == DEBUG;
    }

    public static boolean isInfoEnabled() {
        return LogDbContext.enable && (LogDbContext.level == LogLevel.INFO || LogDbContext.level == DEBUG);
    }

    public static boolean isWarnEnabled() {
        return LogDbContext.enable && (LogDbContext.level == LogLevel.INFO || LogDbContext.level == DEBUG || LogDbContext.level == LogLevel.WARN);
    }

    public static boolean isErrorEnabled() {
        return LogDbContext.enable && (LogDbContext.level == LogLevel.INFO || LogDbContext.level == DEBUG || LogDbContext.level == LogLevel.WARN || LogDbContext.level == LogLevel.ERROR);
    }

    /**
     * async write debug
     */
    public static void debug(Class opModule, String content) {
        debug(opModule != null ? opModule.getSimpleName() : LogDbConstant.MODULE_UNKNOWN, null, LogOpType.OTHER.toString(), null, null, content, null, LogDbConstant.DFT_USER, null, null, LogDbConstant.NO_INPUT_OMIT_LEVEL);
    }

    public static void debug(String opModule, String content) {
        debug(opModule, null, LogOpType.OTHER.toString(), null, null, content, null, LogDbConstant.DFT_USER, null, null, LogDbConstant.NO_INPUT_OMIT_LEVEL);
    }

    public static void debug(String opModule, String opChildModule, String opType, String param, String res, String content, String ref, String empNum, Long reqIp, Long cost, int omitLevel) {
        writeLogAsyncInner(LogLevel.DEBUG, opModule, opChildModule, opType, param, res, content, ref, empNum, reqIp, cost, LogDbConstant.INPUT_OMIT_LEVEL + omitLevel);
    }

    /**
     * async write info
     */
    public static void info(Class opModule, String content) {
        info(opModule != null ? opModule.getSimpleName() : LogDbConstant.MODULE_UNKNOWN, null, LogOpType.OTHER.toString(), null, null, content, null, LogDbConstant.DFT_USER, null, null, LogDbConstant.NO_INPUT_OMIT_LEVEL);
    }

    public static void info(String opModule, String content) {
        info(opModule, null, LogOpType.OTHER.toString(), null, null, content, null, LogDbConstant.DFT_USER, null, null, LogDbConstant.NO_INPUT_OMIT_LEVEL);
    }

    public static void info(String opModule, String content, Long costTm) {
        info(opModule, null, LogOpType.OTHER.toString(), null, null, content, null, LogDbConstant.DFT_USER, null, costTm, LogDbConstant.NO_INPUT_OMIT_LEVEL);
    }

    public static void info(String opModule, String content, String param, String res, Long costTm) {
        info(opModule, null, LogOpType.OTHER.toString(), param, res, content, null, LogDbConstant.DFT_USER, null, costTm, LogDbConstant.NO_INPUT_OMIT_LEVEL);
    }

    public static void info(String opModule, String opChildModule, String opType, String param, String res, String content, String ref, String empNum, Long reqIp, Long cost, int omitLevel) {
        writeLogAsyncInner(LogLevel.INFO, opModule, opChildModule, opType, param, res, content, ref, empNum, reqIp, cost, LogDbConstant.INPUT_OMIT_LEVEL + omitLevel);
    }

    /**
     * async write warn log to db
     */
    public static void warn(String opModule, String content) {
        writeLogAsyncInner(LogLevel.WARN, opModule, null, LogOpType.OTHER.toString(), null, null, content, null, LogDbConstant.DFT_USER, null, null, LogDbConstant.NO_INPUT_OMIT_LEVEL);
    }

    /**
     * async write error log to db
     */
    public static void error(Class clz, String ref, Throwable e) {
        error(clz == null ? LogDbConstant.MODULE_UNKNOWN : clz.getClass().getSimpleName(), null, LogOpType.OTHER.toString(), null, null, LogFmtUtil.getStackTrace(e), ref, LogDbConstant.DFT_USER, null, null, LogDbConstant.NO_INPUT_OMIT_LEVEL);
    }

    public static void error(String opModule, String ref) {
        error(opModule, null, LogOpType.OTHER.toString(), null, null, null, ref, LogDbConstant.DFT_USER, null, null, LogDbConstant.NO_INPUT_OMIT_LEVEL);
    }

    public static void error(String opModule, String ref, Throwable e) {
        error(opModule, null, LogOpType.OTHER.toString(), null, null, LogFmtUtil.getStackTrace(e), ref, LogDbConstant.DFT_USER, null, null, LogDbConstant.NO_INPUT_OMIT_LEVEL);
    }

    public static void error(String opModule, String content, String ref) {
        error(opModule, null, LogOpType.OTHER.toString(), null, null, content, ref, LogDbConstant.DFT_USER, null, null, LogDbConstant.NO_INPUT_OMIT_LEVEL);
    }

    public static void error(String opModule, String opChildModule, String opType, String param, String res, String content, String ref, String empNum, Long reqIp, Long cost, int omitLevel) {
        writeLogAsyncInner(LogLevel.ERROR, opModule, opChildModule, opType, param, res, content, ref, empNum, reqIp, cost, LogDbConstant.INPUT_OMIT_LEVEL + omitLevel);
    }

    /**
     * 异步写日志到队列
     */
    public static void writeLogAsyncInner(
            LogLevel lv, String opModule, String opChildModule,
            String opType, String param, String res,
            String content, String ref, String empNum,
            Long reqIp, Long cost, int omitLevel) {
        /**
         *
         */
        if (!LogDbContext.isWrite(lv)) {
            return;
        }

        OpLog log = new OpLog();
        log.setLv(lv.toString().substring(0, 1));
        log.setOpModule(opModule);
        log.setOpChildModule(opChildModule);
        log.setOpType(opType);


        param = StringUtil.filter2len(param, LogDbConstant.PARAM_MAX_SIZE);
        log.setOpParam(param);

        res = StringUtil.filter2len(res, LogDbConstant.RES_MAX_SIZE);
        log.setOpRes(res);

        ref = StringUtil.filter2len(ref, LogDbConstant.REF_MAX_SIZE);
        log.setOpRef(ref);

        log.setOpContent(content);

        log.setEmpNum(empNum);
        log.setOpIp(reqIp);
        log.setCost(cost);

        String logStr;
        try {
            logStr = JSONFastJsonUtil.objectToJson(log);
        } catch (Exception e) {
            logStr = log.toString();
            if (logger.isDebugEnabled()) {
                logger.debug("toJson {}", e);
            }
        }
        /**
         * 先写本地日志
         */
        try {
            switch (lv) {
                case INFO:
                    LogUtil.hackWrite(LogLevel.INFO, logStr, omitLevel);
                    break;
                case WARN:
                    LogUtil.hackWrite(LogLevel.WARN, logStr, omitLevel);
                    break;
                case ERROR:
                    LogUtil.hackWrite(LogLevel.ERROR, logStr, omitLevel);
                    break;
                default:
                    LogUtil.hackWrite(LogLevel.DEBUG, logStr, omitLevel);
                    break;
            }
            LogDbContext.getLogQueue().sendMessage(log);
        } catch (InterruptedException e) {
            logger.error("send log error,raw log {},exception {}", logStr, e.getMessage());
        }
    }


    /**
     * sync write error log to db
     * 异常日志用，记录Log模块 初始化过程异常用
     * spring初始化完毕可用
     */
    public static void errorSync(String opModule, String ref, Throwable e) {
        writeLogRawSyncInner(LogLevel.ERROR, opModule, null, LogOpType.OTHER.toString(), null, null, LogFmtUtil.getStackTrace(e), ref, LogDbConstant.DFT_USER, null, null);
    }

    public static void infoSync(String opModule, String ref) {
        writeLogRawSyncInner(LogLevel.INFO, opModule, null, LogOpType.OTHER.toString(), null, null, "", ref, LogDbConstant.DFT_USER, null, null);
    }

    /**
     * 同步写日志，spring初始化成功后可用
     */
    public static void writeLogRawSyncInner(LogLevel lv, String opModule, String opChildModule, String opType,
                                            String param, String res, String content, String ref,
                                            String empNum, Long reqIp, Long cost) {
        OpLog log = new OpLog();
        try {
            LogService logService = ApplicationContextHepler.getBean("logService", LogService.class);
            log.setLv(lv.toString().substring(0, 1));
            log.setOpModule(opModule);
            log.setOpChildModule(opChildModule);
            log.setOpType(opType);

            param = StringUtil.filter2len(param, LogDbConstant.PARAM_MAX_SIZE);
            log.setOpParam(param);
            res = StringUtil.filter2len(res, LogDbConstant.RES_MAX_SIZE);
            log.setOpRes(res);
            log.setOpContent(content);
            ref = StringUtil.filter2len(ref, LogDbConstant.REF_MAX_SIZE);
            log.setOpRef(ref);

            log.setEmpNum(empNum);
            log.setOpIp(reqIp);
            log.setCost(cost);

            logService.insert(log);
        } catch (Exception e) {
            String logStr;
            try {
                logStr = JSONFastJsonUtil.objectToJson(log);
            } catch (Exception e1) {
                logStr = log.toString();
                if (logger.isDebugEnabled()) {
                    logger.debug("toJson {}", e1);
                }
            }
            logger.error("write sync log exception,{},content {}", e, logStr);
        }
    }

}
