package com.atjl.htmlparser.api;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.*;
import org.junit.rules.ExpectedException;
import com.atjl.util.common.SystemUtil;
import org.junit.runner.RunWith;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.atjl.util.json.JSONFastJsonUtil;
import com.atjl.util.json.JSONFmtUtil;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:test-service.xml"})
public class HtmlParserUtilTest {


    @Test
    public void testUpdateAttr() throws Exception {
        long t = System.currentTimeMillis();
        try {
//            Object res=null;

            String html = "<p> </p><span style=\"line-height: 21px;\">wddesc开机</span><div><span style=\"line-height: 21px;\"><br></span></div><div><span style=\"line-height: 21px;\"><br></span></div><div><table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><th>&nbsp;送大饭店是</th><th>&nbsp;啊啊</th><th>&nbsp;</th><th>&nbsp;</th><th>&nbsp;</th></tr><tr><td>&nbsp;撒旦法师的</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;送大饭店是</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;撒旦法师的</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;是打发士大夫</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr></tbody></table><table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><th>&nbsp;撒旦法撒旦法</th><th>是打发第三方&nbsp;</th><th>送大饭店是&nbsp;</th><th>&nbsp;</th><th>&nbsp;</th></tr><tr><td>&nbsp;是打发第三方</td><td>&nbsp;是打发士大夫</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;是打发士大夫</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;送打发打发</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr></tbody></table><p><br></p><p><br></p><p><br></p></div>";

            UpdateElementAttrReq req = new UpdateElementAttrReq();
            req.setTag("table");
            req.addAttr("border", "1");
            String res = HtmlParserUtil.updateAttr(html, req);

//            System.out.println("res: succ " + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(res)));
            System.out.println("res: succ " + res);
        } catch (Exception e) {
            System.out.println("res: error " + e);
            e.printStackTrace();
        }
        long cost = System.currentTimeMillis() - t;
        System.out.println("cost:" + cost);
    }

    @Test
    public void testMain() throws Exception {
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


    @Test
    public void testEmpty(){
        String html = "<p> </p><span style=\"line-height: 21px;\">wddesc开机</span><div><span style=\"line-height: 21px;\"><br></span></div><div><span style=\"line-height: 21px;\"><br></span></div><div><table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><th>&nbsp;送大饭店是</th><th>&nbsp;啊啊</th><th>&nbsp;</th><th>&nbsp;</th><th>&nbsp;</th></tr><tr><td>&nbsp;撒旦法师的</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;送大饭店是</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;撒旦法师的</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;是打发士大夫</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr></tbody></table><table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><th>&nbsp;撒旦法撒旦法</th><th>是打发第三方&nbsp;</th><th>送大饭店是&nbsp;</th><th>&nbsp;</th><th>&nbsp;</th></tr><tr><td>&nbsp;是打发第三方</td><td>&nbsp;是打发士大夫</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;是打发士大夫</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;送打发打发</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr></tbody></table><p><br></p><p><br></p><p><br></p></div>";
        Document doc = Jsoup.parseBodyFragment(html);
        //Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");

//        Element body = doc.body();
//        Element content = doc.getElementById("content");
        Elements tables = doc.getElementsByTag("table");
        for (Element tab : tables) {
            String border = tab.attr("border");
            System.out.println("border " + border);
            tab.attr("border", "1");
//            String linkText = link.text();
        }
        String str = doc.body().html();
        System.out.println("res:" + str);
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
