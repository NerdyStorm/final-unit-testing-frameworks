package com.techfios.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.omg.CORBA.CurrentOperations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Excel {
    
    String path;
    String sheetName;

    //Logger LOG = LoggerFactory.getLogger(Excel.class);

    public Excel(String path, String sheetName){
        this.path = path;
        this.sheetName = sheetName;
    }

    public  void createFileWithData(){
        XSSFWorkbook wb;

        try {

    
            wb = new XSSFWorkbook();
            XSSFSheet sh = wb.createSheet(sheetName);

            Row r1 = sh.createRow(1);
            r1.createCell(1).setCellValue("Full Name");
            r1.createCell(2).setCellValue("Company");
            r1.createCell(3).setCellValue("Email");
            r1.createCell(4).setCellValue("Phone");
            r1.createCell(5).setCellValue("City");
            r1.createCell(6).setCellValue("State");
            r1.createCell(7).setCellValue("Zip");
            r1.createCell(8).setCellValue("Country");

            Row r2 = sh.createRow(2);
            r2.createCell(1).setCellValue("Md Hossain");
            r2.createCell(2).setCellValue("Techfios");
            r2.createCell(3).setCellValue("mrifayathossain@gmail.com");
            r2.createCell(4).setCellValue("3474764319");
            r2.createCell(5).setCellValue("Queens");
            r2.createCell(6).setCellValue("New York");
            r2.createCell(7).setCellValue("11111");
            r2.createCell(8).setCellValue("United States");

            Row r3 = sh.createRow(3);
            r3.createCell(1).setCellValue("Techfios");
            r3.createCell(2).setCellValue("Techfios");
            r3.createCell(3).setCellValue("demo@techfios.com");
            r3.createCell(4).setCellValue("");
            r3.createCell(5).setCellValue("Dallas");
            r3.createCell(6).setCellValue("Texas");
            r3.createCell(7).setCellValue("091224");
            r3.createCell(8).setCellValue("United States");

            wb.write(new FileOutputStream(path));            


        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        


    }
    /**
     * 
     * @param targetRowName Column name to retrieve data from
     * @param occurence Retrieve data from this specified location as it appears from top
     * @return
     */
    public String readFileWithData(String targetRowName, int occurence){

        XSSFWorkbook wb;

        try {

            // System.out.println("1");
            
            wb = new XSSFWorkbook(new FileInputStream(path));
            XSSFSheet sh = wb.getSheet(sheetName);

            // System.out.println("2");

            Iterator<Row> i = sh.iterator();
            Row currentRow;
            int cellNumber = 0;
            String cellValue = "";

            while (i.hasNext()){
                // System.out.println("3");

                

                currentRow = i.next();
                System.out.println("Row: " + currentRow.getRowNum());

                


                Iterator<Cell> currentCell = currentRow.cellIterator();

                if (currentRow.getRowNum() != 0 && currentRow.getRowNum() == occurence){
                    
                    cellValue = currentRow.getCell(cellNumber).getStringCellValue();
                    // System.out.println("Retrieved Cell Value: " + cellValue);
                    return cellValue;
                }

                    
                while (currentRow.getRowNum()==0 && currentCell.hasNext()){
                    
                    // System.out.println("4");

                    cellValue = currentCell.next().getStringCellValue();
                    

                    // System.out.println("Cell Value2: " + cellValue);

                    if (cellValue.equalsIgnoreCase(targetRowName)){

                        System.out.println("Fetched Row: " + cellValue + " " + cellNumber);
                        break;

                    }
                    cellNumber++;


                }

                

            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "pppp";

    }


}
