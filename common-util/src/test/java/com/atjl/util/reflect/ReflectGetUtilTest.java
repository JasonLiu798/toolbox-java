package com.atjl.util.reflect;

import com.atjl.util.character.StringCheckUtil;
import org.junit.*;
import org.junit.rules.ExpectedException;


public class ReflectGetUtilTest {


    @Test
    public void testGetterForce() throws Exception {
        Object res = ReflectGetUtil.getterForceClz(StringCheckUtil.class, "NULL");
        System.out.println(res);
    }

    @Test
    public void testGetterForceForObjFieldName() throws Exception {

    }

    @Test
    public void testGetterForceForClzObjFieldName() throws Exception {

    }

    @Test
    public void testGetter() throws Exception {

    }

    @Test
    public void testGetterInner() throws Exception {

    }

    @Test
    public void testGetGetterMethod() throws Exception {

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
