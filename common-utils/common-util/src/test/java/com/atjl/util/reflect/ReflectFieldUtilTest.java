package com.atjl.util.reflect;

import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.common.ReflectUtil.GetClzOpt;
import com.atjl.util.dto.TestDtoChild;
import com.atjl.util.json.JSONFastJsonUtil;
import com.atjl.util.json.JSONFmtUtil;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.List;
import java.util.Map;


public class ReflectFieldUtilTest {


    @Test
    public void testGetFields() throws Exception {
        /**
         * Object obj,boolean allowNullValue, GetClzOpt parentOpt, String[] blackList, String[] whiteList
         */
        TestDtoChild src = new TestDtoChild();
        src.setChildField(100L);
        src.setF1(1);
        src.setF2(2);
        List res = ReflectFieldUtil.filed2string(ReflectFieldUtil.getFieldListAll(src));
        System.out.println("res all:" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(res)));
        System.out.println("#########################");

        res = ReflectFieldUtil.getFieldList(src, GetClzOpt.SELF, null, null);
        res = ReflectFieldUtil.filed2string(res);
        System.out.println("res self:" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(res)));
        System.out.println("#########################");

        res = ReflectFieldUtil.getFieldList(src, GetClzOpt.PARENT, null, null);
        res = ReflectFieldUtil.filed2string(res);
        System.out.println("res parent:" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(res)));
        System.out.println("#########################");

        res = ReflectFieldUtil.getFieldList(src, GetClzOpt.ALLPARENT, null, null);
        res = ReflectFieldUtil.filed2string(res);
        System.out.println("res all parent:" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(res)));

        System.out.println("#########################");
        res = ReflectFieldUtil.getFieldList(src.getClass(), GetClzOpt.ALL, null, null);
        res = ReflectFieldUtil.filterField(res, CollectionUtil.newArr(Long.class));
        res = ReflectFieldUtil.filed2string(res);
        System.out.println("res Long:" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(res)));

    }

    @Test
    public void testGetFieldValueMapAll() throws Exception {
        /**
         * Object obj,boolean allowNullValue, GetClzOpt parentOpt, String[] blackList, String[] whiteList
         */
        TestDtoChild src = new TestDtoChild();
        src.setChildField(100L);
        src.setF1(1);
        src.setF2(2);
//        Map res = ReflectFieldUtil.getFieldValue(src, GetClzOpt.ALL, true, null, null);
        Map res = ReflectFieldUtil.getFieldValue(src);
        System.out.println("res all:" + JSONFastJsonUtil.objectToJson(res));

        res = ReflectFieldUtil.getFieldValue(src, GetClzOpt.SELF, true, null, null);
        System.out.println("res self:" + JSONFastJsonUtil.objectToJson(res));

        res = ReflectFieldUtil.getFieldValue(src, GetClzOpt.PARENT, true, null, null);
        System.out.println("res parent:" + JSONFastJsonUtil.objectToJson(res));

        res = ReflectFieldUtil.getFieldValue(src, GetClzOpt.ALLPARENT, true, null, null);
        System.out.println("res all parent:" + JSONFastJsonUtil.objectToJson(res));
    }

    @Test
    public void testGetFieldBlackWihte() {
        TestDtoChild src = new TestDtoChild();
        src.setChildField(2L);
        src.setF1(1);
        src.setF2(2);

        Map res = ReflectFieldUtil.getFieldValue(src, GetClzOpt.ALL, true, CollectionUtil.newArr("f2"), null);
        System.out.println("res black:" + JSONFastJsonUtil.objectToJson(res));
//
        res = ReflectFieldUtil.getFieldValue(src, GetClzOpt.ALL, true, null, CollectionUtil.newArr("f1", "childField"));
        System.out.println("res white:" + JSONFastJsonUtil.objectToJson(res));

        res = ReflectFieldUtil.getFieldValue(src, GetClzOpt.ALL, true, CollectionUtil.newArr("f1"), CollectionUtil.newArr("f1", "f2"));
        System.out.println("res white and black:" + JSONFastJsonUtil.objectToJson(res));
    }


    @Test
    public void getFieldFilterNull() {

        TestDtoChild src = new TestDtoChild();
        src.setChildField(100L);
        src.setF1(1);
        src.setF2(2);
//        List res = ReflectFieldUtil.filed2string(ReflectFieldUtil.getFieldListAll(src));

        List res = ReflectFieldUtil.getFieldList(src, GetClzOpt.ALL, null, null, true);
//        res = ReflectFieldUtil.filterField(res, CollectionUtil.newArr(Long.class));
        res = ReflectFieldUtil.filed2string(res);
        System.out.println("res Long:" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(res)));


    }

    @Test
    public void testGetFieldListForObjParentOptBlackArrWhiteArr() throws Exception {

    }

    @Test
    public void testGetFieldListAll() throws Exception {

    }

    @Test
    public void testGetFieldMapForObjParentOptBlackArrWhiteArr() throws Exception {

    }

    @Test
    public void testFilterField() throws Exception {

    }

    @Test
    public void testGetAllFieldsHaveSetter() throws Exception {

    }

    @Test
    public void testGetFieldsHaveSetter() throws Exception {

    }

    @Test
    public void testGetFieldType() throws Exception {

    }

    @Test
    public void testGetFieldTypeAll() throws Exception {

    }

    @Test
    public void testGetFieldValueForObjParentOptAllowNullValueBlackArrWhiteArr() throws Exception {

    }

    @Test
    public void testGetFieldValueForClzParentOptAllowNullValueBlackArrWhiteArr() throws Exception {

    }

    @Test
    public void testGetFieldValueForObjBlackList() throws Exception {

    }

    @Test
    public void testGetFieldValueObj() throws Exception {

    }

    @Test
    public void testGetFieldMapForBeanOptAllowNullValueBlackWhite() throws Exception {

    }

    @Test
    public void testGetFieldMapAll() throws Exception {

    }

    @Test
    public void testGetDeclaredFieldForClzFieldName() throws Exception {

    }

    @Test
    public void testGetDeclaredFieldForObjectFieldName() throws Exception {

    }

    @Test
    public void testGetDeclaredFieldValue() throws Exception {

    }

    @Test
    public void testFiled2string() throws Exception {

    }


    @Test
    public void testIsBoolean() throws Exception { 
                /* 
                try { 
                   Method method = ReflectFieldUtil.getClass().getMethod("isBoolean", Field.class); 
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
