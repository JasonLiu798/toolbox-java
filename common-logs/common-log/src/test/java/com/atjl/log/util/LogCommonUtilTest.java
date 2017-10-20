package com.atjl.log.util; 

import com.atjl.log.api.LogUtil;
import com.atjl.log.api.LogLevel;
import org.junit.*;
import org.junit.rules.ExpectedException;


public class LogCommonUtilTest {


    
    @Test
    public void testFilter2Json() throws Exception {
        LogUtil.setLevel(LogLevel.DEBUG);
        String res = LogCommonUtil.exception2str(new RuntimeException());
        System.out.println("res:"+res);
    }
    
    @Test
    public void testGetStackTrace() throws Exception { 
        
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
