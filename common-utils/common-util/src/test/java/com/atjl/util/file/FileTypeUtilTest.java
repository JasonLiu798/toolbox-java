package com.atjl.util.file;

import org.junit.*;
import org.junit.rules.ExpectedException;


public class FileTypeUtilTest {

    @Test
    public void testGetFileType() throws Exception {
        String ft = FileTypeUtil.getFileType("D:\\a.pdf");
        System.out.println("res:" + ft);
    }

    @Test
    public void testGetFileHeader() throws Exception {

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
