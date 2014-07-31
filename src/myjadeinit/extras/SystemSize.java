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
public final class SystemSize implements Serializable {

    private static final long serialVersionUID = 34874925819000L;

    private int SoftSize;

    private Date date;

    private final Map<String, String> records;

    private final DateFormat dateFormatter;

    /**
     *
     * @param SoftSize
     */
    public SystemSize(int SoftSize) {
        this.SoftSize = SoftSize;
        dateFormatter = new SimpleDateFormat("HH:mm:ss");
        records = new TreeMap<>();
        logRecord();
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
        logRecord();
    }

    /**
     * <p>
     * public method to increase the software size by any number. This method is
     * dominantly used by the random requirement change functionality of this
     * method.
     * </p>
     *
     * @param evolveBy the integer value by which the software size is to be
     * evolved.
     */
    public void increaseSize(int evolveBy) {
        this.SoftSize += evolveBy;
        logRecord();
    }

    /**
     * public method to decrease the software size. This method decreases the
     * size by 1.
     */
    public void decreaseSize() {
        this.SoftSize -= 1;
    }

    /**
     * This method records the time stamp and the software size in a TreeMap
     * which then is used to write the values to excel file.
     * <p>
     * TreeMap is used here to make sure that the values are stored in sorted
     * manner as they are taken and to avoid unnecessary duplicate time stamp
     * values.</p>
     */
    public final void logRecord() {
        if (records != null) {
            date = new Date();
            records.put(dateFormatter.format(date), String.valueOf(SoftSize));
        }
    }

    /**
     * This method starts a new thread which then executed the task to write the
     * values on excel file.
     */
    public void writeToFile() {

        Thread thread = new Thread(new RecordsWriter(records, RecordsWriter.RECORD_NAME_SOFTSIZE));
        thread.start();
    }
}
