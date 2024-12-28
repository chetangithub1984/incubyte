package com.incubyte.utils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtility {

    private String excelFilePath;
    private Workbook workbook;
    private Sheet sheet;

    /**
     * Constructor to initialize the Excel file and sheet.
     *
     * @param filePath  The path of the Excel file.
     * @param sheetName The name of the sheet to work on.
     * @throws IOException If the file cannot be read.
     */
    public ExcelUtility(String filePath, String sheetName) throws IOException {
        this.excelFilePath = filePath;
        FileInputStream fis = new FileInputStream(new File(filePath));
        this.workbook = new XSSFWorkbook(fis);
        this.sheet = workbook.getSheet(sheetName);
        fis.close();
    }

    /**
     * Finds the row number for a given Cucumber tag (TestCaseName).
     *
     * @param tagName The tag name to search for.
     * @return The row number (0-based index) if found, otherwise -1.
     */
    public int findRowByTagName(String tagName) {
        for (Row row : sheet) {
            Cell cell = row.getCell(0); // Assuming "TestCaseName" is in the first column
            if (cell != null && cell.getCellType() == CellType.STRING && cell.getStringCellValue().equals(tagName)) {
                return row.getRowNum();
            }
        }
        return -1; // Tag not found
    }

    /**
     * Updates the data in a specific column for the row corresponding to the given tag name.
     *
     * @param tagName      The tag name (TestCaseName) to search for.
     * @param columnName   The column name to update.
     * @param newValue     The new value to set in the column.
     * @throws IOException If the file cannot be written.
     */
    public void updateDataByTagName(String tagName, String columnName, String newValue) throws IOException {
        Row headerRow = sheet.getRow(0); // Assuming the first row is the header
        int columnIndex = -1;

        // Find the column index for the given column name
        for (Cell cell : headerRow) {
            if (cell.getCellType() == CellType.STRING && cell.getStringCellValue().equalsIgnoreCase(columnName)) {
                columnIndex = cell.getColumnIndex();
                break;
            }
        }

        if (columnIndex == -1) {
            throw new IllegalArgumentException("Column '" + columnName + "' not found in the sheet.");
        }

        // Find the row number for the tag name
        int rowNum = findRowByTagName(tagName);
        if (rowNum == -1) {
            throw new IllegalArgumentException("Tag name '" + tagName + "' not found in the sheet.");
        }

        // Update the cell data
        Row row = sheet.getRow(rowNum);
        Cell cell = row.getCell(columnIndex);
        if (cell == null) {
            cell = row.createCell(columnIndex);
        }
        cell.setCellValue(newValue);

        // Save the changes to the Excel file
        try (FileOutputStream fos = new FileOutputStream(new File(excelFilePath))) {
            workbook.write(fos);
        }
    }

    /**
     * Closes the workbook to free resources.
     *
     * @throws IOException If the workbook cannot be closed.
     */
    public void close() throws IOException {
        workbook.close();
    }

    public static void main(String[] args) {
        try {
            // Provide the file path and sheet name
            String filePath = "src/test/java/com/incubyte/testData/TestData.xlsx";
            String sheetName = "CreateNewCustomerAccount"; // Update with the correct sheet name

            ExcelUtility excelUtility = new ExcelUtility(filePath, sheetName);

            // Example: Find a row by tag name
            String tagName = "TC02"; // Replace with the actual tag name
            int rowNum = excelUtility.findRowByTagName(tagName);
            if (rowNum != -1) {
                System.out.println("Row found for tag '" + tagName + "': Row " + (rowNum + 1));
            } else {
                System.out.println("Tag '" + tagName + "' not found in the sheet.");
            }

            // Example: Update a column value for the row corresponding to the tag name
            String columnName = "FirstName"; // Replace with the column name
            String newValue = "Mohan"; // Replace with the new value
            excelUtility.updateDataByTagName(tagName, columnName, newValue);

            System.out.println("Data updated successfully for tag '" + tagName + "'.");

            // Close the workbook
            excelUtility.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
