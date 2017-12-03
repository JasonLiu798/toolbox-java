package com.atjl.dbservice.manager; 

import com.atjl.dbservice.domain.SeparatedDatas;
import com.atjl.dbservice.domain.TgtTableDataPkg;
import com.atjl.util.common.SystemUtil;
import com.atjl.util.json.JSONFastJsonUtil;
import com.atjl.util.json.JSONFmtUtil;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-service.xml"})
public class DataImportManagerTest { 

	@Resource
	private DataImportManager dataImportManager;
	@Resource
	private TgtTableDataManager tgtTableDataManager;
	@Resource
	private RawTableDataGetManager rawTableDataGetManager;
	
	
	@Test
	public void testInsert() throws Exception { 
		long t = System.currentTimeMillis();
		try {
			List<Map> l = rawTableDataGetManager.getData(DataTestUtil.getConfig());
			System.out.println("res: bf" +l.size()+","+ JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(l)));
//            List<Map> l2 = tgtTableDataManager.getTgtData(l, DataTestUtil.getConfig());
			SeparatedDatas l2 = tgtTableDataManager.separate(l, DataTestUtil.getConfig());
			System.out.println("res: sep " + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(l2)));
			
//			List res1 = tgtTableDataManager.tgtDataGenInsert(l2.getExistDatas(), DataTestUtil.getConfig());
//			List res2 = tgtTableDataManager.tgtDataGenInsert(l2.getNotExistDatas(), DataTestUtil.getConfig());
			
//			System.out.println("res: succ e " + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(res1)));
//			System.out.println("res: succ ne " + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(res2)));
			
//			TgtTableDataPkg pkg = new TgtTableDataPkg();
//			pkg.setDatas(res2);
//			pkg.setFields(DataTestUtil.getConfig().getAllTgtSortFields());
			
//			dataImportManager.insert(pkg, DataTestUtil.getConfig());
			System.out.println("res: succ" );
//			System.out.println("res: succ" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson()));
		} catch (Exception e) {
			System.out.println("res: error " + e);
			e.printStackTrace();
		}
		long cost = System.currentTimeMillis() - t;
		System.out.println("res: cost " + cost);		 
	}
	
	
	@BeforeClass
	public static void beforeClass() throws Exception {
    /*
        String dir = System.getProperty("user.dir");
        System.out.println("now " + dir);
        String config = dir.substring(0, dir.lastIndexOf("\\")) + "\\config";
        System.out.println("config " + config);
        */
		SystemUtil.addClasspath("D:\\project\\java\\aos\\config");
	}
    
    @Before
	public void before() throws Exception { 
	} 

	@After
	public void after() throws Exception { 
	}
} 
