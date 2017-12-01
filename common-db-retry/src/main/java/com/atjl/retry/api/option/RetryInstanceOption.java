package com.atjl.retry.api.option;

/**
 * 立即重试 相关参数
 */
public class RetryInstanceOption extends PageOption {
    public RetryInstanceOption() {
    }

    /**
     * 执行 重试操作时，出现异常是否立即重试
     * true需要设置instanceRetryOption
     */
    private boolean exceptionInstanceRetry = false;

    /**
     * 立即重试 相关配置
     */
    private InstanceRetryConf instanceRetryOption = new InstanceRetryConf();

    public boolean isExceptionInstanceRetry() {
        return exceptionInstanceRetry;
    }

    public void setExceptionInstanceRetry(boolean exceptionInstanceRetry) {
        this.exceptionInstanceRetry = exceptionInstanceRetry;
    }


    public InstanceRetryConf getInstanceRetryOption() {
        return instanceRetryOption;
    }

    public void setInstanceRetryOption(InstanceRetryConf instanceRetryOption) {
        this.instanceRetryOption = instanceRetryOption;
    }
}
