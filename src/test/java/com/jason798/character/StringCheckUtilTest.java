package com.jason798.character; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StringCheckUtilTest {


    
    @Test
    public void testIsEmptyTarget() throws Exception { 
        
    }
    
    @Test
    public void testIsNotEmptyTarget() throws Exception { 
        
    }
    
    @Test
    public void testIsEmptyStr() throws Exception { 
        
    }
    
    @Test
    public void testIsNULL() throws Exception { 
        
    }
    
    @Test
    public void testIsExistEmptyTargets() throws Exception {
        String s1="a";
        String s2= "b";
        String n= null;
        assertEquals(true, StringCheckUtil.isExistEmpty(n));
        assertEquals(false,  StringCheckUtil.isExistEmpty(s1));
        assertEquals(false,  StringCheckUtil.isExistEmpty(s1,s2));
        assertEquals(true,  StringCheckUtil.isExistEmpty(s1,n,n));
        assertEquals(true,  StringCheckUtil.isExistEmpty(s1,s2,n));
        assertEquals(true, StringCheckUtil.isExistEmpty(n,n,n));
    }
    
    @Test
    public void testIsExistNotEmpty() throws Exception {
        String s1="a";
        String s2= "b";
        String n= null;
        assertEquals(false,  StringCheckUtil.isExistNotEmpty(n));
        assertEquals(true, StringCheckUtil.isExistNotEmpty(s1));
        assertEquals(true, StringCheckUtil.isExistNotEmpty(s1,s2));
        assertEquals(true, StringCheckUtil.isExistNotEmpty(s1,n,n));
        assertEquals(true, StringCheckUtil.isExistNotEmpty(s1,s2,n));
        assertEquals(false,  StringCheckUtil.isExistNotEmpty(n,n,n));
    }
    
    @Test
    public void testIsAllEmptyTargets() throws Exception {
        String s1="a";
        String s2= "b";
        String n= null;
        assertEquals(true,StringCheckUtil.isAllEmpty(n));
        assertEquals(false,StringCheckUtil.isAllEmpty(s1));
        assertEquals(false,StringCheckUtil.isAllEmpty(s1,s2));
        assertEquals(false,StringCheckUtil.isAllEmpty(s1,s2,n));
        assertEquals(true,StringCheckUtil.isAllEmpty(n,n,n));
    }
    
    @Test
    public void testIsAllNotEmptyTargets() throws Exception { 
        String s1="a";
        String s2= "b";
        assertEquals(false,StringCheckUtil.isAllNotEmpty(null));
        assertEquals(true,StringCheckUtil.isAllNotEmpty(s1));
        assertEquals(true,StringCheckUtil.isAllNotEmpty(s1,s2));
        assertEquals(false,StringCheckUtil.isAllNotEmpty(s1,s2,null));
    }
    
    @Test
    public void testEqual() throws Exception { 
        
    }
    
    @Test
    public void testContains() throws Exception { 
        
    }
    
    @Test
    public void testHasLengthStr() throws Exception { 
        
    }
    
    @Test
    public void testHasTextStr() throws Exception { 
        
    }
    
    @Test
    public void testIsPisubstr() throws Exception { 
        
    }

    @Test
    public void testStrInCollection(){
        String s = "1";
        List<String> l = new LinkedList<>();
        for(int i=0;i<5;i++){
            l.add(i+"");
        }
        boolean res = StringCheckUtil.strInCollection(s,l);
        assertEquals(true,res);

        s = "6";
        res = StringCheckUtil.strInCollection(s,l);
        assertEquals(false,res);
    }

    @Test
    public void strInStrings(){
        String s = "1";
        boolean res = StringCheckUtil.strInStrings(s,"1","2","3");
        assertEquals(true,res);

        s = "6";
        res = StringCheckUtil.strInStrings(s,"1","2","3");
        assertEquals(false,res);

        s = null;
        res = StringCheckUtil.strInStrings(s,"1","2","3");
        assertEquals(false,res);

        res = StringCheckUtil.strInStrings(null,null);
        assertEquals(false,res);

        res = StringCheckUtil.strInStrings(null,"1");
        assertEquals(false,res);

        res = StringCheckUtil.strInStrings("1",null);
        assertEquals(false,res);
    }


    
        
    @Before
    public void before() throws Exception { 
    } 

    @After
    public void after() throws Exception { 
    }
} 
