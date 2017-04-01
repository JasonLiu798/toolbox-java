package com.jason798.common; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After; 

public class SystemHelperTest {


    
    @Test
    public void testSleep() throws Exception { 
        
    }
    
    @Test
    public void testGetPid() throws Exception { 
        String pid = SystemUtil.getPid();
        System.out.println(pid);
    }
    
        
    @Before
    public void before() throws Exception { 
    } 

    @After
    public void after() throws Exception { 
    }
} 
