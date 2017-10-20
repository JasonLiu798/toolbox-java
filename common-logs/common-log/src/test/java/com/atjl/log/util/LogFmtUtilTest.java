package com.atjl.log.util;

import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;


public class LogFmtUtilTest {


    @Test
    public void testFormatForLocateException() throws Exception {
        String call = "Log.test(Log.java:15)";
        String raw = "com.atjl.log.api."+call;
        System.out.println("raw:" + raw + ",len:" + raw.length());
        System.out.println("calllen " + call.length());

//        String res = LogFmtUtil.simplifyStack(raw, 29);
//        String res = LogFmtUtil.simplifyStack(raw, 30);

        String res = LogFmtUtil.simplifyStack("com.abc."+call,21);
        System.out.println("res:" + res+",reslen:"+res.length());


    }

    @Test
    public void testSimplifyFullClassName(){
        String call = "Log.tst(Log.java:15)";//20
        assertEquals("c.a."+call,LogFmtUtil.simplifyStack("com.abc."+call,21));
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
