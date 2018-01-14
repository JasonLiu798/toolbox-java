package com.atjl.dbservice.domain; 

import com.atjl.dbservice.api.domain.DataCpConfig;
import com.atjl.eg.DataTestUtil;
import com.atjl.util.common.SystemUtil;
import com.atjl.util.json.JSONFastJsonUtil;
import com.atjl.util.json.JSONFmtUtil;
import org.junit.*;

import java.util.List;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:test-service.xml"})
public class DbTableTransferConfigTest { 

	@Test
	public void testGetTgtPkFields() throws Exception { 
		long t = System.currentTimeMillis();
		try {
			Object res = null;
			
			DataCpConfig config = DataTestUtil.getConfig();
			List l = config.getAllTgtSortFields();
			System.out.println("res: succ" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(l)));
		} catch (Exception e) {
			System.out.println("res: error " + e);
			e.printStackTrace();
		}
		long cost = System.currentTimeMillis() - t;
		System.out.println("res: cost " + cost);		 
	} 
	@Test
	public void testGetPkFieldRandomOne() throws Exception { 
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
	public void testGetAllTgtSortFields() throws Exception { 
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
	public void testGetAllRawFieldsStr() throws Exception { 
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
	public void testField2string() throws Exception { 
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
	public void testGetTgtTablePk() throws Exception { 
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
	public void testSetTgtTablePk() throws Exception { 
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
	public void testGetJsonFieldMapping() throws Exception { 
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
	public void testSetJsonFieldMapping() throws Exception { 
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
	public void testGetRawDataValidator() throws Exception { 
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
	public void testSetRawDataValidator() throws Exception { 
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
	public void testGetOtherCond() throws Exception { 
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
	public void testSetOtherCond() throws Exception { 
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
	public void testGetFieldMapping() throws Exception { 
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
	public void testSetFieldMapping() throws Exception { 
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
	public void testGetPkFieldMapping() throws Exception { 
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
	public void testSetPkFieldMapping() throws Exception { 
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
	public void testGetRawTable() throws Exception { 
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
	public void testSetRawTable() throws Exception { 
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
	public void testGetJsonField() throws Exception { 
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
	public void testSetJsonField() throws Exception { 
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
	public void testGetTgtTable() throws Exception { 
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
	public void testSetTgtTable() throws Exception { 
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
