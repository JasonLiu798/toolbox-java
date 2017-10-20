package com.atjl.logdb.api.domain;

import java.util.HashMap;
import java.util.Map;

public class LogDbConstant {


    private LogDbConstant() {
        throw new UnsupportedOperationException();
    }



    public static final String LOG_SERVICE = "logService";
    public static final int NO_INPUT_OMIT_LEVEL = 2;
    public static final int INPUT_OMIT_LEVEL = 1;

    public static final int RES_MAX_SIZE = 4000;
    public static final int PARAM_MAX_SIZE = 2000;
    public static final int REF_MAX_SIZE = 1000;

    public static final String MODULE_UNKNOWN = "unknown";
    public static final String LOG_QUEUE = "logq";
//    public static final String DICT_LOG_PK = "BisArguSetting.log";

//    public static final String DICT_LOG_BLACK_LIST_KEY = DICT_LOG_PK + ".blacklist";

    //public static final int MAX_SIZE = 2048;


    /**
     * 默认类型
     */
    public static final String DFT_USER = "SYS";


    private static final Map<String, LogOpType> METHOD_TO_LOG_TYPE = new HashMap<>();

    static {
        METHOD_TO_LOG_TYPE.put("find", LogOpType.GET);
        METHOD_TO_LOG_TYPE.put("get", LogOpType.GET);
        METHOD_TO_LOG_TYPE.put("query", LogOpType.GET);

        METHOD_TO_LOG_TYPE.put("delete", LogOpType.DEL);
        METHOD_TO_LOG_TYPE.put("del", LogOpType.DEL);

        METHOD_TO_LOG_TYPE.put("add", LogOpType.CREATE);
        METHOD_TO_LOG_TYPE.put("create", LogOpType.DEL);

        METHOD_TO_LOG_TYPE.put("export", LogOpType.DATAOUT);
        METHOD_TO_LOG_TYPE.put("import", LogOpType.DATAIN);

        METHOD_TO_LOG_TYPE.put("login", LogOpType.LOGIN);
        METHOD_TO_LOG_TYPE.put("logout", LogOpType.LOGOUT);

        METHOD_TO_LOG_TYPE.put("update", LogOpType.UPD);
        METHOD_TO_LOG_TYPE.put("set", LogOpType.UPD);
    }

    public static String getMethodType(String methodName) {
        return METHOD_TO_LOG_TYPE.get(methodName).toString();
    }

}
