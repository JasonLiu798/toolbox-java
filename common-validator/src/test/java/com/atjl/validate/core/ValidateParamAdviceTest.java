package com.atjl.validate.core;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * ValidateParamAdvice Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>三月 31, 2016</pre>
 */
public class ValidateParamAdviceTest {

    @Before
    public void before() throws Exception {

    }

    @After
    public void after() throws Exception {

    }

    /**
     * Method: doAround(ProceedingJoinPoint jp)
     */
    @Test
    public void testHelper() throws Exception {
        /*
        MobileBindMessage mbm = new MobileBindMessage();
        mbm.setUid(123L);
        mbm.setMobile("15166155221");
        Map<String, Object> paramMap = BeanHelper.getFieldValueMap(mbm);
        for (String key : paramMap.keySet()) {
            System.out.println("key:" + key + ",v:" + paramMap.get(key));
        }
        */
    }

    /**
     * Method: getErrorResult(String msg, int... code)
     */
    @Test
    public void testValidate() throws Exception {
/*
        MobileBindMessage mbm = new MobileBindMessage();
        mbm.setUid(123L);
        mbm.setMobile("15166155221");
        mbm.setIsvest(2);
        Map<String, Object> paramMap = BeanHelper.getFieldValueMap(mbm);

        Map<String,String> keyRule = LoadValidate.getKeyRuleMap();
        LoadValidate.readConfiguration();
        Map<String, ActionValidate> map = LoadValidate.getValidateMap();
        for(String key:map.keySet()){
            System.out.println("key:"+key+",v:"+map.get(key));
        }

        ValidateParamAdvice va = new ValidateParamAdvice();
        Class[] paramArr = {String.class,Map.class};
        Method m = va.getClass().getDeclaredMethod("validate",paramArr);
        m.setAccessible(true);
        String requestURI = "com.juanpi.member.helper.MobileHelper";
        Object res = m.invoke(va, requestURI,paramMap);
        System.out.println("res:"+res);
        */
    }

    /**
     * Method: getPatterns(String regex, String targetStr)
     */
    @Test
    public void testAround() throws Exception {


    }
}
