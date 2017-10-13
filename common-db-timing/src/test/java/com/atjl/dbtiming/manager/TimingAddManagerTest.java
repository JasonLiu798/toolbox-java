package com.atjl.dbtiming.manager;

import com.atjl.common.api.resp.ResponseDataDto;
import com.atjl.dbtiming.api.req.DynamicTaskParam;
import com.atjl.dbtiming.util.TaskParamUtil;
import com.atjl.util.json.JSONFastJsonUtil;
import com.atjl.util.json.JSONFmtUtil;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

//@TransactionConfiguration(defaultRollback = false, transactionManager = "transactionManager")
//@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-service.xml"})
public class TimingAddManagerTest {

    @Resource
    TimingAddManager timingAddManager;

    @Test
    public void addDynCron() throws Exception {
        DynamicTaskParam p = TaskParamUtil.genNewCronTask("1", "*/10 * * * * ?", "fixratecond");
//        p.getTaskConf().setHasCond(true);
//        p.getTaskConf().setHasParam(true);
        System.out.println("param:" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(p)));
        /**
         {
         "taskConf":{
         "cronExpression":"1 * * * * ?",
         "delay":0,
         "hasCond":false,
         "hasParam":false,
         "hasRunCnt":0,
         "maxRunCnt":0,
         "service":"fixratecond",
         "taskType":"CRON",
         "timeUnit":"MILLISECONDS"
         },
         "tkey":"1"
         }
         */
        ResponseDataDto resp = timingAddManager.add(p);
        System.out.println("res:" + JSONFastJsonUtil.objectToJson(resp));

    }

    @Test
    public void addDynInt() throws Exception {
        DynamicTaskParam p = TaskParamUtil.genFixRateLimitCntTask("2", "testCron", 10 * 1000, 20 * 1000, TimeUnit.MILLISECONDS, 10);
        System.out.println("param:" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(p)));
        ResponseDataDto resp = timingAddManager.add(p);
        System.out.println("res:" + JSONFastJsonUtil.objectToJson(resp));
    }


    @Test
    public void addDbCron() throws Exception {
//        DynamicTaskParam p = TaskParamUtil.genFixRateLimitCntTask("2", "testCron", 10 * 1000, 20 * 1000, TimeUnit.MILLISECONDS, 10);
//        System.out.println("param:" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(p)));
        ResponseDataDto resp = timingAddManager.add(74L);
        System.out.println("res:" + JSONFastJsonUtil.objectToJson(resp));
    }


    @Test
    public void testAddT() throws Exception {

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
