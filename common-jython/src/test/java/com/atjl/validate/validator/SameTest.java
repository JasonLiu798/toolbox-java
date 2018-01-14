package com.atjl.validate.validator;

import com.atjl.validate.api.field.ValidateField;
import com.atjl.validate.form.BaseForm;
import com.atjl.validate.form.FormEg;
import com.atjl.validate.validator.param.Same;
import org.junit.*;
import org.junit.rules.ExpectedException;


public class SameTest {


    @Test
    public void testValidate() throws Exception {
        Same s = new Same("f1", "ffff");
        BaseForm bf = new BaseForm(FormEg.class);
        ValidateField f2 = bf.getField("f2");
        ValidateField f1 = bf.getField("f1");
        f1.setRawValue("aa");
        f2.setRawValue("aaa");
        s.validate(bf, f2);
    }


    @Test
    public void testInit() throws Exception { 
                /* 
                try { 
                   Method method = Same.getClass().getMethod("init", String.class, String.class, String.class, boolean.class); 
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
                   Method method = Same.getClass().getMethod("chk"); 
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
