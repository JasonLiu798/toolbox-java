package com.atjl.retry.api.option;

import com.atjl.retry.api.constant.RetryConstant;

/**
 * 重试选项
 */
public class InitOption {
    public InitOption(){}

    /**
     * 服务名（必填）
     */
    private String serviceName;

    /**
     * 取数类型：
     * false 默认取数
     * true 自定义取数（需要实现RetryServiceCustomGetDatas接口，如果使用默认后置处理服务，结果对象需要设置 主键、上次执行时间、执行次数，后置服务会自动更新）
     */
    private GetDataType getDataType = GetDataType.DEFAULT;

    /**
     * 后置处理类型：
     * 只用默认、只用自定义、
     * 先默认 再自定义
     * 先自定义 再默认
     */
    private RetryAfterType afterType = RetryAfterType.DEFAULT;

    /**
     * 重试表相关元数据
     * 开启以下选项时不能为空：
     * 取数 GetDataType.DEFAULT
     * 后置使用 RetryAfterType.DEFAULT 或 ALLSEQ ALLREV
     */
    private RetryTableMetaConf retryTabMeta;

    /**
     * 重试间隔
     */
    private IntervalCoefficientOption intervalCoefficientOption = new IntervalCoefficientOption();

    /**
     * 如果重试数据量超过分页大小，则分页查询
     */
    private int pageSize = 10;

    /**
     * 重试最大次数
     */
    private Long retryMaxCount = 3L;

    /**
     * 执行 重试操作时，出现异常是否立即重试
     * true需要设置instanceRetryOption
     */
    private boolean exceptionInstanceRetry = false;

    /**
     * 立即重试 相关配置
     */
    private InstanceRetryConf instanceRetryOption = new InstanceRetryConf();

    /**
     * ############## getter && setter ####################
     */
    public RetryTableMetaConf getRetryTabMeta() {
        return retryTabMeta;
    }

    public void setRetryTabMeta(RetryTableMetaConf retryTabMeta) {
        this.retryTabMeta = retryTabMeta;
    }

    public void setRetryMaxCount(Long cnt) {
        this.retryMaxCount = cnt;
    }

    public Long getRetryMaxCount() {
        return retryMaxCount == null || retryMaxCount < 0 ? RetryConstant.DFT_RETRY_CNT : retryMaxCount;
    }

    public Long getRetryMaxCountDecreaseOne() {
        return getRetryMaxCount() - 1;
    }

    public InstanceRetryConf getInstanceRetryOption() {
        return instanceRetryOption;
    }

    public boolean isExceptionInstanceRetry() {
        return exceptionInstanceRetry;
    }

    public void setExceptionInstanceRetry(boolean exceptionInstanceRetry) {
        this.exceptionInstanceRetry = exceptionInstanceRetry;
    }

    public void setInstanceRetryOption(InstanceRetryConf instanceRetryOption) {
        this.instanceRetryOption = instanceRetryOption;
    }

    public IntervalCoefficientOption getIntervalCoefficientOption() {
        return intervalCoefficientOption;
    }

    public void setIntervalCoefficientOption(IntervalCoefficientOption intervalCoefficientOption) {
        this.intervalCoefficientOption = intervalCoefficientOption;
    }

    public RetryAfterType getAfterType() {
        return afterType;
    }

    public void setAfterType(RetryAfterType afterType) {
        this.afterType = afterType;
    }

    public GetDataType getGetDataType() {
        return getDataType;
    }

    public void setGetDataType(GetDataType getDataType) {
        this.getDataType = getDataType;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

}
