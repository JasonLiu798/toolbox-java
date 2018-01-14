package com.atjl.validate.api;

import com.atjl.validate.form.ref.FormRefEg;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.HashMap;
import java.util.Map;


public class FormFieldTest {


    @Test
    public void testIsRefForm() throws Exception {
        ValidateForm form = ValidateFormFactory.build(FormRefEg.class);

        Map<String, Object> inner1 = new HashMap<>();
        inner1.put("f10", "sdf@sd");
        inner1.put("f11", "dkdd");

        Map<String, Object> inner = new HashMap<>();
        inner.put("f3", "sdf@sdfjk");
        inner.put("f4", inner1);

        Map<String, Object> values = new HashMap<>();
        values.put("f1", "aaa@aa.jdkdf");
        values.put("f2", inner);

        form.setValue(values);
        boolean res = form.validate();

        System.out.println("res:" + res);
        System.out.println("msg:" + form.getErrors());
    }

    @Test
    public void testGetRefForm() throws Exception {

    }


    @Test
    public void testInit() throws Exception { 
                /* 
                try { 
                   Method method = FormField.getClass().getMethod("init", Class.class); 
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
