package com.sf.inv.process.dto;


public class FlowConstant {

    //public static final int OP_NORMAL = 0x01;

    public static final int OP_EXCEPTION_CONTINUE_ENABLE = 0x02;
    public static final int OP_ERROR_CODE_CONTINUE_ENABLE = 0x04;
    public static final int OP_CHNAGE_NEXT_FLOWID_ENABLE = 0x08;
    public static final int OP_FORCE_CHNAGE_NEXT_FLOWID_ENABLE = 0x10;
    /**
     * if set , flow loop count,must be set
     */
    public static final int OP_FLOW_LOOP_ENABLE = 0x20;

    public static final String KEY_REQUEST = "request$";
    public static final String KEY_RESPONSE = "response$";
    public static final String KEY_GLOBAL = "global$";
    public static final String KEY_NEXT = "nextflowid$";

    public static final String METHOD_ACTION = "action";


    public static final int TP_CONTINUE = 2;
    public static final int TP_DUAL = 1;
    public static final int TP_MULTI = 2;

public static final int TP_UNKNOWN = -1;


    public static final int TP_FLOW = 1;
    //public static final int TP_FLOW_PROC = 2;
    public static final int TP_FLOW_FUNC = 2;


    public static final String FLOW_ID_NOT_EXIST = "none";
    public static final String FLOW_ID_CONTINUE = "continue";

    public static final String CLZ_FLOW_FUNC_PARAM_GLOBAL = "FlowFuncParamGlobal";//.class.getSimpleName();
    public static final String CLZ_FLOW_FUNC_PARAM = "FlowFuncParam";//.class.getSimpleName();

    public static final String CLZ_FLOW_FUNC_NO_PARAM = "FlowFuncNoParam";//.class.getSimpleName();


}
