package com.atjl.dbtiming.service;

import com.atjl.dbtiming.api.TimingService;
import com.atjl.dbtiming.helper.TimingInnerManager;
import com.atjl.util.common.SystemUtil;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = false, transactionManager = "transactionManager")
@ContextConfiguration(locations = {"classpath:test-service.xml"})
@Transactional
public class TimingTaskHelperTest {

    @Resource
    TimingInnerManager timingCoreHelper;
    @Resource
    TimingService timingManager;

    @Test
    public void testAddFixRateCondTask() throws Exception {
        //    public FixRateTask(Long tid,TimingCoreHelper helper,ITaskExecute service) {
//        TimingTaskBase t = new FixRateTask(1L, innerManager);
//        innerManager.saveHistory(t);
    }

    @Test
    public void testSaveHistory() throws Exception {
        timingManager.init();

        //timingTaskHelper.addFixRateCondTask("fixratecond",3000L, 3000L, 10L);
        SystemUtil.sleep(200 * 1000);
    }

    @Test
    public void testGetTaskByKey() throws Exception {

//        List<GenTask> l = timingTaskHelper.getTaskNotEndButNoManagerProcessing();
//        System.out.println("res:"+ JSONFastJsonUtil.objectToJson(l));


    }

    @Test
    public void testGetTaskById() throws Exception {

    }

    @Test
    public void testStartTask() throws Exception {

    }

    @Test
    public void testCheckTaskCanProcess() throws Exception {

    }

    @Test
    public void testStartTaskPre() throws Exception {

    }


    @Test
    public void testMutex() throws Exception { 
                /* 
                try { 
                   Method exeMethod = TimingTaskHelper.getClass().getMethod("mutex", GenTask.class);
                   exeMethod.setAccessible(true);
                   exeMethod.invoke(<Object>, <Parameters>);
                } catch(NoSuchMethodException e) { 
                } catch(IllegalAccessException e) { 
                } catch(InvocationTargetException e) { 
                } 
                */
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
