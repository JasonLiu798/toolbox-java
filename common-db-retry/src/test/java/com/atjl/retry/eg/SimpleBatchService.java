package com.atjl.retry.eg;


import com.atjl.retry.api.CustomGetSimpleDatas;
import com.atjl.retry.api.ExecuteBatchService;
import com.atjl.retry.api.GetOptionService;
import com.atjl.retry.api.domain.ExecuteStatusResp;
import com.atjl.retry.api.option.GetDataType;
import com.atjl.retry.api.option.PageOption;
import com.atjl.retry.api.option.RetryAfterType;
import com.atjl.util.json.JSONFastJsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SimpleBatchService implements ExecuteBatchService<Data>, CustomGetSimpleDatas, GetOptionService {

    private static final Logger logger = LoggerFactory.getLogger(SimpleBatchService.class);

//    @Override
//    public boolean execute(List<Data> datas) {
//        System.out.println("execute get " + JSONFastJsonUtil.objectToJson(datas));
//        return true;
//    }

    @Override
    public ExecuteStatusResp execute(List datas) {
        System.out.println("execute get " + JSONFastJsonUtil.objectToJson(datas));
        return new ExecuteStatusResp();
    }

    @Override
    public PageOption getInitOption() {
        PageOption opt = new PageOption();
        opt.setServiceName(TestConstant.SIMPLE_BATCH_SERVICE);
        opt.setPageSize(5);
        opt.setBatchProcess(true);
        opt.setAfterType(RetryAfterType.NONE);
        opt.setGetDataType(GetDataType.CUSTOM_BATCH_USEPAGE);
        return opt;
    }

//    @Override
//    public List<DataContext<Data>> getRetryDataContextListPaged(int page, int pageSize) {
//        List<DataContext<Data>> l = new ArrayList<>();
//        Data d = new Data();
//        d.setS("ssss");
//
//        DataContext<Data> c = DataContextFactory.build(d);
//        c.setId("3");
//        c.setRetriedCnt(2L);
//        c.setLastRetriedTs(0L);
//        l.add(c);
//        return l;
//    }


    public List<Data> getRetryDataContextListPaged(int page, Cond cond) {
        if (page == 1) {
            List<Data> l = new ArrayList<>();
            Data d = new Data();
            d.setS("ssss");
//            DataContext<Data> c = DataContextFactory.build(d);
//            c.setId("3");
//            c.setRetriedCnt(2L);
//            c.setLastRetriedTs(0L);
            l.add(d);
            System.out.println("cond:" + cond);
            return l;
        } else if (page == 2) {
            List<Data> l = new ArrayList<>();
            Data d = new Data();
            d.setS("ssssllll");
//            DataContext<Data> c = DataContextFactory.build(d);
//            c.setId("234");
            l.add(d);
            System.out.println("cond:" + cond);
            return l;
        }
        return null;
    }

    @Override
    public int getRetryDataCount(Object cond) {
        System.out.println("cond:" + cond);
        return 8;
    }

    @Override
    public List getRetryDataContextListPaged(int page, int pagesize, Object cond) {
        if (page == 1) {
            List<Data> l = new ArrayList<>();
            Data d = new Data();
            d.setS("ssss");
//            DataContext<Data> c = DataContextFactory.build(d);
//            c.setId("3");
//            c.setRetriedCnt(2L);
//            c.setLastRetriedTs(0L);
            l.add(d);
            System.out.println("cond:" + cond);
            return l;
        } else if (page == 2) {
            List<Data> l = new ArrayList<>();
            Data d = new Data();
            d.setS("ssssllll");
//            DataContext<Data> c = DataContextFactory.build(d);
//            c.setId("234");
            l.add(d);
            System.out.println("cond:" + cond);
            return l;
        }
        return null;
    }


//    @Override
//    public boolean execute(DataContext<Data> context) {
//        logger.info("TestRetryService execute {}", JSONFastJsonUtil.objectToJson(context));
////        throw new RuntimeException();
//        return true;
//    }


}
