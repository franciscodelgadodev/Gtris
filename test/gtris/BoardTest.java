/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gtris;

import java.util.LinkedList;
import java.util.List;
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
public class BoardTest {

    Board instance;
    int defaultRows;
    int defaultColumns;
    int defaultLevel;

    public BoardTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        defaultRows = 15;
        defaultColumns = 6;
        defaultLevel = 1;
        instance = new Board(defaultRows, defaultColumns);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of validStaticPosition method, of class Board.
     */
    @Test
    public void testValidStaticPositionFalsewithOverflow() {
        System.out.println("testValidStaticPositionLimitWithInstance");
        assertFalse(instance.isValidStaticPosition(defaultRows, defaultColumns, null));
    }

    /**
     * Test of validStaticPosition method, of class Board.
     */
    @Test
    public void testValidStaticPositionFalseInLimit() {
        System.out.println("testValidStaticPositionLimitWithInstance");
        List<Block> list = new LinkedList<Block>();
        list.add(new Block(1, false, new Coordinate(defaultRows - 1, defaultColumns - 1)));
        instance.setBlocks(list);
        assertFalse(instance.isValidStaticPosition(defaultRows - 1, defaultColumns - 1, null));
    }

    /**
     * Test of validStaticPosition method, of class Board.
     */
    @Test
    public void testValidStaticPositionTrueInLimit() {
        System.out.println("testValidStaticPositionLimitWithoutInstance");
        assertTrue(instance.isValidStaticPosition(defaultRows - 1, defaultColumns - 1, null));
    }

    /**
     * Test of validStaticPosition method, of class Board.
     */
    @Test
    public void testValidStaticPositionNoLimitWithBlockDown() {
        System.out.println("testValidStaticPositionWithBlockDown");
        List<Block> list = new LinkedList<Block>();
        for (int i = 0; i < defaultColumns; i++) {
            list.add(new Block(1, false, new Coordinate(defaultRows - 1, i)));
        }
        assertTrue(instance.isValidStaticPosition(defaultRows - 2, defaultColumns - 1, list));
    }

    /**
     * Test of validStaticPosition method, of class Board.
     */
    @Test
    public void testValidStaticPositionNoLimitWithoutBlockDown() {
        System.out.println("testValidStaticPositionWithoutBlockDown");
        List<Block> list = new LinkedList<Block>();
        for (int i = 0; i < defaultColumns; i++) {
            list.add(null);
        }
        assertFalse(instance.isValidStaticPosition(defaultRows - 2, defaultColumns - 1, list));
        System.out.println("OK");
    }

    /**
     * Test of isRowOverflow method, of class Board.
     */
    @Test
    public void testIsRowOverflowTrue() {
        System.out.println("testIsRowOverflowTrue");
        boolean result = instance.isRowOverflow(defaultRows);
        assertTrue(result);
    }

    /**
     * Test of isRowOverflow method, of class Board.
     */
    @Test
    public void testIsRowOverflowFalse() {
        System.out.println("testIsRowOverflowFalse");
        boolean result = instance.isRowOverflow(defaultRows - 2);
        assertFalse(result);
    }

    /**
     * Test of isColumnOverflow method, of class Board.
     */
    @Test
    public void testIsColumnOverflowTrue() {
        System.out.println("testIsColumnOverflowTrue");
        boolean result = instance.isColumnOverflow(defaultColumns);
        assertTrue(result);
    }

    /**
     * Test of isColumnOverflow method, of class Board.
     */
    @Test
    public void testIsColumnOverflowFalse() {
        System.out.println("testIsColumnOverflowFalse");
        boolean result = instance.isColumnOverflow(defaultColumns - 2);
        assertFalse(result);
    }

    /**
     * Test of isNull method, of class Board.
     */
    @Test
    public void testIsNullTrue() {
        System.out.println("testIsNullTrue");
        List<Block> list = new LinkedList<Block>();
        list.add(new Block(1, false, new Coordinate(defaultRows - 1, defaultColumns - 1)));
        assertTrue(instance.isNull(defaultRows - 1, defaultColumns - 1));
    }

    /**
     * Test of isNull method, of class Board.
     */
    @Test
    public void testIsNullFalse() {
        System.out.println("testIsNullFalse");
        List<Block> list = new LinkedList<Block>();
        list.add(new Block(1, false, new Coordinate(defaultRows - 1, defaultColumns - 1)));
        instance.setBlocks(list);
        assertFalse(instance.isNull(defaultRows - 1, defaultColumns - 1));
    }

    /**
     * Test of isLastRow method, of class Board.
     */
    @Test
    public void testIsLastRowTrue() {
        System.out.println("testIsLastRowTrue");
        assertTrue(instance.isLastRow(defaultRows - 1));
    }

