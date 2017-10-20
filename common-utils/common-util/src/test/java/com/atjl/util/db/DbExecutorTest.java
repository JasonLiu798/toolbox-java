package com.atjl.util.db;

import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.List;


public class DbExecutorTest {


    @Test
    public void testGetTableData() throws Exception {
        DataSourceUtil.initDataSource();
        List res = DbExecutor.getTableData(DataSourceUtil.getDataSource(), "select * from aa limit 1");
        System.out.println(res);
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
