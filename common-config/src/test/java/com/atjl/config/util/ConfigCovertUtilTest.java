package com.atjl.config.util;

import org.junit.*;
import org.junit.rules.ExpectedException;

import java.lang.reflect.Array;
import java.util.Arrays;


public class ConfigCovertUtilTest {

    @Test
    public void testCovert() throws Exception {

        String raw = "123";
        Long res = ConfigCovertUtil.covert(raw, Long.class);
        System.out.println("res:" + res);

    }

    @Test
    public void testCovertArr() throws Exception {
//        String[] raw = {"","213"};
//        String raw = "123,234";
        String raw = "";
        String[] res = ConfigCovertUtil.covert(raw,String[].class);
        System.out.println("res:"+ Arrays.toString(res));
//        System.out.println("res:" + res);

    }

    @Test
    public void testSimple() throws Exception {
        String sname = Long.class.getSimpleName();
        System.out.println("res:" + sname);
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
