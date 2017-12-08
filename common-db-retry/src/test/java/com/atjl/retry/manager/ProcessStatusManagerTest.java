package com.atjl.retry.manager; 

import com.atjl.retry.api.option.PageOption;
import org.junit.*;
import org.junit.rules.ExpectedException;
import com.atjl.util.common.SystemUtil;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.atjl.util.json.JSONFastJsonUtil;
import com.atjl.util.json.JSONFmtUtil;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context.xml"})
public class ProcessStatusManagerTest {


    @Resource
    private ProcessStatusManager processStatusManager;

    
    @Test
    public void testRepeatExecuteCheck() throws Exception { 
        long t = System.currentTimeMillis();
        try {
            Object res=null;

            PageOption opt = new PageOption();
            opt.setCheckRepeatExecuteInterval(600);
            opt.setServiceName("simpleBatchService");

            processStatusManager.repeatExecuteCheck(opt);
            System.out.println("res: succ " + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(res)));
        } catch (Exception e) {
            System.out.println("res: error " + e);
            e.printStackTrace();
        }
        long cost = System.currentTimeMillis() - t;
        System.out.println("cost:" + cost);
    }
    
    @Test
    public void testAddStatus() throws Exception { 
        long t = System.currentTimeMillis();
        try {
            Object res=null;
        
            System.out.println("res: succ " + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(res)));
        } catch (Exception e) {
            System.out.println("res: error " + e);
            e.printStackTrace();
        }
        long cost = System.currentTimeMillis() - t;
        System.out.println("cost:" + cost);
    }
    
    @Test
    public void testUpdateByPk() throws Exception { 
        long t = System.currentTimeMillis();
        try {
            Object res=null;
        
            System.out.println("res: succ " + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(res)));
        } catch (Exception e) {
            System.out.println("res: error " + e);
            e.printStackTrace();
        }
        long cost = System.currentTimeMillis() - t;
        System.out.println("cost:" + cost);
    }
    
    @Test
    public void testAddLogDetail() throws Exception { 
        long t = System.currentTimeMillis();
        try {
            Object res=null;
        
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
    public static void beforeClass() throws Exception{
        
        String dir = System.getProperty("user.dir");
        System.out.println("now " + dir);
        String config = dir.substring(0, dir.lastIndexOf("\\")) + "\\config";
        System.out.println("config " + config);
        SystemUtil.addClasspath(config);
    }
    
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();
} 
