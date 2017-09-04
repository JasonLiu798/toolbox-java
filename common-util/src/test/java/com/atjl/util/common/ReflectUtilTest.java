package com.atjl.util.common;

import com.atjl.util.character.StringCheckUtil;
import com.atjl.util.collection.CollectionUtil;
import com.atjl.util.dto.TestDtoChild;
import com.atjl.util.json.JSONFastJsonUtil;
import com.atjl.util.json.JSONFmtUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class ReflectUtilTest {


    @Test
    public void testTryConvertToBoxClass() throws Exception {

    }

    @Test
    public void testCheckClassExist() throws Exception {

    }

    @Test
    public void testGetInstance() throws Exception {

    }

    @Test
    public void testChkAssigned() throws Exception {
        //interface
        Class clz = List.class;
        Class aclz = ArrayList.class;
        boolean a = ReflectUtil.chkAImplementB(aclz, clz);
        assertEquals(true, a);
        //abstract class
        Class abclz = AbstractList.class;
        boolean b = ReflectUtil.chkAImplementB(aclz, abclz);
        assertEquals(true, b);
        //class
        Class ca = Vector.class;
        Class cb = Stack.class;
        boolean c = ReflectUtil.chkAImplementB(cb, ca);
        assertEquals(true, c);
        //error
        ca = Vector.class;
        cb = Stack.class;
        c = ReflectUtil.chkAImplementB(ca, cb);
        assertEquals(false, c);

    }

    @Test
    public void testChkImplementIntfForObjIntf() throws Exception {

    }

    @Test
    public void testChkImpiIntfForObjIntfs() throws Exception {

    }

    @Test
    public void testChkImpiIntfForClzIntfs() throws Exception {

    }

    @Test
    public void testGetAllField() {
        TestDtoChild child = new TestDtoChild();
        List<Field> fields = ReflectUtil.getFieldAll(child);
        System.out.println("res:" + fields);
    }

    @Test
    public void testGetFieldSet() throws Exception {

    }

    @Test
    public void testGetFieldsHaveSetter() throws Exception {

    }

    @Test
    public void testGetAllFieldsHaveSetter() throws Exception {

    }

    @Test
    public void testGetFieldType() throws Exception {

    }

    @Test
    public void testGetFieldTypeAll() throws Exception {

    }

    @Test
    public void testGetterForce() throws Exception {
        Object res = ReflectUtil.getterForceClz(StringCheckUtil.class, "NULL");
        System.out.println(res);
    }

    @Test
    public void testGetter() throws Exception {

    }

    @Test
    public void testGetGetterMethod() throws Exception {

    }

    @Test
    public void testGetterInner() throws Exception {

    }

    @Test
    public void testSetterForObjFieldValTypeVal() throws Exception {

    }

    @Test
    public void testSetterForObjFieldVal() throws Exception {

    }

    @Test
    public void testSetterForce() throws Exception {

    }

    /**
     * get field
     *
     * @throws Exception
     */
    @Test
    public void testGetField() throws Exception {


    }

    @Test
    public void testCopyFieldForSourceTargetIgnoresAllowNull() throws Exception {
        TestDtoChild src = new TestDtoChild();
        src.setChildField(2L);
        src.setF1(1);
        TestDtoChild dest = new TestDtoChild();
        System.out.println("bf:" + JSONFastJsonUtil.objectToJson(src) + "," + JSONFastJsonUtil.objectToJson(dest));
        ReflectUtil.copyField(src, dest);
        System.out.println("af:" + JSONFastJsonUtil.objectToJson(src) + "," + JSONFastJsonUtil.objectToJson(dest));
    }

    @Test
    public void testCopyFieldForSourceTarget() throws Exception {

    }

    @Test
    public void testGetSelfAndParentClassListBean() throws Exception {

    }

    @Test
    public void testGetSelfAndParentClassListClz() throws Exception {

    }

    @Test
    public void testGetClassListForClzOpt() throws Exception {

    }

    @Test
    public void testGetClassListForBeanOpt() throws Exception {

    }

    @Test
    public void testGetFieldValueMapForObjAllowNullValueParentOptIgnoresSpecifies() throws Exception {

    }

    @Test
    public void testGetFieldValueMapIncludeNull() throws Exception {

    }

    @Test
    public void testGetFieldValueMapAllIncludeNull() throws Exception {

    }

    @Test
    public void testGetFieldValueMapForBeanAllowNullValue() throws Exception {

    }


    @Test
    public void testGetFields() throws Exception {
        /**
         * Object obj,boolean allowNullValue, GetClzOpt parentOpt, String[] blackList, String[] whiteList
         */
        TestDtoChild src = new TestDtoChild();
        src.setChildField(100L);
        src.setF1(1);
        src.setF2(2);
        List res = ReflectUtil.filed2string(ReflectUtil.getFieldAll(src));
        System.out.println("res all:" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(res)));
        System.out.println("#########################");

        res = ReflectUtil.getFields(src, ReflectUtil.GetClzOpt.SELF, null, null);
        res = ReflectUtil.filed2string(res);
        System.out.println("res self:" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(res)));
        System.out.println("#########################");

        res = ReflectUtil.getFields(src, ReflectUtil.GetClzOpt.PARENT, null, null);
        res = ReflectUtil.filed2string(res);
        System.out.println("res parent:" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(res)));
        System.out.println("#########################");

        res = ReflectUtil.getFields(src, ReflectUtil.GetClzOpt.ALLPARENT, null, null);
        res = ReflectUtil.filed2string(res);
        System.out.println("res all parent:" + JSONFmtUtil.formatJsonConsole(JSONFastJsonUtil.objectToJson(res)));

        System.out.println("#########################");
        res = ReflectUtil.getFields(src.getClass(), ReflectUtil.GetClzOpt.ALL, null, null);
        res = ReflectUtil.filterField(res, CollectionUtil.newArr(Long.class));
        res = ReflectUtil.filed2string(res);
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
        Map res = ReflectUtil.getFieldValue(src, ReflectUtil.GetClzOpt.ALL, true, null, null);
        System.out.println("res all:" + JSONFastJsonUtil.objectToJson(res));

        res = ReflectUtil.getFieldValue(src, ReflectUtil.GetClzOpt.SELF, true, null, null);
        System.out.println("res self:" + JSONFastJsonUtil.objectToJson(res));

        res = ReflectUtil.getFieldValue(src, ReflectUtil.GetClzOpt.PARENT, true, null, null);
        System.out.println("res parent:" + JSONFastJsonUtil.objectToJson(res));

        res = ReflectUtil.getFieldValue(src, ReflectUtil.GetClzOpt.ALLPARENT, true, null, null);
        System.out.println("res all parent:" + JSONFastJsonUtil.objectToJson(res));
    }

    @Test
    public void testGetFieldBlackWihte() {
        TestDtoChild src = new TestDtoChild();
        src.setChildField(2L);
        src.setF1(1);
        src.setF2(2);

        Map res = ReflectUtil.getFieldValue(src, ReflectUtil.GetClzOpt.ALL, true, CollectionUtil.newArr("f2"), null);
        System.out.println("res black:" + JSONFastJsonUtil.objectToJson(res));
//
        res = ReflectUtil.getFieldValue(src, ReflectUtil.GetClzOpt.ALL, true, null, CollectionUtil.newArr("f1", "childField"));
        System.out.println("res white:" + JSONFastJsonUtil.objectToJson(res));

        res = ReflectUtil.getFieldValue(src, ReflectUtil.GetClzOpt.ALL, true, CollectionUtil.newArr("f1"), CollectionUtil.newArr("f1", "f2"));
        System.out.println("res white and black:" + JSONFastJsonUtil.objectToJson(res));

    }


    @Test
    public void testGetFieldValueMapIncludeNullSpec() throws Exception {

    }

    @Test
    public void testGetFieldMapForBeanAllowNullValueOptIgnoresSpecifies() throws Exception {

    }

    @Test
    public void testGetFieldMapBean() throws Exception {

    }

    @Test
    public void testGetFieldMapAll() throws Exception {

    }

    @Test
    public void testGetFieldMapForBeanAllowNullValue() throws Exception {

    }

    @Test
    public void testGetDeclaredMethod() throws Exception {

    }

    @Test
    public void testGetDeclaredField() throws Exception {

    }

    @Test
    public void testConvert() throws Exception {

    }

    @Test
    public void testInvokeMethod() throws Exception {

    }


    @Test
    public void testCheckGetMethod() throws Exception { 
                /* 
                try { 
                   Method method = ReflectUtil.getClass().getMethod("checkGetMethod", Method[].class, String.class);
                   method.setAccessible(true); 
                   method.invoke(<Object>, <Parameters>); 
                } catch(NoSuchMethodException e) { 
                } catch(IllegalAccessException e) { 
                } catch(InvocationTargetException e) { 
                } 
                */
    }

    @Test
    public void testIsBoolean() throws Exception { 
                /* 
                try { 
                   Method method = ReflectUtil.getClass().getMethod("isBoolean", Field.class);
                   method.setAccessible(true); 
                   method.invoke(<Object>, <Parameters>); 
                } catch(NoSuchMethodException e) { 
                } catch(IllegalAccessException e) { 
                } catch(InvocationTargetException e) { 
                } 
                */
    }

    @Test
    public void testGenerateGetName() throws Exception { 
                /* 
                try { 
                   Method method = ReflectUtil.getClass().getMethod("generateGetName", String.class, boolean.class);
                   method.setAccessible(true); 
                   method.invoke(<Object>, <Parameters>); 
                } catch(NoSuchMethodException e) { 
                } catch(IllegalAccessException e) { 
                } catch(InvocationTargetException e) { 
                } 
                */
    }

    @Test
    public void testIsEmpty() throws Exception { 
                /* 
                try { 
                   Method method = ReflectUtil.getClass().getMethod("isEmpty", Object.class);
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
} 
