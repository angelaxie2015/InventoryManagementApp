import java.io.*;

import java.lang.Object;
import org.apache.poi.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;


/*

    TODO:
    1. 更多的表格
    2. 表格的格式是否都是一样的
    3. input=ITEM# output=？
    4. 想要新的excel还是直接在program里search




 */

public class ExcelReader {
    public static void main(String args[]){

    //一：选择load

        //1. put all the files in a folder

        //2. store all the excel files in a string array as the names of the files;
        File f = new File("input/");

        String[] files = f.list();
        for(String name: files)
            System.out.println(name);

        //3. for loop to go through the string array, 存储
        for(int i = 0; i < files.length; i++) {
            String fileName = "input/" + files[i];
            FileInputStream fis = null;
            Workbook wb = null;

            try {
                fis = new FileInputStream(new File(fileName));
                wb = new XSSFWorkbook(fis);
            } catch(IOException e){
                e.printStackTrace();
            }
            //i. store string for the date


            XSSFSheet sheet = (XSSFSheet) wb.getSheetAt(0);
            for(Row row: sheet){
                for(Cell cell: row){
                    if(cell.equals("Date") || cell.equals("DATE"))
                        System.out.println(cell);

                }
            }

//            sheet.getRow(8).getCell(2).setCellFormula("SUM(C5:C7) + SUM(C5:C7) * 0.1");
//
//            inputStream.close();
//
//            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
//            workbook.write(outputStream);
//            workbook.close();
//            outputStream.close();






            //ii. store string for container

            //i. look for item --> store in a string array of all the items.
            //item对应一个class --》date，Container，PCS
            //-> 对应的列求出来
            //-> 存储每一列--》global array or new excel sheet

        }

    // 二： load 好了，选择search

        //4. search excel sheet


    }

}
