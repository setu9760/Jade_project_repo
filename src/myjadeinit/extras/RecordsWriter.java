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
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author Desai
 */
public class RecordsWriter implements Runnable {

    private File file;

    private FileOutputStream fileOutputStream;

    private HSSFWorkbook workbook = null;

    private HSSFSheet worksheet = null;

    private Row row;

    private Cell cell;

    private final Map<String, String> records;

    private final String PATH;

    private final String WORKSHEET;

    private final Date date;

    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM -- HH.mm.ss");

    public static final String RECORD_NAME_SOFTSIZE = "SoftwareSize";

    public static final String RECORD_NAME_CODEQUALITY = "CodeQuality";

    public RecordsWriter(Map<String, String> records, String recordName) {

        switch (recordName) {
            case RECORD_NAME_SOFTSIZE:
                PATH = "raw/SoftwareSize.xls";
                break;
            case RECORD_NAME_CODEQUALITY:
                PATH = "raw/CodeQuality.xls";
                break;
            default:
                PATH = "raw/unknown.xls";
                break;
        }

        this.records = new TreeMap<>(records);
        date = new Date();
        this.WORKSHEET = "Record @ " + dateFormatter.format(date);
    }

    @Override
    public void run() {
        writeToFile();
    }

    /**
     * This is the method that is mainly used to do the final write up of
     * records in an excel workbook. This method creates a new worksheet if the
     * workbook already exists and if not then creates the workbook as well.
     * worksheet name contains the current time stamp to distinctly identify
     * each worksheets.
     */
    public void writeToFile() {
        try {
            file = new File(PATH);

            if (file.exists()) {
                workbook = (HSSFWorkbook) WorkbookFactory.create(file);
            } else {
                workbook = new HSSFWorkbook();
            }

            fileOutputStream = new FileOutputStream(file);

            worksheet = workbook.createSheet(WORKSHEET);

            Set<String> keySet = records.keySet();
            int rowNum = 0;

            row = worksheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellStyle(style());
            cell.setCellValue("Time-stamp");

            cell = row.createCell(1);
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
            Logger.getLogger(RecordsWriter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RecordsWriter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException ex) {
            Logger.getLogger(RecordsWriter.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException ex) {
                    Logger.getLogger(RecordsWriter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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
