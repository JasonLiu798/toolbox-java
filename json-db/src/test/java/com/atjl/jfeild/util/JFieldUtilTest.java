package com.atjl.jfeild.util;

import com.atjl.jfeild.domain.JTabMeta;
import com.atjl.jfeild.util.eg.*;
import com.atjl.util.common.SystemUtil;
import com.atjl.util.json.JSONFastJsonUtil;
import com.atjl.util.json.JSONFmtUtil;
import org.junit.*;
import org.junit.rules.ExpectedException;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:test-service.xml"})
public class JFieldUtilTest {


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

            BizObj biz = new BizObj();
            biz.setExtf1("nnnnn");
            biz.setFidx("fdfd");
//            biz.setBizObjI1(new BizObjI1("xx", "bbb"));

            DbObj d = JFieldUtil.updatePrimitive(biz, dbObj, BizObjMetaUtil.getTabMeta());

            System.out.println("after db json:" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(d)));
            bo = JFieldUtil.select(d, BizObjMetaUtil.getTabMeta(), BizObj.class);
            System.out.println("after obj json: " + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(bo)));


        } catch (Exception e) {
            System.out.println("res: error " + e);
        }
        long cost = System.currentTimeMillis() - t;
        System.out.println("cost:" + cost);
    }

    @Test
    public void testUpdateSpecified() throws Exception {
        long t = System.currentTimeMillis();
        try {
//            Object res = null;
//            BizObj biz = new BizObj();
//            BizObjI1 bi1 = new BizObjI1("A", "B");
//            BizObjI2 bi2 = new BizObjI2("C", "D");
//            biz.setBizObjI1(bi1);
//            biz.setBizObjI2(bi2);
//            biz.setExtf1("ext1");
//            biz.setExtf2("ext2");

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

            BizObj biz = new BizObj();
            biz.setExtf1("nnnnn");
            biz.setBizObjI1(new BizObjI1("xx", "bbb"));

//            DbObj d = JFieldUtil.updateSpecified(biz, dbObj, BizObjMetaUtil.getTabMeta(), true, true, CollectionUtil.newList("bizObjI1"));

/**
 before: {
 "basic":"{\"extf1\":\"ext1\",\"extf2\":\"ext2\"}",
 "fidx":"idx",
 "oi1":"{\"f3\":\"C\",\"f4\":\"D\"}"
 }
 */
//            System.out.println("res: succ " + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(d)));
//            bo = JFieldUtil.select(d, BizObjMetaUtil.getTabMeta(), BizObj.class);
            System.out.println("before: " + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(bo)));


        } catch (Exception e) {
            System.out.println("res: error " + e);
        }
        long cost = System.currentTimeMillis() - t;
        System.out.println("cost:" + cost);
    }

    @Test
    public void testUpdate() throws Exception {
        long t = System.currentTimeMillis();
        try {
//            Object res = null;


            JTabMeta meta = BizObjMetaUtil.getTabMeta();
            System.out.println("res: succ " + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(meta)));
        } catch (Exception e) {
            System.out.println("res: error " + e);
        }
        long cost = System.currentTimeMillis() - t;
        System.out.println("cost:" + cost);
    }

    @Test
    public void testSelectBatch() throws Exception {
        long t = System.currentTimeMillis();
        try {
            Object res = null;

            System.out.println("res: succ " + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(res)));
        } catch (Exception e) {
            System.out.println("res: error " + e);
        }
        long cost = System.currentTimeMillis() - t;
        System.out.println("cost:" + cost);
    }

    @Test
    public void testSelect() throws Exception {
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

            System.out.println("before: " + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(dbObj)));
            BizObj bo = JFieldUtil.select(dbObj, BizObjMetaUtil.getTabMeta(), BizObj.class);
            System.out.println("before: " + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(bo)));

//            System.out.println("res: succ " + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(res)));
        } catch (Exception e) {
            System.out.println("res: error " + e);
        }
        long cost = System.currentTimeMillis() - t;
        System.out.println("cost:" + cost);
    }

    @Test
    public void testJson2obj() throws Exception {
        long t = System.currentTimeMillis();
        try {
            Object res = null;

            System.out.println("res: succ " + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(res)));
        } catch (Exception e) {
            System.out.println("res: error " + e);
        }
        long cost = System.currentTimeMillis() - t;
        System.out.println("cost:" + cost);
    }


    @Test
    public void testFilter() throws Exception { 
                /* 
                try { 
                   Method method = JFieldUtil.getClass().getMethod("filter", Object.class, Object.class, JTabMeta.class); 
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

        String dir = System.getProperty("user.dir");
        System.out.println("now " + dir);
        String config = dir.substring(0, dir.lastIndexOf("\\")) + "\\config";
        System.out.println("config " + config);
        SystemUtil.addClasspath(config);
    }

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();
} 
