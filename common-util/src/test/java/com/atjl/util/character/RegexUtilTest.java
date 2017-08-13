package com.atjl.util.character;

import org.junit.*;
import org.junit.rules.ExpectedException;


public class RegexUtilTest {


    
    @Test
    public void testLeftNumStrCNUL() throws Exception { 
//        String dig = RegexUtil.getDigits("char(123)");
        String dig = RegexUtil.getDigits("timestamp");
        System.out.println("res:"+dig);


    }
    
    @Test
    public void testGetMatcher() throws Exception { 
        
    }
    
    @Test
    public void testGetPattern() throws Exception { 
        
    }
    
    @Test
    public void testIsMobile() throws Exception { 
        
    }
    
    @Test
    public void testIsEmail() throws Exception { 
        
    }
    
    @Test
    public void testGetDigits() throws Exception { 
        
    }
    
    @Test
    public void testIsDigit() throws Exception { 
        
    }
    
    @Test
    public void testIsPositive() throws Exception { 
        
    }
    
    @Test
    public void testIsNatural() throws Exception { 
        
    }
    
    @Test
    public void testIsInteger() throws Exception { 
        
    }
    
    @Test
    public void testIsBoolean() throws Exception { 
        
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
