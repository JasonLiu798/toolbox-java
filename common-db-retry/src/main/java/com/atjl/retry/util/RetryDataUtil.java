package com.atjl.retry.util;


import com.atjl.retry.api.option.RetryOption;

public class RetryDataUtil {
    private RetryDataUtil() {
        throw new UnsupportedOperationException();
    }

    public static long getRetriedCount(Long retriedCntDb, RetryOption initOption) {
        if (retriedCntDb == null || retriedCntDb < 0) {
            return initOption.getRetryMaxCount() - 1;
        }
        return retriedCntDb;
    }
}
