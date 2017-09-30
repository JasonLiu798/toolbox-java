package com.atjl.office.api;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.io.FileOutputStream;


public class ExcelUtilTest {


    @Test
    public void testGetXSSFWorkbookFile() throws Exception {
        Workbook wb = new XSSFWorkbook();
        FileOutputStream fileOut = new FileOutputStream("workbook.xlsx");
        wb.write(fileOut);
        fileOut.close();
    }

    @Test
    public void testGetXSSFWorkbookIn() throws Exception {

    }

    @Test
    public void testReadExcelForStreamFileName() throws Exception {

    }

    @Test
    public void testReadExcelForFileFileName() throws Exception {

    }

    @Test
    public void testCrateXSL() throws Exception {

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
