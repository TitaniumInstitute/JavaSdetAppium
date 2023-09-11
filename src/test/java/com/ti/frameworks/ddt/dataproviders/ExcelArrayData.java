package com.ti.frameworks.ddt.dataproviders;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static com.ti.frameworks.ddt.config.Constants.WORKBOOKS_FOLDER;

public class ExcelArrayData {
    private static Sheet excelWSheet;
    //Excel Reader
    private static Workbook workBook;

    private static final DataFormatter dataFormatter = new DataFormatter();

    public static void readExcel(String excelWorkBook) throws IOException {
        workBook = WorkbookFactory.create(new File(WORKBOOKS_FOLDER + excelWorkBook));
    }

    public static Sheet getWorkSheet(String sheetName){
        return workBook.getSheet(sheetName);
    }

    public static Object[][] getExcelTableArray(String excelWorkBook, String excelWorkSheet) throws IOException {
        readExcel(excelWorkBook);
        excelWSheet = getWorkSheet(excelWorkSheet);
        Iterable<Row> rows = excelWSheet::rowIterator;
        List<LinkedHashMap<String,String>> results = new ArrayList<>();
        boolean header = true;
        List<String> keys = null;
        for (Row row : rows) {
            List<String> values = getValuesInEachRow(row);
            if (header) {
                header = false;
                keys = values;
                continue;
            }
            results.add(transform(keys, values));
        }
        return asTwoDimensionalArray(results);
    }

    private static Object[][] asTwoDimensionalArray(List<LinkedHashMap<String, String>> interimResults) {
        Object[][] results = new Object[interimResults.size()][1];
        int index = 0;
        for (LinkedHashMap<String, String> interimResult : interimResults) {
            results[index++] = new Object[] {interimResult};
        }
        return results;
    }

    private static LinkedHashMap<String, String> transform(List<String> names, List<String> values) {
        LinkedHashMap<String, String> results = new LinkedHashMap<>();
        for (int i = 0; i < names.size(); i++) {
            String key = names.get(i);
            String value = values.get(i);
            results.put(key, value);
        }
        return results;
    }

    private static List<String> getValuesInEachRow(Row row) {
        List<String> data = new ArrayList<>();
        Iterable<Cell> columns = row::cellIterator;
        for (Cell column : columns) {
            data.add(column.equals("empty")?"":dataFormatter.formatCellValue(column));
        }
        return data;
    }

}
