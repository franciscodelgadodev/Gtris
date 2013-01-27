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
public class BlockTest {

    Block instance;
    int defaultColor;
    int defaultRow;
    int defaultColumn;
    boolean defaultMovement;

    public BlockTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        defaultColor = 0;
        defaultMovement = true;
        defaultRow = 5;
        defaultColumn = 5;
        instance = new Block(defaultColor, defaultMovement, new Coordinate(defaultRow, defaultColumn));
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getColor method, of class Block.
     */
    @Test
    public void testGetColor() {
        System.out.println("testGetColor");
        int expResult = defaultColor;
        int result = instance.getColor();
        assertEquals(expResult, result);
    }

    /**
     * Test of setColor method, of class Block.
     */
    @Test
    public void testSetColor() {
        System.out.println("testSetColor");
        int color = 3;
        instance.setColor(color);
        int result = instance.getColor();
        assertEquals(color, result);
    }

    /**
     * Test of getMovement method, of class Block.
     */
    @Test
    public void testGetMovement() {
        System.out.println("testGetMovement");
        boolean expResult = defaultMovement;
        boolean result = instance.getMovement();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMovement method, of class Block.
     */
    @Test
    public void testSetMovement() {
        System.out.println("testSetMovement");
        boolean movement = false;
        instance.setMovement(movement);
        boolean result = instance.getMovement();
        assertEquals(movement, result);
    }

    /**
     * Test of factory method, of class Block.
     */
    @Test
    public void testFactoryBlockSameClass() {
        System.out.println("testFactoryBlockSameClass");
        Block result = Block.factory(5, false, new Coordinate(5, 5));
        assertEquals(instance.getClass(), result.getClass());
    }

    /**
     * Test of factory method, of class Block.
     */
    @Test
    public void testFactoryBlockSameColor() {
        System.out.println("testFactoryBlockSameColor");
        int maxColor = 1;
        Block result = Block.factory(maxColor, false, new Coordinate(5, 5));
        assertEquals(defaultColor, result.getColor());
    }

    /**
     * Test of factory method, of class Block.
     */
    @Test
    public void testFactoryBlockCorrectRow() {
        System.out.println("testFactoryBlockCorrectRow");
        Block result = Block.factory(1, false, new Coordinate(5, 0));
        assertEquals(defaultRow, result.getCoordinate().getRow());
    }

    /**
     * Test of factory method, of class Block.
     */
    @Test
    public void testFactoryBlockCorrectColumn() {
        System.out.println("testFactoryBlockCorrectColumn");
        Block result = Block.factory(5, false, new Coordinate(0, 5));
        assertEquals(defaultColumn, result.getCoordinate().getColumn());
    }
}
