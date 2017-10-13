package com.atjl.dbtiming.service;

import com.atjl.common.constant.CommonConstant;
import com.atjl.common.context.DevContext;
import com.atjl.dbtiming.api.TimingService;
import com.atjl.dbtiming.core.TimingContext;
import com.atjl.dbtiming.domain.gen.GenTask;
import com.atjl.dbtiming.helper.TimingDbHelper;
import com.atjl.dbtiming.helper.TimingInnerManager;
import com.atjl.dbtiming.task.FixRateCntTask;
import com.atjl.util.common.SystemUtil;
import com.atjl.util.json.JSONFastJsonUtil;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = false, transactionManager = "transactionManager")
@ContextConfiguration(locations = {"classpath:test-service.xml"})
@Transactional
public class TimingInnerManagerTest {


    @Resource
    private TimingInnerManager timingInnerManager;
//    @Resource
//    private TestCron testCron;
//    @Resource
//    private TestRate testRate;

    @Test
    public void newCron() throws Exception {


        timingInnerManager.init();
        //    public RespDto<Long> submitCronTask(Long tid, String key, Object target, String cronExpression) throws ParseException {
        String express = "*/5 * * * * ?";

//        RespDto res = timingInnerManager.submitCronTask(1L, "1", testCron, express);

//        System.out.println("res:" + JSONFastJsonUtil.objectToJson(res));

        SystemUtil.sleepForever();
    }


    @Test
    public void newFixRate() throws Exception {
        timingInnerManager.init();

        //Long tid,Object target, long delayMs, long intervalMs, long maxTime
        /*
Long tid,
                                 Object target,
                                 boolean forever,
                                 boolean hasParam,
                                 boolean hasCond,
                                 String param,
                                 long delayMs, long intervalMs,
                                 long maxTime
    */
        boolean forever = false;
        boolean param = true;
        boolean cond = true;
        boolean persist = false;

        /*
        boolean res = timingInnerManager.submitFixRate(1L,
                "1",
                testRate,
                forever,
                param,
                cond,
                persist,
                "paramTest",
                2000, 2000, 10, 0L);
                */
//        System.out.println("res:" + res);

        SystemUtil.sleepForever();
    }

    @Test
    public void testClear() throws Exception {

        timingInnerManager.clearTask();

    }

    @Test
    public void testRestart() throws Exception {

        timingInnerManager.reExecTask();

        SystemUtil.sleepForever();
    }


    @Autowired
    private TimingService timingManager;

    @Test
    public void testReSubmitCronTask() throws Exception {
        //String service, String paramStr, Long maxTime, Long delay, Long interval
//        AddTaskParam p = AddTaskParam.buildNewFixRateCntConditionParam(
//                "aaa",
//                "123", 10L, 3000L, 5000L
//        );
//
//        timingManager.addDynamicTask(p);

        SystemUtil.sleepForever();
    }


    @Resource
    TimingDbHelper timingDbHelper;

    @Test
    public void test() throws Exception {
        List<GenTask> delTasks = timingDbHelper.getTasksEndOrInvalid();
        System.out.println("del tasks " + JSONFastJsonUtil.objectToJson(delTasks));
        //del task,cancel task
        timingInnerManager.delTaskAndBackup(delTasks);
    }

    @Test
    public void testClearCronHis() {
        //bf 90857
        timingInnerManager.clearCronTaskHistory(3600L);
    }


    /**
     * test task
     *
     * @throws Exception
     */
    @Test
    public void testClearTaskNotInDbButInPool() throws Exception {

        List bf = timingDbHelper.getTasksByValid(CommonConstant.YES);
        TimingContext.addTask(100L, new FixRateCntTask(1L, timingInnerManager, null, null));

        timingInnerManager.clearTaskNotInDbButInPool();

        List af = timingDbHelper.getTasksByValid(CommonConstant.YES);

        System.out.println("bf:"+JSONFastJsonUtil.objectToJson(bf));
        System.out.println("af:"+JSONFastJsonUtil.objectToJson(af));
    }


    @Test
    public void testUpd(){

        String mid = "123";
        List<Long> taskIds = new LinkedList<>();
        taskIds.add(61L);
        taskIds.add(66L);
        timingDbHelper.updateTaskLiveTm(taskIds, mid);
        //timingInnerManager.updateLiveTm();
    }



    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @BeforeClass
    public static void beforeClass() throws Exception {
        DevContext.test = true;
    }

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();
} 
