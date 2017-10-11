package com.atjl.util.thread;


import com.atjl.util.character.StringCheckUtil;

public class ThreadInnerUtil {
    private ThreadInnerUtil() {
        throw new UnsupportedOperationException();
    }

    public static boolean validParam(String param) {
        if (StringCheckUtil.isEmpty(param)) {
            return false;
        }
        String[] arr = param.split(",");
        if (arr.length < 3) {
            return false;
        }
        return true;
    }

    public static String getName(String param) {
        if (StringCheckUtil.isEmpty(param)) {
            return "";
        }
        int idx = param.indexOf(",");
        if (idx > 0 && idx < param.length()) {
            return param.substring(0, idx);
        }
        return "";
    }
}
