package com.atjl.office.util;

import com.atjl.util.file.FileUtil;
import org.junit.*;
import org.junit.rules.ExpectedException;


public class Html2WordUtilTest {


    @Test
    public void testHtmlToWord() throws Exception {
        String content = FileUtil.cat("E:\\a.html");
        Html2WordUtil.htmlToWord(content, "e:\\1.docx");

    }


    @Test
    public void testInputStreamToWord() throws Exception { 
                /* 
                try { 
                   Method method = Html2WordUtil.getClass().getMethod("inputStreamToWord", InputStream.class, OutputStream.class); 
                   method.setAccessible(true); 
                   method.invoke(<Object>, <Parameters>); 
                } catch(NoSuchMethodException e) { 
                } catch(IllegalAccessException e) { 
                } catch(InvocationTargetException e) { 
                } 
                */
    }

    @Test
    public void testGetContent() throws Exception { 
                /* 
                try { 
                   Method method = Html2WordUtil.getClass().getMethod("getContent", InputStream....class); 
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
    public static void beforeClass() throws Exception {

    }

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();
} 
