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
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author S Desai
 */
public class SourceCodeQualityTest {

    public SourceCodeQualityTest() {
    }
    SourceCodeQuality codeQuality;
    int exp;

    @Before
    public void setUp() {
        codeQuality = new SourceCodeQuality(30);
        exp = 30;
    }

    @After
    public void tearDown() {
        codeQuality = null;
    }

    /**
     * Test of getCodeQuality method, of class SourceCodeQuality.
     */
    @Test
    public void testGetCodeQuality() {
        System.out.println("getCodeQuality");
        assertEquals(exp, codeQuality.getCodeQuality());
    }

    /**
     * Test of setCodeQuality method, of class SourceCodeQuality.
     */
    @Test
    public void testSetCodeQuality() {
        System.out.println("setCodeQuality");
        codeQuality.setCodeQuality(10);
        assertEquals(10, codeQuality.getCodeQuality());

        codeQuality.setCodeQuality(-100);
        assertFalse(codeQuality.getCodeQuality() == -100);
        assertEquals(30, codeQuality.getCodeQuality());

        codeQuality.setCodeQuality(0);
        assertFalse(codeQuality.getCodeQuality() == 0);
        assertEquals(30, codeQuality.getCodeQuality());
    }

    /**
     * Test of increaseQuality method, of class SourceCodeQuality.
     */
    @Test
    public void testIncreaseQuality_0args() {
        System.out.println("increaseQuality");
        codeQuality.increaseQuality();
        assertEquals(32, codeQuality.getCodeQuality());
    }

    /**
     * Test of increaseQuality method, of class SourceCodeQuality.
     */
    @Test
    public void testIncreaseQuality_int() {
        System.out.println("increaseQuality");
        codeQuality.increaseQuality(10);
        assertEquals(40, codeQuality.getCodeQuality());

        codeQuality.increaseQuality(0);
        assertEquals(42, codeQuality.getCodeQuality());

        codeQuality.increaseQuality(-10);
        assertEquals(44, codeQuality.getCodeQuality());
    }

    /**
     * Test of decreaseQuality method, of class SourceCodeQuality.
     */
    @Test
    public void testDecreaseQuality_0args() {
        System.out.println("decreaseQuality");
        codeQuality.decreaseQuality();
        assertEquals(29, codeQuality.getCodeQuality());
    }

    /**
     * Test of decreaseQuality method, of class SourceCodeQuality.
     */
    @Test
    public void testDecreaseQuality_int() {
        System.out.println("decreaseQuality");
        codeQuality.decreaseQuality(10);
        assertEquals(20, codeQuality.getCodeQuality());

        codeQuality.decreaseQuality(0);
        assertEquals(19, codeQuality.getCodeQuality());

        codeQuality.decreaseQuality(-10);
        assertEquals(18, codeQuality.getCodeQuality());
    }

    /**
     * Test of isBelowZero method, of class SourceCodeQuality.
     */
    @Test
    public void testIsBelowZero() {
        System.out.println("isBelowZero");

        @SuppressWarnings("LocalVariableHidesMemberVariable")
        SourceCodeQuality codeQuality = new SourceCodeQuality(-10);
        assertFalse(codeQuality.isBelowZero());

        codeQuality = new SourceCodeQuality(0);
        assertFalse(codeQuality.isBelowZero());

        codeQuality.setCodeQuality(-100);
        assertFalse(codeQuality.isBelowZero());
        codeQuality.setCodeQuality(0);
        assertFalse(codeQuality.isBelowZero());
        codeQuality.setCodeQuality(10);
        assertFalse(codeQuality.isBelowZero());

    }

    /**
     * Test of toString method, of class SourceCodeQuality.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        assertEquals("Code quality is: 30", codeQuality.toString());
    }

    /**
     * Test of hashCode method, of class SourceCodeQuality.
     */
    @Test
    @SuppressWarnings({"ObjectEqualsNull", "IncompatibleEquals"})
    public void testHashCode_Equals() {
        System.out.println("hashCode and equals");

        assertFalse(codeQuality.equals(null));
        assertTrue(codeQuality.hashCode() != 0);

        assertFalse(codeQuality.equals("hello"));
        assertFalse(codeQuality.hashCode() == "hello".hashCode());

        assertTrue(codeQuality.equals(codeQuality));
        assertEquals(codeQuality.hashCode(), codeQuality.hashCode());

        @SuppressWarnings("LocalVariableHidesMemberVariable")
        SourceCodeQuality codeQuality = new SourceCodeQuality(30);
        assertTrue(codeQuality.equals(this.codeQuality));
        assertTrue(this.codeQuality.equals(codeQuality));
        assertTrue(this.codeQuality.hashCode() == codeQuality.hashCode());

        codeQuality = new SourceCodeQuality(100);
        assertFalse(codeQuality.equals(this.codeQuality));
        assertFalse(this.codeQuality.hashCode() == codeQuality.hashCode());

        codeQuality = new SourceCodeQuality(0);
        assertTrue(codeQuality.equals(this.codeQuality));
        assertTrue(this.codeQuality.equals(codeQuality));
        assertTrue(this.codeQuality.hashCode() == codeQuality.hashCode());

        codeQuality = new SourceCodeQuality(-100);
        assertTrue(codeQuality.equals(this.codeQuality));
        assertTrue(this.codeQuality.equals(codeQuality));
        assertTrue(this.codeQuality.hashCode() == codeQuality.hashCode());
    }

}
