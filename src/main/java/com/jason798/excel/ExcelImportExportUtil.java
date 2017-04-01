package com.jason798.excel;

import com.jason798.character.StringCheckUtil;
import com.jason798.character.StringUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ExcelImportExportUtil {
    
    public static XSSFWorkbook getXSSFWorkbook(File file) throws Exception {
        return new XSSFWorkbook(file);
    }
    
    public static XSSFWorkbook getXSSFWorkbook(InputStream in) throws Exception {
        return new XSSFWorkbook(in);
    }
    
    public static void writeExcel(List<List<String>> rowlist, Workbook wb)
    		throws IOException {
    	writeExcel(rowlist, wb, 0);
    }

    public static void writeExcel(List<List<String>> rowlist, Workbook wb, int sheetIndex)
            throws IOException {
        Sheet sheet = wb.getSheetAt(sheetIndex);
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setWrapText(true);
        for (int rowNum=1; rowNum<=rowlist.size(); rowNum++) {
            Row row = sheet.getRow(rowNum);
            if (row == null) {
                row = sheet.createRow(rowNum);
            }
            List<String> list = rowlist.get(rowNum-1);
            for (int i=0; i<list.size(); i++) {
                String value = list.get(i);
                Cell cell = row.getCell(i);
                if (cell == null) {
                    cell = row.createCell(i);
                }
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(value==null?"":value);
            }
        }
    }


    public static List<List<Object>> readExcel(InputStream stream, String fileName) {
        String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);
        if ("xls".equals(extension)) {  
            return read2003Excel(stream);  
        } else if ("xlsx".equals(extension)) {  
            return read2007Excel(stream);  
        } else {
            throw new RuntimeException("不支持的文件类型!!");
        }
    }


    public static List<List<Object>> readExcel(File file, String fileName) {
        String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName.substring(fileName.lastIndexOf(".") + 1);
        if ("xls".equals(extension)) {  
            return read2003Excel(file);  
        } else if ("xlsx".equals(extension)) {  
            return read2007Excel(file);  
        } else {
            throw new RuntimeException("不支持的文件类型!!");
        }
    }

    private static List<List<Object>> getRowLst(Sheet sheet) {
        if(sheet.getPhysicalNumberOfRows() > 1000){
            throw new RuntimeException("每次只导入1000条记录!!");
        }
        List<List<Object>> rowlist = new LinkedList<>();
        for (Row row : sheet) {
            List<Object> values = new ArrayList<>();
            for (int i=0; i<row.getPhysicalNumberOfCells(); i++) {
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
            if (!isEmptyLine(values)) {
                rowlist.add(values);
            }
        }
        return rowlist;
    }

    private static boolean isEmptyLine(List<Object> values) {
        for (Object object : values) {
            if (object instanceof String) {
                if (!StringCheckUtil.isEmpty((String) object))
                    return false;
            } else {
                return false;
            }
        }
        return true;
    }


    private static List<List<Object>> read2007Excel(File file) {
        try {
            OPCPackage pkg = OPCPackage.open(file);
            Workbook wb = new XSSFWorkbook(pkg);
            Sheet sheet = wb.getSheetAt(0);
            try {
                return getRowLst(sheet);
            } finally {
                pkg.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static List<List<Object>> read2003Excel(File file) {
        try {
            NPOIFSFileSystem fs = new NPOIFSFileSystem(file);
            Workbook wb = new HSSFWorkbook(fs.getRoot(), true);
            Sheet sheet = wb.getSheetAt(0);
            try {
                List<List<Object>> rowlist = getRowLst(sheet);
                return rowlist;
            } finally {
                fs.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private static List<List<Object>> read2007Excel(InputStream stream) {
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


    private static List<List<Object>> read2003Excel(InputStream stream) {
        try {
            Workbook wb = new HSSFWorkbook(stream, true);
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

}
