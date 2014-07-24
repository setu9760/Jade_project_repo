/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjadeinit.extras;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author Desai
 */
public class SystemSize implements Serializable {

    private static final long serialVersionUID = 34874925819000L;

    private int SoftSize;

    private final String path = "raw/testing.txt";
    private final File file;
    private final DateFormat dateFormatter;

    private FileWriter fileWriter;
    private BufferedWriter bufferedWritter;
    private Calendar calender;

    /**
     *
     * @param SoftSize
     */
    public SystemSize(int SoftSize) {
        this.SoftSize = SoftSize;
        dateFormatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        file = new File(path);
        initFile();

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
        writeToFile(SoftSize);
    }

    /**
     * public method to decrease the software size. This method decreases the
     * size by 1.
     */
    public void decreaseSize() {
        this.SoftSize -= 1;
    }

    private void initFile() {
        if (file.exists()) {
            try {
                file.delete();
                file.createNewFile();
                fileWriter = new FileWriter(file, true);
                bufferedWritter = new BufferedWriter(fileWriter);
                bufferedWritter.append("This file has record of changing Software size over time");
                bufferedWritter.append(System.lineSeparator());
                bufferedWritter.append(" Date - Time " + " --- " + " System Size ");
                bufferedWritter.append(System.lineSeparator());
            } catch (IOException ex) {
                Logger.getLogger(SystemSize.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    bufferedWritter.close();
                    fileWriter.close();
                } catch (IOException ex) {
                    Logger.getLogger(SystemSize.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

    private void writeToFile(int SoftSize) {
        calender = Calendar.getInstance();
        try {
            fileWriter = new FileWriter(file, true);
            bufferedWritter = new BufferedWriter(fileWriter);
            bufferedWritter.append(dateFormatter.format(calender.getTime()) + " --- " + SoftSize);
            bufferedWritter.append(System.lineSeparator());
        } catch (IOException ex) {
            Logger.getLogger(SystemSize.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bufferedWritter.close();
                fileWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(SystemSize.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
