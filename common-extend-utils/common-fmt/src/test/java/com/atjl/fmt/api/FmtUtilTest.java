package com.atjl.fmt.api;

import com.atjl.util.collection.CollectionUtil;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.Map;


public class FmtUtilTest {


    @Test
    public void testRender() throws Exception {
        String template = "欢迎\n${name}哈哈${file}好";

        Map<String,Object> param = CollectionUtil.newMap("name","呵呵");
        param.put("file","fff");

        param.put("font1","aaa");
        param.put("font2","aaa");


        String res = FmtUtil.render(template,param);
        System.out.println("res:"+res);
    }

    @Test
    public void testRenderTemplateError() throws Exception {
        String template = "欢迎\n${name哈哈file好";

        Map<String,Object> param = CollectionUtil.newMap("name","呵呵");
        param.put("file","fff");
        String res = FmtUtil.render(template,param);
        System.out.println("res:"+res);
    }

    @Test
    public void testValid(){
        String template = "欢迎\n${name哈哈file好";
//        boolean res = FmtUtil.templateValid(template);
//        System.out.println("res:"+res);
    }


    @Test
    public void aRender() throws Exception {


        Map<String,Object> fonts = CollectionUtil.newMap("f1","F1");
        fonts.put("f2","F2");
        fonts.put("f3","F3");

        Map<String,Object> param = CollectionUtil.newMap("name","呵呵");
        param.put("file","fff");
        param.put("font","f1");
        param.put("fonts",fonts);

        String template = "欢迎\n${name}哈哈${fonts[font]}好";


        String res = FmtUtil.render(template,param);
        System.out.println("res:"+res);
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
