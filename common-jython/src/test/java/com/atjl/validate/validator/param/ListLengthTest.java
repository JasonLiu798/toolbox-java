package com.atjl.validate.validator.param; 

import com.atjl.util.common.SystemUtil;
import com.atjl.validate.api.ValidateForm;
import com.atjl.validate.api.ValidateFormFactory;
import com.atjl.validate.validator.param.form.FormListLength;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-service.xml"})
public class ListLengthTest { 

	/** 
	 * 
	 * Method: chk() 
	 * 
	 */ 
	@Test
	public void testChk() throws Exception {
		
		ValidateForm f = ValidateFormFactory.build(FormListLength.class);
		Map<String, Object> param = new HashMap<>();
		
		List<Map<String, Object>> l = new ArrayList<>();
		Map<String, Object> inner = new HashMap<>();
		l.add(inner);
//		inner = new HashMap<>();
//		l.add(inner);
		inner = new HashMap<>();
		l.add(inner);
		
		param.put("f1", l);
		
		
		f.setValue(param);
		if (!f.validate()) {
			System.out.println("check fail:" + f.getErrors());
		} else {
			System.out.println("check pass");
		}
	} 
	/** 
	 * 
	 * Method: validate(ValidateForm form, ValidateField field) 
	 * 
	 */ 
	@Test
	public void testValidate() throws Exception { 
		 
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
