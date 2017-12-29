package com.atjl.validate.validator.param; 

import com.atjl.util.common.SystemUtil;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.ValidateFormFactory;
import com.atjl.validate.form.ReqEg;
import com.atjl.validate.validator.param.form.FormRequireIfExist;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-service.xml"})
public class RequireIfExistTest { 

	@Test
	public void testValidate() throws Exception {
		
		ValidateForm f = ValidateFormFactory.build(FormRequireIfExist.class);
//        RequireWith requiredWith = new RequireWith(CollectionUtil.newSet("f1", "f2"));
		
		ReqEg r = new ReqEg();
//		r.setF1("vv@v.v");
		// vvv bbb
		r.setF1("vvv");
		r.setF1("bbb");
//		r.setF3("vvv");
		f.setValue(r);
		
		boolean res = f.validate();
		if (res) {
			System.out.println("res:" + res);
		} else {
			String msg = f.getOneLineError();
			System.out.println("res:" + res + ",msg:" + msg);
		}
		 
	}
	
	
	
	
	
	/** 
	 * 
	 * Method: parse(String str) 
	 * 
	 */ 
	@Test
	public void testParse() throws Exception { 
		 
	} 


    @BeforeClass
    public static void beforeClass() throws Exception {

        String dir = System.getProperty("user.dir");
        System.out.println("now " + dir);
        String config = dir.substring(0, dir.lastIndexOf("\\")) + "\\config";
        System.out.println("config " + config);
        SystemUtil.addClasspath(config);
    }
    
    @Before
	public void before() throws Exception { 
	} 

	@After
	public void after() throws Exception { 
	}
} 
