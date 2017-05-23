package com.jason798.log;

import com.jason798.character.StringCheckUtil;
import com.jason798.collection.CollectionUtil;
import com.jason798.common.DevContext;
import com.jason798.json.JSONFastJsonUtil;
import com.jason798.queue.IQueue;
import com.jason798.queue.QueueManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * log client,send data to queue for async write or sync write to file
 */
public class LogClient {
    private static Logger LOG = LoggerFactory.getLogger(LogClient.class);
    
    public static final String LOG_QUEUE = "logq";

    private static IQueue logQueue;
    
    private static boolean enable;
    private static LogLevel level;

    static {
        logQueue = QueueManager.getQueue(LOG_QUEUE);
        if (logQueue == null) {
            throw new RuntimeException("创建队列异常");
        }
    }

    /**
     * 判断 AOP 类 是否在白名单
     *
     * @param clz
     * @return
     */
    public static boolean isClzInWhiteList(String clz) {
        for (String s : LogConstant.WHITE) {
            if (StringCheckUtil.equal(clz, s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断 AOP 类 是否在黑名单
     *
     * @param clz
     * @return
     */
    public static boolean isClzInBlackList(String clz) {
        for (String s : LogConstant.BLACK) {
            if (StringCheckUtil.equal(clz, s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * async write debug
     * @param module
     * @param content
     */
    public static void writeDebug(Class module, String content) {
        String moduleName = LogConstant.MODULE_UNKNOWN;
        if (module != null) {
            moduleName = module.getSimpleName();
        }
        writeDebug(moduleName, content);
    }

    /**
     * async write debug
     * @param opModule
     * @param content
     */
    public static void writeDebug(String opModule, String content) {
        writeLogRaw(LogConstant.LV_DEBUG, opModule, null, LogConstant.OTHER,
                null, null, content, null,
                LogConstant.DFT_USER, null, null);
    }


    /**
     * 普通日志，参数所在类名
     *
     * @param opModule
     * @param content
     */
    public static void writeInfo(Class opModule, String content) {
        String moduleName = LogConstant.MODULE_UNKNOWN;
        if (opModule != null) {
            moduleName = opModule.getSimpleName();
        }
        writeInfo(moduleName, content, null);
    }

    /**
     * 普通日志
     *
     * @param opModule 模块
     * @param content  内容
     */
    public static void writeInfo(String opModule, String content) {
        writeInfo(opModule, content, null);
    }

    /**
     * info日志写库， 包括消耗时间
     *
     * @param opModule
     * @param content
     * @param costTm   消耗时间
     */
    public static void writeInfo(String opModule, String content, Long costTm) {
        writeInfoRaw(opModule, null, LogConstant.OTHER,
                null, null, content, null,
                LogConstant.DFT_USER, null, costTm);
    }

    /**
     * 调用外部接口 记录日志用
     *
     * @param opModule 模块
     * @param content  改变的内容
     * @param param    入参
     * @param res      出参
     * @param costTm   耗费时间
     */
    public static void writeInfo(String opModule, String content, String param, String res, Long costTm) {
        writeInfoRaw(opModule, null, LogConstant.OTHER,
                param, res, content, null,
                LogConstant.DFT_USER, null, costTm);
    }

    public static void writeWarn(String opModule,
                                 String content) {
        writeLogRaw(LogConstant.LV_WARN, opModule, null, LogConstant.OTHER,
                null, null, content, null,
                LogConstant.DFT_USER, null, null);
    }


    /**
     * async write error log to db
     *
     * @param opModule
     * @param e
     */
    public static void writeError(String opModule, String ref, Throwable e) {
        if(DevContext.isDev()){
            e.printStackTrace();
        }
        String content = LogCommonUtil.getStackTrace(e);
        writeLogRaw(LogConstant.LV_ERROR, opModule, null, LogConstant.OTHER,
                null, null, content, ref,
                LogConstant.DFT_USER, null, null);
    }


    public static void writeError(Class clz, String ref, Throwable e) {
        String name = LogConstant.MODULE_UNKNOWN;
        if(clz==null){
            name = clz.getClass().getSimpleName();
        }
        writeError(name, ref, e);
    }

    /**
     * sync write error log to db
     * 异常日志用，记录Log模块 初始化过程异常用
     * spring初始化完毕可用
     * @param opModule
     * @param ref
     * @param e
     */
    public static void writeErrorSync(String opModule, String ref, Throwable e) {
        String content = LogCommonUtil.getStackTrace(e);
        writeLogRawSync(LogConstant.LV_ERROR, opModule, null, LogConstant.OTHER,
                null, null, content, ref,
                LogConstant.DFT_USER, null, null);
    }

    /**
     * async write error log to db
     * @param opModule
     * @param ref
     */
    public static void writeError(String opModule, String ref) {
        writeError(opModule, null, ref);
    }

    /**
     * async write error log to db
     * @param opModule
     * @param content
     * @param ref
     */
    public static void writeError(String opModule, String content, String ref) {
        writeLogRaw(LogConstant.LV_ERROR, opModule, null, LogConstant.OTHER,
                null, null, content, ref,
                LogConstant.DFT_USER, null, null);
    }


    /**
     * async write info log to db
     * @param opModule
     * @param opChildModule
     * @param opType
     * @param param
     * @param res
     * @param content
     * @param ref
     * @param empNum
     * @param reqIp
     * @param cost
     */
    public static void writeInfoRaw(String opModule, String opChildModule, String opType,
                                    String param, String res, String content, String ref,
                                    String empNum, Long reqIp, Long cost) {
        writeLogRaw(LogConstant.LV_INFO, opModule, opChildModule, opType,
                param, res, content, ref,
                empNum, reqIp, cost);
    }

    /**
     * async write debug log to db
     * @param opModule
     * @param opChildModule
     * @param opType
     * @param param
     * @param res
     * @param content
     * @param ref
     * @param empNum
     * @param reqIp
     * @param cost
     */
    public static void writeDebugRaw(String opModule, String opChildModule, String opType,
                                     String param, String res, String content, String ref,
                                     String empNum, Long reqIp, Long cost) {
        writeLogRaw(LogConstant.LV_DEBUG, opModule, opChildModule,
                opType, param, res, content, ref,
                empNum, reqIp, cost);
    }

    public static void writeErrorRaw(String opModule, String opChildModule, String opType,
                                     String param, String res, String content, String ref,
                                     String empNum, Long reqIp, Long cost) {
        writeLogRaw(LogConstant.LV_ERROR, opModule, opChildModule, opType,
                param, res, content, ref,
                empNum, reqIp, cost);
    }

    /**
     * 异步写日志到队列
     *
     * @param opModule
     * @param opType
     * @param param
     * @param reqIp
     */
    public static void writeLogRaw(String lv, String opModule,
                                   String opChildModule, String opType,

                                   String param, String res, String content, String ref,
                                   String empNum, Long reqIp, Long cost) {
    }
    /*
        if (lv == null) {
            lv = LogConstant.LV_DEBUG;
        }
        GenLogWithBLOBs log = new GenLogWithBLOBs();
        log.setLogLv(lv);
        log.setOpModule(opModule);
        log.setOpChildModuel(opChildModule);
        log.setOpType(opType);
        log.setOpRef(ref);

        param = StringUtil.strCutFilter(param, LogConstant.PARAM_MAX_SIZE);
        res = StringUtil.strCutFilter(res, LogConstant.RES_MAX_SIZE);
        ref = StringUtil.strCutFilter(ref, LogConstant.REF_MAX_SIZE);

        log.setOpParam(param);
        log.setOpRes(res);
        log.setOpContent(content);

        log.setEmpNum(empNum);
        log.setOpIp(reqIp);
        log.setCost(cost);

        String logStr = null;
        try {
            logStr = JSONFastJsonUtil.objectToJson(log);
        } catch (Exception e) {
            logStr = "opTm=" + DateUtil.getNowTS() +
                    ", logLv='" + lv + '\'' +
                    ", opModule='" + opModule + '\'' +
                    ", opChildModuel='" + opChildModule + '\'' +
                    ", empNum='" + empNum + '\'' +
                    ", opType='" + opType + '\'' +
                    ", opParam='" + param + '\'' +
                    ", opRes='" + res + '\'' +
                    ", opRef='" + ref + '\'' +
                    ", cost=" + cost +
                    ", opIp=" + reqIp +
                    "opRes='" + res + '\'' +
                    ", opContent='" + content + '\'';
        }

        try {
            switch (lv) {
                case LogConstant.LV_DEBUG:
                    debug(logStr);
                    break;
                case LogConstant.LV_INFO:
                    info(logStr);
                    break;
                case LogConstant.LV_WARN:
                    warn(logStr);
                    break;
                case LogConstant.LV_ERROR:
                    error(logStr);
                    break;
                default:
                    debug(logStr);
                    break;
            }
            logQueue.sendMessage(log);
        } catch (InterruptedException e) {
            LOG.error("send log error,raw log {},exception {}", logStr, e.getMsg());
        }
    }*/


    public static void writeLogRawSync(String lv, String opModule, String opChildModule, String opType,
                                       String param, String res, String content, String ref,
                                       String empNum, Long reqIp, Long cost) {

    }

    /**
     * 同步写日志，spring初始化成功后可用
     *
     * @param lv
     * @param opModule
     * @param opChildModule
     * @param opType
     * @param param
     * @param res
     * @param content
     * @param ref
     * @param empNum
     * @param reqIp
     * @param cost
     *
    public static void writeLogRawSync(String lv, String opModule, String opChildModule, String opType,
                                       String param, String res, String content, String ref,
                                       String empNum, Long reqIp, Long cost) {
        GenLogWithBLOBs log = new GenLogWithBLOBs();
        try {
            LogService logService = ApplicationContextHepler.getBean("logService", LogService.class);
//StringUtil.
            log.setLogLv(lv);
            log.setOpModule(opModule);
            log.setOpChildModuel(opChildModule);
            log.setOpType(opType);

            param = StringUtil.strCutFilter(param, LogConstant.PARAM_MAX_SIZE);
            log.setOpParam(param);
            res = StringUtil.strCutFilter(res, LogConstant.RES_MAX_SIZE);
            log.setOpRes(res);
            log.setOpContent(content);
            ref = StringUtil.strCutFilter(ref, LogConstant.REF_MAX_SIZE);
            log.setOpRef(ref);

            log.setEmpNum(empNum);
            log.setOpIp(reqIp);
            log.setCost(cost);

            logService.insert(log);
        } catch (Exception e) {
            String logStr = null;
            try {
                logStr = JSONFastJsonUtil.objectToJson(log);
            } catch (Exception e1) {
                logStr = "opTm=" + DateUtil.getNowTS() +
                        ", logLv='" + lv + '\'' +
                        ", opModule='" + opModule + '\'' +
                        ", opChildModuel='" + opChildModule + '\'' +
                        ", empNum='" + empNum + '\'' +
                        ", opType='" + opType + '\'' +
                        ", opParam='" + param + '\'' +
                        ", opRes='" + res + '\'' +
                        ", opRef='" + ref + '\'' +
                        ", cost=" + cost +
                        ", opIp=" + reqIp +
                        "opRes='" + res + '\'' +
                        ", opContent='" + content + '\'';
            }
            LOG.error("write sync log exception,{},content {}", e.getMsg(),logStr);
        }
    }
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
            //TODO: 需要读取配置
            value = "";//ConfigUtil.get("loginit", "sysconfig");
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
            debug(msg, LogConstant.OUTER_CALLER_LEVEL, null, param);
        }
    }

    public static void debug(String msg, Throwable e, Object... param) {
        if (isDebugEnable()) {
            debug(msg, LogConstant.OUTER_CALLER_LEVEL, e, param);
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
            String msgStack = LogCommonUtil.getStackAndMsg(msg, level);
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
            objStr = "[J]" + JSONFastJsonUtil.objectToJson(params[i]);
            //发送exception，使用string
            //objStr = "[S]" + params[i].toString();
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
            info(msg, LogConstant.OUTER_CALLER_LEVEL, null, param);
        }
    }

    public static void infoJson(String msg, Object... param) {
        if (isInfoEnable()) {
            Object[] jparam = filter2Json(param);
            info(msg, LogConstant.OUTER_CALLER_LEVEL, null, jparam);
        }
    }


    public static void infoJson(String msg, Throwable e, Object... param) {
        if (isInfoEnable()) {
            Object[] jparam = filter2Json(param);
            info(msg, LogConstant.OUTER_CALLER_LEVEL, e, jparam);
        }
    }

    public static void info(String msg, Throwable e, Object... param) {
        if (isInfoEnable()) {
            info(msg, LogConstant.OUTER_CALLER_LEVEL, e, param);
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
            String msgStack = LogCommonUtil.getStackAndMsg(msg, level);
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
            warn(msg, LogConstant.OUTER_CALLER_LEVEL, null, param);
        }
    }

    public static void warn(String msg, Throwable e, Object... param) {
        if (isWarnEnable()) {
            warn(msg, LogConstant.OUTER_CALLER_LEVEL, e, param);
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
            String msgStack = LogCommonUtil.getStackAndMsg(msg, level);
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
            error(msg, LogConstant.OUTER_CALLER_LEVEL, null, param);
        }
    }

    public static void errorJson(String msg, Object... param) {
        if (isErrorEnable()) {
            Object[] jparam = filter2Json(param);
            error(msg, LogConstant.OUTER_CALLER_LEVEL, null, jparam);
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
            error(msg, LogConstant.OUTER_CALLER_LEVEL, e, jparam);
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
            String msgStack = LogCommonUtil.getStackAndMsg(msg, level);
            LOG.error(msgStack, param);//注： 测试环境logback使用info级别，设为debu，将不会打印
            if (e != null && isDebugEnable()) {
                e.printStackTrace();//开发环境打印堆栈，错误更快暴露
            }
        }
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
	
}
