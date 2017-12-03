package com.atjl.dbservice.manager;

import com.atjl.dbservice.domain.DbTableTransferConfig;
import com.atjl.util.common.SystemUtil;
import com.atjl.util.json.JSONFastJsonUtil;
import com.atjl.util.json.JSONFmtUtil;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-service.xml"})
public class RawTableDataGetManagerTest {


    @Resource
    private RawTableDataGetManager rawTableDataGetManager;

    @Test
    public void testGetData() throws Exception {
        long t = System.currentTimeMillis();
        try {

            DbTableTransferConfig config = new DbTableTransferConfig();

            Map<String, String> pkmap = new HashMap<>();
            pkmap.put("month_code", "ORG_TM_RAW");
            pkmap.put("area_code", "ORG_CODE_RAW");
            config.setPkFieldMapping(pkmap);

            Map<String, String> fmap = new HashMap<>();
            fmap.put("area_name", "ORG_NAME_RAW");

            config.setFieldMapping(fmap);

            Map<String, String> jmap = new HashMap<>();
            jmap.put("shou_mont", "shouMont");
			jmap.put("dept_count", "deptCount");
			jmap.put("emp_count","empCount");
            config.setJsonFieldMapping(jmap);


            config.setRawTable("bie_fact_audit_idx_sum");

            List<Map> res = rawTableDataGetManager.getData(config);
            System.out.println("res: succ " + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(res)));
        } catch (Exception e) {
            System.out.println("res: error " + e);
            e.printStackTrace();
        }
        long cost = System.currentTimeMillis() - t;
        System.out.println("cost:" + cost);
    }


    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
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

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();
} 
