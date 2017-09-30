package com.atjl.validate.validator.param;

import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.ValidateFormFactory;
import com.atjl.validate.form.ReqEg;
import org.junit.*;
import org.junit.rules.ExpectedException;


public class RequireIfTest {

    @Test
    public void testValidate() throws Exception {
        ValidateForm f = ValidateFormFactory.build(FormReqIf.class);
//        RequireWith requiredWith = new RequireWith(CollectionUtil.newSet("f1", "f2"));

        ReqEg r = new ReqEg();
        r.setF1("vv@v.v");
        f.setValue(r);

        boolean res = f.validate();
        if (res) {
            System.out.println("res:" + res);
        } else {
            String msg = f.getOneLineError();
            System.out.println("res:" + res + ",msg:" + msg);
        }
    }

    @Test
    public void testParse() throws Exception {

    }


    @Test
    public void testInit() throws Exception { 
                /* 
                try { 
                   Method method = RequireIf.getClass().getMethod("init", String.class, String.class, String.class, boolean.class); 
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
                   Method method = RequireIf.getClass().getMethod("chk"); 
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
