/* 
 * Copyright (C) 2014 S Desai
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package utils;

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
import org.apache.commons.lang3.time.DateFormatUtils;
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

    private final String FILE;

    private final String WORKSHEET1;

    private final String WORKSHEET2;

    private final Date date;

    DateFormatUtils d = new DateFormatUtils();

    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM--HH.mm.ss");

    public static final String RECORD_NAME_SOFTSIZE = "SoftwareSize";

    public static final String RECORD_NAME_CODEQUALITY = "CodeQuality";

    public final File DIRECTORY;

    public RecordsWriter(Map<String, String> records, String recordName) {

        DIRECTORY = new File(System.getProperty("user.home") + "/results");

        this.records = new TreeMap<>(records);
        date = new Date();
        switch (recordName) {
            case RECORD_NAME_SOFTSIZE:
                FILE = DIRECTORY.getAbsolutePath() + "/" + dateFormatter.format(date) + "-SoftwareSize.xls";
                //PATH = "raw/SoftwareSize.xls";
                break;
            case RECORD_NAME_CODEQUALITY:
                FILE = DIRECTORY.getAbsolutePath() + "/" + dateFormatter.format(date) + "-CodeQuality.xls";
                //PATH = "raw/CodeQuality.xls";
                break;
            default:
                FILE = "raw/unknown.xls";
                break;
        }
        //this.WORKSHEET1 = "Record @ " + dateFormatter.format(date);
        this.WORKSHEET1 = "Sheet1";
        this.WORKSHEET2 = "Sheet2";
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

            if (!DIRECTORY.isDirectory()) {
                DIRECTORY.mkdir();
            }

            file = new File(FILE);

            if (file.exists()) {
                workbook = (HSSFWorkbook) WorkbookFactory.create(file);
            } else {
                workbook = new HSSFWorkbook();
            }

            fileOutputStream = new FileOutputStream(file);

            //worksheet = workbook.createSheet(WORKSHEET1);
            workbook.createSheet(WORKSHEET1);
            workbook.createSheet(WORKSHEET2);
            worksheet = workbook.getSheet(WORKSHEET1);
            Set<String> keySet = records.keySet();
            int rowNum = 0;

            row = worksheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellStyle(style());
            cell.setCellValue("Time-stamp");

            cell = row.createCell(1);
            cell.setCellStyle(style());
            if (FILE.contains(RECORD_NAME_SOFTSIZE)) {
                cell.setCellValue("SoftSize");
            } else if (FILE.contains(RECORD_NAME_CODEQUALITY)) {
                cell.setCellValue("CodeQuality");
            }

            for (String key : keySet) {
                row = worksheet.createRow(rowNum++);
                row.createCell(0).setCellValue(key);
                row.createCell(1).setCellValue(Integer.parseInt(records.get(key)));
            }
            workbook.write(fileOutputStream);
            System.out.println("Records writen to the file succesfully.");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(RecordsWriter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | InvalidFormatException ex) {
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
