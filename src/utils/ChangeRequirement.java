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

import java.util.Random;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 *
 * @author Desai
 */
public class ChangeRequirement {

    /**
     * Size of the changeRequirement.
     */
    private final int changeRequirementSize;
    /**
     * Maximum value of changeRequirement.
     */
    private final int MAX = 100;
    /**
     * Minimum value of changeRequirement.
     */
    private final int MIN = 1;
    /**
     * Random number generator.
     */
    private final Random random = new Random();

    /**
     * Default constructor uses randomChangeGenerator method to generator a
     * random change request.
     */
    public ChangeRequirement() {
        changeRequirementSize = randomChangeGenerator();
    }

    /**
     * This constructor can be used to generate pre-defined sized
     * changeRequirement. This method checks if the parameter passed is of valid
     * change size. If it is not it use the same method as default constructor
     * (i.e. randomly generating valid change request)
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
     * @return A randomly generated valid change request.
     */
    private int randomChangeGenerator() {
        return random.nextInt((MAX - MIN) + 1) + MIN;
    }

    /**
     * This method checks is the change requested in the constructor is valid or
     * not.
     *
     * @param changeRequirementSize size of change requested.
     * @return true if change is valid (ie between 0 and 100 inclusive) false
     * otherwise.
     */
    private boolean changeIsValid(int changeRequirementSize) {
        return changeRequirementSize >= 0 && changeRequirementSize <= 100;
    }

    /**
     * Overridden toString() method.
     *
     * @return String representation of this object
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Change Requirement size is: ");
        sb.append(this.changeRequirementSize);
        return sb.toString();
    }

    /**
     * Overridden hashCode() method; mainly used for testing purposes.
     *
     * @return the hashCode of this object.
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(7, 37)
                .append(this.changeRequirementSize)
                .toHashCode();
    }

    /**
     * Overridden equals() method, mainly used for testing purposes.
     *
     * @param object The object to check equals.
     * @return true if this is equals to the object passed as parameter false
     * otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ChangeRequirement)) {
            return false;
        }
        if (object == this) {
            return true;
        }
        ChangeRequirement changeRequirement = (ChangeRequirement) object;
        return new EqualsBuilder().append(changeRequirementSize, changeRequirement.changeRequirementSize).isEquals();
    }
}
