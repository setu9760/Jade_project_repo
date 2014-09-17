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
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * This is a class that is used as one of the two resulting parameters. this
 * object would generally belong to SoftwareSystem agent (unless future
 * developments change its definition).
 *
 * @author Desai
 */
public final class SystemSize implements Serializable {

    private static final long serialVersionUID = 34874925819000L;

    private int SoftSize;

    private Date date;
    /**
     * The map to keep record of change in systemSize. each changing value will
     * be stored in the key-value map where key will be time-stamp and value
     * will be corresponding softwareSize at that time.
     */
    private final Map<String, String> records;
    /**
     * dateFortter object used to format the date in simplified readable
     * version.
     */
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
    @SuppressWarnings("FinalPrivateMethod")
    private final void logRecord() {
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

    /**
     * overridden toString() method.
     *
     * @return String representation of this object.
     */
    @Override
    public String toString() {
        return new StringBuilder()
                .append("Software size is: ")
                .append(SoftSize)
                .toString();
    }

    /**
     * overridden hashCode() method; mainly used for testing purposes.
     *
     * @return hasCode value of this object.
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(7, 31)
                .append(SoftSize)
                .toHashCode();
    }

    /**
     * overridden equals() method; mainly used for testing purposes.
     *
     * @param object the object to be checked for equals
     * @return true if this is equals to the object passed as parameter.
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SystemSize)) {
            return false;
        }
        if (object == this) {
            return true;
        }
        SystemSize size = (SystemSize) object;
        return new EqualsBuilder()
                .append(SoftSize, size.SoftSize)
                .isEquals();
    }
}
