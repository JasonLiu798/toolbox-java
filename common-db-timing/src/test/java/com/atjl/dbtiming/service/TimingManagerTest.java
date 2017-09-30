package com.atjl.dbtiming.service;

import com.atjl.common.context.DevContext;
import com.atjl.dbtiming.api.req.AddTaskParam;
import com.atjl.dbtiming.api.RetCode;
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

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = false, transactionManager = "transactionManager")
@ContextConfiguration(locations = {"classpath:test-tk-service.xml"})
@Transactional
public class TimingManagerTest {

    @Autowired
    private TimingServiceImpl timingManager;

    @Test
    public void testExecCronTask() {
        timingManager.init();

        RetCode res = timingManager.execCronTask(1L);
        System.out.println("res:" + res);
//        res = timingManager.execCronTask(2L);
//        System.out.println("res:" + res);
        SystemUtil.sleepForever();
    }

    @Test
    public void addDynamicTask() {
        DevContext.test = true;
        timingManager.init();

        AddTaskParam p = AddTaskParam.buildNewFixRateCntConditionParam(
                "test",
                String.valueOf(1L),
                20L,
                5 * 1000L,
                5 * 1000L
        );

        RetCode res = null;//timingManager.addDynamicTask(p);
//        System.out.println("res:" + res);

        p = AddTaskParam.buildNewFixRateCntParam(
                "test",
                String.valueOf(1L),
                10L,
                5 * 1000L,
                5 * 1000L
        );
        res = timingManager.addDynamicTask(p);
        System.out.println("res:" + res);

        SystemUtil.sleepForever();
    }


    @Test
    public void reExecDynamicTask() {
        timingManager.init();

        RetCode res = timingManager.reExecDynamicTask(72L);
        System.out.println("res:" + res);

        SystemUtil.sleepForever();
    }


    @Test
    public void MTreExecDynamicTask() {
        timingManager.init();

        CountDownLatch l = new CountDownLatch(2);
        T1 t1 = new T1("t1", timingManager, l);
        T1 t2 = new T1("t2", timingManager, l);
        ExecutorService es = Executors.newFixedThreadPool(10);
        es.submit(t1);
        es.submit(t2);

        SystemUtil.sleepForever();
    }


    public static class T1 implements Runnable {
        private CountDownLatch latch;
        private TimingServiceImpl tm;
        private String key;

        public T1(String key, TimingServiceImpl manager, CountDownLatch latch) {
            this.key = key;
            this.tm = manager;
            this.latch = latch;
        }

        public void run() {
            long id = Thread.currentThread().getId();
            try {
                SystemUtil.sleep(4000);
                latch.countDown();
                latch.await();
                System.out.println(key + "thread start run " + id);
                RetCode res = tm.reExecDynamicTask(3L);
                System.out.println(key + "res:" + res);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    @Test
    public void testList() {
        List l = timingManager.getTaskList("Y", "Y");
        System.out.println("res:" + l.size() + "," + JSONFastJsonUtil.objectToJson(l));

        l = timingManager.getTaskList("Y", "N");
        System.out.println("res:" + l.size() + "," + JSONFastJsonUtil.objectToJson(l));

    }


    @Test
    public void testUpdateCron() {
        timingManager.init();

//        TimingConstant.NOT_ALIVE_INTERVAL = 5L;
        //TimingConstant.MUTEX_INTERVAL;


        RetCode res = timingManager.execCronTask(1L);
        System.out.println("exe cron eres:" + res);

        SystemUtil.sleep(28 * 1000);

        String newExpression = "*/5 * * * * ?";
        RetCode ures = timingManager.updateCronTask(1L, newExpression);

        System.out.println("ures:" + JSONFastJsonUtil.objectToJson(ures));

        SystemUtil.sleepForever();

    }


    @Test
    public void manualRun(){

        RetCode res = timingManager.manualRun(1L,"");

//        res = timingManager.manualRun(2L,null);
//        res = timingManager.manualRun(3L,null);
        res = timingManager.manualRun(4L,null);
        System.out.println("res:"+res);

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
