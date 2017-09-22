package com.atjl.validate.validator.multiparam; 

import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.ValidateFormFactory;
import com.atjl.validate.form.ReqEg;
import org.junit.*;
import org.junit.rules.ExpectedException;


public class RequireWithOutAllTest {


    
    @Test
    public void testValidate() throws Exception {
        ValidateForm f = ValidateFormFactory.build(FormReqWithOutAll.class);
//        RequireWith requiredWith = new RequireWith(CollectionUtil.newSet("f1", "f2"));

        ReqEg r = new ReqEg();
//        r.setF1("ss@sdkfsd.djkf");
//        r.setF2("ss@sdkfsd.djkf");
        f.setValue(r);

        boolean res = f.validate();
        String msg = f.getOneLineError();
        System.out.println("res:" + res + ",msg:" + msg);
    }
    
    @Test
    public void testParse() throws Exception { 
        
    }
    
        
    @Before
    public void before() throws Exception { 
    } 

    @After
    public void after() throws Exception { 
    }
    
    @BeforeClass
    public static void beforeClass() throws Exception{
        
    }
    
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();
} 
