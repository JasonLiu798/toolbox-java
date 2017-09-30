package com.atjl.dbtiming.api;

import com.atjl.dbtiming.api.req.AddTaskParam;
import com.atjl.util.json.JSONFastJsonUtil;
import org.junit.*;
import org.junit.rules.ExpectedException;


public class AddTaskParamTest {

    @Test
    public void testIsSucc() throws Exception {
        //String service, String paramStr, Long maxTime, Long delay, Long interval)
        AddTaskParam p = AddTaskParam.buildNewFixRateCntConditionParam("aa", "p", 10L, 1000L, 2000L);
        System.out.println("res:" + JSONFastJsonUtil.objectToJson(p));

        p = AddTaskParam.buildNewFixRateCntParam("aa", "p", 10L, 1000L, 2000L);

        System.out.println("res:" + JSONFastJsonUtil.objectToJson(p));


    }

    @Test
    public void testIsFail() throws Exception {

    }

    @Test
    public void testBuildNewFixRateCntConditionParam() throws Exception {

    }

    @Test
    public void testBuildNewFixRateCntParam() throws Exception {

    }

    @Test
    public void testBuildFailNull() throws Exception {

    }

    @Test
    public void testBuildFailStopOrInvalid() throws Exception {

    }

    @Test
    public void testIsCheckUnique() throws Exception {

    }

    @Test
    public void testGetUniqueChkInterval() throws Exception {

    }

    @Test
    public void testSetUniqueChkInterval() throws Exception {

    }

    @Test
    public void testSetCheckUnique() throws Exception {

    }

    @Test
    public void testEnableUniqueChk() throws Exception {

    }

    @Test
    public void testGetFailReason() throws Exception {

    }

    @Test
    public void testSetFailReason() throws Exception {

    }

    @Test
    public void testGetRunedCnt() throws Exception {

    }

    @Test
    public void testSetRunedCnt() throws Exception {

    }

    @Test
    public void testIsCron() throws Exception {

    }

    @Test
    public void testSetCron() throws Exception {

    }

    @Test
    public void testEnableCron() throws Exception {

    }

    @Test
    public void testGetAliveTm() throws Exception {

    }

    @Test
    public void testIsForce() throws Exception {

    }

    @Test
    public void testIsNewTask() throws Exception {

    }

    @Test
    public void testSetNewTask() throws Exception {

    }

    @Test
    public void testEnableNew() throws Exception {

    }

    @Test
    public void testEnableDbNew() throws Exception {

    }

    @Test
    public void testSetForce() throws Exception {

    }

    @Test
    public void testGetCronExpression() throws Exception {

    }

    @Test
    public void testSetCronExpression() throws Exception {

    }

    @Test
    public void testSetAliveTm() throws Exception {

    }

    @Test
    public void testIsMutex() throws Exception {

    }

    @Test
    public void testGetStatus() throws Exception {

    }

    @Test
    public void testSetStatus() throws Exception {

    }

    @Test
    public void testGetTid() throws Exception {

    }

    @Test
    public void testSetTid() throws Exception {

    }

    @Test
    public void testGetTkey() throws Exception {

    }

    @Test
    public void testSetTkey() throws Exception {

    }

    @Test
    public void testSetDelayDelay() throws Exception {

    }

    @Test
    public void testSetIntervalInterval() throws Exception {

    }

    @Test
    public void testSetMaxTimeMaxTime() throws Exception {

    }

    @Test
    public void testGetMutex() throws Exception {

    }

    @Test
    public void testSetMutex() throws Exception {

    }

    @Test
    public void testGetMutexTm() throws Exception {

    }

    @Test
    public void testSetMutexTm() throws Exception {

    }

    @Test
    public void testIsForever() throws Exception {

    }

    @Test
    public void testIsHasParam() throws Exception {

    }

    @Test
    public void testSetHasParam() throws Exception {

    }

    @Test
    public void testEnableParam() throws Exception {

    }

    @Test
    public void testGetParam() throws Exception {

    }

    @Test
    public void testGetType() throws Exception {

    }

    @Test
    public void testSetType() throws Exception {

    }

    @Test
    public void testSetParam() throws Exception {

    }

    @Test
    public void testSetForever() throws Exception {

    }

    @Test
    public void testEnableForever() throws Exception {

    }

    @Test
    public void testGetDelay() throws Exception {

    }

    @Test
    public void testGetInterval() throws Exception {

    }

    @Test
    public void testGetMaxTime() throws Exception {

    }

    @Test
    public void testGetService() throws Exception {

    }

    @Test
    public void testSetService() throws Exception {

    }

    @Test
    public void testIsHasCond() throws Exception {

    }

    @Test
    public void testSetHasCond() throws Exception {

    }

    @Test
    public void testEnableCond() throws Exception {

    }

    @Test
    public void testDisableCond() throws Exception {

    }

    @Test
    public void testIsHasCnt() throws Exception {

    }

    @Test
    public void testSetHasCnt() throws Exception {

    }

    @Test
    public void testEnableLimit() throws Exception {

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
