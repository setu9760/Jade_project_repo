/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjadeinit.extras;

/**
 *
 * @author Desai
 */
public class SystemSize {

    private int SoftSize;

    public SystemSize() {
        this.SoftSize = 0;
    }

    public SystemSize(int SoftSize) {
        this.SoftSize = SoftSize;
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
    }

    /**
     * public method to decrease the software size. This method decreases the
     * size by 1.
     */
    public void decreaseSize() {
        this.SoftSize -= 1;
    }

}
