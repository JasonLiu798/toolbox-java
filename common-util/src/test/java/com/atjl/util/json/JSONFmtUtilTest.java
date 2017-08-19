package com.atjl.util.json;

import org.junit.*;
import org.junit.rules.ExpectedException;


public class JSONFmtUtilTest {


    @Test
    public void testFormatJson() throws Exception {
        String raw = "";
        String res = JSONFmtUtil.formatJsonConsole(raw);
        System.out.println("res:" + res);

    }


    @Test
    public void testAddIndentBlank() throws Exception { 
                /* 
                try { 
                   Method method = JSONFmtUtil.getClass().getMethod("addIndentBlank", StringBuilder.class, int.class); 
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
