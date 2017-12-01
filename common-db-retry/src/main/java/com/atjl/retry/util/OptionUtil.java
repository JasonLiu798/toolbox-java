package com.atjl.retry.util;

import com.atjl.retry.api.constant.RetryConstant;
import com.atjl.retry.api.domain.RetryData;
import com.atjl.retry.api.option.*;
import com.atjl.util.common.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 选项辅助工具类
 */
public class OptionUtil {
    private static final Logger logger = LoggerFactory.getLogger(OptionUtil.class);

    private OptionUtil() {
        throw new UnsupportedOperationException();
    }

    /**
     * 判断后置服务，是否内置外置都执行
     */
    public static boolean hasSequential(PageOption opt) {
        return opt.getAfterType() == RetryAfterType.ALLREV || opt.getAfterType() == RetryAfterType.ALLSEQ;
    }

    /**
     * 判断后置服务 顺序/逆序
     * 是否正向 default -> custom
     *
     * @return
     */
    public static boolean isForward(PageOption opt) {
        return RetryAfterType.ALLSEQ == opt.getAfterType();
    }

    /**
     * 是否有自定义 后置
     */
    public static boolean isCustomAfter(PageOption opt) {
        return opt.getAfterType() == RetryAfterType.ALLREV || opt.getAfterType() == RetryAfterType.ALLSEQ || opt.getAfterType() == RetryAfterType.CUSTOM;
    }

//    public static boolean isDefaultAfter(RetryOption opt) {
//        return opt.getAfterType() == RetryAfterType.ALLREV || opt.getAfterType() == RetryAfterType.ALLSEQ || opt.getAfterType() == RetryAfterType.CUSTOM;
//    }

    /**
     * 是否自定义获取数据 使用页号
     */
    public static boolean isCustomGetDatas(PageOption opt) {
        return opt.getGetDataType() == GetDataType.CUSTOM_USEPAGE || opt.getGetDataType() == GetDataType.CUSTOM_BATCH_USEPAGE;
    }

    /**
     * 是否自定义取数，使用主键
     */
    public static boolean isCustomGetDatasUseId(PageOption opt) {
        return opt.getGetDataType() == GetDataType.CUSTOM_USEID;
    }

//    public static boolean isInstanceRetry(RetryOption opt) {
//        return opt.isExceptionInstanceRetry();
//    }


    /**
     * 如果设定了multiple 检查当前时间 是否超过 lastTm+Multiple*5min
     * 超过则可以执行
     *
     * @param opt       初始化选项
     * @param retryData 主要获取 上次重试时间，重试次数
     * @return 是否到达重试时间
     */
    public static boolean isTimeUp(RetryOption opt, RetryData retryData) {
        long nowTs = DateUtil.getNowTS();
        Long lastRetryTs = retryData.getLastRetriedTs();
        if (lastRetryTs == null) {
            lastRetryTs = 0L;
        }
        IntervalCoefficientOption copt = opt.getIntervalCoefficientOption();
        IntervalCoeffientType type = copt.getIntervalCoeffientType();

        long expect;
        if (type == IntervalCoeffientType.FIX) {
            expect = lastRetryTs + copt.getIntervalCoefficient() * RetryConstant.DFT_RETRY_INTERVAL;
        } else {
            int[] backOffArr = copt.getExponentialBackoffArray();
            long cnt = RetryDataUtil.getRetriedCount(retryData.getRetriedCnt(), opt);
            int idx;
            if (cnt >= backOffArr.length) {
                idx = backOffArr.length - 1;//超出长度，设为最后一项
            } else if (cnt < 0) {
                idx = 0;
            } else {
                idx = Math.toIntExact(cnt - 1);//index需要count-1
            }
            int coff = backOffArr[idx];
            int mCoff = 1;
            if (type == IntervalCoeffientType.VARIABLE_MULTIPLE_FIX) {
                mCoff = copt.getIntervalCoefficient();
            }
            expect = lastRetryTs + mCoff * coff * RetryConstant.DFT_RETRY_INTERVAL;
        }

        if (logger.isDebugEnabled()) {
            logger.debug("time up calc,now {},expect {},res {}", nowTs, expect, nowTs > expect);
        }
        return nowTs > expect;
    }
}
