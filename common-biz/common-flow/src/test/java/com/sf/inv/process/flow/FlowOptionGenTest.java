package com.sf.inv.process.flow;

import com.sf.inv.process.core.FlowOptionGen;
import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;


public class FlowOptionGenTest {

    @Test
    public void testGetNormalOption() throws Exception {
        FlowOptionGen gen = new FlowOptionGen();
        int option = gen.reset().enableChangeNextFlowId().enableErrorCodeContinue().enableExceptionContinue().get();
        assertEquals(0x2 | 0x4 | 0x8, option);

        option = gen.reset().enableErrorCodeContinue().enableExceptionContinue().get();
        assertEquals(0x2 | 0x4, option);

        option = gen.reset().enableExceptionContinue().get();
        assertEquals(0x2, option);

        option = gen.reset().enableErrorCodeContinue().get();
        assertEquals(0x4, option);

        option = gen.reset().enableChangeNextFlowId().get();
        assertEquals(0x8, option);
    }

    @Test
    public void testGetOption() throws Exception {
    }

    @Test
    public void testSetOption() throws Exception {

    }

    @Test
    public void testEnableErrorCodeContinue() throws Exception {

    }

    @Test
    public void testEnableExceptionContinue() throws Exception {

    }

    @Test
    public void testEnableChangeNextFlowId() throws Exception {

    }

    @Test
    public void testIsEnableErrorCodeContinue() throws Exception {

    }

    @Test
    public void testIsEnableExceptionContinue() throws Exception {

    }

    @Test
    public void testIsEnableChangeNextFlowId() throws Exception {

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
