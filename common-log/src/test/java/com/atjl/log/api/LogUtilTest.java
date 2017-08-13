package com.atjl.log.api;

import org.junit.*;
import org.junit.rules.ExpectedException;


public class LogUtilTest {


    @Test
    public void testSetLevel() throws Exception {


        LogUtil.setLevel(LogLevel.DEBUG);
        LogUtil.d("test {} hahah ", "ssss", "dfdf");

    }

    @Test
    public void testGetLevel() throws Exception {

    }

    @Test
    public void testEnable() throws Exception {

    }

    @Test
    public void testDisable() throws Exception {

    }

    @Test
    public void testIsDebugEnable() throws Exception {

    }

    @Test
    public void testIsInfoEnable() throws Exception {

    }

    @Test
    public void testIsWarnEnable() throws Exception {

    }

    @Test
    public void testIsErrorEnable() throws Exception {

    }

    @Test
    public void testD() throws Exception {

    }

    @Test
    public void testWriteDebugForModuleContent() throws Exception {

    }

    @Test
    public void testWriteDebugForOpModuleContent() throws Exception {

    }

    @Test
    public void testWriteInfoForOpModuleContent() throws Exception {

    }

    @Test
    public void testWriteInfoForOpModuleContentCostTm() throws Exception {

    }

    @Test
    public void testWriteInfoForOpModuleContentParamResCostTm() throws Exception {

    }

    @Test
    public void testWriteWarn() throws Exception {

    }

    @Test
    public void testWriteErrorForOpModuleRefE() throws Exception {

    }

    @Test
    public void testWriteErrorForClzRefE() throws Exception {

    }

    @Test
    public void testWriteErrorSync() throws Exception {

    }

    @Test
    public void testWriteInfoSync() throws Exception {

    }

    @Test
    public void testWriteErrorForOpModuleRef() throws Exception {

    }

    @Test
    public void testWriteErrorForOpModuleContentRef() throws Exception {

    }

    @Test
    public void testWriteInfoRaw() throws Exception {

    }

    @Test
    public void testWriteDebugRaw() throws Exception {

    }

    @Test
    public void testWriteErrorRaw() throws Exception {

    }

    @Test
    public void testWriteLogRaw() throws Exception {

    }

    @Test
    public void testWriteLogRawSync() throws Exception {

    }

    @Test
    public void testGetStackTrace() throws Exception {

    }

    @Test
    public void testInfo() throws Exception {

    }


    @Test
    public void testGetStackAndMsg() throws Exception { 
                /* 
                try { 
                   Method method = LogUtil.getClass().getMethod("getStackAndMsg", String.class, int.class);
                   method.setAccessible(true); 
                   method.invoke(<Object>, <Parameters>); 
                } catch(NoSuchMethodException e) { 
                } catch(IllegalAccessException e) { 
                } catch(InvocationTargetException e) { 
                } 
                */
    }

    @Test
    public void testGetStack() throws Exception { 
                /* 
                try { 
                   Method method = LogUtil.getClass().getMethod("getStack", int.class);
                   method.setAccessible(true); 
                   method.invoke(<Object>, <Parameters>); 
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
