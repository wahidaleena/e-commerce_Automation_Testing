package utilities;

import java.io.IOException;
import java.io.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SharedUserData {

	private static String email;
    private static String password;
    private static String fullName;
    private static String fname;
    private static String lname;
    private static String orderNumber;
    private static boolean initialized = false;
   

    private static final String FILE_PATH = System.getProperty("user.home") + Constants.filepath;
    private static final String SHEET_NAME = "Login Credentials";

    private SharedUserData() {}

    private static void initializeIfNeeded(boolean forceNew) {
        if (initialized && !forceNew) return;

        File file = new File(FILE_PATH);

        // Check if Excel file exists
        if (!forceNew && file.exists()) {
            try (FileInputStream fis = new FileInputStream(file);
                 Workbook workbook = new XSSFWorkbook(fis)) {

                Sheet sheet = workbook.getSheet(SHEET_NAME);

                if (sheet != null && sheet.getLastRowNum() >= 1) {
                    
                	//get the current row as the last row
                	int currentRowNum=sheet.getLastRowNum();
                    Row row = sheet.getRow(currentRowNum);
                    if (row != null) {
                        Cell emailCell = row.getCell(0);
                        Cell passwordCell = row.getCell(1);
                        Cell fnameCell=row.getCell(2);
                        Cell lnameCell=row.getCell(3);
                        //Cell orderCell=row.getCell(4);

                        if (emailCell != null && passwordCell != null && fnameCell!=null && lnameCell!=null) {
                            email = emailCell.getStringCellValue();
                            password = passwordCell.getStringCellValue();
                            fname=fnameCell.getStringCellValue();
                            lname=lnameCell.getStringCellValue();
                            //orderNumber=orderCell.getStringCellValue();
                            fullName=fname+" "+lname;
                            System.out.println("Loaded user from Excel: " + email + " / " + password+ " / "+fname+" / "+lname);
                            initialized = true;
                            return;
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Either forced or no data — generate new
        email = RandomDataUtility.getEmail();
        password = RandomDataUtility.getPassword();
        fname = RandomDataUtility.getFirstName();
		lname = RandomDataUtility.getLastName();
		fullName=fname+" "+lname;
		
		
        ExcelWrite.writeDataIntoExcelFile(email, password,fname,lname,fullName,orderNumber);
        System.out.println("Generated and saved new user to Excel: " + email + " / " + password);
        
        initialized = true;
    }
 // Overloaded getter methods with forceNew option
    public static String getEmail(boolean forceNew) {
        initializeIfNeeded(forceNew);
        return email;
    }

    public static String getPassword(boolean forceNew) {
        initializeIfNeeded(forceNew);
        return password;
    }
    public static String getFname(boolean forceNew) {
        initializeIfNeeded(forceNew);
        return fname;
    }
    public static String getLname(boolean forceNew) {
        initializeIfNeeded(forceNew);
        return lname;
    }
    public static String getFullname(boolean forceNew) {
        initializeIfNeeded(forceNew);
        return fullName;
    }
 // Default no-arg getters to reuse existing user
    public static String getEmail() {
        initializeIfNeeded(false);
        return email;
    }

    public static String getPassword() {
        initializeIfNeeded(false);
        return password;
    }

    public static String getFname() {
        initializeIfNeeded(false);
        return fname;
    }

    public static String getLname() {
        initializeIfNeeded(false);
        return lname;
    }

    public static String getFullname() {
        initializeIfNeeded(false);
        return fullName;
    }
    public static void generateNewUser() {
        initialized = false;
        initializeIfNeeded(true);
    }
    public static String getOrderNumber() {
        return orderNumber;
    }

    public static void setOrderNumber(String orderNumber) {
        SharedUserData.orderNumber = orderNumber;
    }
}
