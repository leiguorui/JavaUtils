package com.renrennet.utils.file;

import java.io.*;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Created by leiguorui on 12/23/14.
 *
 * 读取excel，文件类型是.xlsx
 */
public class ReadExcelFile {
    public static void main(String[] args){
        try {
            FileInputStream file = new FileInputStream(new File("C:/Users/1104.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            //获取sheet
            XSSFSheet sheet = workbook.getSheetAt(0);
            //遍历sheet，获取行
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            while(rowIterator.hasNext()){
                Row row = rowIterator.next();
                //遍历单元格
                Iterator<Cell> cellIterator = row.cellIterator();
                while(cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    switch(cell.getCellType()){
                        case Cell.CELL_TYPE_BOOLEAN:
                            System.out.println("boolean===>>>"+cell.getBooleanCellValue() + "\t");
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.println("numeric===>>>"+cell.getNumericCellValue() + "\t");
                            break;
                        case Cell.CELL_TYPE_STRING:
                            System.out.println("String===>>>"+cell.getStringCellValue() + "\t");
                            break;
                    }
                }
                System.out.println("");
            }
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
