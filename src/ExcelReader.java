import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import javax.swing.*;


/*

    TODO:

    1. title 和内容不能在一个格子
        e.g. : CNTR and the number itself
    2. 把CNTR等东西放到最开头为最好



    1. 更多的表格
    2. 表格的格式是否都是一样的
    3. input=ITEM# output=？
    4. 想要新的excel还是直接在program里search

    1. ITEM#




 */

public class ExcelReader {
    public static void main(String args[]) {

        //Creating the Frame
        JFrame frame = new JFrame("Search Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("FILE");//
        JMenu m2 = new JMenu("Help");//use this to open README
        mb.add(m1);
        mb.add(m2);
        JMenuItem m11 = new JMenuItem("Open");
        JMenuItem m22 = new JMenuItem("Save as");
        m1.add(m11);
        m1.add(m22);

        // Text Area at the Center


        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Enter item number:");
        JTextField tf = new JTextField(20); // accepts upto 10 characters
        JButton search = new JButton("Search");
        JButton reset = new JButton("Reset");
        JTextArea ta = new JTextArea();
        frame.getContentPane().add(BorderLayout.CENTER, ta);

        panel.add(label); // Components Added using Flow Layout
        panel.add(tf);
        panel.add(search);
        panel.add(reset);

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);

        frame.setVisible(true);


        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                String toFind = tf.getText();


                ArrayList<Product> products = new ArrayList<Product>();
                String pattern = "MM/dd/yyyy";
                DateFormat df = new SimpleDateFormat(pattern); //formatting the date

                //一：选择load

                //1. put all the files in a folder

                //2. store all the excel files in a string array as the names of the files;
                File f = new File("input/");

                String[] files = f.list();
                for (String name : files)
                    System.out.println(name);



                int itemRow=0;
                int itemCol=0;
                int pcRow=0;
                int pcCol=0;


                //3. for loop to go through the string array, 存储
                for (int i = 0; i < files.length; i++) {
                    String cellDate="";
                    String containerNO="";
                    String pcs="";
                    String poNO = "";
                    boolean findETA = false;
                    boolean findContainer = false;
                    boolean findPO = false;
                    boolean findItem = false;
                    boolean findPc = false;
                    int sameItem = 0;


                    String fileName = "input/" + files[i];
                    FileInputStream fis = null;
                    Workbook wb = null;

                    try {
                        fis = new FileInputStream(new File(fileName));
                        wb = new XSSFWorkbook(fis);
                    } catch (IOException em) {
                        em.printStackTrace();
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
                                    if (cell.getStringCellValue().contains("ETA") || cell.getStringCellValue().contains("eta")) {
                                        System.out.print("ETA: ");
                                        int tempCol = cell.getAddress().getColumn() + 1;
                                        Cell temp = findCellCol(row, tempCol);
                                        cellDate = df.format(temp.getDateCellValue());
                                        System.out.println(cellDate);
                                        findETA = true;
                                    }

                                    //finding the container number
                                    else if (cell.getStringCellValue().contains("CONTAINER") || cell.getStringCellValue().contains("CNTR")) {
                                        System.out.print("CONTAINER NUMBER: ");
                                        int tempCol = cell.getAddress().getColumn() + 1;
                                        Cell temp = findCellCol(row, tempCol);
                                        containerNO = temp.getStringCellValue();
                                        System.out.println(containerNO);
                                        findContainer = true;
                                    }
                                    //finding PO#
                                    else if (cell.getStringCellValue().contains("PO #") || cell.getStringCellValue().contains("PO#") || cell.getStringCellValue().toUpperCase().contains("PURCHASE ORDER")) {
                                        System.out.println();
                                        System.out.print("PURCHASE ORDER:");
                                        int tempCol = cell.getAddress().getColumn() + 1;
                                        Cell temp = findCellCol(row, tempCol);
                                        poNO = temp.getStringCellValue();
                                        System.out.println(poNO);
                                        findPO = true;
                                    }
                                    //find item row and column
                                    else if (cell.getCellTypeEnum() == CellType.STRING && cell.getStringCellValue().toUpperCase().contains("ITEM")) {
                                        System.out.println(cell.getStringCellValue());
                                        itemCol = cell.getColumnIndex();
                                        findContainer = true;
                                    }
                                    //find pcs row and column
                                    else if (cell.getStringCellValue().toUpperCase().contains("PCS")) {
                                        System.out.println(cell.getStringCellValue());
                                        pcCol = cell.getColumnIndex();
                                        findPc = true;
                                    }
                                    else if (cell.getStringCellValue().equals(toFind)) {
                                        System.out.println(toFind);
                                        findItem = true;
                                        int diff = pcCol - itemCol;
                                        Row tempRow = cell.getRow();
                                        Cell pcTempCell = tempRow.getCell(cell.getColumnIndex() + diff);
                                        System.out.println(pcTempCell.getNumericCellValue());
                                        double pcNum = pcTempCell.getNumericCellValue();
                                        Product product = new Product(poNO, toFind, containerNO,cellDate, pcNum);
                                        products.add(product);
                                        sameItem++;
                                    }

                                    if(findContainer && findETA && findItem && findPc){
                                        for(int x = products.size() - 1; x >= 0; x--){

                                            while(sameItem-- > 0){
                                                products.get(x).setContainerNo(containerNO);
                                                System.out.println(products.get(x).getContainerNo());
                                            }
                                        }
                                    }



                            }
                        }
                    }


                    //i. look for item --> store in a string array of all the items.
                    //item对应一个class --》date，Container，PCS
                    //-> 对应的列求出来
                    //-> 存储每一列--》global array or new excel sheet (update: 每个月的单子不是一起来的，所以还是要做成arraylist)


                }

                System.out.println(products.size());

                String combined = "";
                for(Product pro: products) {
                    String result = "\nPO#: " + pro.getPoNum() + "\npcs: " + pro.getPcs() + "\nContainer NO/ seal: " + pro.getContainerNo() + "\nETA: " + pro.getEta() + "\n\n";


                    String temp = "Item: " + tf.getText();
                    combined = combined + temp + result;
                    ta.setFont(new Font("Ariel", Font.PLAIN, 18));
                    ta.setText(combined);
                }
            }
        });


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

