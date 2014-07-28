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
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
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
public class SystemSize implements Serializable {

    private static final long serialVersionUID = 34874925819000L;

    private int SoftSize;

    private Date date;

    private File file;

    private FileOutputStream fileOutputStream;

    private HSSFWorkbook workbook;

    private HSSFSheet worksheet;

    private Row row;

    private Cell cell;

    private final Map<String, String> records;

    private final DateFormat dateFormatter;

    private final String PATH = "raw/test.xls";

    private final String WORKSHEET = "My worksheet";

    /**
     *
     * @param SoftSize
     */
    public SystemSize(int SoftSize) {
        this.SoftSize = SoftSize;
        dateFormatter = new SimpleDateFormat("HH:mm:ss");
        date = new Date();
        records = new TreeMap<>();
        records.put(dateFormatter.format(date), String.valueOf(SoftSize));
    }

    /**
     * @return the SoftSize
     */
    public int getSoftSize() {
        return SoftSize;
    }

    /**
     * @param SoftSize the SoftSize to set
     */
    public void setSoftSize(int SoftSize) {
        this.SoftSize = SoftSize;

    }

    /**
     * Public method to increase the software size This method increases the
     * size by 1'
     */
    public void increaseSize() {
        this.SoftSize += 1;
        date = new Date();
        records.put(dateFormatter.format(date), String.valueOf(SoftSize));
    }

    /**
     * public method to decrease the software size. This method decreases the
     * size by 1.
     */
    public void decreaseSize() {
        this.SoftSize -= 1;
    }

    /**
     *
     */
    public void writeToFile() {

        Thread thread = new Thread(new RecordsWriter(records));
        thread.start();

//        try {
//            file = new File(PATH);
//
//            fileOutputStream = new FileOutputStream(file);
//
//            workbook = new HSSFWorkbook();
//
//            worksheet = workbook.createSheet(WORKSHEET);
//
//            Set<String> keySet = records.keySet();
//            int rowNum = 0;
//
//            row = worksheet.createRow(rowNum++);
//
//            row.createCell(0).setCellValue("Time-stamp");
//            row.createCell(1).setCellValue("SoftSize");
//
//            for (String key : keySet) {
//                row = worksheet.createRow(rowNum++);
//                row.createCell(0).setCellValue(key);
//                row.createCell(1).setCellValue(Integer.parseInt(records.get(key)));
//            }
//            workbook.write(fileOutputStream);
//            fileOutputStream.close();
//
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(SystemSize.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(SystemSize.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

//    private HSSFCellStyle style() {
//
//        HSSFCellStyle style = workbook.createCellStyle();
//        style.setAlignment(CellStyle.ALIGN_CENTER);
//        Font font = workbook.createFont();
//        font.setFontHeightInPoints((short) 24);
//        font.setFontName("Courier New");
//        font.setItalic(true);
//        font.setStrikeout(true);
//        style.setFont(font);
//        return style;
//    }
}
