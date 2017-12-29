package com.atjl.validate.validator.base;

import com.atjl.validate.validator.noparam.Alpha;
import org.junit.*;
import org.junit.rules.ExpectedException;


public class ValidatorBaseTest {


    @Test
    public void testEquals() throws Exception {

        String msg = "asdf";
        Alpha a = new Alpha(msg);
        Alpha b = new Alpha("aaa");
        boolean res = a.equals(b);
        System.out.println("ha:" + a.hashCode());
        System.out.println("hb:" + b.hashCode());
        System.out.println("str:" + msg.hashCode());
        System.out.println("res:" + res);
    }

    @Test
    public void testHashCode() throws Exception {

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
