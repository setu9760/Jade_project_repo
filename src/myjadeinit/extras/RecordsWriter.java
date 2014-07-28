/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjadeinit.extras;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author Desai
 */
public class RecordsWriter implements Runnable {

    private File file;

    private FileOutputStream fileOutputStream;

    private HSSFWorkbook workbook;

    private HSSFSheet worksheet;

    private Row row;

    private Cell cell;

    private final Map<String, String> records;

    private final String PATH = "raw/test.xls";

    private final String WORKSHEET = "My worksheet";

    public RecordsWriter(Map<String, String> records) {
        this.records = new TreeMap<>(records);

    }

    @Override
    public void run() {
        writeToFile();
    }

    public void writeToFile() {
        try {
            file = new File(PATH);

            fileOutputStream = new FileOutputStream(file);

            workbook = new HSSFWorkbook();

            worksheet = workbook.createSheet(WORKSHEET);

            Set<String> keySet = records.keySet();
            int rowNum = 0;

            row = worksheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellStyle(style());
            cell.setCellValue("Time-stamp");

            cell = row.createCell(2);
            cell.setCellStyle(style());
            cell.setCellValue("SoftSize");

            for (String key : keySet) {
                row = worksheet.createRow(rowNum++);
                row.createCell(0).setCellValue(key);
                row.createCell(1).setCellValue(Integer.parseInt(records.get(key)));
            }
            workbook.write(fileOutputStream);
            fileOutputStream.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(SystemSize.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SystemSize.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private HSSFCellStyle style() {

        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 16);
        font.setFontName("Courier New");
        font.setItalic(true);
        style.setFont(font);
        return style;
    }
}
