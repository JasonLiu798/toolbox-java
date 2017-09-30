package com.atjl.logdb.whole;


import com.atjl.logdb.api.LogContextUtil;
import com.atjl.logdb.api.LogDbUtil;
import com.atjl.logdb.api.LogService;
import com.atjl.logdb.api.domain.LogDbContext;
import com.atjl.logdb.api.domain.LogOpType;
import com.atjl.util.common.SystemUtil;
import com.atjl.util.queue.IQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-service.xml"})
public class TestAll {

    @Resource
    private LogService logService;


    @Test
    public void testAll() {
        LogContextUtil.envDev();

        int poolsize = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(poolsize);

//            public WriteTask(String id, LogService logService, IQueue queue, Long srvIp) {
        IQueue q = LogDbContext.getLogQueue();//QueueManager.getQueue(LogDbConstant.LOG_QUEUE);
        WriteTask t = new WriteTask("1", logService, q, 123123L);
        executorService.submit(t);

        LogDbUtil.debug("testModule", "hahahha", LogOpType.OTHER.toString(), null, null, "cccc", "ref", "34234", 123123L, null, 1);
        LogDbUtil.error("aaa", "dfsd", new RuntimeException());
        LogDbUtil.error("aaa", "dfsd", LogOpType.CREATE.toString(), null, null, "ccc", null, "234234", 2132L, null, 1);


        SystemUtil.sleepForever();

    }
}