    /**
     * Test of isLastRow method, of class Board.
     */
    @Test
    public void testIsLastRowFalse() {
        System.out.println("testIsLastRowFalse");
        assertFalse(instance.isLastRow(defaultRows - 2));
    }

    /**
     * Test of isLastColumn method, of class Board.
     */
    @Test
    public void testIsLastColumnTrue() {
        System.out.println("testIsisLastColumnTrue");
        assertTrue(instance.isLastColumn(defaultColumns - 1));
    }

    /**
     * Test of isLastColumn method, of class Board.
     */
    @Test
    public void testIsLastColumnFalse() {
        System.out.println("testIsLastColumnFalse");
        assertFalse(instance.isLastColumn(2));
    }

    /**
     * Test of getBlock method, of class Board.
     */
    @Test
    public void testGetBlockNull() {
        System.out.println("testGetBlockNull");
        assertNull(instance.getBlock(defaultRows - 1, defaultColumns - 1));
    }

    /**
     * Test of getBlock method, of class Board.
     */
    @Test
    public void testGetBlockNotNull() {
        System.out.println("testGetBlockNotNull");
        List<Block> newBlock = new LinkedList<Block>();
        newBlock.add(new Block(1, false, new Coordinate(defaultRows - 1, defaultColumns - 1)));
        instance.setBlocks(newBlock);
        assertNotNull(instance.getBlock(defaultRows - 1, defaultColumns - 1));
    }

    /**
     * Test of setBlocks method, of class Board.
     */
    @Test
    public void testSetBlocks() {
        System.out.println("testSetBlocks");
        List<Block> newBlock = new LinkedList<Block>();
        newBlock.add(new Block(1, false, new Coordinate(defaultRows - 1, defaultColumns - 1)));
        instance.setBlocks(newBlock);
        assertEquals(newBlock.get(0), instance.getBlock(defaultRows - 1, defaultColumns - 1));
    }

    /**
     * Test of removeBlocks method, of class Board.
     */
    @Test
    public void testRemoveBlocks() {
        System.out.println("testRemoveBlocks");
        List<Block> newBlock = new LinkedList<Block>();
        newBlock.add(new Block(1, false, new Coordinate(defaultRows - 1, defaultColumns - 1)));
        instance.setBlocks(newBlock);
        instance.removeBlocks(newBlock);
        assertNull(instance.getBlock(defaultRows - 1, defaultColumns - 1));
    }

    /**
     * Test of getBoard method, of class Board.
     */
    @Test
    public void testGetBoard() {
        System.out.println("testGetBoard");
        assertArrayEquals(new Block[15][6], instance.getBoard());
    }

    @Test
    public void testGetRows() {
        System.out.println("testGetRows");
        int expResult = defaultRows;
        int result = instance.getRows();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRows method, of class Board.
     */
    @Test
    public void testSetRows() {
        System.out.println("testSetRows");
        int rows = 0;
        instance.setRows(rows);
        assertEquals(rows, instance.getRows());
    }

    /**
     * Test of getColumns method, of class Board.
     */
    @Test
    public void testGetColumns() {
        System.out.println("testGetColumns");
        int expResult = defaultColumns;
        int result = instance.getColumns();
        assertEquals(expResult, result);
    }

    /**
     * Test of setColumns method, of class Board.
     */
    @Test
    public void testSetColumns() {
        System.out.println("testSetColumns");
        int columns = 0;
        instance.setColumns(columns);
        assertEquals(columns, instance.getColumns());
    }
    /*
     *
     */
    /* @Test
    public void testGenerateStaticBlocksSize() {
    System.out.println("testGenerateStaticBlocksSize");
    List<Block> listBlocks = instance.generateStaticBlocks(defaultLevel);
    int counterBlocks = 0;
    Iterator<Block> iterator = listBlocks.iterator();
    while (iterator.hasNext()) {
    if (iterator.next() instanceof Block) {
    counterBlocks++;
    }
    }
    assertEquals(defaultLevel * 20, counterBlocks);
    System.out.println("OK");
    }*/
    /*
     *
     */

    /* @Test
    public void testGenerateDynamicBlocksSize() {
    System.out.println("testGenerateStaticBlocksSize");
    int counterBlocks = 0;
    int expResult = 2;
    List<Block> listBlocks = instance.generateDynamicBlocks(expResult);
    Iterator<Block> iterator = listBlocks.iterator();
    while (iterator.hasNext()) {
    if (iterator.next() instanceof Block) {
    counterBlocks++;
    }
    }
    assertEquals(expResult, counterBlocks);
    System.out.println("OK");
    }*/
    /**
     * Test of getRows method, of class Board.
     */
}
