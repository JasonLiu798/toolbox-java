package com.atjl.util.thread;


import com.atjl.util.character.StringCheckUtil;

public class ThreadInnerUtil {
    private ThreadInnerUtil() {
        throw new UnsupportedOperationException();
    }

    public static final String PARAM_SEP = ",";

    public static boolean validParam(String param) {
        if (StringCheckUtil.isEmpty(param)) {
            return false;
        }
        String[] arr = param.split(PARAM_SEP);
        if (arr.length < 3) {
            return false;
        }
        return true;
    }

    public static String getName(String param) {
        if (StringCheckUtil.isEmpty(param)) {
            return "";
        }
        int idx = param.indexOf(PARAM_SEP);
        if (idx > 0 && idx < param.length()) {
            return param.substring(0, idx);
        }
        return "";
    }

    public static Class getType(String param) {
        String[] params = param.split(ThreadConstant.PARAM_SEP);
        switch (params[1]) {
            case ThreadConstant.BUFPOOL_TYPE_USERDEFINE:
                return ThreadManualManager.class;
            case ThreadConstant.BUFPOOL_TYPE_FIX:
                //PoolName,Fix,123
                return ThreadFixManager.class;
            default:
        }
        return null;
    }
}
