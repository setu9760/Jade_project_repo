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
        Runtime.getRuntime().addShutdownHook(thread);

    }
}
