package com.atjl.dbtiming.service;

import com.atjl.dbtiming.api.ITaskExecute;
import com.atjl.dbtiming.helper.TimingInnerManager;
import com.atjl.util.common.SystemUtil;
import org.junit.*;
import org.junit.rules.ExpectedException;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = false, transactionManager = "transactionManager")
@ContextConfiguration(locations = {"classpath:test-service.xml"})
@Transactional
public class TimingCoreHelperTest {

    @Test
    public void testInit() throws Exception { 
        TimingInnerManager helper = new TimingInnerManager();
        helper.init();

        TestTask t = new TestTask();

//        helper.submitFixRate(t,1000,1000);

//        CancelTask c = new CancelTask();
//        helper.submitDelayTask(c,10*1000);

        //"0 0 14-6 ? * FRI-MON
        String cron = "3 * * * * *";
        cron = "*/5 * * * * ?";

        //CronTask ct = new CronTask();
//        helper.submitCronTask(t,cron);

        SystemUtil.sleep(200*1000);
        //    public ScheduledFuture fixRate(ITaskExecute task, long delayMs, long intervalMs)
        //ITaskExecute t = new
        //helper.fixRate();
    }

    static String t1 = "t1";
    static String tc = "tc";

    public static class TestTask implements ITaskExecute {
        int i=0;
        public String getTid() {
            return t1;
        }
        @Override
        public void execute() {
            System.out.println("test task run "+i);
            i++;
        }
    }

    public static class CancelTask implements ITaskExecute {

        public String getTid() {
            return tc;
        }
        @Override
        public void execute() {
            System.out.println("cancel task start run");
            System.out.println("cancel task "+t1);
//            TimingContext.cancleTask(t1);
        }
    }

    @Before
    public void before() throws Exception { 
    } 

    @After
    public void after() throws Exception { 
    }
    
    @BeforeClass
    public static void beforeClass() throws Exception{
        
    }
    
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();
} 
