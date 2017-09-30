package com.atjl.retry.api.option;

import com.atjl.retry.api.constant.RetryConstant;

/**
 * 重试 间隔系数相关
 */
public class IntervalCoefficientOption {
    public IntervalCoefficientOption() {
        this.intervalCoeffientType = IntervalCoeffientType.FIX;
        this.intervalCoefficient = RetryConstant.DFT_RETRY_INTERVAL_COFF;
    }

    /**
     * true 递增间隔
     * false 固定间隔
     */
    private IntervalCoeffientType intervalCoeffientType;
    // = IntervalCoeffientType.FIX;

    /**
     * 重试间隔倍数
     * <p>
     * x分钟被调用一次
     * 如果需要重试时间大于x分钟，需要设定此函数返回y，
     * 最终重试间隔=x*y
     * 默认 5min*6=30 分钟，重试一次
     * 调度服务，通过此间隔+当前时间戳 与 最后一次执行时间做比较，超过则执行
     * <p>
     * 规则：
     * 1~580
     */
    private int intervalCoefficient;
    // = RetryConstant.DFT_RETRY_INTERVAL_COFF;

    /**
     * 递增间隔系数
     * x分钟被调用一次
     * exponentialBackoffArray[重试次数]*
     * 如果重试次数超过数组长度，则一直使用数组最后一项作为系数
     */
    private int[] exponentialBackoffArray;// = {1, 2, 3, 5, 10, 20, 40, 100, 100, 100, 100, 200, 200};

    public IntervalCoeffientType getIntervalCoeffientType() {
        return intervalCoeffientType;
    }

    public void setIntervalCoeffientType(IntervalCoeffientType intervalCoeffientType) {
        this.intervalCoeffientType = intervalCoeffientType;
    }

    public int getIntervalCoefficient() {
        return intervalCoefficient;
    }

    public void setIntervalCoefficient(int intervalCoefficient) {
        this.intervalCoefficient = intervalCoefficient;
    }

    public int[] getExponentialBackoffArray() {
        return exponentialBackoffArray;
    }

    public void setExponentialBackoffArray(int[] exponentialBackoffArray) {
        this.exponentialBackoffArray = exponentialBackoffArray;
    }
}
