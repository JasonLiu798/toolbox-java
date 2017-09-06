package com.atjl.validate.validator;

import com.atjl.validate.api.StringField;
import com.atjl.validate.validator.noparam.Url;
import org.junit.*;
import org.junit.rules.ExpectedException;


public class UrlTest {


    @Test
    public void testValidate() throws Exception {
        Url u = new Url();
        StringField s = new StringField("aa");
        s.setRawValue("sdf://sdf");
        s.setRawValue("sdf://aaa:23");
        u.validate(null, s);

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
