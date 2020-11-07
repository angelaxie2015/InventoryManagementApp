import java.io.*;

import java.lang.Object;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;

import org.apache.poi.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;


/*

    TODO:
    1. 更多的表格
    2. 表格的格式是否都是一样的
    3. input=ITEM# output=？
    4. 想要新的excel还是直接在program里search

    1. ITEM#




 */

public class ExcelReader {
    public static void main(String args[]) {
        String pattern = "MM/dd/yyyy";
        DateFormat df = new SimpleDateFormat(pattern); //formatting the date

        //一：选择load

        //1. put all the files in a folder

        //2. store all the excel files in a string array as the names of the files;
        File f = new File("input/");

        String[] files = f.list();
        for (String name : files)
            System.out.println(name);

        //3. for loop to go through the string array, 存储
        for (int i = 0; i < files.length; i++) {
            String cellDate;
            String containerNO;
            String etaVal;
            String poNO;
            boolean findETA = false;
            boolean findContainer = false;
            boolean findPO = false;
            boolean findItem = false;
            boolean findPc = false;
            int itemRow;
            int itemCol;
            int pcRow;
            int pcCol;


            String fileName = "input/" + files[i];
            FileInputStream fis = null;
            Workbook wb = null;

            try {
                fis = new FileInputStream(new File(fileName));
                wb = new XSSFWorkbook(fis);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //i. store string for the date


            XSSFSheet sheet = (XSSFSheet) wb.getSheetAt(0);
            for (Row row : sheet) {
                for (Cell cell : row) {
                    switch (cell.getCellTypeEnum()) {
                        case NUMERIC:
                            break;
                        case STRING:
                            //finding the date
<<<<<<< HEAD
                            if (cell.getStringCellValue().contains("ETA") || cell.getStringCellValue().contains("eta")) {
                                System.out.print("ETA: ");
                                int tempCol = cell.getAddress().getColumn() + 1;
                                Cell temp = findCellCol(row, tempCol);
=======
//                            if(cell.getStringCellValue().contains("Date") || cell.getStringCellValue().contains("DATE")) {
//                                System.out.print(cell.getStringCellValue());
//                                int colDate = cell.getAddress().getColumn() + 1;
//                                Cell temp = row.getCell(colDate);
//                                cellDate = df.format(temp.getDateCellValue());
//                                System.out.println(cellDate);
//                                findDate = true;
//                            }
                            //finding the date
                            //finding the date
                            if(cell.getStringCellValue().contains("ETA") || cell.getStringCellValue().contains("eta")) {
                                System.out.print(cell.getStringCellValue() + ": ");
                                int colDate = cell.getAddress().getColumn() + 1;
                                Cell temp = row.getCell(colDate);
                                while (temp == null || temp.getCellTypeEnum() == CellType.BLANK)
                                {
                                    temp = row.getCell(colDate++);
                                }
                                if (temp.getStringCellValue().toString().contains(":"))
                                    temp = row.getCell(colDate++);
>>>>>>> master
                                cellDate = df.format(temp.getDateCellValue());
                                System.out.println(cellDate);
                                findETA = true;
                            }

                            //finding the container number
                            if (cell.getStringCellValue().contains("CONTAINER") || cell.getStringCellValue().contains("CNTR")) {
                                System.out.print("CONTAINER NUMBER: ");
                                int tempCol = cell.getAddress().getColumn() + 1;
                                Cell temp = findCellCol(row, tempCol);
                                containerNO = temp.getStringCellValue();
                                System.out.println(containerNO);
                                findContainer = true;
                            }
                            //finding PO#
                            if (cell.getStringCellValue().contains("PO #") || cell.getStringCellValue().contains("PO#") || cell.getStringCellValue().toUpperCase().contains("PURCHASE ORDER")) {
                                System.out.println();
                                System.out.print("PURCHASE ORDER:");
                                int tempCol = cell.getAddress().getColumn() + 1;
                                Cell temp = findCellCol(row, tempCol);
                                poNO = temp.getStringCellValue();
                                System.out.println(poNO);
                                findPO = true;
                                break;
                            }
                            //find item row and column
                            if (cell.getCellTypeEnum() == CellType.STRING && cell.getStringCellValue().toUpperCase().contains("ITEM")) {
                                System.out.println(cell.getStringCellValue());
                                itemCol = cell.getColumnIndex();
                                itemRow = cell.getRowIndex(); //do we really need this?
                                findItem = true;
                            }
                            //find pcs row and column
                            if (cell.getCellTypeEnum() == CellType.STRING && cell.getStringCellValue().toUpperCase().contains("PCS")) {
                                System.out.println(cell.getStringCellValue());
                                pcCol = cell.getColumnIndex();
                                pcRow = cell.getRowIndex(); //do we really need this?
                                findPc = true;
                            }

                            if (findETA && findPO && findContainer && findItem && findPc)
                                break;
                    }
                    if (findETA && findPO && findContainer && findItem && findPc)
                        break;
                }
            }


            //i. look for item --> store in a string array of all the items.
            //item对应一个class --》date，Container，PCS
            //-> 对应的列求出来
            //-> 存储每一列--》global array or new excel sheet (update: 每个月的单子不是一起来的，所以还是要做成arraylist)


        }

        // 二： load 好了，选择search

        //4. search excel sheet


        //finding the column of the cell with information looking for
    }
    public static Cell findCellCol (Row row,int column){
        Cell temp = row.getCell(column);
        while (temp == null || temp.getCellTypeEnum() == CellType.BLANK) //in case there's empty cell between "CNTR" and ":"
        {
            temp = row.getCell(column++);
        }
        if (temp.getCellTypeEnum() == CellType.STRING && temp.getStringCellValue().contains(":"))
            temp = row.getCell(column++);
        while (temp == null || temp.getCellTypeEnum() == CellType.BLANK) //in case there's empty cell between ":" and number
        {
            temp = row.getCell(column++);
        }
        return temp;
    }

}
