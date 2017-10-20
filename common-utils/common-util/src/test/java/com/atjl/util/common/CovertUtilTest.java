package com.atjl.util.common; 

import org.junit.*;
import org.junit.rules.ExpectedException;


public class CovertUtilTest {


    
    @Test
    public void testPrim2box() throws Exception { 
        Long l = CovertUtil.covert("2",Long.class);
        System.out.println("res:"+l);
    }
    
    @Test
    public void testCovertObjForRawValTgtClz() throws Exception { 
        Class clz = CovertUtil.prim2box(int.class);
        System.out.println("res:"+clz);
    }
    
    @Test
    public void testCovertObjForRawValTgtClzDft() throws Exception { 
        
    }
    
    @Test
    public void testCovertObjForRawValTgtClzDftSep() throws Exception { 
        
    }
    
    @Test
    public void testCovertForRawValTgtClz() throws Exception { 
        
    }
    
    @Test
    public void testCovertForRawValTgtClzDft() throws Exception { 
        
    }
    
    @Test
    public void testCovertForRawValTgtClzDftSep() throws Exception { 
        
    }
    
      
    @Test
    public void testGenParseFuncName() throws Exception { 
                /* 
                try { 
                   Method method = CovertUtil.getClass().getMethod("genParseFuncName", Class.class); 
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
    public static void beforeClass() throws Exception{
        
    }
    
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();
} 
