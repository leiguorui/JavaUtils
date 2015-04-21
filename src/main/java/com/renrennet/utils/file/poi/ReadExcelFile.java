package com.renrennet.utils.file.poi;

import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

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
            FileInputStream file = new FileInputStream(new File("C:\\Documents and Settings\\Administrator\\桌面\\hello.xlsx"));
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
                System.out.println("------------------------");
            }
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<Integer, Object[]> readExcelToList(String fileName) {
        Map<Integer, Object[]> result = new TreeMap<Integer, Object[]>();

        try {
            //Create the input stream from the xlsx/xls file
            FileInputStream file = new FileInputStream(new File("D:\\dev\\workspace_cy\\avg\\src\\main\\java\\excel/"+fileName+".xlsx"));

            XSSFWorkbook workbook = new XSSFWorkbook(file);
            //获取sheet
            XSSFSheet sheet = workbook.getSheetAt(0);
            //遍历sheet，获取行
            Iterator<Row> rowIterator = sheet.iterator();
            int lineNo = 0 ;
            while(rowIterator.hasNext()){
                Row row = rowIterator.next();
                Object[] lineData = new Object[row.getLastCellNum()+1] ;
                //遍历单元格
                Iterator<Cell> cellIterator = row.cellIterator();
                while(cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
//                    if(cell != null){
//                        cell.setCellType(1);
//                        lineData[cell.getColumnIndex()] = cell.getStringCellValue();
//                    }
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
                result.put(lineNo++, lineData);
            }
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

}
