package com.jason798.collection; 

import com.jason798.number.RangeHelper;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class CollectionHelperTest {


    
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
        List<Integer> list =  RangeHelper.range(10);
        System.out.println(list);
        list = CollectionHelper.removeListFirstN(list,3);
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
        List<String> l = CollectionHelper.getDuplicateItemUseHash(l1,l2);
        assertEquals(true,CollectionHelper.isEmpty(l));

        //one empty
        l1=null;
        l2 = new LinkedList<>();
        l = CollectionHelper.getDuplicateItemUseHash(l1,l2);
        assertEquals(true,CollectionHelper.isEmpty(l));

        l1 = new LinkedList<>();
        l2=null;
        l = CollectionHelper.getDuplicateItemUseHash(l1,l2);
        assertEquals(true,CollectionHelper.isEmpty(l));

        //no duplicat
        l1 = new LinkedList<>();
        l1.add("1");
        l1.add("2");
        l1.add("3");
        l2 = new LinkedList<>();
        l2.add("4");
        l2.add("5");
        l = CollectionHelper.getDuplicateItemUseHash(l1,l2);
        assertEquals(true,CollectionHelper.isEmpty(l));

        //contain one duplicate
        l1 = new LinkedList<>();
        l1.add("123");
        l1.add("234");
        l1.add("345");
        l2 = new LinkedList<>();
        l2.add("123");
        l2.add("2340");
        l = CollectionHelper.getDuplicateItemUseHash(l1,l2);
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
        l = CollectionHelper.getDuplicateItemUseHash(l1,l2);
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
        String item = CollectionHelper.getOneDuplicateItemUseHash(l1,l2);
        assertEquals("1",item);
    }
        
    @Before
    public void before() throws Exception { 
    } 

    @After
    public void after() throws Exception { 
    }
} 
