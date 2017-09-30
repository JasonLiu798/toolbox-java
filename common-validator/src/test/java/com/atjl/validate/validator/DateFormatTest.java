package com.atjl.validate.validator;

import com.atjl.validate.api.field.StringField;
import com.atjl.validate.validator.param.DateFormat;
import org.junit.*;
import org.junit.rules.ExpectedException;


public class DateFormatTest {


    @Test
    public void testValidate() throws Exception {
        DateFormat df = new DateFormat("yyyy-MM-dd");

        StringField f = new StringField("aa");
        f.setRawValue("123343-33");
        df.validate(null, f);
    }


    @Test
    public void testInit() throws Exception { 
                /* 
                try { 
                   Method method = DateFormat.getClass().getMethod("init", String.class, String.class, boolean.class); 
                   method.setAccessible(true); 
                   method.invoke(<Object>, <Parameters>); 
                } catch(NoSuchMethodException e) { 
                } catch(IllegalAccessException e) { 
                } catch(InvocationTargetException e) { 
                } 
                */
    }

    @Test
    public void testChk() throws Exception { 
                /* 
                try { 
                   Method method = DateFormat.getClass().getMethod("chk"); 
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
