package com.atjl.retry.manager;

import com.atjl.retry.api.option.InitOption;
import com.atjl.retry.api.option.RetryTableMetaConf;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context.xml"})
public class RetryOptionManagerTest {

    @Resource
    private OptionManager retryOptionManager;


    @Test
    public void succNoInsRetry() throws Exception {
        InitOption opt = new InitOption();
        opt.getIntervalCoefficientOption().setIntervalCoefficient(10);
        opt.setRetryMaxCount(3L);
        opt.setExceptionInstanceRetry(false);
        opt.setServiceName("aaaa");

        RetryTableMetaConf meta = new RetryTableMetaConf();
        meta.setTab("tm_send_log");
        meta.setIdCol("SEND_LOG_ID");
        meta.setResCol("SEND_RES");
        meta.setFailReasonCol("SEND_FAIL_REASON");
        meta.setLastRetriedTsCol("SEND_TM");
        meta.setRetryCountCol("SEND_CNT");

        opt.setRetryTabMeta(meta);
        retryOptionManager.checkOption(opt);
    }

    @Test
    public void succInsRetry() throws Exception {
        InitOption opt = new InitOption();
        opt.getIntervalCoefficientOption().setIntervalCoefficient(10);
        opt.setRetryMaxCount(3L);
//        opt.setExceptionInstanceRetry(true);
        //1-5
//        opt.setRetryCount(3);
        //0-10*10000
//        opt.setWaitMs(1232L);
        opt.setServiceName("aaaa");

        RetryTableMetaConf meta = new RetryTableMetaConf();
        meta.setTab("tm_send_log");
        meta.setIdCol("SEND_LOG_ID");
        meta.setResCol("SEND_RES");
        meta.setFailReasonCol("SEND_FAIL_REASON");
        meta.setLastRetriedTsCol("SEND_TM");
        meta.setRetryCountCol("SEND_CNT");

        opt.setRetryTabMeta(meta);
        retryOptionManager.checkOption(opt);
    }


    @Test
    public void testCheckRetryTab() throws Exception {

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
