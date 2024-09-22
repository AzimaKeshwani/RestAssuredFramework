package api.utilities;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class XLUtility {

    public FileInputStream fileInputStream;
    public FileOutputStream fileOutputStream;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFCell cell;

    public XSSFRow row;
    public CellStyle style;
    String path;

    public XLUtility(String path) {
        this.path = path;
    }

    public int getRowCount(String sheetName) throws IOException {
        fileInputStream = new FileInputStream(path);
        workbook = new XSSFWorkbook(fileInputStream);
        sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum();
        workbook.close();
        fileInputStream.close();
        return rowCount;
    }

    public int getCellCount(String sheetName, int rownum) throws IOException {
        fileInputStream = new FileInputStream(path);
        workbook = new XSSFWorkbook(fileInputStream);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        int cellCount = row.getLastCellNum();
        workbook.close();
        fileInputStream.close();
        return cellCount;
    }

    // Method to read data from an Excel file
    public String readCellData(String sheetIndex, int rowIndex, int cellIndex) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(path));
        workbook = new XSSFWorkbook(fileInputStream);
        sheet = workbook.getSheet(sheetIndex);
        row = sheet.getRow(rowIndex);
        cell = row.getCell(cellIndex);
        String cellValue = cell.toString();
        workbook.close();
        fileInputStream.close();
        return cellValue;
    }

    // Method to write data to an Excel file
    public void writeCellData(int sheetIndex, int rowIndex, int cellIndex, String data) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(path));
        workbook = new XSSFWorkbook(fileInputStream);
        sheet = workbook.getSheetAt(sheetIndex);
        if (sheet == null) {
            sheet = workbook.createSheet();
        }
        row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }
        cell = row.getCell(cellIndex);
        if (cell == null) {
            cell = row.createCell(cellIndex);
        }
        cell.setCellValue(data);

        fileInputStream.close();
        fileOutputStream = new FileOutputStream(path);
        workbook.write(fileOutputStream);
        workbook.close();
        fileOutputStream.close();
    }


}



