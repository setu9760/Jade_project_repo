/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myjadeinit.extras;

import java.util.Random;

/**
 *
 * @author Desai
 */
public class ChangeRequirement {

    /**
     */
    private final int changeRequirementSize;
    /**
     */
    private final int MAX = 100;
    /**
     */
    private final int MIN = 1;
    /**
     */
    private final Random random = new Random();

    /**
     *
     */
    public ChangeRequirement() {
        changeRequirementSize = randomChangeGenerator();
    }

    /**
     *
     * @param changeRequirementSize
     */
    public ChangeRequirement(int changeRequirementSize) {
        if (changeIsValid(changeRequirementSize)) {
            this.changeRequirementSize = changeRequirementSize;
        } else {
            this.changeRequirementSize = randomChangeGenerator();
        }
    }

    /**
     * @return the changeRequirementSize
     */
    public int getChangeRequirementSize() {
        return changeRequirementSize;
    }

    /**
     *
     * @return
     */
    private int randomChangeGenerator() {
        return random.nextInt((MAX - MIN) + 1) + MIN;
    }

    /**
     *
     * @param changeRequirementSize
     * @return
     */
    private boolean changeIsValid(int changeRequirementSize) {
        return changeRequirementSize >= 0 && changeRequirementSize <= 100;
    }
}
