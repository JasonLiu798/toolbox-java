package com.atjl.util.collection;

import com.atjl.common.domain.KeyValue;
import com.atjl.util.json.JSONFastJsonUtil;
import com.atjl.util.number.RangeUtil;
import com.atjl.util.test.TestDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class CollectionUtilExTest {


    @Test
    public void freeze() throws Exception {
        Map<String, TestDto> map = new HashMap<>();
        TestDto t1 = new TestDto(1, "t1");
        TestDto t2 = new TestDto(2, "t2");
        map.put("t1", t1);
        map.put("t2", t2);
        System.out.println(map);
        Map<String, TestDto> fmap = CollectionUtilEx.copyMap(map);
        //Map fmap = (Map) BeanUtils.cloneBean(map);
        t1.setS1("t3");
        System.out.println(fmap);
    }


    @Test
    public void testfilterDelListInner() {
        List<String> tgt = new LinkedList<>();
        tgt.add("abc");
        tgt.add("def");
        tgt.add("hij");
        tgt.add("klm");
        List<String> dellist = new LinkedList<>();
        dellist.add("def");
        dellist.add("klm");
        List<String> res = CollectionFilterUtil.filterDelListInner(tgt, dellist, CollectionConstant.TP_EXIST_STR);
        System.out.println(res);
    }

    @Test
    public void testPrintKey() {
        Map map = new HashMap();
        map.put(1, 2);
        map.put(2, 3);
        String s = CollectionUtilEx.getKeySetString(map);
        System.out.println(s);
    }


    @Test
    public void testExistDuplicate() {

        List<String> l = CollectionUtilEx.newList("123", "1", "1");
        boolean res = CollectionUtilEx.existDuplicate(l);
        assertEquals(true, res);


        l = CollectionUtilEx.newList("112312", "1", "213421", "323sdf", "1");
        res = CollectionUtilEx.existDuplicate(l);
        assertEquals(true, res);


        l = CollectionUtilEx.newList("112312", "1", "213421", "1", "323sdf", "1");
        res = CollectionUtilEx.existDuplicate(l);
        assertEquals(true, res);

        l = CollectionUtilEx.newList("123", "1");
        res = CollectionUtilEx.existDuplicate(l);
        assertEquals(false, res);

        l = CollectionUtilEx.newList("123");
        res = CollectionUtilEx.existDuplicate(l);
        assertEquals(false, res);
    }

    @Test
    public void testIsEmptyArr() throws Exception {

    }

    @Test
    public void testIsNotEmptyArr() throws Exception {

    }

    @Test
    public void testIsEmptyCollection() throws Exception {

    }

    @Test
    public void testIsNotEmptyCollection() throws Exception {

    }

    @Test
    public void testIsEmptyMap() throws Exception {

    }

    @Test
    public void testGetPreNode() throws Exception {

    }

    @Test
    public void testGetNextNode() throws Exception {

    }

    @Test
    public void testFilterList2Size() throws Exception {

    }

    @Test
    public void testFilterMap() throws Exception {

    }

    @Test
    public void testFilterDelList() throws Exception {

    }

    @Test
    public void testSet2List() throws Exception {

    }

    @Test
    public void testArray2List() throws Exception {

    }

    @Test
    public void testArray2Set() throws Exception {

    }

    @Test
    public void testDeleteDuplicatItemLoop() throws Exception {

    }

    @Test
    public void testDeleteDuplicatItemHash() throws Exception {

    }

    @Test
    public void testPrintListC() throws Exception {

    }

    @Test
    public void testPrintListForCName() throws Exception {

    }

    @Test
    public void testPrintListForCLevel() throws Exception {

    }

    @Test
    public void testPrintListForCNameLevel() throws Exception {

    }

    @Test
    public void testSeparateList() throws Exception {

    }

    @Test
    public void testGetInterval() throws Exception {

    }

    @Test
    public void testCopyList() throws Exception {

    }

    @Test
    public void testCopyForListStartEnd() throws Exception {

    }

    @Test
    public void testResizeArray() throws Exception {

    }

    @Test
    public void testRemoveListFirstN() throws Exception {
        List<Integer> list = RangeUtil.range(10);
        System.out.println(list);
        list = CollectionUtilEx.removeListFirstN(list, 3);
        assertEquals(7, list.size());
        for (int i = 0; i < list.size(); i++) {
            int num = list.get(i);
            assertEquals(i + 3, num);
        }
        System.out.println(list);
    }

    @Test
    public void testList() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        System.out.println(list);
        list.remove(3);
        System.out.println(list);
        Integer res = list.get(3);
        System.out.println(res);

        list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        System.out.println(list);
        list.remove(3);
        System.out.println(list);
        Integer i = list.get(3);
        System.out.println(i);

    }

    @Test
    public void getDup() {
        //empty
        List<String> l1 = new LinkedList<>();
        List<String> l2 = new LinkedList<>();
        List<String> l = CollectionUtilEx.getDuplicateItemUseHash(l1, l2);
        assertEquals(true, CollectionUtilEx.isEmpty(l));

        //one empty
        l1 = null;
        l2 = new LinkedList<>();
        l = CollectionUtilEx.getDuplicateItemUseHash(l1, l2);
        assertEquals(true, CollectionUtilEx.isEmpty(l));

        l1 = new LinkedList<>();
        l2 = null;
        l = CollectionUtilEx.getDuplicateItemUseHash(l1, l2);
        assertEquals(true, CollectionUtilEx.isEmpty(l));

        //no duplicat
        l1 = new LinkedList<>();
        l1.add("1");
        l1.add("2");
        l1.add("3");
        l2 = new LinkedList<>();
        l2.add("4");
        l2.add("5");
        l = CollectionUtilEx.getDuplicateItemUseHash(l1, l2);
        assertEquals(true, CollectionUtilEx.isEmpty(l));

        //contain one duplicate
        l1 = new LinkedList<>();
        l1.add("123");
        l1.add("234");
        l1.add("345");
        l2 = new LinkedList<>();
        l2.add("123");
        l2.add("2340");
        l = CollectionUtilEx.getDuplicateItemUseHash(l1, l2);
        System.out.println(l);
        assertEquals(1, l.size());
        assertEquals("123", l.get(0));

        //contain two dup
        l1 = new LinkedList<>();
        l1.add("1");
        l1.add("2");
        l1.add("3");
        l2 = new LinkedList<>();
        l2.add("1");
        l2.add("2");
        l = CollectionUtilEx.getDuplicateItemUseHash(l1, l2);
        assertEquals(2, l.size());
        List<String> expecL = new LinkedList<>();
        expecL.add("1");
        expecL.add("2");

        System.out.println(l + "," + expecL);
        assertEquals(expecL.get(0), l.get(0));
        assertEquals(expecL.get(1), l.get(1));

    }

    @Test
    public void getOneDup() {
        //empty
        List<String> l1 = new LinkedList<>();
        l1.add("1");
        l1.add("2");
        l1.add("3");
        List<String> l2 = new LinkedList<>();
        l2.add("1");
        l2.add("2");
        String item = CollectionUtilEx.getOneDuplicateItemUseHash(l1, l2);
        assertEquals("1", item);
    }


    @Test
    public void testFilterNull() {
        String[] arr = {"123", null, "343", null, null};
        String[] newA = CollectionUtilEx.filterNull(arr);
        System.out.println("res:" + Arrays.toString(newA));
    }


    @Test
    public void getStringMap() {

        Map<String, String> m = CollectionUtilEx.newStringMap("a", "b", "c");

        assertEquals(m.get("a"), "b");
        assertEquals(m.get("c"), null);
        System.out.println("res:" + m);

        m = CollectionUtilEx.newStringMap("a", "b", "c", "d");

        assertEquals(m.get("a"), "b");
        assertEquals(m.get("c"), "d");
        System.out.println("res:" + m);

        m = CollectionUtilEx.newStringMap(null);
        assertEquals(m, new HashMap<>());
        System.out.println("res:" + m);


    }


    @Test
    public void getKVL() {

        List<KeyValue> m = CollectionUtilEx.newKVL("a", "b", "c");

        assertEquals(m.get(0).getKey(), "a");
        assertEquals(m.get(0).getValue(), "b");
        assertEquals(m.get(1).getKey(), "c");
        assertEquals(m.get(1).getValue(), null);
        System.out.println("res:" + JSONFastJsonUtil.objectToJson(m));


        m = CollectionUtilEx.newKVL("a", "b", "c", "d");
        assertEquals(m.get(0).getKey(), "a");
        assertEquals(m.get(0).getValue(), "b");
        assertEquals(m.get(1).getKey(), "c");
        assertEquals(m.get(1).getValue(), "d");
        System.out.println("res:" + JSONFastJsonUtil.objectToJson(m));

        m = CollectionUtilEx.newKVL(null);
        assertEquals(m, new ArrayList<>());
        System.out.println("res:" + m);


    }


    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }
} 
