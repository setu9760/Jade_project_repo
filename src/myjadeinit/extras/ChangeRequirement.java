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
