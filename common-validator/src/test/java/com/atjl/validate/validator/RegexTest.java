package com.atjl.validate.validator; 

import com.atjl.validate.api.StringField;
import com.atjl.validate.validator.oneparam.Regex;
import org.junit.*;
import org.junit.rules.ExpectedException;


public class RegexTest {


    
    @Test
    public void testValidate() throws Exception { 
        Regex r = new Regex("^-*\\d+$");

        StringField f = new StringField("aa");
        f.setRawValue("123s");
        r.validate(null,f);

    }
    
      
    @Test
    public void testInit() throws Exception { 
                /* 
                try { 
                   Method method = Regex.getClass().getMethod("init", String.class, String.class); 
                   method.setAccessible(true); 
                   method.invoke(<Object>, <Parameters>); 
                } catch(NoSuchMethodException e) { 
                } catch(IllegalAccessException e) { 
                } catch(InvocationTargetException e) { 
                } 
                */ 
            }
      
    @Test
    public void testChk() throws Exception { 
                /* 
                try { 
                   Method method = Regex.getClass().getMethod("chk"); 
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
