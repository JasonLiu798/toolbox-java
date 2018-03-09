package com.sf.inv.process;

import com.sf.inv.log.LogContext;
import com.sf.inv.spi.IProcedure;
import com.sf.inv.util.ConfigUtil;
import com.sf.inv.util.StringCheckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionContext {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionContext.class);
    /**
     * 是否抛出非必要模块异常，主要用于测试环境 kafka ，hbase等宕机，导致其他模块无法运行
     * 开发环境通过sysconfig内 throwexception设置为false，相关代码放入 filterException， 不抛出非必要模块异常
     * 生产环境：必须设为true，以便问题排查
     */
    public static boolean THROW_EXCEPTION = true;
    private static int DFT_RETRY_COUNT = 3;

    static {
        /**
         * 初始化是否异常捕捉，默认true，抛出异常
         */
        String val = null;
        try {
            val = ConfigUtil.get("throwexception");
        } catch (Exception e) {
            LOG.warn("read properties {} fail", "throwexception");
        }
        if (StringCheckUtil.equal(val, "false")) {
            THROW_EXCEPTION = false;
        }
    }

    /**
     * filter,catch exception or not
     *
     * @param action
     */
    public static void filterException(IProcedure action) throws Exception {
        try {
            action.action();
        } catch (Exception e) {
            if (THROW_EXCEPTION) {
                throw e;
            }else{
                LogContext.error("filter exception,exception occure",e);
            }
        }
    }

    public static boolean retry(IProcedure action) {
        return retry(DFT_RETRY_COUNT, action);
    }

    /**
     * general retry
     *
     * @param retryCount
     * @param action
     * @return @true success; @false fail
     */
    public static boolean retry(int retryCount, IProcedure action) {
        if (retryCount <= 0) {
            LOG.warn("retry count <=0");
            return false;
        }
        boolean fail = true;
        int triedCnt = 0;
        while (fail && triedCnt < retryCount) {
            try {
                action.action();
                fail = false;
                return true;
            } catch (Exception e) {
                LOG.error("retry error,count {}", triedCnt);
                if(LOG.isDebugEnabled()){
                    LOG.debug(e.getMessage(),e);
                }
                triedCnt++;
            }
        }
        return false;
    }
}
