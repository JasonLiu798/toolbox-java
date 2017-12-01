package com.atjl.retry.api.option;

/**
 * 取数方式
 */
public enum GetDataType {
    DEFAULT,//默认使用 主键排序后分页
    CUSTOM_USEPAGE,//如果查询数据在条件中，且不会改变可用此种
    CUSTOM_BATCH_USEPAGE,//分页查，只需返回 数据
    CUSTOM_USEID//
}
