package com.atjl.log.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class LogConstantInner {
    private LogConstantInner() {
        throw new UnsupportedOperationException();
    }

    public static final int STACK_PRE_LV = 3;




    /**
     * CRT创建，DEL删除，UPD修改，GET查看
     * LOGIN 登录，LOGOUT 登出，
     * DATAIN 导入，DATAOUT 导出，
     * ECP ecp相关
     * OT其他
     */
    public static final String CREATE = "CRT";
    public static final String DEL = "DEL";
    public static final String UPD = "UPD";
    public static final String GET = "GET";
    public static final String LOGIN = "LOGIN";
    public static final String LOGOUT = "LOGOUT";
    public static final String DATAIN = "DATAIN";
    public static final String DATAOUT = "DATAOUT";
    public static final String OTHER = "OT";


    /**
     * 默认类型
     */
    public static final String DFT_USER = "SYS";
//    public static final String DFT_USER_NAME = "SYS";

    //kpublic static final String MODULE_ = "ecp";


//    public static final String METHOD_PREFIX_TO_TYPE = "";


    private static final Map<String, String> METHOD_TO_LOG_TYPE = new HashMap<>();

    static {
        METHOD_TO_LOG_TYPE.put("find", GET);
        METHOD_TO_LOG_TYPE.put("get", GET);
        METHOD_TO_LOG_TYPE.put("query", GET);

        METHOD_TO_LOG_TYPE.put("delete", DEL);
        METHOD_TO_LOG_TYPE.put("del", DEL);

        METHOD_TO_LOG_TYPE.put("add", CREATE);
        METHOD_TO_LOG_TYPE.put("create", DEL);

        METHOD_TO_LOG_TYPE.put("export", DATAOUT);
        METHOD_TO_LOG_TYPE.put("import", DATAIN);

        //METHOD_TO_LOG_TYPE.put("ecp",ECP);

        METHOD_TO_LOG_TYPE.put("login", LOGIN);
        METHOD_TO_LOG_TYPE.put("logout", LOGOUT);

        METHOD_TO_LOG_TYPE.put("update", UPD);
        METHOD_TO_LOG_TYPE.put("set", UPD);
    }

    public static String getMethodType(String methodName) {
        return METHOD_TO_LOG_TYPE.get(methodName);
    }


}
