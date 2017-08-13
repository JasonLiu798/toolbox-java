package com.atjl.util.collection;

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;

import java.util.Comparator;


public class SearchHelperTest {

    @Before
    public void before() throws Exception { 
    } 

    @After
    public void after() throws Exception { 
    } 

        /** 
     * 
     * Method: binarySearchRaw(ArrayList<T> srcArray, T tgt, Comparator<T> comparator) 
     * 
     */ 
    @Test
    public void testBinarySearchRawForSrcArrayTgtComparator() throws Exception { 
        
    } 

        /** 
     * 
     * Method: binarySearch(ArrayList<T> srcArray, T tgt, Comparator<T> comparator) 
     * 
     */ 
    @Test
    public void testBinarySearch() throws Exception { 
        
    } 

        /** 
     * 
     * Method: binarySearchExpect(ArrayList<T> srcArray, T tgt, Comparator<T> comparator) 
     * 
     */ 
    @Test
    public void testBinarySearchExpect() throws Exception { 
        
    } 

        /** 
     * 
     * Method: binarySearchRaw(ArrayList<T> srcArray, T tgt, Comparator<T> comparator, boolean findExpect) 
     * 
     */ 
    @Test
    public void testBinarySearchRawForSrcArrayTgtComparatorFindExpect() throws Exception { 
        
    } 

        /** 
     * 
     * Method: binarySearchRecu(ArrayList<T> srcArray, int low, int high, T tgt, Comparator<T> comparator) 
     * 
     */ 
    @Test
    public void testBinarySearchRecu() throws Exception { 
        
    } 

        /** 
     * 
     * Method: partition(T[] a, Comparator<? super T> c, int p, int r) 
     * 
     */ 
    @Test
    public void testPartition() throws Exception { 
        
    } 

        /** 
     * 
     * Method: randomizedPartition(T[] a, Comparator<? super T> c, int p, int r) 
     * 
     */ 
    @Test
    public void testRandomizedPartition() throws Exception { 
        
    } 

        /** 
     * 
     * Method: randomizedSelect(T[] t, Comparator<? super T> comparator, int p, int r, int i) 
     * 
     */ 
    @Test
    public void testRandomizedSelectForTComparatorPRI() throws Exception { 
        
    } 

        /** 
     * 
     * Method: randomizedSelect(T[] t, Comparator<? super T> comparator, int i) 
     * 
     */ 
    @Test
    public void testRandomizedSelectForTComparatorI() throws Exception {

        Integer[] ints = new Integer[] { 31, 41, 59, 26, 41, 58 };
        Integer positiong = SearchUtil.randomizedSelect(ints, new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o1.intValue() - o2.intValue();
            }
        }, ints.length/2);
        System.out.println(positiong);
    } 

        /** 
     * 
     * Method: main(String[] args) 
     * 
     */ 
    @Test
    public void testMain() throws Exception { 
        
    } 

    
    } 
