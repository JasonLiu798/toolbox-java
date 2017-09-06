package com.atjl.validate.util;

import com.atjl.validate.api.Validator;
import com.atjl.validate.validator.noparam.Email;
import com.atjl.validate.validator.noparam.IsBoolean;
import org.junit.*;
import org.junit.rules.ExpectedException;


public class ValidateParseUtilTest {


    @Test
    public void testSimpleParse() throws Exception {
//        Validator v = ValidateParseUtil.simpleParse(Email.class, "email");
        Validator v = ValidateParseUtil.simpleParse(IsBoolean.class, "boolean");

        System.out.println("res:" + v);
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
