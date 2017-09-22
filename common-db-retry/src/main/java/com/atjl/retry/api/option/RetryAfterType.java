package com.atjl.retry.api.option;

/**
 * 后置服务类型
 */
public enum RetryAfterType {
    DEFAULT,//使用默认 after处理
    CUSTOM,//使用自定义
    ALLSEQ,//全都使用，先执行default
    ALLREV,//全都使用，先执行 自定义
    NONE//无后置服务，自行在 execute内执行所有操作
}
