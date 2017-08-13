package com.atjl.log.util;

import com.atjl.log.api.LogContext;
import com.atjl.log.api.LogLevel;
import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.character.StringUtil;

/**
 *
 */
public class LogFmtUtil {
    private LogFmtUtil() {
        super();
    }

//    public static String format(String locate, String exception) {
//        return format(locate, exception, null);
//    }
//
//    public static String format(String locate, String exception, String data) {
//        if (data == null) {
//            return String.format("%s,%s", locate, exception);
//        }
//        return String.format("%s,%s,data %s", locate, exception, data);
//    }

    /**
     * 类名简化
     * @param raw
     * @param tgtLen
     * @return
     */
    public static String simplifyStack(String raw, int tgtLen) {
        if (StringCheckUtil.isEmpty(raw) || raw.length() <= tgtLen) {
            return raw;
        }
        char[] rawArr = raw.toCharArray();
        int dotCnt = 0;
        int clzAndCallIdx = 0;

        for (int i = rawArr.length - 1; i >= 0; i--) {
            if (rawArr[i] == '.') {
                dotCnt++;
                if (dotCnt == 3) {
                    clzAndCallIdx = i;
                }
            }
        }
        int needRemoveCnt = raw.length() - tgtLen;
        //System.out.println("needRemoveCnt " + needRemoveCnt);
        String clzName = raw.substring(0,clzAndCallIdx);
        int pkgTgtLen = Math.max(0,clzName.length()-needRemoveCnt);
        String simplifiedClzName = StringUtil.simplifyFullClassName(clzName,pkgTgtLen);
        return simplifiedClzName+"."+raw.substring(clzAndCallIdx+1);
    }


    /**
     * ####################### 内部方法 #######################
     */
    /**
     * level 1
     *
     * @param msg
     * @return
     */
    public static String getStackAndMsg(LogLevel lv, String msg, int level) {
        String msgStack = getStack(level);//直接调用，本层为1层，本类调用方还有2层，类外调用方1层 = 4
        if (LogContext.showLv) {
            return lv + " " + msgStack + " " + msg;
        } else {
            return msgStack + " " + msg;
        }
    }

    /**
     * 获取第几层 caller
     * level 0
     *
     * @param preLevel
     * @return
     */
    public static String getStack(int preLevel) {
        if (preLevel < 0) {
            preLevel = 0;
        }
        Throwable throwable = new Throwable();
        StackTraceElement[] stes = throwable.getStackTrace();
        if (stes.length > preLevel) {
            StringBuilder sb = new StringBuilder();
            //get upper caller stack trace
            String msg = stes[preLevel].toString();
            if (msg.length() > LogContext.simplifyLen) {//如开启，则简化类名
                msg = LogFmtUtil.simplifyStack(msg, LogContext.simplifyLen);
            }
            sb.append(msg);
            return sb.toString();
        }
        return "";
    }
}
