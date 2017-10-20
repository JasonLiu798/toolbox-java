package com.atjl.util.thread;

import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;


public class ThreadInnerUtilTest {


    @Test
    public void testGetName() throws Exception {
        String param = "123,sdjkfls";
        String name = ThreadInnerUtil.getName(param);
        assertEquals(name, "123");
        name = ThreadInnerUtil.getName("123,");
        assertEquals(name, "123");
        name = ThreadInnerUtil.getName("123");
        assertEquals(name, "");
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
