package com.atjl.util.common;


import com.atjl.util.character.StringCheckUtil;

public class CmdOptionUtil {


    public static boolean hasOption(String opts, String acceptOpt) {
        if (StringCheckUtil.isEmpty(opts) || StringCheckUtil.isEmpty(acceptOpt)) {
            return false;
        }
        return opts.indexOf(acceptOpt) >= 0;
    }



}
