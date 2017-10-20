package com.atjl.log.util;


import com.atjl.log.api.LogUtil;
import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.json.JSONFastJsonUtil;

import java.io.PrintWriter;
import java.io.StringWriter;

public class LogCommonUtil {
    private LogCommonUtil() {
        super();
    }



    public static Object[] filter2Json(Object... params) {
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
     * 异常转string
     *
     * @param t
     * @return
     */
    public static String exception2str(Throwable t) {
        if (t == null) {
            return "exception null";
        }
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        String res = "get stack trace fail,throwable " + t;
        try {
            t.printStackTrace(pw);
            res = sw.toString();
            return res;
        } catch (Exception e) {
            res = res + "," + e.getMessage();
            if (LogUtil.isDebugEnabled()) {
                LogUtil.debug("exception2str {}", e);
            }
        } finally {
            pw.close();
        }
        return res;
    }


}
