package com.atjl.office.util;


import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.util.List;

public class ExcelWriteUtil {
    private ExcelWriteUtil() {
        throw new UnsupportedOperationException();
    }


    public static void writeExcel(List<List<String>> rowlist, Workbook wb) throws IOException {
        writeExcel(rowlist, wb, 0);
    }

    public static void writeExcel(List<List<String>> rowlist, Workbook wb, int sheetIndex)
            throws IOException {
        Sheet sheet = wb.getSheetAt(sheetIndex);
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setWrapText(true);
        for (int rowNum = 1; rowNum <= rowlist.size(); rowNum++) {
            Row row = sheet.getRow(rowNum);
            if (row == null) {
                row = sheet.createRow(rowNum);
            }
            List<String> list = rowlist.get(rowNum - 1);
            for (int i = 0; i < list.size(); i++) {
                String value = list.get(i);
                Cell cell = row.getCell(i);
                if (cell == null) {
                    cell = row.createCell(i);
                }
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(value == null ? "" : value);
            }
        }
    }


}
