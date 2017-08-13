package com.atjl.util.file;

import org.junit.*;
import org.junit.rules.ExpectedException;


public class PDFUtilTest {


    
    @Test
    public void testReadText() throws Exception {
        String file = "D:\\test.pdf";
        String res = PDFUtil.readText(file);
        System.out.println(res);
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
