package com.atjl.common.context;

import com.atjl.common.api.IProcedure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 异常重试
 * 过滤异常是否抛出
 */
public class ExceptionContext {
    private ExceptionContext() {
        throw new IllegalAccessError("Utility class");
    }

    private static final Logger logger = LoggerFactory.getLogger(ExceptionContext.class);
    /**
     * 是否抛出非必要模块异常，主要用于测试环境 kafka ，hbase等宕机，导致其他模块无法运行
     * 开发环境通过sysconfig内 throwexception设置为false，相关代码放入 filterException， 不抛出非必要模块异常
     * 生产环境：必须设为true，以便问题排查
     */
    private static final boolean THROW_EXCEPTION = true;
    private static int DFT_RETRY_COUNT = 3;

    static {
        /**
         * 初始化是否异常捕捉，默认true，抛出异常
         */
        /*
        String val = null;
        try {
            val = ConfigUtil.get("throwexception");
        } catch (Exception e) {
            LOG.warn("read properties {} fail", "throwexception");
        }
        if (StringCheckUtil.equal(val, "false")) {
            THROW_EXCEPTION = false;
        }*/
    }

    /**
     * filter,catch exception or not
     * 开发阶段吞掉 非必要模块异常，以免影响 其他模块开发
     *
     * @param action
     */
    public static void filterException(IProcedure action) throws Exception {
        try {
            action.action();
        } catch (Exception e) {
            if (THROW_EXCEPTION) {
                throw e;
            } else {
                logger.error("filter exception,exception occure", e);
            }
        }
    }

    public static class ExcepResDto {
        /**
         * execute succ = true
         * fail = false
         */
        private boolean res = true;
        /**
         * execute fail's exception
         */
        private Exception e;

        public ExcepResDto() {
        }

        public ExcepResDto(boolean res) {
            this.res = res;
        }

        public ExcepResDto(boolean res, Exception e) {
            this.res = res;
            this.e = e;
        }

        public boolean isRes() {
            return res;
        }

        public void setRes(boolean res) {
            this.res = res;
        }

        public Exception getE() {
            return e;
        }

        public void setE(Exception e) {
            this.e = e;
        }
    }

    /**
     * 默认次数 重试
     *
     * @param action
     * @return
     */
    public static ExcepResDto retry(IProcedure action) {
        return retry(DFT_RETRY_COUNT, action);
    }

    /**
     * 重试 指定次数
     *
     * @param retryCount
     * @param action
     * @return @true success; @false fail
     */
    public static ExcepResDto retry(int retryCount, IProcedure action) {
        if (retryCount <= 0) {
            logger.warn("retry count <=0");
            return new ExcepResDto(false);
        }
        int triedCnt = 0;
        Exception resE = null;
        while (triedCnt < retryCount) {
            try {
                action.action();
                return new ExcepResDto();
            } catch (Exception e) {
                logger.error("retry error,count {}", triedCnt, e);
                triedCnt++;
                resE = e;
            }
        }//todo: if every exception not equal,it will hide the exception before
        return new ExcepResDto(false, resE);
    }
}
