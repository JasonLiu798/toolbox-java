package com.atjl.util.collection;

import com.atjl.util.number.RangeUtil;
import com.atjl.util.test.TestDto;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.*;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class CollectionUtilTest {


    @Test
    public void freeze() throws Exception {
        Map<String,TestDto> map = new HashMap<>();
        TestDto t1 = new TestDto(1,"t1");
        TestDto t2 = new TestDto(2,"t2");
        map.put("t1",t1);
        map.put("t2",t2);
        System.out.println(map);
        Map<String,TestDto> fmap = CollectionUtil.copyMap(map);
        //Map fmap = (Map) BeanUtils.cloneBean(map);
        t1.setS1("t3");
        System.out.println(fmap);
    }


    @Test
    public void testfilterDelListInner(){
        List<String> tgt = new LinkedList<>();
        tgt.add("abc");
        tgt.add("def");
        tgt.add("hij");
        tgt.add("klm");
        List<String> dellist = new LinkedList<>();
        dellist.add("def");
        dellist.add("klm");
        List<String> res = CollectionUtil.filterDelListInner(tgt,dellist,CollectionUtil.TP_EXIST_STR);
        System.out.println(res);
    }

    @Test
    public void testPrintKey(){
        Map map = new HashMap();
        map.put(1,2);
        map.put(2,3);
        String s = CollectionUtil.getKeySetString(map);
        System.out.println(s);
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
        List<Integer> list =  RangeUtil.range(10);
        System.out.println(list);
        list = CollectionUtil.removeListFirstN(list,3);
        assertEquals(7,list.size());
        for(int i=0;i<list.size();i++) {
            int num = list.get(i);
            assertEquals(i+3 , num);
        }
        System.out.println(list);
    }

    @Test
    public void testList(){
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            list.add(i);
        }
        System.out.println(list);
        list.remove(3);
        System.out.println(list);
        Integer res = list.get(3);
        System.out.println(res);

        list = new LinkedList<>();
        for(int i=0;i<10;i++){
            list.add(i);
        }
        System.out.println(list);
        list.remove(3);
        System.out.println(list);
        Integer i = list.get(3);
        System.out.println(i);

    }

    @Test
    public void getDup(){
        //empty
        List<String> l1 = new LinkedList<>();
        List<String> l2 = new LinkedList<>();
        List<String> l = CollectionUtil.getDuplicateItemUseHash(l1,l2);
        assertEquals(true, CollectionUtil.isEmpty(l));

        //one empty
        l1=null;
        l2 = new LinkedList<>();
        l = CollectionUtil.getDuplicateItemUseHash(l1,l2);
        assertEquals(true, CollectionUtil.isEmpty(l));

        l1 = new LinkedList<>();
        l2=null;
        l = CollectionUtil.getDuplicateItemUseHash(l1,l2);
        assertEquals(true, CollectionUtil.isEmpty(l));

        //no duplicat
        l1 = new LinkedList<>();
        l1.add("1");
        l1.add("2");
        l1.add("3");
        l2 = new LinkedList<>();
        l2.add("4");
        l2.add("5");
        l = CollectionUtil.getDuplicateItemUseHash(l1,l2);
        assertEquals(true, CollectionUtil.isEmpty(l));

        //contain one duplicate
        l1 = new LinkedList<>();
        l1.add("123");
        l1.add("234");
        l1.add("345");
        l2 = new LinkedList<>();
        l2.add("123");
        l2.add("2340");
        l = CollectionUtil.getDuplicateItemUseHash(l1,l2);
        System.out.println(l);
        assertEquals(1,l.size());
        assertEquals("123",l.get(0));

        //contain two dup
        l1 = new LinkedList<>();
        l1.add("1");
        l1.add("2");
        l1.add("3");
        l2 = new LinkedList<>();
        l2.add("1");
        l2.add("2");
        l = CollectionUtil.getDuplicateItemUseHash(l1,l2);
        assertEquals(2,l.size());
        List<String> expecL = new LinkedList<>();
        expecL.add("1");
        expecL.add("2");

        System.out.println(l+","+expecL);
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
        String item = CollectionUtil.getOneDuplicateItemUseHash(l1,l2);
        assertEquals("1",item);
    }
        
    @Before
    public void before() throws Exception { 
    } 

    @After
    public void after() throws Exception { 
    }
} 
