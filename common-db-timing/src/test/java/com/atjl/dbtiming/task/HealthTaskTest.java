package com.atjl.dbtiming.task;

import com.atjl.util.common.SystemUtil;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import com.atjl.dbtiming.helper.TimingInnerManager;
import com.atjl.dbtiming.api.TimingService;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = false, transactionManager = "transactionManager")
@ContextConfiguration(locations = {"classpath:test-service.xml"})
@Transactional
public class HealthTaskTest {

    @Resource
    private TimingInnerManager timingInnerManager;
    @Resource
    private TimingService timingManager;

    @Test
    public void testExecute() throws Exception {
        timingManager.init();

        HealthTask t = new HealthTask();
        t.setTimingInnerManager(timingInnerManager);

        SystemUtil.sleep(10 * 1000);
        t.execute();


        SystemUtil.sleepForever();
    }

    @Test
    public void testGetTimingInnerManager() throws Exception {

        HealthTask t = new HealthTask();
        t.setTimingInnerManager(timingInnerManager);

        SystemUtil.sleep(10 * 1000);


        SystemUtil.sleepForever();
    }

    @Test
    public void testSetTimingInnerManager() throws Exception {

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
