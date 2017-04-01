package com.jason798.log;


import java.util.HashMap;
import java.util.Map;

public class LogConstant {

    public static final String LV_DEBUG = "D";
    public static final String LV_INFO = "I";
    public static final String LV_WARN = "W";
    public static final String LV_ERROR = "E";

    public static final String DICT_LOG_PK = "log";

    public static final String DICT_LOG_BLACK_LIST_KEY = DICT_LOG_PK+ ".blacklist";

    //public static final int MAX_SIZE = 2048;
    public static final int RES_MAX_SIZE = 4000;
    public static final int PARAM_MAX_SIZE = 2000;
    public static final int REF_MAX_SIZE = 1000;

    public static final String LOG_RES_EXCEPTION = "发送异常";
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
     * 模块
     */
    public static final String MODULE_ECP = "ecp";
    /**
     * ecp 返回处理相关
     */
    public static final String MODULE_ECP_RECV = "ecprecv";
    //public static final String MODULE_ECP = "ecpres";
    public static final String MODULE_LOG = "log";
    public static final String MODULE_UNKNOWN = "unknown";

    /**
     * 默认类型
     */
    public static final String DFT_USER = "SYS";
    public static final String DFT_USER_NAME = "SYS";

    //kpublic static final String MODULE_ = "ecp";


    public static final String METHOD_PREFIX_TO_TYPE = "";


    public static final Map<String,String> METHOD_TO_LOG_TYPE = new HashMap<>();

    static {
        METHOD_TO_LOG_TYPE.put("find",GET);
        METHOD_TO_LOG_TYPE.put("get",GET);
        METHOD_TO_LOG_TYPE.put("query",GET);

        METHOD_TO_LOG_TYPE.put("delete",DEL);
        METHOD_TO_LOG_TYPE.put("del",DEL);

        METHOD_TO_LOG_TYPE.put("add",CREATE);
        METHOD_TO_LOG_TYPE.put("create",DEL);

        METHOD_TO_LOG_TYPE.put("export",DATAOUT);
        METHOD_TO_LOG_TYPE.put("import",DATAIN);

        //METHOD_TO_LOG_TYPE.put("ecp",ECP);

        METHOD_TO_LOG_TYPE.put("login",LOGIN);
        METHOD_TO_LOG_TYPE.put("logout",LOGOUT);

        METHOD_TO_LOG_TYPE.put("update",UPD);
        METHOD_TO_LOG_TYPE.put("set",UPD);
    }

    public static String getMethodType(String methodName){
        return METHOD_TO_LOG_TYPE.get(methodName);
    }


}
