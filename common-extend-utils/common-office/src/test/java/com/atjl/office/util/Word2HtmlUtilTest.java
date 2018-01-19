package com.atjl.office.util;

import com.atjl.util.common.SystemUtil;
import com.atjl.util.json.JSONFastJsonUtil;
import com.atjl.util.json.JSONFmtUtil;
import org.junit.*;
import org.junit.rules.ExpectedException;

//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:test-service.xml"})
public class Word2HtmlUtilTest {


    @Test
    public void testWord07ToHtml() throws Exception {
        long t = System.currentTimeMillis();
        try {
            Object res = null;

//            Word2HtmlUtil.word07ToHtml("E:\\rpt.docx");
            Word2HtmlUtil.word07ToHtml("E:\\rpt.docx");
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

        String dir = System.getProperty("user.dir");
        System.out.println("now " + dir);
        String config = dir.substring(0, dir.lastIndexOf("\\")) + "\\config";
        System.out.println("config " + config);
        SystemUtil.addClasspath(config);
    }

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();
} 
