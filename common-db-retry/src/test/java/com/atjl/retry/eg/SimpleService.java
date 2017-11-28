package com.atjl.retry.eg;


import com.atjl.retry.api.AfterService;
import com.atjl.retry.api.DataContext;
import com.atjl.retry.api.DataContextFactory;
import com.atjl.retry.api.RetryService;
import com.atjl.retry.api.option.*;
import com.atjl.util.json.JSONFastJsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("simplePageService")
public class SimpleService implements RetryService<Data>, AfterService<Data> {

    private static final Logger logger = LoggerFactory.getLogger(SimpleService.class);

    @Override
    public InitOption getInitOption() {
        InitOption opt = new InitOption();
        opt.setServiceName("simplePageService");
        
        opt.setPageSize(100);
        opt.setAfterType(RetryAfterType.CUSTOM);
        opt.setExceptionInstanceRetry(false);
        
        opt.setGetDataType(GetDataType.CUSTOM_USEPAGE);
        
        
        return opt;
    }

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

    public int getRetryDataCount() {
        return 2;
    }

    public List<DataContext<Data>> getRetryDataContextListPaged(int page, int pageSize) {
    	if(page==1){
			List<DataContext<Data>> l = new ArrayList<>();
			Data d = new Data();
			d.setS("ssss");
			DataContext<Data> c = DataContextFactory.build(d);
			c.setId("3");
			c.setRetriedCnt(2L);
			c.setLastRetriedTs(0L);
			l.add(c);
    		return l;
		}else if(page==2){
			List<DataContext<Data>> l = new ArrayList<>();
			Data d = new Data();
			d.setS("ssss");
			DataContext<Data> c = DataContextFactory.build(d);
			c.setId("234");
			l.add(c);
			return l;
		}
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
