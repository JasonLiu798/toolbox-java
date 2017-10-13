package com.atjl.dbtiming.task;

import com.atjl.dbtiming.core.WaitDispatchQueue;
import com.atjl.dbtiming.domain.biz.QueueWaitTask;
import com.atjl.dbtiming.domain.constant.TimingConstant;
import com.atjl.util.common.SystemUtil;
import com.atjl.util.thread.ThreadConstant;
import com.atjl.util.thread.ThreadPoolManager;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;


public class DispatchTaskTest {


    @Test
    public void testBizRun() throws Exception {
/*
        QueueWaitTask t = new QueueWaitTask(5, 1);
        WaitDispatchQueue.getInstence().put(t);
        t = new QueueWaitTask(6, 2);
        WaitDispatchQueue.getInstence().put(t);
        t = new QueueWaitTask(4, 3);
        WaitDispatchQueue.getInstence().put(t);
        t = new QueueWaitTask(3, 4);
        WaitDispatchQueue.getInstence().put(t);


        List<String> paramList = new ArrayList<>();
        paramList.add(TimingConstant.POOL_TIMING_EXECUTE + ThreadConstant.DFT_POOL_PARAM);
        ThreadPoolManager.init(paramList);
        ThreadPoolManager.execute(TimingConstant.POOL_TIMING_EXECUTE, new ExecuteTask());
        ThreadPoolManager.execute(TimingConstant.POOL_TIMING_EXECUTE, new ExecuteTask());
        ThreadPoolManager.execute(TimingConstant.POOL_TIMING_EXECUTE, new ExecuteTask());

        DispatchTask dispatchTask = new DispatchTask();
        dispatchTask.bizRun();
*/
        SystemUtil.sleepForever();
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
