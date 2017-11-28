package com.atjl.dbfront.service;

import com.atjl.util.common.SystemUtil;
import com.atjl.util.json.JSONFastJsonUtil;
import com.atjl.util.json.JSONFmtUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-service.xml"})
public class ContentServiceTest {
	@Resource
	ContentService contentService;

	@Test
	public void testPrintHtml() throws Exception { 
		long t = System.currentTimeMillis();
		try {
			String res = contentService.getCurIndexContent();
//			System.out.println("res: succ" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(res)));
			System.out.println("res: succ" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(res)));
		} catch (Exception e) {
			System.out.println("res: error " + e);
			e.printStackTrace();
		}
		long cost = System.currentTimeMillis() - t;
		System.out.println("res: cost " + cost);		 
	} 
	@Test
	public void testPrintJs() throws Exception { 
		long t = System.currentTimeMillis();
		try {
			Object res = null;
			
			System.out.println("res: succ" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(res)));
		} catch (Exception e) {
			System.out.println("res: error " + e);
			e.printStackTrace();
		}
		long cost = System.currentTimeMillis() - t;
		System.out.println("res: cost " + cost);		 
	} 
	@Test
	public void testPubHtml() throws Exception { 
		long t = System.currentTimeMillis();
		try {
			Object res = null;
			
			System.out.println("res: succ" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(res)));
		} catch (Exception e) {
			System.out.println("res: error " + e);
			e.printStackTrace();
		}
		long cost = System.currentTimeMillis() - t;
		System.out.println("res: cost " + cost);		 
	} 
	@Test
	public void testUpdateContent() throws Exception { 
		long t = System.currentTimeMillis();
		try {
			Object res = null;
			
			System.out.println("res: succ" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(res)));
		} catch (Exception e) {
			System.out.println("res: error " + e);
			e.printStackTrace();
		}
		long cost = System.currentTimeMillis() - t;
		System.out.println("res: cost " + cost);		 
	} 
	@Test
	public void testUpdateContentObj() throws Exception { 
		long t = System.currentTimeMillis();
		try {
			Object res = null;
			
			System.out.println("res: succ" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(res)));
		} catch (Exception e) {
			System.out.println("res: error " + e);
			e.printStackTrace();
		}
		long cost = System.currentTimeMillis() - t;
		System.out.println("res: cost " + cost);		 
	} 
	@Test
	public void testAddContent() throws Exception { 
		long t = System.currentTimeMillis();
		try {
			Object res = null;
			
			System.out.println("res: succ" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(res)));
		} catch (Exception e) {
			System.out.println("res: error " + e);
			e.printStackTrace();
		}
		long cost = System.currentTimeMillis() - t;
		System.out.println("res: cost " + cost);		 
	} 
	@Test
	public void testAddOrUpdateContent() throws Exception { 
		long t = System.currentTimeMillis();
		try {
			Object res = null;
			
			System.out.println("res: succ" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(res)));
		} catch (Exception e) {
			System.out.println("res: error " + e);
			e.printStackTrace();
		}
		long cost = System.currentTimeMillis() - t;
		System.out.println("res: cost " + cost);		 
	} 
	@Test
	public void testRollBack() throws Exception { 
		long t = System.currentTimeMillis();
		try {
			Object res = null;
			
			System.out.println("res: succ" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(res)));
		} catch (Exception e) {
			System.out.println("res: error " + e);
			e.printStackTrace();
		}
		long cost = System.currentTimeMillis() - t;
		System.out.println("res: cost " + cost);		 
	} 
	@Test
	public void testGetContent() throws Exception { 
		long t = System.currentTimeMillis();
		try {
			Object res = null;
			
			System.out.println("res: succ" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(res)));
		} catch (Exception e) {
			System.out.println("res: error " + e);
			e.printStackTrace();
		}
		long cost = System.currentTimeMillis() - t;
		System.out.println("res: cost " + cost);		 
	} 

	/** 
	 * 
	 * Method: exist(String type, String name, String ver)  
	 */ 
	@Test
	public void testExist() throws Exception { 
	 
				/* 
				try { 
				   Method method = ContentService.getClass().getMethod("exist", String.class, String.class, String.class); 
				   method.setAccessible(true); 
				   method.invoke(<Object>, <Parameters>); 
				} catch(NoSuchMethodException e) { 
				} catch(IllegalAccessException e) { 
				} catch(InvocationTargetException e) { 
				} 
				*/ 
			} 
	/** 
	 * 
	 * Method: backupHtml(String bakVer)  
	 */ 
	@Test
	public void testBackupHtml() throws Exception { 
	 
				/* 
				try { 
				   Method method = ContentService.getClass().getMethod("backupHtml", String.class); 
				   method.setAccessible(true); 
				   method.invoke(<Object>, <Parameters>); 
				} catch(NoSuchMethodException e) { 
				} catch(IllegalAccessException e) { 
				} catch(InvocationTargetException e) { 
				} 
				*/ 
			} 
	/** 
	 * 
	 * Method: getCurIndexContent()  
	 */ 
	@Test
	public void testGetCurIndexContent() throws Exception { 
	 
				/* 
				try { 
				   Method method = ContentService.getClass().getMethod("getCurIndexContent"); 
				   method.setAccessible(true); 
				   method.invoke(<Object>, <Parameters>); 
				} catch(NoSuchMethodException e) { 
				} catch(IllegalAccessException e) { 
				} catch(InvocationTargetException e) { 
				} 
				*/ 
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
