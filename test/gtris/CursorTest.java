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
public class CursorTest {

    public CursorTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of moveCursor method, of class Cursor.
     */
    @Test
    public void testMoveCursorTrue() {
        System.out.println("moveCursorTrue");
        int row = 0;
        int column = 0;
        Manager manager = new Manager(1);
        manager.start();
        Cursor instance = new Cursor(manager, row, column);
        instance.moveCursor(row + 1, column,5,5);
        assertEquals(row+1, instance.getCoordinate().getRow());
    }

    /**
     * Test of moveCursor method, of class Cursor.
     */
    @Test
    public void testMoveCursorFalse() {
        System.out.println("moveCursorFalse");
        int row = 0;
        int column = 0;
        Manager manager = new Manager(1);
        manager.start();
        Cursor instance = new Cursor(manager, row, column);
        instance.moveCursor(row + 1, column,1,0);
        assertEquals(row, instance.getCoordinate().getRow());
    }
}
