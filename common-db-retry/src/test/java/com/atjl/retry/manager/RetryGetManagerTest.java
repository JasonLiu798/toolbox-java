package com.atjl.retry.manager;

import com.atjl.retry.api.option.RetryOption;
import com.atjl.retry.api.option.RetryTableMetaConf;
import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.json.JSONFastJsonUtil;
import com.atjl.util.json.JSONFmtUtil;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context.xml"})
public class RetryGetManagerTest {


    @Resource
    private GetDataManager retryGetManager;


    @Test
    public void testGetData() throws Exception {
        RetryOption opt = new RetryOption();
//        opt.setIntervalCoefficient(10);
        opt.getIntervalCoefficientOption().setIntervalCoefficient(3);
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
        meta.setOtherConds("SEND_TM < unix_timestamp(now())-3600*24*2 ");
        meta.setDataCols(CollectionUtil.newSet("SEND_TOPIC", "SEND_CONTENT"));
//        meta.setOrderByClause("SEND_TM desc");
        opt.setRetryTabMeta(meta);

        String startId = null;
//        String startId = "12";
        List l = retryGetManager.getRetryDatas(opt, startId);

        System.out.println("res:" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(l)));
    }


    @Test
    public void testCount() {
        RetryOption opt = new RetryOption();
        //opt.setIntervalCoefficient(10);
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
//        meta.setOrderByClause("SEND_TM desc");
        opt.setRetryTabMeta(meta);

        int l = retryGetManager.getRetryTotalCount(opt);

        System.out.println("res:" + l);
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
