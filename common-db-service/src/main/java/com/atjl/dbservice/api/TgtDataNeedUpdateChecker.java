package com.atjl.dbservice.api;


import java.util.Map;

public interface TgtDataNeedUpdateChecker {

    /**
     *
     */
    boolean needUpdate(Map raw,Map tgt);

}
