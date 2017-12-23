package com.atjl.dbservice.manager;

import com.atjl.dbservice.api.domain.SearchCondBase;
import com.atjl.eg.EgConstant;
import com.atjl.eg.TransAreaMonitorService;
import com.atjl.retry.api.RetryDispatch;
import com.atjl.util.common.DateUtil;
import com.atjl.util.common.SystemUtil;
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
public class WithRetryServiceTest {

    @Resource
    private RetryDispatch retryDispatch;

//    @Resource
//    private DataTransferService dataTransferService;

    @Resource
    private TransAreaMonitorService transAreaMonitorService;

    @Test
    public void testAll() {

        retryDispatch.registe(EgConstant.TRANS_AREA_MONITOR_SERVICE);

        SearchCondBase cond = new SearchCondBase();
        String st = DateUtil.format(DateUtil.getDate(-30), DateUtil.yyyy_MM_dd_HH_mm_ss_EN);
        String et = DateUtil.format(DateUtil.getDate(0), DateUtil.yyyy_MM_dd_HH_mm_ss_EN);
        cond.setStartLoadTm(st);
        cond.setEndLoadTm(et);
        cond.setPageSize(200);
        retryDispatch.executeService(EgConstant.TRANS_AREA_MONITOR_SERVICE, cond);

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


}
