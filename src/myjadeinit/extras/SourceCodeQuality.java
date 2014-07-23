/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjadeinit.extras;

import java.io.Serializable;

/**
 *
 * @author Desai
 */
public class SourceCodeQuality implements Serializable {

    private static final long serialVersionUID = 34874915819000L;

    private int CodeQuality;

    public SourceCodeQuality(int CodeQuality) {
        this.CodeQuality = CodeQuality;
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
    }

    /**
     * Method to decrease the code quality.
     */
    public void decreaseQuality() {
        this.CodeQuality -= 1;
    }

}
