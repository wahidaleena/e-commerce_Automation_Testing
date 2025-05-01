package utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.*;

public class ExcelWrite {

	private static final String FILE_PATH = System.getProperty("user.home") + Constants.filepath;

	public static void writeDataIntoExcelFile(String email, String password, String fname, String lname,String fullname,
			String orderNumber) {

		Workbook workbook = null;
		Sheet sheet = null;
		File file = new File(FILE_PATH);

		try {
			boolean emailFound = false;

			if (file.exists()) {
				// Load existing workbook
				FileInputStream fis = new FileInputStream(file);
				workbook = new XSSFWorkbook(fis);

				// Get or create sheet
				sheet = workbook.getSheet("Login Credentials");
				if (sheet == null) {
					sheet = workbook.createSheet("Login Credentials");

					// Add headers
					Row header = sheet.createRow(0);
					header.createCell(0).setCellValue("Email");
					header.createCell(1).setCellValue("Password");
					header.createCell(2).setCellValue("First Name");
					header.createCell(3).setCellValue("Last Name");
					header.createCell(4).setCellValue("Full Name");
					header.createCell(5).setCellValue("Order Number");
				} else {
					// Check if email/password/firstname/lastname/order number values already exists
					// Check all rows for existing email
					Row headerRow = sheet.getRow(0);
					if (headerRow == null) {
					    headerRow = sheet.createRow(0);
					}

					// Create headers only if they're missing
					if (headerRow.getCell(0) == null) headerRow.createCell(0).setCellValue("Email");
					if (headerRow.getCell(1) == null) headerRow.createCell(1).setCellValue("Password");
					if (headerRow.getCell(2) == null) headerRow.createCell(2).setCellValue("First Name");
					if (headerRow.getCell(3) == null) headerRow.createCell(3).setCellValue("Last Name");
					if (headerRow.getCell(4) == null) headerRow.createCell(4).setCellValue("Full Name");
					if (headerRow.getCell(5) == null) headerRow.createCell(5).setCellValue("Order Number");
					for (Row row : sheet) {
					    if (row.getRowNum() == 0) continue; // Skip header

					    Cell emailCell = row.getCell(0);
					    if (emailCell != null && email.equals(emailCell.getStringCellValue())) {

					    	//update last name
					    	Cell lastNameCell = row.getCell(3);
					        if (lastNameCell == null) {
					            lastNameCell = row.createCell(3);
					        }
					        lastNameCell.setCellValue(lname);
					        
					        // Update Full Name
					        Cell fullNameCell = row.getCell(4);
					        if (fullNameCell == null) {
					            fullNameCell = row.createCell(4);
					        }
					        fullNameCell.setCellValue(fullname);

					        // Update Order Number
					        Cell orderNumberCell = row.getCell(5);
					        if (orderNumberCell == null) {
					            orderNumberCell = row.createCell(5);
					            orderNumberCell.setCellValue(orderNumber);
					        }
					        else {
					        	String existingOrderNumbers=orderNumberCell.getStringCellValue();
					        	orderNumberCell.setCellValue(existingOrderNumbers+","+orderNumber);
					        }
					        	

					        emailFound = true;
					        break;
					    }
					}
                    
				}

				fis.close();
			}

			else {
				// Create new workbook and sheet if no file exists
				workbook = new XSSFWorkbook();
				sheet = workbook.createSheet("Login Credentials");

				// Add headers
				Row header = sheet.createRow(0);
				header.createCell(0).setCellValue("Email");
				header.createCell(1).setCellValue("Password");
				header.createCell(2).setCellValue("First Name");
				header.createCell(3).setCellValue("Last Name");
				header.createCell(4).setCellValue("Full Name");
				header.createCell(5).setCellValue("Order Number");
			}

			// If data already exists, skip writing
			if (emailFound) {
				System.out.println("Data already exists.Updating row...");
				FileOutputStream fos = new FileOutputStream(FILE_PATH);
			    workbook.write(fos);
			    fos.close();
			    System.out.println("Updated existing user data for: " + email);
			} else {
				int lastRow = sheet.getLastRowNum();
				Row row = sheet.createRow(lastRow + 1);
				row.createCell(0).setCellValue(email);
				row.createCell(1).setCellValue(password);
				row.createCell(2).setCellValue(fname);
				row.createCell(3).setCellValue(lname);
				row.createCell(4).setCellValue(fullname);
				row.createCell(5).setCellValue(orderNumber);

				// Save workbook
				FileOutputStream fos = new FileOutputStream(FILE_PATH);
				workbook.write(fos);
				fos.close();
				System.out.println("User data saved to Excel: " + email + " / " + password + " / " + fname + " / "
						+ lname + " / " +" / "+fullname+" / "+ orderNumber);
			}

			workbook.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
