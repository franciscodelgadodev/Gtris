/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gtris;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author chisco
 */
public class CoordinateTest {

    Coordinate instance;
    int defaultRow;
    int defaultColumn;

    public CoordinateTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        defaultRow = 5;
        defaultColumn = 6;
        instance = new Coordinate(defaultRow, defaultColumn);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getRow method, of class Coordinate.
     */
    @Test
    public void testGetRow() {
        System.out.println("getRow");
        int expResult = defaultRow;
        int result = instance.getRow();
        assertEquals(expResult, result);
        System.out.println("OK");
    }

    /**
     * Test of setRow method, of class Coordinate.
     */
    @Test
    public void testSetRow() {
        System.out.println("setRow");
        int row = 0;
        instance.setRow(row);
        assertEquals(row, instance.getRow());
        System.out.println("OK");
    }

    /**
     * Test of getColumn method, of class Coordinate.
     */
    @Test
    public void testGetColumn() {
        System.out.println("getColumn");
        int expResult = defaultColumn;
        int result = instance.getColumn();
        assertEquals(expResult, result);
        System.out.println("OK");
    }

    /**
     * Test of setColumn method, of class Coordinate.
     */
    @Test
    public void testSetColumn() {
        System.out.println("setColumn");
        int column = 0;
        instance.setColumn(column);
        assertEquals(column, instance.getColumn());
        System.out.println("OK");
    }

}