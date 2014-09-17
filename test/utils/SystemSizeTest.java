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
public class SystemSizeTest {

    public SystemSizeTest() {

    }
    SystemSize systemSize;
    int exp;

    @Before
    public void setUp() {
        systemSize = new SystemSize(100);
        exp = 100;
    }

    @After
    public void tearDown() {
        systemSize = null;
    }

    /**
     * Test of getSoftSize method, of class SystemSize.
     */
    @Test
    public void testGetSoftSize() {
        System.out.println("getSoftSize");
        assertEquals(exp, systemSize.getSoftSize());
    }

    /**
     * Test of setSoftSize method, of class SystemSize.
     */
    @Test
    public void testSetSoftSize() {
        System.out.println("setSoftSize");
        systemSize.setSoftSize(101);
        assertFalse(systemSize.getSoftSize() == exp);
        assertTrue(systemSize.getSoftSize() == 101);
    }

    /**
     * Test of increaseSize method, of class SystemSize.
     */
    @Test
    public void testIncreaseSize_0args() {
        System.out.println("increaseSize");
        systemSize.increaseSize();
        assertFalse(systemSize.getSoftSize() == 100);
        assertTrue(systemSize.getSoftSize() == 101);
    }

    /**
     * Test of increaseSize method, of class SystemSize.
     */
    @Test
    public void testIncreaseSize_int() {
        System.out.println("increaseSize");
        systemSize.increaseSize(10);
        assertFalse(systemSize.getSoftSize() == 100);
        assertTrue(systemSize.getSoftSize() == 110);
        assertEquals(110, systemSize.getSoftSize());
    }

    /**
     * Test of decreaseSize method, of class SystemSize.
     */
    @Test
    public void testDecreaseSize() {
        System.out.println("decreaseSize");
        systemSize.decreaseSize();
        assertFalse(systemSize.getSoftSize() == 100);
        assertTrue(systemSize.getSoftSize() == 99);
        assertEquals(99, systemSize.getSoftSize());
    }

    /**
     * Test of toString method, of class SystemSize.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expString = "Software size is: " + systemSize.getSoftSize();
        assertEquals(expString, systemSize.toString());
        assertFalse(systemSize.toString().equals(""));

    }

    /**
     * Test of hashCode method, of class SystemSize.
     */
    @Test
    @SuppressWarnings({"ObjectEqualsNull", "IncompatibleEquals"})
    public void testHashCode_Equals() {
        System.out.println("hashCode and equals");

        assertFalse(systemSize.equals(null));
        assertTrue(systemSize.hashCode() != 0);

        assertFalse(systemSize.equals("hello"));
        assertFalse(systemSize.hashCode() == "hello".hashCode());

        assertTrue(systemSize.equals(systemSize));
        assertTrue(systemSize.hashCode() == systemSize.hashCode());

        @SuppressWarnings("LocalVariableHidesMemberVariable")
        SystemSize systemSize = new SystemSize(100);
        assertTrue(systemSize.equals(this.systemSize));
        assertTrue(systemSize.hashCode() == this.systemSize.hashCode());

        systemSize = new SystemSize(200);
        assertFalse(systemSize.equals(this.systemSize));
        assertFalse(systemSize.hashCode() == this.systemSize.hashCode());
    }

}
