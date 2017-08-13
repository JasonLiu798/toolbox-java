package com.atjl.util.common;

import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;


public class PathUtilTest {


    @Test
    public void testParse(){
        String sep = "/";
//        PathUtil.parseInner(sep,sep+"a"+sep+"b"+sep+"c");
        PathUtil.parseInner(sep,sep);
    }
    
    @Test
    public void testJoinPath() throws Exception { 
//        String res = PathUtil.joinInner("/","a","b");
        String sep = "/";
        String res = PathUtil.joinInner(true,sep,"");
        assertEquals("/",res);

        res = PathUtil.joinInner(true,sep,"/");
        assertEquals("/",res);

        res = PathUtil.joinInner(true,sep,"/","/a");
        assertEquals("/a",res);

        res = PathUtil.joinInner(true,sep,"/","a");
        assertEquals("/a",res);

        res = PathUtil.joinInner(true,sep,"","a","b");
        assertEquals("/a/b",res);

        res = PathUtil.joinInner(true,sep,"");
        assertEquals("/",res);

        res = PathUtil.joinInner(true,sep,"","/bb");
        assertEquals("/bb",res);

        res =PathUtil.joinInner(true,sep,"","/","");
        assertEquals("/",res);

        res = PathUtil.joinInner(true,sep,"/abc","/","");
        assertEquals("/abc",res);

        res = PathUtil.joinInner(true,sep,"abc","bb","cc");
        assertEquals("/abc/bb/cc",res);

    }
    
    @Test
    public void testJoin() throws Exception {
        String sep = "\\";
        String res = PathUtil.join("");
        assertEquals("\\",res);

        res = PathUtil.join("\\","\\a");
        assertEquals("\\a",res);

        res = PathUtil.join(sep,"a");
        assertEquals(sep+"a",res);

        res = PathUtil.join("","a","b");
        assertEquals(sep+"a"+sep+"b",res);

        res = PathUtil.join("",sep+"bb");
        assertEquals(sep+"bb",res);

        res =PathUtil.join("",sep,"");
        assertEquals(sep,res);

        res = PathUtil.join(sep+"abc",sep,"");
        assertEquals(sep+"abc",res);

        res = PathUtil.join("abc","bb","cc");
        assertEquals(sep+"abc"+sep+"bb"+sep+"cc",res);
    }
    
    @Test
    public void testFilterPathForSepPaths() throws Exception { 
        
    }
    
    @Test
    public void testFilterPathForSepS() throws Exception { 
        
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
