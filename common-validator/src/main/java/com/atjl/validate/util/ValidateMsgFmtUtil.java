package com.atjl.validate.util;


import com.atjl.util.collection.CollectionUtil;

import java.util.Set;

public class ValidateMsgFmtUtil {
    private ValidateMsgFmtUtil(){
        throw new UnsupportedOperationException();
    }

    public static String fmtAnyOfMsg(String msgTemplate,Set<String> s){
        String[] msgs = CollectionUtil.set2array(s);
        return String.format(msgTemplate,msgs);
    }
}
