package com.atjl.dbservice.service;

import com.atjl.common.api.resp1.ResponseDataDtoV1;
import com.atjl.dbservice.mapper.biz.SysDao;
import com.atjl.util.common.SystemUtil;
import com.atjl.util.json.JSONFastJsonUtil;
import com.atjl.util.json.JSONFmtUtil;
import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-service.xml"})
public class TextServiceTest {


    @Resource
    TextService textService;

    @Resource
    private SysDao sysDao;

    @Test
    public void clearTable() {

        sysDao.clearTable("t_approve");


    }


    @Test
    public void testProcess() throws Exception {
        long t = System.currentTimeMillis();
        try {
            Object res = null;

//            String text = "select * from testdate";
            String text = "update/**/ testdate set D='ddd' ";
//            String type = "j";
            String type = "h";
            text = StringEscapeUtils.escapeHtml4(text);
            type = StringEscapeUtils.escapeHtml4(type);
            ResponseDataDtoV1 resp = textService.process(text, type);

//            textService.process("", "");
            System.out.println("res: succ " + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(resp)));
        } catch (Exception e) {
            System.out.println("res: error " + e);
            e.printStackTrace();
        }
        long cost = System.currentTimeMillis() - t;
        System.out.println("cost:" + cost);
    }


    @Test
    public void testContainX() throws Exception { 
                /* 
                try { 
                   Method method = TextService.getClass().getMethod("containX", String.class); 
                   method.setAccessible(true); 
                   method.invoke(<Object>, <Parameters>); 
                } catch(NoSuchMethodException e) { 
                } catch(IllegalAccessException e) { 
                } catch(InvocationTargetException e) { 
                } 
                */
    }

    @Test
    public void testProcessSelect() throws Exception { 
                /* 
                try { 
                   Method method = TextService.getClass().getMethod("processSelect", String.class, String.class); 
                   method.setAccessible(true); 
                   method.invoke(<Object>, <Parameters>); 
                } catch(NoSuchMethodException e) { 
                } catch(IllegalAccessException e) { 
                } catch(InvocationTargetException e) { 
                } 
                */
    }

    @Test
    public void testProcessTable() throws Exception { 
                /* 
                try { 
                   Method method = TextService.getClass().getMethod("processTable", StringBuilder.class, List<LinkedHashMap<String,.class); 
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
