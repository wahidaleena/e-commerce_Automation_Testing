package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelRead {

	static FileInputStream f;

	static XSSFWorkbook wb;

	static XSSFSheet sh;

	public static String getStringData(int a, int b,String sheetName) throws IOException {

		f = new FileInputStream(System.getProperty("user.home")
				+ "//eclipse-workspace//com.magento_e-commerce//src//test//resources/excel//TestData_Magento.xlsx");

		wb = new XSSFWorkbook(f);

		sh = wb.getSheet(sheetName);

		XSSFRow r = sh.getRow(a);
		XSSFCell c = r.getCell(b);

		return c.getStringCellValue();

	}

	public static String getIntegerData(int a, int b,String sheetName) throws IOException {

		f = new FileInputStream(System.getProperty("user.home")
				+ "//eclipse-workspace//com.magento_e-commerce//src//test//resources/excel//TestData_Magento.xlsx");

		wb = new XSSFWorkbook(f);

		sh = wb.getSheet(sheetName);

		XSSFRow r = sh.getRow(a);
		XSSFCell c = r.getCell(b);
		int x = (int) c.getNumericCellValue();

		return String.valueOf(x);
	}
	public static String getLastRowsStringData(int cellNumber,String sheetName) throws IOException {

		f = new FileInputStream(System.getProperty("user.home")
				+ "//eclipse-workspace//com.magento_e-commerce//src//test//resources/excel//TestData_Magento.xlsx");

		wb = new XSSFWorkbook(f);

		sh = wb.getSheet(sheetName);
	
		int currentRowNum=sh.getLastRowNum();
        Row row = sh.getRow(currentRowNum);
        if (row != null) {

		Cell fullnameCell= row.getCell(cellNumber);
		String fullname=fullnameCell.getStringCellValue();
		return fullname;

	}
		return null;
		
			
	}
}
