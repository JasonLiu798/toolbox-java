package com.jason.excel;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

/**
 * http://poi.apache.org/spreadsheet/quick-guide.html
 *
 * @author JianLong
 * @date 2014/11/23 15:22
 */
public class Excel {

    private static final Logger Log = LoggerFactory.getLogger(Excel.class);

    /**
     * work book
     */
    private HSSFWorkbook workbook;

    public Excel(String file) {
        try {
            workbook = new HSSFWorkbook(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<List<String>> getSheet(int idx){
        Sheet sheet = workbook.getSheetAt(idx);
        return sheet2List(sheet);
    }

    public List<List<String>> getSheet(String name){
        Sheet sheet = workbook.getSheet(name);
        return sheet2List(sheet);
    }

    public List<List<String>> sheet2List(Sheet sheet){
        List<List<String>> res = new ArrayList<>();
        for (Row row : sheet) {
            List<String> rowList = new ArrayList<>();
            for (Cell cell : row) {
                String content = null;
                Log.debug("cell type "+cell.getCellType() );
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

    /**
     * read xsl file
     *                MultiSheet    => List<List<List<String>>>
     * sheet1         OneSheet      => List<List<String>>
     *  cell,cell...  OneRow        => List<String>
     *  cell,cell...
     *
     *
     * @param readFile
     */
    public static List<List<List<String>>> readXLS(String readFile,int idx) {
        List<List<List<String>>> res = new ArrayList<>();
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(readFile));
//            HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
//            HSSFSheet sheet = workbook.getSheetAt(0);
            int sheetCount = workbook.getNumberOfSheets();
            Log.debug("sheet count "+sheetCount );
            for(int i=0;i<sheetCount;i++){
                List<List<String>> sheet = new ArrayList<>();
                for (Row row : workbook.getSheetAt(i)) {
                    List<String> rowList = new ArrayList<>();
                    for (Cell cell : row) {
                        String content = null;
                        Log.debug("cell type "+cell.getCellType() );
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


    /**
     * crateXSL
     *
     * @throws IOException
     */
    public void crateXSL(String filename) throws IOException {
        Workbook wb = new HSSFWorkbook();
        FileOutputStream fileOut = new FileOutputStream(
                filename);
        Sheet sheet1 = wb.createSheet("new sheet");
        Sheet sheet2 = wb.createSheet("second sheet");
        // 设置sheet的标题
        Header header = sheet1.getHeader();
        header.setCenter("Center Header");
        header.setLeft("Left Header");
        header.setRight(HSSFHeader.font("Stencil-Normal", "Italic")
                + HSSFHeader.fontSize((short) 16)
                + "Right w/ Stencil-Normal Italic font and size 16");

        // 合并单元格
        sheet1.groupRow(5, 14);
        sheet1.groupRow(7, 14);
        sheet1.groupRow(16, 19);

        sheet1.groupColumn((short) 4, (short) 7);
        sheet1.groupColumn((short) 9, (short) 12);
        sheet1.groupColumn((short) 10, (short) 11);

        sheet1.addMergedRegion(new CellRangeAddress(1, // first row (0-based)
                1, // last row (0-based)
                1, // first column (0-based)
                2 // last column (0-based)
        ));

        // 设置图片
        // Create the drawing patriarch. This is the top level container for all
        // shapes.
        // Drawing drawing = sheet1.createDrawingPatriarch();
        //
        // //add a picture shape
        // ClientAnchor anchor = helper.createClientAnchor();
        // //set top-left corner of the picture,
        // //subsequent call of Picture#resize() will operate relative to it
        // anchor.setCol1(3);
        // anchor.setRow1(2);
        // Picture pict = drawing.createPicture(anchor, pictureIdx);
        //
        // //auto-size picture relative to its top-left corner
        // pict.resize();

        String sname = "TestSheet", cname = "TestName";//, cvalue = "TestVal";
        Name namedCell = wb.createName();
        namedCell.setNameName(cname);
        String reference = sname + "!A1:A1"; // area reference
        namedCell.setRefersToFormula(reference);

        // 2. create named range for a single cell using cellreference
        // Name namedCel2 = wb.createName();
        // namedCel2.setNameName(cname);
        // reference = sname+"!A1"; // cell reference
        // namedCel2.setRefersToFormula(reference);
        //
        // // 3. create named range for an area using AreaReference
        // Name namedCel3 = wb.createName();
        // namedCel3.setNameName(cname);
        // reference = sname+"!A1:C5"; // area reference
        // namedCel3.setRefersToFormula(reference);
        //
        // // 4. create named formula
        // Name namedCel4 = wb.createName();
        // namedCel4.setNameName("my_sum");
        // namedCel4.setRefersToFormula("SUM(sname+!$I$2:$I$6)");

        // Note that sheet name is Excel must not exceed 31 characters
        // and must not contain any of the any of the following characters:
        // 0x0000
        // 0x0003
        // colon (:)
        // backslash (\)
        // asterisk (*)
        // question mark (?)
        // forward slash (/)
        // opening square bracket ([)
        // closing square bracket (])

        // You can use
        // org.apache.poi.ss.util.WorkbookUtil#createSafeSheetName(String
        // nameProposal)}
        // for a safe way to create valid names, this utility replaces invalid
        // characters with a space (' ')"

        //设置单元格值
        // Create a row and put some cells in it. Rows are 0 based.
        Row row = sheet1.createRow((short) 0);
        // Create a cell and put a value in it.
        Cell cell = row.createCell(0);
        cell.setCellValue(1);

        // Or do it on one line.
        row.createCell(1).setCellValue(1.2);
        row.createCell(2).setCellValue("This is a string");
        row.createCell(3).setCellValue(true);

        Row row2 = sheet2.createRow(0);

        // Create a cell and put a date value in it. The first cell is not
        // styled
        // as a date.
        Cell cell2 = row2.createCell(0);
        cell2.setCellValue(new Date());

        // we style the second cell as a date (and time). It is important to
        // create a new cell style from the workbook otherwise you can end up
        // modifying the built in style and effecting not only this cell but
        // other cells.
        //设置单元格日期格式
        CellStyle cellStyle = wb.createCellStyle();
        DataFormat format = wb.createDataFormat();
        cellStyle.setDataFormat(format.getFormat("yyyy/mm/dd"));
        // style.setDataFormat(format.getFormat("#,##0.0000"));

        //设置单元格字体
        Font font = wb.createFont();
        font.setFontHeightInPoints((short) 24);
        font.setFontName("Courier New");
        font.setItalic(true);
        font.setStrikeout(true);
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);

        cellStyle.setFont(font);
        cellStyle.setWrapText(true);

        cell2 = row2.createCell(1);
        cell2.setCellValue(new Date());
        cell2.setCellStyle(cellStyle);

        // you can also set date as java.util.Calendar
        cell2 = row2.createCell(2);
        cell2.setCellValue(Calendar.getInstance());
        cell2.setCellStyle(cellStyle);

        // 註釋
        // Create the comment and set the text+author
        CreationHelper factory = wb.getCreationHelper();
        // When the comment box is visible, have it show in a 1x3 space
        ClientAnchor anchor = factory.createClientAnchor();
        anchor.setCol1(cell.getColumnIndex());
        anchor.setCol2(cell.getColumnIndex() + 1);
        anchor.setRow1(row.getRowNum());
        anchor.setRow2(row.getRowNum() + 3);
        Drawing drawing = sheet1.createDrawingPatriarch();
        Comment comment = drawing.createCellComment(anchor);
        RichTextString str = factory.createRichTextString("Hello, World!");
        comment.setString(str);
        comment.setAuthor("Apache POI");

        // Assign the comment to the cell
        cell.setCellComment(comment);

        // Hyperlink link = createHelper.createHyperlink(Hyperlink.LINK_URL);
        // link.setAddress("http://poi.apache.org/");
        // cell.setHyperlink(link);
        // cell.setCellStyle(cellStyle);

        Row row3 = sheet2.createRow((short) 2);
        row3.createCell(0).setCellValue(1.1);
        row3.createCell(1).setCellValue(new Date());
        row3.createCell(2).setCellValue(Calendar.getInstance());
        row3.createCell(3).setCellValue("a string");
        row3.createCell(4).setCellValue(true);
        row3.createCell(5).setCellType(Cell.CELL_TYPE_ERROR);

        //获取sheet的每个单元格的值
        Sheet sheet = wb.getSheetAt(0);
        for (Iterator<Row> rit = sheet.rowIterator(); rit.hasNext(); ) {
            Row row4 = rit.next();
            for (Iterator<Cell> cit = row4.cellIterator(); cit.hasNext(); ) {
                Cell cell4 = cit.next();
                // do something here
            }
        }

        Sheet sheet5 = wb.getSheetAt(1);
        for (Row row5 : sheet5) {
            for (Cell cell5 : row5) {
                CellReference cellRef = new CellReference(row5.getRowNum(),
                        cell5.getColumnIndex());
                System.out.print(cellRef.formatAsString());
                System.out.print(" - ");

                switch (cell5.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        System.out.println(cell5.getRichStringCellValue()
                                .getString());
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        if (DateUtil.isCellDateFormatted(cell5)) {
                            System.out.println(cell5.getDateCellValue());
                        } else {
                            System.out.println(cell5.getNumericCellValue());
                        }
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        System.out.println(cell5.getBooleanCellValue());
                        break;
                    case Cell.CELL_TYPE_FORMULA:
                        System.out.println(cell5.getCellFormula());
                        break;
                    default:
                        System.out.println();
                }
            }
        }

        wb.write(fileOut);
        fileOut.close();

    }

    public void test2() throws IOException {
        Workbook wb = new XSSFWorkbook();
        FileOutputStream fileOut = new FileOutputStream("workbook.xlsx");
        wb.write(fileOut);
        fileOut.close();
    }




}


