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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author S Desai
 */
public class ChangeRequirementTest {

    public ChangeRequirementTest() {
    }
    ChangeRequirement changeRequirement;
    int exp;

    @Before
    public void setUp() {
        changeRequirement = new ChangeRequirement(10);
        exp = 10;
    }

    @After
    public void tearDown() {
        changeRequirement = null;
    }

    /**
     * Test of getChangeRequirementSize method, of class ChangeRequirement.
     */
    @Test
    public void testGetChangeRequirementSize() {
        System.out.println("getChangeRequirementSize");
        assertEquals(exp, changeRequirement.getChangeRequirementSize());
    }

    /**
     * Test of toString method, of class ChangeRequirement.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expString = "Change Requirement size is: " + changeRequirement.getChangeRequirementSize();
        assertEquals(expString, changeRequirement.toString());
    }

    /**
     * Test of hashCode and equals methods, of class ChangeRequirement.
     */
    @Test
    @SuppressWarnings({"ObjectEqualsNull", "IncompatibleEquals"})
    public void testHashCode_Equals() {
        System.out.println("hashCode and equals");

        assertFalse(changeRequirement.equals(null));
        assertTrue(changeRequirement.hashCode() != 0);

        assertFalse(changeRequirement.equals("Hello"));
        assertFalse(changeRequirement.hashCode() == "Hello".hashCode());

        assertTrue(changeRequirement.equals(changeRequirement));
        assertTrue(changeRequirement.hashCode() == changeRequirement.hashCode());

        @SuppressWarnings("LocalVariableHidesMemberVariable")
        ChangeRequirement changeRequirement = new ChangeRequirement(10);
        assertTrue(changeRequirement.equals(this.changeRequirement));
        assertTrue(changeRequirement.hashCode() == this.changeRequirement.hashCode());

        changeRequirement = new ChangeRequirement(20);
        assertFalse(changeRequirement.equals(this.changeRequirement));
        assertFalse(changeRequirement.hashCode() == this.changeRequirement.hashCode());
    }

}
