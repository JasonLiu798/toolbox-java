package com.atjl.util.common;

import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;


public class PageUtilTest {


    @Test
    public void testGetPageCount() throws Exception {
        int total = 10;
        int pageSize = 4;
        int cnt = PageUtil.getPageCount(total, pageSize);
        assertEquals(3, cnt);
        cnt = PageUtil.getPageCount(10, 5);
        assertEquals(2, cnt);
        cnt = PageUtil.getPageCount(10, 6);
        assertEquals(2, cnt);
        cnt = PageUtil.getPageCount(10, 10);
        assertEquals(1, cnt);
        cnt = PageUtil.getPageCount(10, 11);
        assertEquals(1, cnt);

        cnt = PageUtil.getPageCount(0, 11);
        assertEquals(0, cnt);

        cnt = PageUtil.getPageCount(10, 0);
        assertEquals(0, cnt);

        cnt = PageUtil.getPageCount(10, 1);
        assertEquals(10, cnt);
    }

    @Test
    public void testGetPageCountLong() throws Exception {
        long total = 10;
        long pageSize = 4;
        long cnt = PageUtil.getPageCountLong(total, pageSize);
        assertEquals(3, cnt);
        cnt = PageUtil.getPageCountLong(10, 5);
        assertEquals(2, cnt);
        cnt = PageUtil.getPageCountLong(10, 6);
        assertEquals(2, cnt);
        cnt = PageUtil.getPageCountLong(10, 10);
        assertEquals(1, cnt);
        cnt = PageUtil.getPageCountLong(10, 11);
        assertEquals(1, cnt);
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
