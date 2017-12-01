package com.atjl.retry.api.option;

import com.atjl.retry.api.constant.RetryConstant;

/**
 * 重试相关选项
 */
public class RetryOption extends RetryInstanceOption {
    public RetryOption() {
        super();
    }

    /**
     * 是否进行时间 到达校验
     * 为false，则此类内所有字段均不会使用
     * 为true，则会使用以下字段
     *
    private boolean retrySwitch = false;*/

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
     * 重试最大次数
     */
    private Long retryMaxCount = 3L;

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

    public IntervalCoefficientOption getIntervalCoefficientOption() {
        return intervalCoefficientOption;
    }

//    public boolean isRetrySwitch() {
//        return retrySwitch;
//    }
//
//    public void setRetrySwitch(boolean retrySwitch) {
//        this.retrySwitch = retrySwitch;
//    }

    public void setIntervalCoefficientOption(IntervalCoefficientOption intervalCoefficientOption) {
        this.intervalCoefficientOption = intervalCoefficientOption;
    }

}
