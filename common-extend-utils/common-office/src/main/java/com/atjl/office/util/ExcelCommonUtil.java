package com.atjl.office.util;


import com.atjl.util.character.StringCheckUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.List;

public class ExcelCommonUtil {
    private ExcelCommonUtil() {
        throw new UnsupportedOperationException();
    }


    public static boolean isEmptyLine(List<Object> values) {
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
    public List<List<String>> getSheet(HSSFWorkbook workbook, int idx) {
        Sheet sheet = workbook.getSheetAt(idx);
        return sheet2List(sheet);
    }

    public List<List<String>> getSheet(HSSFWorkbook workbook,String name) {
        Sheet sheet = workbook.getSheet(name);
        return sheet2List(sheet);
    }

    public List<List<String>> sheet2List(Sheet sheet) {
        List<List<String>> res = new ArrayList<>();
        for (Row row : sheet) {
            List<String> rowList = new ArrayList<>();
            for (Cell cell : row) {
                String content = null;
                //logger.debug("cell type " + cell.getCellType());
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
                System.out.println(content);
                rowList.add(content);
            }
            res.add(rowList);
        }
        return res;
    }

}
