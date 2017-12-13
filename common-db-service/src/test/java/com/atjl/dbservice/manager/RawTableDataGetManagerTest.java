package com.atjl.dbservice.manager;

import com.atjl.dbservice.api.domain.SearchCondBase;
import com.atjl.eg.DataTestUtil;
import com.atjl.util.common.DateUtil;
import com.atjl.util.common.SystemUtil;
import com.atjl.util.json.JSONFastJsonUtil;
import com.atjl.util.json.JSONFmtUtil;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-service.xml"})
public class RawTableDataGetManagerTest {


    @Resource
    private RawTableDataGetManager rawTableDataGetManager;

    @Test
    public void testCount() {
        long t = System.currentTimeMillis();
        try {
            // 213条 ， succ 209
            SearchCondBase cond = new SearchCondBase();
            String st = DateUtil.format(DateUtil.getDate(-20), DateUtil.yyyy_MM_dd_HH_mm_ss_EN);
            String et = DateUtil.format(DateUtil.getDate(0), DateUtil.yyyy_MM_dd_HH_mm_ss_EN);
            cond.setStartLoadTm(st);
            cond.setEndLoadTm(et);
            int l = rawTableDataGetManager.getCount(DataTestUtil.getConfig(), cond);
            System.out.println("res: succ " + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(l)));
        } catch (Exception e) {
            System.out.println("res: error " + e);
            e.printStackTrace();
        }
        long cost = System.currentTimeMillis() - t;
        System.out.println("cost:" + cost);
    }

    @Test
    public void testGetData() throws Exception {
        long t = System.currentTimeMillis();
        try {
            SearchCondBase cond = new SearchCondBase();
            String st = DateUtil.format(DateUtil.getDate(-20), DateUtil.yyyy_MM_dd_HH_mm_ss_EN);
            String et = DateUtil.format(DateUtil.getDate(0), DateUtil.yyyy_MM_dd_HH_mm_ss_EN);
            cond.setStartLoadTm(st);
            cond.setEndLoadTm(et);
            List<Map> l = rawTableDataGetManager.getData(DataTestUtil.getConfig(), cond);
            System.out.println("res: succ " + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(l)));
        } catch (Exception e) {
            System.out.println("res: error " + e);
            e.printStackTrace();
        }
        long cost = System.currentTimeMillis() - t;
        System.out.println("cost:" + cost);
    }


    @Test
    public void testCovCount() {
        long t = System.currentTimeMillis();
        try {
            // 213条 ， succ 209
            SearchCondBase cond = new SearchCondBase();
            String st = DateUtil.format(DateUtil.getDate(-20), DateUtil.yyyy_MM_dd_HH_mm_ss_EN);
            String et = DateUtil.format(DateUtil.getDate(0), DateUtil.yyyy_MM_dd_HH_mm_ss_EN);
            cond.setStartLoadTm(st);
            cond.setEndLoadTm(et);
            int l = rawTableDataGetManager.getCount(DataTestUtil.getConfig(), cond);
            System.out.println("res: succ " + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(l)));
        } catch (Exception e) {
            System.out.println("res: error " + e);
            e.printStackTrace();
        }
        long cost = System.currentTimeMillis() - t;
        System.out.println("cost:" + cost);
    }

    @Test
    public void testCovGetData() throws Exception {
        long t = System.currentTimeMillis();
        try {
            SearchCondBase cond = new SearchCondBase();
            String st = DateUtil.format(DateUtil.getDate(-20), DateUtil.yyyy_MM_dd_HH_mm_ss_EN);
            String et = DateUtil.format(DateUtil.getDate(0), DateUtil.yyyy_MM_dd_HH_mm_ss_EN);
            cond.setStartLoadTm(st);
            cond.setEndLoadTm(et);
            cond.setPageSize(8);
            List<Map> l = rawTableDataGetManager.getData(DataTestUtil.getCovConfig(), cond);

            System.out.println("res: succ " + l.size() + "," + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(l)));
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
