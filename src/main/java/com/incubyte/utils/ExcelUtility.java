package com.incubyte.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Utility class for performing Excel file operations such as reading and updating data based on tags.
 */
public class ExcelUtility {

    private final String excelFilePath;
    private final Workbook workbook;
    private final Sheet sheet;

    /**
     * Constructor to initialize the Excel file and sheet.
     *
     * @param filePath  The path of the Excel file.
     * @param sheetName The name of the sheet to work on.
     * @throws IOException If the file cannot be read.
     */
    public ExcelUtility(String filePath, String sheetName) throws IOException {
        this.excelFilePath = filePath;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File(filePath));
            this.workbook = new XSSFWorkbook(fis);
            this.sheet = workbook.getSheet(sheetName);
            if (this.sheet == null) {
                throw new IllegalArgumentException("Sheet '" + sheetName + "' does not exist in the Excel file.");
            }
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
    }

    /**
     * Example usage of the ExcelUtility class.
     */
    public static void main(String[] args) {
        String filePath = "path/to/your/excel-file.xlsx";
        String sheetName = "Sheet1"; // Replace with your sheet name

        try {
            ExcelUtility excelUtility = new ExcelUtility(filePath, sheetName);

            // Example: Read data
            String tagName = "@TC01";
            String columnName = "Status"; // Replace with your column name
            String status = excelUtility.readDataByTagName(tagName, columnName);
            System.out.println("Current Status for " + tagName + ": " + status);

            // Example: Update data
            String newStatus = "Passed";
            excelUtility.updateDataByTagName(tagName, columnName, newStatus);
            System.out.println("Updated Status for " + tagName + " to: " + newStatus);

            // Verify the update
            String updatedStatus = excelUtility.readDataByTagName(tagName, columnName);
            System.out.println("Verified Updated Status for " + tagName + ": " + updatedStatus);

            // Close the utility to free resources
            excelUtility.close();

        } catch (IOException e) {
            System.err.println("Error accessing the Excel file: " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.err.println("Error processing Excel data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Finds the row number for a given Cucumber tag (TestCaseName).
     *
     * @param tagName The tag name to search for.
     * @return The row number (0-based index) if found, otherwise -1.
     */
    public int findRowByTagName(String tagName) {
        if (tagName == null || tagName.trim().isEmpty()) {
            throw new IllegalArgumentException("Tag name must not be null or empty.");
        }

        for (Row row : sheet) {
            Cell cell = row.getCell(0); // Assuming "TestCaseName" is in the first column (index 0)
            if (cell != null) {
                // Handle different cell types gracefully
                String cellValue = getCellValueAsString(cell);
                if (tagName.equals(cellValue)) {
                    return row.getRowNum();
                }
            }
        }
        return -1; // Tag not found
    }

    /**
     * Reads the data from a specific column for the row corresponding to the given tag name.
     *
     * @param tagName    The tag name (TestCaseName) to search for.
     * @param columnName The column name to read data from.
     * @return The cell value as a String.
     * @throws IOException If there is an error reading the file.
     */
    public String readDataByTagName(String tagName, String columnName) throws IOException {
        if (columnName == null || columnName.trim().isEmpty()) {
            throw new IllegalArgumentException("Column name must not be null or empty.");
        }

        // Find the column index for the given column name
        Row headerRow = sheet.getRow(0); // Assuming the first row is the header
        if (headerRow == null) {
            throw new IllegalStateException("The sheet is empty. No header row found.");
        }

        int columnIndex = -1;
        for (Cell cell : headerRow) {
            if (cell.getCellType() == CellType.STRING && columnName.equalsIgnoreCase(cell.getStringCellValue())) {
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

        // Get the cell value
        Row row = sheet.getRow(rowNum);
        if (row == null) {
            throw new IllegalStateException("Row number " + rowNum + " does not exist.");
        }

        Cell cell = row.getCell(columnIndex);
        if (cell == null) {
            return ""; // Return empty string if cell is empty
        }

        return getCellValueAsString(cell);
    }

    /**
     * Updates the data in a specific column for the row corresponding to the given tag name.
     *
     * @param tagName    The tag name (TestCaseName) to search for.
     * @param columnName The column name to update.
     * @param newValue   The new value to set in the column.
     * @throws IOException If the file cannot be written.
     */
    public void updateDataByTagName(String tagName, String columnName, String newValue) throws IOException {
        if (columnName == null || columnName.trim().isEmpty()) {
            throw new IllegalArgumentException("Column name must not be null or empty.");
        }

        // Find the column index for the given column name
        Row headerRow = sheet.getRow(0); // Assuming the first row is the header
        if (headerRow == null) {
            throw new IllegalStateException("The sheet is empty. No header row found.");
        }

        int columnIndex = -1;
        for (Cell cell : headerRow) {
            if (cell.getCellType() == CellType.STRING && columnName.equalsIgnoreCase(cell.getStringCellValue())) {
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
        if (row == null) {
            row = sheet.createRow(rowNum);
        }

        Cell cell = row.getCell(columnIndex);
        if (cell == null) {
            cell = row.createCell(columnIndex);
        }

        cell.setCellValue(newValue);

        // Save the changes to the Excel file
        saveChanges();
    }

    /**
     * Saves the current state of the workbook to the Excel file.
     *
     * @throws IOException If the file cannot be written.
     */
    private void saveChanges() throws IOException {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File(excelFilePath));
            workbook.write(fos);
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
    }

    /**
     * Retrieves the cell value as a String, handling different cell types.
     *
     * @param cell The cell from which to retrieve the value.
     * @return The cell value as a String.
     */
    private String getCellValueAsString(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue()).trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString().trim();
                } else {
                    // Remove trailing .0 for whole numbers
                    double numericValue = cell.getNumericCellValue();
                    if (numericValue == (long) numericValue) {
                        return String.valueOf((long) numericValue).trim();
                    } else {
                        return String.valueOf(numericValue).trim();
                    }
                }
            case FORMULA:
                // Evaluate the formula and return the result as String
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                CellValue cellValue = evaluator.evaluate(cell);
                switch (cellValue.getCellType()) {
                    case STRING:
                        return cellValue.getStringValue().trim();
                    case BOOLEAN:
                        return String.valueOf(cellValue.getBooleanValue()).trim();
                    case NUMERIC:
                        double formulaNumericValue = cellValue.getNumberValue();
                        if (formulaNumericValue == (long) formulaNumericValue) {
                            return String.valueOf((long) formulaNumericValue).trim();
                        } else {
                            return String.valueOf(formulaNumericValue).trim();
                        }
                    default:
                        return "";
                }
            case BLANK:
            case _NONE:
            case ERROR:
            default:
                return "";
        }
    }

    /**
     * Closes the workbook to free resources.
     *
     * @throws IOException If the workbook cannot be closed.
     */
    public void close() throws IOException {
        if (workbook != null) {
            workbook.close();
        }
    }
}
