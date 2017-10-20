package com.atjl.util.reflect; 

import com.atjl.util.common.ReflectUtil;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.*;

import static org.junit.Assert.assertEquals;


public class ReflectClassUtilTest {

    @Test
    public void testChkAssigned() throws Exception {
        //interface
        Class clz = List.class;
        Class aclz = ArrayList.class;
        boolean a = ReflectClassUtil.chkAImplementB(aclz, clz);
        assertEquals(true, a);
        //abstract class
        Class abclz = AbstractList.class;
        boolean b = ReflectClassUtil.chkAImplementB(aclz, abclz);
        assertEquals(true, b);
        //class
        Class ca = Vector.class;
        Class cb = Stack.class;
        boolean c = ReflectClassUtil.chkAImplementB(cb, ca);
        assertEquals(true, c);
        //error
        ca = Vector.class;
        cb = Stack.class;
        c = ReflectClassUtil.chkAImplementB(ca, cb);
        assertEquals(false, c);

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
    public void testChkAImplementBForAB() throws Exception { 
        
    }
    
    @Test
    public void testChkAImplementBListForAB() throws Exception { 
        
    }
    
    @Test
    public void testGetClassSimpleName() throws Exception { 
        
    }
    
        
    @Before
    public void before() throws Exception { 
    } 

    @After
    public void after() throws Exception { 
    }
    
    @BeforeClass
    public static void beforeClass() throws Exception{
        
    }
    
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();
} 
