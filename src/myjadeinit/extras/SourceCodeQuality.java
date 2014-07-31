/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjadeinit.extras;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Desai
 */
public final class SourceCodeQuality implements Serializable {

    private static final long serialVersionUID = 34874915819050L;

    private int CodeQuality;

    private Date date;

    private final Map<String, String> records;

    private final DateFormat dateFormatter;

    /**
     *
     * @param CodeQuality
     */
    public SourceCodeQuality(int CodeQuality) {
        this.CodeQuality = CodeQuality;
        dateFormatter = new SimpleDateFormat("HH:mm:ss");
        records = new TreeMap<>();
        logRecords();
    }

    /**
     * @return the CodeQuality
     */
    public int getCodeQuality() {
        return CodeQuality;
    }

    /**
     * @param CodeQuality the CodeQuality to set
     */
    public void setCodeQuality(int CodeQuality) {
        this.CodeQuality = CodeQuality;
    }

    /**
     * Method to increase the code quality, generally used in refactoring
     * behaviour applied by developer on the Software System.
     */
    public void increaseQuality() {
        this.CodeQuality += 2;
        logRecords();
    }

    /**
     * <p>
     * This is overloaded method of increaseQuality(). This method also
     * increases the code quality but it increases by the value passed as
     * parameter.
     * </p>
     *
     * @param increaseBy the value by which the code quality is to be increased.
     */
    public void increaseQuality(int increaseBy) {
        this.CodeQuality += increaseBy;
        logRecords();
    }

    /**
     * Method to decrease the code quality, generally used in defactoring
     * behaviour applied by the Software system to the source code agent. Method
     * to decrease the code quality.
     */
    public void decreaseQuality() {
        this.CodeQuality -= 1;
        logRecords();
    }

    /**
     * This is overloaded method of decreaseQuality(). This method also
     * decreases the code quality but it decreases it by the values passes as
     * parameter.
     *
     * @param decreaseBy the value by which the code quality is to be decreased.
     */
    public void decreaseQuality(int decreaseBy) {
        if (decreaseBy > 0) {
            this.CodeQuality -= decreaseBy;
            logRecords();
        }
    }

    /**
     * This method records the time stamp and the code quality in a tree map
     * which then is used to write the values to excel file.
     * <p>
     * TreeMap is used here to make sure that the values are stored in sorted
     * manner as they are taken and to avoid unnecessary duplicate time stamp
     * values.</p>
     */
    public final void logRecords() {
        if (records != null) {
            date = new Date();
            records.put(dateFormatter.format(date), String.valueOf(CodeQuality));
        }
    }

    /**
     * This method starts a new thread which then executed the task to write the
     * values on excel file.
     */
    public void writeToFile() {

        Thread thread = new Thread(new RecordsWriter(records, RecordsWriter.RECORD_NAME_CODEQUALITY));
        thread.start();
    }
}
