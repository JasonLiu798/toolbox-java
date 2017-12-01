package com.atjl.retry.eg;


import com.atjl.retry.api.AfterService;
import com.atjl.retry.api.DataContext;
import com.atjl.retry.api.DataContextFactory;
import com.atjl.retry.api.ExecuteService;
import com.atjl.retry.api.option.RetryOption;
import com.atjl.retry.api.option.RetryTableMetaConf;
import com.atjl.util.json.JSONFastJsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("testRetryService")
public class TestRetryService implements ExecuteService<Data>, AfterService<Data> {

    private static final Logger logger = LoggerFactory.getLogger(TestRetryService.class);

    @Override
    public RetryOption getInitOption() {
        RetryOption opt = new RetryOption();
        opt.setServiceName("testRetryService");
        opt.getIntervalCoefficientOption().setIntervalCoefficient(10);
        opt.setRetryMaxCount(6L);
//        opt.setExceptionInstanceRetry(false);
//        opt.setWaitMs(9999999999999L);

        RetryTableMetaConf meta = new RetryTableMetaConf();
        meta.setTab("tm_send_log");
        meta.setIdCol("SEND_LOG_ID");
        meta.setResCol("SEND_RES");
        meta.setFailReasonCol("SEND_FAIL_REASON");
        meta.setLastRetriedTsCol("SEND_TM");
        meta.setRetryCountCol("SEND_CNT");
        opt.setRetryTabMeta(meta);

        return opt;
    }

    //    @Override
    public List<DataContext<Data>> getRetryDataContextList() {
        List<DataContext<Data>> l = new ArrayList<>();
        Data d = new Data();
        d.setS("ssss");

        DataContext<Data> c = DataContextFactory.build(d);
        c.setId("3");
        c.setRetriedCnt(2L);
        c.setLastRetriedTs(0L);
        l.add(c);
        return l;
    }

    //    @Override
    public int getRetryDataCount() {
        return 2;
    }

    //    @Override
    public List<DataContext<Data>> getRetryDataContextListPaged(int page, int pageSize) {
        return null;
    }

    @Override
    public boolean execute(DataContext<Data> context) {
        logger.info("TestRetryService execute {}", JSONFastJsonUtil.objectToJson(context));
        throw new RuntimeException();
//        return true;
    }


    @Override
    public void succ(DataContext<Data> context) {
        logger.info("testRetryService succ {}", JSONFastJsonUtil.objectToJson(context));
    }

    @Override
    public void fail(DataContext<Data> context) {
        logger.info("testRetryService fail {}", JSONFastJsonUtil.objectToJson(context));
    }
}
