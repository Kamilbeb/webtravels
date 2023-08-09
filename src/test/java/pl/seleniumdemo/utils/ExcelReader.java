package pl.seleniumdemo.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelReader {

    public static Object[][] readExcel(String fileName) throws IOException {
        File file = new File("src/test/resources/" + fileName);  //tworzymy plik w podanej ścieżce
        FileInputStream inputStream = new FileInputStream(file);

        Workbook workbook = null;
        String fileExt = fileName.substring(fileName.indexOf(".")); //sprawdzamy rozszerzenie pliku excela czyli w nazwie po kropce
        if(fileExt.equals(".xlsx")){
            workbook = new XSSFWorkbook(inputStream);
        }else if(fileExt.equals(".xls")){
            workbook = new HSSFWorkbook(inputStream);
        }
        Sheet sheet = workbook.getSheetAt(0);         //pobranie pierwszego arkusza z excela
        int rowCount = sheet.getLastRowNum();       //sprawdzamy ile wierszy ma nasz arkusz poprzez uzyskanie numeru ostatniego
        int columnCount = sheet.getRow(0).getLastCellNum();  //pobieramy pierwszy wiersz i sprawdzamy jaki jest numer ostatniej kolumny
        Object[][] data = new Object[rowCount][columnCount];  //tworzymy tablicę dwuwymiarową z taką samą ilością wierszy i kolumn czo w excelu

        for(int i=1; i<=rowCount; i++){
            Row row = sheet.getRow(i);          //pobieramy wartości z wierszy
            for(int j=0; j<columnCount;j++){
                data[i-1][j] = row.getCell(j).getStringCellValue();
            }

        }
        return data;
    }

    public static void main(String[] args) throws IOException {
        readExcel("testData.xlsx");
    }
}
