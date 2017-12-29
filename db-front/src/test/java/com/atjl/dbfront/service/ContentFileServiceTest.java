package com.atjl.dbfront.service;

import com.atjl.dbfront.constant.ContentConstant;
import com.atjl.util.common.SystemUtil;
import com.atjl.util.json.JSONFastJsonUtil;
import com.atjl.util.json.JSONFmtUtil;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-service.xml"})
public class ContentFileServiceTest {

    ContentFileService contentFileService;


    @Test
    public void testAddContent() throws Exception {
        long t = System.currentTimeMillis();
        try {
            contentFileService = new ContentFileService();
            String fileName = "aaa";
            contentFileService.addContent(ContentConstant.TP_JS, fileName, "v1", "aaaa");
            String res = contentFileService.getContent(ContentConstant.TP_JS, fileName, "v1");

            System.out.println("res: succ " + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(res)));
        } catch (Exception e) {
            System.out.println("res: error " + e);
            e.printStackTrace();
        }
        long cost = System.currentTimeMillis() - t;
        System.out.println("cost:" + cost);
    }

    @Test
    public void testGetContent() throws Exception {
        long t = System.currentTimeMillis();
        try {
            Object res = null;

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
