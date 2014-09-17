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
 * This is a class that is used as one of the two resulting parameters. This
 * object would generally belong to SourceCode agent (unless future developments
 * change its definition).
 *
 *
 * @author Desai
 */
public final class SourceCodeQuality implements Serializable {

    private static final long serialVersionUID = 34874915819050L;

    private int CodeQuality;

    private Date date;
    /**
     * The map to keep record of change in the sourceCodeQuality. Each changing
     * value will be stored in the key-value Map where key will be time-stamp
     * and value will be corresponding CodeQuality at that time.
     */
    private final Map<String, String> records;
    /**
     * dateFormatter object used to format the date in simplified readable
     * version.
     */
    private final DateFormat dateFormatter;

    /**
     *
     * @param CodeQuality
     */
    public SourceCodeQuality(int CodeQuality) {
        this.CodeQuality = CodeQuality;
        if (isBelowZero()) {
            this.CodeQuality = 30;
        }
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
        if (isBelowZero()) {
            this.CodeQuality = 30;
        }

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
        if (increaseBy > 0) {
            this.CodeQuality += increaseBy;
        } else {
            increaseQuality();
        }
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
        } else {
            decreaseQuality();
        }
    }

    /**
     * This method checks whether the code quality has reached 0. It is used
     * before applying defactoring behaviour to make sure the quality does not
     * reach negative number.
     *
     * @return <i>true if the code quality is 0, false otherwise.</i>
     */
    public boolean isBelowZero() {
        return CodeQuality <= 0;
    }

    /**
     * This method records the time stamp and the code quality in a tree map
     * which then is used to write the values to excel file.
     * <p>
     * TreeMap is used here to make sure that the values are stored in sorted
     * manner as they are taken and to avoid unnecessary duplicate time stamp
     * values.</p>
     */
    @SuppressWarnings("FinalPrivateMethod")
    private final void logRecords() {
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
        Runtime.getRuntime().addShutdownHook(thread);
    }

    /**
     * overridden toString() method.
     *
     * @return String representation of this object.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Code quality is: ");
        sb.append(CodeQuality);
        return sb.toString();
    }

    /**
     * overridden hashCode() method; mainly used for testing purposes.
     *
     * @return hashCode value of this object.
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(7, 31)
                .append(CodeQuality)
                .toHashCode();
    }

    /**
     * overridden equals() method; mainly used for testing purposes.
     *
     * @param object the object to be checked for equals
     * @return true if this is equals to the object passed in parameter, false
     * otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SourceCodeQuality)) {
            return false;
        }
        if (object == this) {
            return true;
        }
        SourceCodeQuality quality = (SourceCodeQuality) object;
        return new EqualsBuilder()
                .append(CodeQuality, quality.CodeQuality)
                .isEquals();
    }
}
