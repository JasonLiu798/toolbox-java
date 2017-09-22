package com.atjl.util.common;

import com.atjl.common.api.IProcedure;
import com.atjl.util.common.domain.ExcepResDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InstanceRetryUtil {
    private static final Logger logger = LoggerFactory.getLogger(InstanceRetryUtil.class);

    private InstanceRetryUtil() {
        throw new IllegalAccessError("Utility class");
    }

    /**
     * 是否抛出非必要模块异常，主要用于测试环境 kafka ，hbase等宕机，导致其他模块无法运行
     * 开发环境通过sysconfig内 throwexception设置为false，相关代码放入 filterException， 不抛出非必要模块异常
     * 生产环境：必须设为true，以便问题排查
     */
    public static int DFT_RETRY_COUNT = 3;
    public static final long DFT_RETRY_WAIT_MS = 0L;

    /**
     * 默认次数 重试
     *
     * @param action 重试服务
     */
    public static ExcepResDto retry(IProcedure action) {
        return retry(DFT_RETRY_COUNT, DFT_RETRY_WAIT_MS, action);
    }

    /**
     * 重试 指定次数
     *
     * @param retryCount 重试次数
     * @param action     重试服务
     * @return @true success; @false fail
     */
    public static ExcepResDto retry(int retryCount, long waitMs, IProcedure action) {
        if (retryCount <= 0) {
            logger.warn("retry count <=0");
            return new ExcepResDto(false);
        }
        int triedCnt = 0;
        Exception resE = null;
        while (triedCnt < retryCount) {
            try {
                if (waitMs > 0) {
                    Thread.sleep(waitMs);
                }
                action.action();
                return new ExcepResDto();
            } catch (Exception e) {
                logger.error("retry error,count {}", triedCnt, e);
                triedCnt++;
                resE = e;
            }
        }
        //todo: if every exception not equal,it will hide the exception before
        return new ExcepResDto(false, resE);
    }


    public static void eatException(IProcedure p) {
        try {
            filterException(true, p);
        } catch (Exception e) {
        }
    }

    /**
     * filter,catch exception or not
     * 开发阶段吞掉 非必要模块异常，以免影响 其他模块开发
     *
     * @param action
     */
    public static void filterException(boolean eat, IProcedure action) throws Exception {
        try {
            action.action();
        } catch (Exception e) {
            if (eat) {
                logger.error("filter exception,exception occure", e);
            } else {
                throw e;
            }
        }
    }


}
