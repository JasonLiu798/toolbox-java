package com.atjl.logdb.api;

import com.atjl.log.api.LogUtil;
import com.atjl.logdb.api.domain.LogOpType;
import org.junit.*;
import org.junit.rules.ExpectedException;


public class LogDbUtilTest {


    @Test
    public void testEnvDev() throws Exception {
        LogUtil.envDev();
        LogDbUtil.envDev();

        LogDbUtil.debug("testModule", "hahahha");

//    public static void debug(String opModule, String opChildModule, String opType, String param, String res, String content, String ref, String empNum, Long reqIp, Long cost, int omitLevel) {
        LogDbUtil.debug("testModule", "hahahha", LogOpType.OTHER.toString(), null, null, "cccc", "ref", "34234", 123123L, null, 1);

    }

    @Test
    public void testEnvPrd() throws Exception {

    }

    @Test
    public void testIsDebugEnabled() throws Exception {

    }

    @Test
    public void testIsInfoEnabled() throws Exception {

    }

    @Test
    public void testIsWarnEnabled() throws Exception {
        LogDbUtil.warn("testModule", "hahahha");

    }

    @Test
    public void testIsErrorEnabled() throws Exception {
        LogUtil.envDev();
        LogDbUtil.envDev();

        LogDbUtil.error("aaa", "dfsd", new RuntimeException());

        /**
         *     public static void error(String opModule, String opChildModule, String opType, String param, String res, String content, String ref, String empNum, Long reqIp, Long cost, int omitLevel) 
         */
        LogDbUtil.error("aaa", "dfsd", LogOpType.CREATE.toString(), null, null, "ccc", null, "234234", 2132L, null, 1);
    }


    @Test
    public void testDebugForOpModuleContent() throws Exception {

    }

    @Test
    public void testDebugForOpModuleOpChildModuleOpTypeParamResContentRefEmpNumReqIpCostOmitLevel() throws Exception {

    }

    @Test
    public void testInfoForOpModuleContent() throws Exception {

    }

    @Test
    public void testInfoForOpModuleContentCostTm() throws Exception {

    }

    @Test
    public void testInfoForOpModuleContentParamResCostTm() throws Exception {

    }

    @Test
    public void testInfoForOpModuleOpChildModuleOpTypeParamResContentRefEmpNumReqIpCostOmitLevel() throws Exception {

    }

    @Test
    public void testWarn() throws Exception {

    }

    @Test
    public void testErrorForClzRefE() throws Exception {

    }

    @Test
    public void testErrorForOpModuleRef() throws Exception {

    }

    @Test
    public void testErrorForOpModuleRefE() throws Exception {

    }

    @Test
    public void testErrorForOpModuleContentRef() throws Exception {

    }

    @Test
    public void testErrorForOpModuleOpChildModuleOpTypeParamResContentRefEmpNumReqIpCostOmitLevel() throws Exception {

    }

    @Test
    public void testWriteLogAsyncInner() throws Exception {

    }

    @Test
    public void testErrorSync() throws Exception {

    }

    @Test
    public void testInfoSync() throws Exception {

    }

    @Test
    public void testWriteLogRawSyncInner() throws Exception {

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
