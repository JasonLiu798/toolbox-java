package com.atjl.office.util;


import com.atjl.office.api.exception.ExcelException;
import com.atjl.util.character.StringUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ExcelReadUtil {
    private ExcelReadUtil() {
        throw new UnsupportedOperationException();
    }

    private static final Logger logger = LoggerFactory.getLogger(ExcelReadUtil.class);


    public static List<List<Object>> getRowLst(Sheet sheet) {
        if (sheet.getPhysicalNumberOfRows() > 1000) {
            throw new RuntimeException("每次只导入1000条记录!!");
        }
        List<List<Object>> rowlist = new LinkedList<>();
        for (Row row : sheet) {
            List<Object> values = new ArrayList<>();
            for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
                Cell cell = row.getCell(i);
                if (cell != null) {
                    Object value = "";
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            value = StringUtil.trim(cell.getRichStringCellValue().getString());
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                value = cell.getDateCellValue();
                            } else {
                                value = cell.getNumericCellValue();
                            }
                            break;
                        case Cell.CELL_TYPE_BOOLEAN:
                            value = cell.getBooleanCellValue();
                            break;
                        case Cell.CELL_TYPE_FORMULA:
                            value = StringUtil.trim(cell.getCellFormula());
                            break;
                        case Cell.CELL_TYPE_BLANK:
                            value = "";
                            break;
                        default:
                            value = "";
                    }
                    values.add(value);
                } else {
                    values.add("");
                }
            }
            if (!ExcelCommonUtil.isEmptyLine(values)) {
                rowlist.add(values);
            }
        }
        return rowlist;
    }

    public static List<List<Object>> read2007Excel(File file) {
        try (OPCPackage pkg = OPCPackage.open(file)) {
            Workbook wb = new XSSFWorkbook(pkg);
            Sheet sheet = wb.getSheetAt(0);
            return getRowLst(sheet);
        } catch (Exception e) {
            throw new ExcelException(e);
        }
    }

    public static List<List<Object>> read2003Excel(File file) {
        try (NPOIFSFileSystem fs = new NPOIFSFileSystem(file);) {
            Workbook wb = new HSSFWorkbook(fs.getRoot(), true);
            Sheet sheet = wb.getSheetAt(0);
            return getRowLst(sheet);
        } catch (Exception e) {
            throw new ExcelException(e);
        }
    }


    public static List<List<Object>> readExcelFromStream(InputStream stream) {
        try {
            Workbook wb = new XSSFWorkbook(stream);
            Sheet sheet = wb.getSheetAt(0);
            try {
                return getRowLst(sheet);
            } finally {
                stream.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//
//    public static List<List<Object>> read2003Excel(InputStream stream) {
//        try {
//            Workbook wb = new HSSFWorkbook(stream, true);
//            Sheet sheet = wb.getSheetAt(0);
//            try {
//                return getRowLst(sheet);
//            } finally {
//                stream.close();
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }


    /**
     * read xsl file
     * MultiSheet    => List<List<List<String>>>
     * sheet1         OneSheet      => List<List<String>>
     * cell,cell...  OneRow        => List<String>
     * cell,cell...
     */
    public static List<List<List<String>>> readXLS(String readFile, int idx) {
        List<List<List<String>>> res = new ArrayList<>();
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(readFile));
//            HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
//            HSSFSheet sheet = workbook.getSheetAt(0);
            int sheetCount = workbook.getNumberOfSheets();
            logger.debug("sheet count " + sheetCount);
            for (int i = 0; i < sheetCount; i++) {
                List<List<String>> sheet = new ArrayList<>();
                for (Row row : workbook.getSheetAt(i)) {
                    List<String> rowList = new ArrayList<>();
                    for (Cell cell : row) {
                        String content = null;
                        logger.debug("cell type " + cell.getCellType());
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_STRING:
                                content = cell.getRichStringCellValue().getString();
                                break;
                            case Cell.CELL_TYPE_NUMERIC:
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    content = String.valueOf(cell.getDateCellValue().getTime());
                                    //System.out.println(cell.getDateCellValue());
                                } else {
                                    //System.out.println(cell.getNumericCellValue());
                                    content = String.valueOf(cell.getNumericCellValue());
                                }
                                break;
                            case Cell.CELL_TYPE_BOOLEAN:
                                content = String.valueOf(cell.getBooleanCellValue());
                                //System.out.println(cell.getBooleanCellValue());
                                break;
                            case Cell.CELL_TYPE_FORMULA:
                                content = cell.getCellFormula();
                                //System.out.println(cell.getCellFormula());
                                break;
                            default:
                                System.out.println();
                        }
                        //System.out.println(content);
                        rowList.add(content);
                    }
                    sheet.add(rowList);
                }
                res.add(sheet);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}
