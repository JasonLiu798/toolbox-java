package com.atjl.dbtiming.service;

import com.atjl.dbtiming.helper.TimingDbHelper;
import com.atjl.util.json.JSONFastJsonUtil;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import com.atjl.dbtiming.domain.gen.GenTask;
import com.atjl.dbtiming.domain.gen.GenTaskRuned;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
//@TransactionConfiguration(defaultRollback = false, transactionManager = "transactionManager")
@ContextConfiguration(locations = {"classpath:test-service.xml"})
@Transactional
public class TimingDbHelperTest {

    @Resource
    private TimingDbHelper timingDbHelper;


    @Test
    public void testRegisteManager() throws Exception {
        List<GenTask> l = timingDbHelper.getTaskNotEndButNoManagerProcessing();

        System.out.println("res:" + JSONFastJsonUtil.objectToJson(l));


    }

    @Test
    public void testUpdateManagerAlive() throws Exception {

        GenTaskRuned t = new GenTaskRuned();
        t.setRunCnt(1L);
//        t.setCrtTm(123L);

        timingDbHelper.addTaskRuned(t);
    }

    @Test
    public void testTaskExistByKey() throws Exception {

    }

    @Test
    public void testGetTasks() throws Exception {

    }

    @Test
    public void testGetTaskById() throws Exception {

    }

    @Test
    public void testGetTaskNotEndButNoManagerProcessing() throws Exception {

    }

    @Test
    public void testAddCronTask() throws Exception {

    }

    @Test
    public void testAddTask() throws Exception {

    }

    @Test
    public void testSetInValid() throws Exception {

    }

    @Test
    public void testUpdateTaskStatus() throws Exception {

    }

    @Test
    public void testUpdateTaskLiveTmForManagerTaskid() throws Exception {

    }

    @Test
    public void testUpdateTaskLiveTmForTaskIdsManager() throws Exception {

    }

    @Test
    public void testUpdateTaskByPk() throws Exception {

    }

    @Test
    public void testSaveHistory() throws Exception {

    }

    @Test
    public void testGetTransManager() throws Exception {

    }

    @Test
    public void testGetDefaultTrans() throws Exception {

    }

    @Test
    public void testGetSerialTrans() throws Exception {

    }

    @Test
    public void testGetTransactionStatus() throws Exception {

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
