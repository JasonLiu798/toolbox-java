package com.atjl.retry.api.constant;


public class RetryConstant {
    private RetryConstant() {
        super();
    }

    /**
     * 默认重试最大次数
     */
    public static final long DFT_RETRY_CNT = 3;

    /**
     * 默认 5分钟调度一次重试
     */
    public static final long DFT_RETRY_INTERVAL = 60 * 5;


    public static final int DFT_RETRY_INTERVAL_COFF = 6;

    /**
     * 默认重试 加倍数组
     */
    public static final int[] DFT_RETRY_ARR = {1, 2, 3, 5, 10, 20, 40, 100, 100, 100, 100, 200, 200};

}
