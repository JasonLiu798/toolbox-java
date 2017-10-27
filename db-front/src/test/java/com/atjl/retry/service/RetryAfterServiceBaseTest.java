package com.atjl.retry.service;

import com.atjl.retry.api.DataContext;
import com.atjl.retry.api.DataContextFactory;
import com.atjl.retry.api.option.InitOption;
import com.atjl.retry.api.option.RetryTableMetaConf;
import com.atjl.retry.domain.RetryInnerConstant;
import com.atjl.util.reflect.ReflectSetUtil;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context.xml"})
public class RetryAfterServiceBaseTest {

    @Resource
    private AfterDefaultService retryAfterServiceBase;


    @Test
    public void testFail() {
        DataContext c = DataContextFactory.build(new Object());
        RetryTableMetaConf retryTabMeta = new RetryTableMetaConf();
        retryTabMeta.setTab("tm_send_log");
        retryTabMeta.setIdCol("SEND_LOG_ID");
        retryTabMeta.setResCol("SEND_RES");
        retryTabMeta.setFailReasonCol("SEND_FAIL_REASON");
        retryTabMeta.setLastRetriedTsCol("SEND_TM");
        retryTabMeta.setRetryCountCol("SEND_CNT");

        InitOption opt = new InitOption();
        opt.setRetryTabMeta(retryTabMeta);
        opt.setRetryMaxCount(3L);
        ReflectSetUtil.setterForce(c, RetryInnerConstant.FIELD_OPTION, opt);

        c.setId("2");
        c.setFailReason("xyyyx");
        c.setRetriedCnt(1L);
        c.setFail();
        retryAfterServiceBase.fail(c);

    }

    @Test
    public void testSucc() throws Exception {
        DataContext c = DataContextFactory.build(new Object());
        RetryTableMetaConf retryTabMeta = new RetryTableMetaConf();
        retryTabMeta.setTab("tm_send_log");
        retryTabMeta.setIdCol("SEND_LOG_ID");
        retryTabMeta.setResCol("SEND_RES");
        retryTabMeta.setFailReasonCol("SEND_FAIL_REASON");
        retryTabMeta.setLastRetriedTsCol("SEND_TM");
        retryTabMeta.setRetryCountCol("SEND_CNT");

        InitOption opt = new InitOption();
        opt.setRetryTabMeta(retryTabMeta);
        opt.setRetryMaxCount(3L);
        ReflectSetUtil.setterForce(c, RetryInnerConstant.FIELD_OPTION, opt);

        c.setId("2");
        //c.setRetriedCnt(null);
        c.setSucc();
        retryAfterServiceBase.fail(c);
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
