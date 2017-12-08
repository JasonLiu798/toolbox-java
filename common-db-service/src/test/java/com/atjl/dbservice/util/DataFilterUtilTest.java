package com.atjl.dbservice.util;

import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.common.SystemUtil;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.HashMap;
import java.util.Map;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:test-service.xml"})
public class DataFilterUtilTest {


    @Test
    public void testCanUpdate() throws Exception {
        long t = System.currentTimeMillis();
        try {

            Map<String, String> raw = CollectionUtil.newMap("load_tm", "2017");
            Map<String, String> tgt = CollectionUtil.newMap("LOAD_TM", "2018");

//            boolean res = DataFilterUtil.canUpdate(raw, tgt, DataTestUtil.getConfig());
//            System.out.println("res: succ " + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(res)));
        } catch (Exception e) {
            System.out.println("res: error " + e);
            e.printStackTrace();
        }
        long cost = System.currentTimeMillis() - t;
        System.out.println("cost:" + cost);
    }


    @Test
    public void testIsAllEqual() throws Exception {
        long t = System.currentTimeMillis();
        try {

            Map<String, String> raw = CollectionUtil.newMap("year_code", "2017");
            raw.put("area_code", "xxx");
            raw.put("risk_id", "1080");
//1171, ORG_TM_RAW=2017, ORG_CODE_RAW=010Y, RISK_MONITOR_ID=46695, MONITOR_TYPE=M, IS_MODIFY=N, LOAD_TM=2017-12-04 19:36:21}

            Map<String, String> tgt = CollectionUtil.newMap("ORG_TM_RAW", "2017");
            tgt.put("ORG_CODE_RAW", "xxx");
            tgt.put("RISK_ID_RAW", "1080");

            Map<String, String> pkmap = new HashMap<>();
            pkmap.put("year_code", "ORG_TM_RAW");
            pkmap.put("area_code", "ORG_CODE_RAW");
            pkmap.put("risk_id", "RISK_ID_RAW");

            boolean res = DataFilterUtil.isAllEqual(pkmap, raw, tgt);
            System.out.println("res:" + res);

//            boolean res = DataFilterUtil.canUpdate(raw, tgt, DataTestUtil.getConfig());
//            System.out.println("res: succ " + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(res)));
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

        String dir = System.getProperty("user.dir");
        System.out.println("now " + dir);
        String config = dir.substring(0, dir.lastIndexOf("\\")) + "\\config";
        System.out.println("config " + config);
        SystemUtil.addClasspath(config);
    }

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();
} 
