package com.atjl.logdb.api.domain;


public enum LogOpType {
    CREATE,
    DEL,
    UPD,
    GET,
    LOGIN,
    LOGOUT,
    DATAIN,//导入
    DATAOUT,//导出
    OTHER
}
