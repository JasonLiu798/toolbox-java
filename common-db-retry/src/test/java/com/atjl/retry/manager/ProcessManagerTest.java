package com.atjl.retry.manager;

import com.atjl.retry.api.DataContext;
import com.atjl.retry.api.DataContextFactory;
import com.atjl.retry.api.option.GetDataType;
import com.atjl.retry.api.option.RetryOption;
import com.atjl.retry.api.option.RetryAfterType;
import com.atjl.retry.api.option.RetryTableMetaConf;
import com.atjl.retry.domain.RetryServiceItem;
import com.atjl.retry.eg.Data;
import com.atjl.retry.eg.TestRetryService;
import com.atjl.util.collection.CollectionUtil;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context.xml"})
public class ProcessManagerTest {

    @Resource
    ProcessManager processManager;
    @Resource
    TestRetryService testRetryService;

    /**
     * no instance retry
     * no after
     *
     * @throws Exception
     */
    @Test
    public void testProcessItem() throws Exception {

        RetryServiceItem item = new RetryServiceItem();
        RetryOption opt = new RetryOption();
        opt.setAfterType(RetryAfterType.NONE);
        opt.setExceptionInstanceRetry(false);
//        opt.getInstanceRetryOption().setRetryCount(3);
//        opt.getInstanceRetryOption().setWaitMs(100L);

        opt.setGetDataType(GetDataType.DEFAULT);
        opt.getIntervalCoefficientOption().setIntervalCoefficient(10);
        opt.setRetryMaxCount(3L);
        opt.setExceptionInstanceRetry(false);
        opt.setServiceName("aaaa");
        opt.setPageSize(2);

        RetryTableMetaConf meta = new RetryTableMetaConf();
        meta.setTab("tm_send_log");
        meta.setIdCol("SEND_LOG_ID");
        meta.setResCol("SEND_RES");
        meta.setFailReasonCol("SEND_FAIL_REASON");
        meta.setLastRetriedTsCol("SEND_TM");
        meta.setRetryCountCol("SEND_CNT");
        meta.setOtherConds("SEND_TM > unix_timestamp(now())-3600*24*2 ");
        meta.setDataCols(CollectionUtil.newSet("SEND_TOPIC", "SEND_CONTENT"));
        item.setPageOption(opt);

        DataContext d = DataContextFactory.build(new Data());
        d.setRetriedCnt(2L);
        d.setLastRetriedTs(1506045456L);

        item.setRetryService(testRetryService);
        processManager.processItem(item, d);
    }


    /**
     * instance retry
     * no after
     */
    @Test
    public void testInstanceRetry() throws Exception {

        RetryServiceItem item = new RetryServiceItem();
        RetryOption opt = new RetryOption();
        opt.setAfterType(RetryAfterType.NONE);
        opt.setExceptionInstanceRetry(true);
        opt.getInstanceRetryOption().setRetryCount(3);
        opt.getInstanceRetryOption().setWaitMs(100);


        opt.setGetDataType(GetDataType.DEFAULT);
        opt.getIntervalCoefficientOption().setIntervalCoefficient(10);
        opt.setRetryMaxCount(3L);


        opt.setServiceName("aaaa");
        opt.setPageSize(2);

        RetryTableMetaConf meta = new RetryTableMetaConf();
        meta.setTab("tm_send_log");
        meta.setIdCol("SEND_LOG_ID");
        meta.setResCol("SEND_RES");
        meta.setFailReasonCol("SEND_FAIL_REASON");
        meta.setLastRetriedTsCol("SEND_TM");
        meta.setRetryCountCol("SEND_CNT");
        meta.setOtherConds("SEND_TM > unix_timestamp(now())-3600*24*2 ");
        meta.setDataCols(CollectionUtil.newSet("SEND_TOPIC", "SEND_CONTENT"));
        item.setPageOption(opt);

        DataContext d = DataContextFactory.build(new Data());
        d.setRetriedCnt(2L);
        d.setLastRetriedTs(1506045456L);

        item.setRetryService(testRetryService);
        processManager.processItem(item, d);
    }

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @BeforeClass
    public static void beforeClass() throws Exception {

    }

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();
} 
