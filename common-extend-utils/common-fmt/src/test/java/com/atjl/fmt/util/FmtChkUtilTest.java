package com.atjl.fmt.util; 

import org.junit.*;
import org.junit.rules.ExpectedException;


public class FmtChkUtilTest {


    
    @Test
    public void testTemplateValid() throws Exception {
        String template = "欢迎\n${name哈哈file好";
        boolean res = FmtChkUtil.validTemplate(template);
        System.out.println("res:"+res);
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
