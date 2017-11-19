package com.atjl.jfeild.util;

import com.atjl.util.common.SystemUtil;
import com.atjl.util.json.JSONFastJsonUtil;
import com.atjl.util.json.JSONFmtUtil;
import org.junit.*;
import org.junit.rules.ExpectedException;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:test-service.xml"})
public class UpdateTest {


    @Test
    public void testUpdatePrimitive() throws Exception {
        long t = System.currentTimeMillis();
        try {
            DbObj dbObj = new DbObj();
            dbObj.setFidx("idx");
            BizObj bizBasic = new BizObj();
            bizBasic.setExtf1("ext1");
            bizBasic.setExtf2("ext2");
            JSONFastJsonUtil.objectToJson(bizBasic);
            dbObj.setBasic(JSONFastJsonUtil.objectToJson(bizBasic));
            BizObjI1 bi1 = new BizObjI1("A", "B");
            dbObj.setOi1(JSONFastJsonUtil.objectToJson(bi1));
            BizObjI2 bi2 = new BizObjI2("C", "D");
            dbObj.setOi2(JSONFastJsonUtil.objectToJson(bi2));
//            String djson = JSONFastJsonUtil.objectToJson(dbObj);

            System.out.println("before db json: " + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(dbObj)));
            BizObj bo = JFieldUtil.select(dbObj, BizObjMetaUtil.getTabMeta(), BizObj.class);
            System.out.println("before obj json: " + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(bo)));

//            BizObj biz = new BizObj();
            bo.setExtf1("nnnnn");
            bo.setFidx("fdfd");
            BizObjI2 bi5 = new BizObjI2("X", "Y");
            bo.setBizObjI2(bi5);
//            biz.setBizObjI1(new BizObjI1("xx", "bbb"));

            DbObj d = JFieldUtil.update(bo, BizObjMetaUtil.getTabMeta(), DbObj.class);

            System.out.println("after db json:" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(d)));
            bo = JFieldUtil.select(d, BizObjMetaUtil.getTabMeta(), BizObj.class);
            System.out.println("after obj json: " + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(bo)));


        } catch (Exception e) {
            System.out.println("res: error " + e);
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
