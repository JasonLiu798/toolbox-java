package com.atjl.validate.form;

import com.atjl.validate.api.ValidateFormFactory;
import com.atjl.validate.api.ValidateForm;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.HashMap;
import java.util.Map;


public class BaseFormTest {


    @Test
    public void testInit() throws Exception {
        ValidateForm form = ValidateFormFactory.build(FormEg.class);

        Map<String, String> values = new HashMap<>();
        values.put("f1", "aaa@aa");
        values.put("f2", null);

        form.setValue(values);
        boolean res = form.validate();

        System.out.println("res:" + res);
        System.out.println("msg:" + form.getErrors());
    }

    @Test
    public void testSetValue() throws Exception {
        ValidateForm form = BaseForm.newForm(FormEg.class);

        ReqEg req = new ReqEg();
        req.setF1(null);//"aaasdfds");
//        req.setF1(null);//"aaasdfds");
        req.setF2("1234");
        form.setValue(req);

        boolean res = form.validate();
        System.out.println("res:" + res);
        System.out.println("msg:" + form.getErrors());
    }

    @Test
    public void testValidate() throws Exception {

    }

    @Test
    public void testGetErrors() throws Exception {

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
