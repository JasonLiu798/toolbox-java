package com.atjl.logdb.service;

import com.atjl.common.api.resp.PageResp;
import com.atjl.logdb.api.LogService;
import com.atjl.logdb.api.domain.OpLog;
import com.atjl.logdb.api.req.OpLogReq;
import com.atjl.util.json.JSONFastJsonUtil;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-service.xml"})
public class LogServiceImplTest {

    @Resource
    private LogService logService;


    @Test
    public void testInsert() throws Exception {
        OpLog log = new OpLog();
        log.setLv("D");
        log.setOpModule("xxxxx");
//        log.setOpTm(DateUtil.getNowTS());
        log.setCost(123L);
        logService.insert(log);
    }

    @Test
    public void testSearchPage() throws Exception {
        OpLogReq req = new OpLogReq();
//        req.set
        PageResp res = logService.searchPage(req);
        System.out.println("res:" + JSONFastJsonUtil.objectToJson(res));
    }


    @Test
    public void testSearch() throws Exception { 
                /* 
                try { 
                   Method method = LogServiceImpl.getClass().getMethod("search", OpLogReq.class); 
                   method.setAccessible(true); 
                   method.invoke(<Object>, <Parameters>); 
                } catch(NoSuchMethodException e) { 
                } catch(IllegalAccessException e) { 
                } catch(InvocationTargetException e) { 
                } 
                */
    }

    @Test
    public void testCount() throws Exception { 
                /* 
                try { 
                   Method method = LogServiceImpl.getClass().getMethod("count", OpLogReq.class); 
                   method.setAccessible(true); 
                   method.invoke(<Object>, <Parameters>); 
                } catch(NoSuchMethodException e) { 
                } catch(IllegalAccessException e) { 
                } catch(InvocationTargetException e) { 
                } 
                */
    }

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @BeforeClass
    public static void beforeClass() throws Exception {

    }

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();
} 
