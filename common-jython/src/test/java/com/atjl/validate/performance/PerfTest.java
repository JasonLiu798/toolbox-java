package com.atjl.validate.performance;

import com.atjl.util.performance.RunTimeUtil;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.ValidateFormFactory;
import com.atjl.validate.form.ref.FormListEg;
import com.atjl.validate.form.ref.FormRefEg;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PerfTest {


    @Test
    public void testIsRefForm() {
        for (int i = 0; i < 10000; i++) {
            RunTimeUtil.addTime();
            ValidateForm form = ValidateFormFactory.build(FormListEg.class);

            Map<String, Object> inner1 = new HashMap<>();
            inner1.put("f3", "sdf@sd.com");
            inner1.put("f4", "dkdd");

            Map<String, Object> inner = new HashMap<>();
            inner.put("f3", "sdf@sdfjk");
            inner.put("f4", "aaaaa");

            List l = new ArrayList();
            l.add(inner);
            l.add(inner1);

            Map<String, Object> values = new HashMap<>();
            values.put("f1", "aaa@aa.jdkdf");
            values.put("f2", l);

            form.setValue(values);
            boolean res = form.validate();

//            RunTimeUtil.addTime();
//            System.out.println("res:" + res);
//            System.out.println("msg:" + form.getErrors());


            ValidateForm form1 = ValidateFormFactory.build(FormRefEg.class);

            Map<String, Object> inner11 = new HashMap<>();
            inner1.put("f10", "sdf@sd");
            inner1.put("f11", "dkdd");

            Map<String, Object> inner12 = new HashMap<>();
            inner.put("f3", "sdf@sdfjk");
            inner.put("f4", inner11);

            Map<String, Object> values1 = new HashMap<>();
            values.put("f1", "aaa@aa.jdkdf");
            values.put("f2", inner12);

            form1.setValue(values1);
            res = form.validate();

//            System.out.println("res:" + res);
//            System.out.println("msg:" + form.getErrors());
        }
        String res = RunTimeUtil.getFmtTimeForStdout();
        System.out.println("res:" + res);

        /**
         * nocache
         * 10000
         * res:total cost: 14131 ms,default
         * res:total cost: 12801 ms,default
         * res:total cost: 10577 ms,no res output
         * cache
         * 10000
         * res:total cost: 5005 ms
         * res:total cost: 4164 ms,no res output
         */

    }

    @Test
    public void testGetRefForm() throws Exception {

    }


    @Test
    public void testInit() throws Exception { 
                /* 
                try { 
                   Method method = ListField.getClass().getMethod("init", Class.class); 
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
