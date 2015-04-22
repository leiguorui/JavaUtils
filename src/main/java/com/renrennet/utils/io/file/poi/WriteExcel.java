package com.renrennet.utils.io.file.poi;

import com.renrennet.utils.beans.UserModelForTest;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

/**
 * 导出list到excel，文件类型是.xlsx
 * User: leiguorui
 * Date: 15-3-12
 * Time: 下午6:09
 */
public class WriteExcel {

    public static void writeExcel(String fileName){
        List<UserModelForTest> listForWrite = new ArrayList<>();  //要写入excel的list
        for(int id = 0; id < 10 ; id++){
            UserModelForTest user = new UserModelForTest();
            user.setId(id);
            user.setName("UserName"+id);
            user.setAge(id+10);
            listForWrite.add(user);
        }

        int lineNo = 0;
        //This data needs to be written (Object[])
        Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>();
        for(UserModelForTest user:listForWrite){
            data.put(lineNo, new Object[] {user.getId(), user.getName(),user.getName()});
            lineNo++;
        }

        System.err.println("---------开始写excel---------");

        //Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("要创建的sheet名称");

        //Iterate over data and write to sheet
        int rownum = 0;
        Iterator<Map.Entry<Integer, Object[]>> it = data.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<Integer, Object[]> entry=it.next();
            Row row = sheet.createRow(rownum++);
            Object[] objArr = entry.getValue();
            int cellnum = 0;
            for (Object obj : objArr){
                Cell cell = row.createCell(cellnum++);
                if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }
        try{
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File("/"+fileName+".xlsx"));
            workbook.write(out);
            out.close();
            System.err.println("/"+fileName+".xlsx written successfully on disk.");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}