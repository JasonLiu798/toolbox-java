package com.atjl.retry.api.option;

/**
 * 重试间隔 系数类型
 */
public enum IntervalCoeffientType {
    FIX,//固定间隔
    VARIABLE,//可变间隔
    VARIABLE_MULTIPLE_FIX//固定 * 可变
}
