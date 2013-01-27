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
public class ManagerTest {

    int defaultRows;
    int defaultColumns;
    int defaultLevel;
    Board board;
    Manager instance;

    public ManagerTest() {
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
        board = new Board(defaultRows, defaultColumns);
        instance = new Manager(defaultLevel);
        instance.setBoard(board);
    }

    @After
    public void tearDown() {
    }
    
    /**
     * Test of generateBlocksSeries method, of class Manager.
     */
    @Test
    public void testGenerateNewBlocksSeries() {
        System.out.println("testGenerateNewBlocksSeries");
        int numbBlocks = 2;
        List<Block> result = instance.generateNewBlocksSeries(numbBlocks);
        assertEquals(numbBlocks, result.size());
    }

    /**
     * Test of searchFigures method, of class Manager
     */
    @Test
    public void testSearchFiguresIncompleteFigure() {
        System.out.println("testSearchFiguresIncompleteFigure");
        List<Block> expResult = new LinkedList<Block>();
        expResult.add(new Block(1, false, new Coordinate(defaultRows - 1, defaultColumns - 1)));
        expResult.add(new Block(1, false, new Coordinate(defaultRows - 1, defaultColumns - 2)));
        expResult.add(new Block(1, false, new Coordinate(defaultRows - 2, defaultColumns - 1)));
        board.setBlocks(expResult);
        List<Block> result = instance.searchFigures(expResult.get(0), null);
        assertNotSame(expResult.size() + 1, result.size());
    }

    /**
     * Test of searchFigures method, of class Manager
     */
    @Test
    public void testSearchFiguresHorizontalFigure() {
        System.out.println("testSearchFiguresHorizontalFigure");
        List<Block> expResult = new LinkedList<Block>();
        expResult.add(new Block(1, false, new Coordinate(defaultRows - 1, defaultColumns - 1)));
        expResult.add(new Block(1, false, new Coordinate(defaultRows - 1, defaultColumns - 2)));
        expResult.add(new Block(1, false, new Coordinate(defaultRows - 1, defaultColumns - 3)));
        expResult.add(new Block(1, false, new Coordinate(defaultRows - 1, defaultColumns - 4)));
        board.setBlocks(expResult);
        List<Block> result = instance.searchFigures(expResult.get(0), null);
        assertEquals(expResult, result);
    }

    /**
     * Test of searchFigures method, of class Manager
     */
    @Test
    public void testSearchFiguresVeticalFigure() {
        System.out.println("testSearchFiguresVeticalFigure");
        List<Block> expResult = new LinkedList<Block>();
        expResult.add(new Block(1, false, new Coordinate(defaultRows - 1, defaultColumns - 1)));
        expResult.add(new Block(1, false, new Coordinate(defaultRows - 2, defaultColumns - 1)));
        expResult.add(new Block(1, false, new Coordinate(defaultRows - 4, defaultColumns - 1)));
        expResult.add(new Block(1, false, new Coordinate(defaultRows - 3, defaultColumns - 1)));
        board.setBlocks(expResult);
        List<Block> result = instance.searchFigures(expResult.get(2), null);
        assertEquals(expResult.size(), result.size());
    }

    /**
     * Test of searchFigures method, of class Manager
     */
    @Test
    public void testSearchFiguresQuadrateFigure() {
        System.out.println("testSearchFiguresQuadrateFigure");
        List<Block> expResult = new LinkedList<Block>();
        expResult.add(new Block(1, false, new Coordinate(defaultRows - 1, defaultColumns - 1)));
        expResult.add(new Block(1, false, new Coordinate(defaultRows - 1, defaultColumns - 2)));
        expResult.add(new Block(1, false, new Coordinate(defaultRows - 2, defaultColumns - 1)));
        expResult.add(new Block(1, false, new Coordinate(defaultRows - 2, defaultColumns - 2)));
        board.setBlocks(expResult);
        List<Block> result = instance.searchFigures(expResult.get(3), null);
        assertEquals(expResult.size(), result.size());
    }
    /*@Test
    public void testSearchFiguresSpecialCaseTFigure() {
    System.out.println("testSearchFiguresTFigure");
    List<Block> expResult = new LinkedList<Block>();
    expResult.add(new Block(1, false, new Coordinate(defaultRows - 1, defaultColumns - 2)));
    expResult.add(new Block(1, false, new Coordinate(defaultRows - 2, defaultColumns - 1)));
    expResult.add(new Block(1, false, new Coordinate(defaultRows - 2, defaultColumns - 2)));
    expResult.add(new Block(1, false, new Coordinate(defaultRows - 3, defaultColumns - 1)));
    expResult.add(new Block(1, false, new Coordinate(defaultRows - 2, defaultColumns - 3)));
    board.setBlocks(expResult);
    List<Block> result = instance.searchFigures(board, expResult.get(2), null);
    assertEquals(4, result.size());
    }*/

    /**
     * Test of searchFigures method, of class Manager
     */
    @Test
    public void testSearchFiguresLFigure() {
        System.out.println("testSearchFiguresLFigure");
        List<Block> expResult = new LinkedList<Block>();
        expResult.add(new Block(1, false, new Coordinate(defaultRows - 1, defaultColumns - 1)));
        expResult.add(new Block(1, false, new Coordinate(defaultRows - 1, defaultColumns - 2)));
        expResult.add(new Block(1, false, new Coordinate(defaultRows - 1, defaultColumns - 3)));
        expResult.add(new Block(1, false, new Coordinate(defaultRows - 1, defaultColumns - 4)));
        expResult.add(new Block(1, false, new Coordinate(defaultRows - 2, defaultColumns - 4)));
        board.setBlocks(expResult);
        List<Block> result = instance.searchFigures(expResult.get(4), null);
        assertEquals(4, result.size());
    }

    /**
     * Test of updateListDownBlocksToFindFigure method, of class Manager
     */
    @Test
    public void testUpdateListDownBlocksToFindFigureWithBlockFigureAbove() {
        System.out.println("testUpdateListDownBlocksToFindFigureWithBlockFigureAbove");
        List<Block> figure = new LinkedList<Block>();
        figure.add(new Block(1, false, new Coordinate(defaultRows - 1, defaultColumns - 1)));
        figure.add(new Block(1, false, new Coordinate(defaultRows - 1, defaultColumns - 2)));
        figure.add(new Block(1, false, new Coordinate(defaultRows - 2, defaultColumns - 1)));
        figure.add(new Block(1, false, new Coordinate(defaultRows - 2, defaultColumns - 2)));
        List<Block> population = new LinkedList<Block>();
        population.add(new Block(2, false, new Coordinate(defaultRows - 3, defaultColumns - 1)));
        population.add(new Block(0, false, new Coordinate(defaultRows - 4, defaultColumns - 1)));
         population.add(new Block(3, false, new Coordinate(defaultRows - 3, defaultColumns - 2)));
        population.add(new Block(4, false, new Coordinate(defaultRows - 4, defaultColumns - 2)));
        board.setBlocks(population);
        List<Block> result = instance.updateListDownBlocksToFindFigure(figure, figure.get(3), null);
        for (Block block : result) {
            System.out.print(block.getCoordinate().getRow() + " ");
            System.out.print(block.getCoordinate().getColumn());
            System.out.println();
        }
        assertEquals(population.subList(2, 4), result);
    }

    /**
     * Test of updateListDownBlocksToFindFigure method, of class Manager
     */
    @Test
    public void testUpdateListDownBlocksToFindFigureWithoutNullAbove() {
        System.out.println("testUpdateListDownBlocksToFindFigureWithoutNullAbove");
        List<Block> figure = new LinkedList<Block>();
        figure.add(new Block(1, false, new Coordinate(defaultRows - 1, defaultColumns - 1)));
        figure.add(new Block(1, false, new Coordinate(defaultRows - 1, defaultColumns - 2)));
        figure.add(new Block(1, false, new Coordinate(defaultRows - 1, defaultColumns - 3)));
        figure.add(new Block(1, false, new Coordinate(defaultRows - 2, defaultColumns - 3)));
        List<Block> population = new LinkedList<Block>();
        population.add(new Block(2, false, new Coordinate(defaultRows - 3, defaultColumns - 3)));
        population.add(new Block(3, false, new Coordinate(defaultRows - 4, defaultColumns - 3)));
        population.add(new Block(0, false, new Coordinate(defaultRows - 2, defaultColumns - 2)));
        population.add(new Block(4, false, new Coordinate(defaultRows - 2, defaultColumns - 1)));
        board.setBlocks(population);
        List<Block> result = instance.updateListDownBlocksToFindFigure(figure, figure.get(3), null);
        assertEquals(population.subList(0, 2), result);
    }

    /**
     * Test of updateListDownBlocksToFindFigure method, of class Manager
     */
    @Test
    public void testUpdateListDownBlocksToFindFigureWithoutBlockFigureAbove() {
        System.out.println("testUpdateListDownBlocksToFindFigureWithoutBlockFigureAbove");
        List<Block> figure = new LinkedList<Block>();
        figure.add(new Block(1, false, new Coordinate(defaultRows - 1, defaultColumns - 1)));
        figure.add(new Block(1, false, new Coordinate(defaultRows - 1, defaultColumns - 2)));
        figure.add(new Block(1, false, new Coordinate(defaultRows - 1, defaultColumns - 3)));
        figure.add(new Block(1, false, new Coordinate(defaultRows - 2, defaultColumns - 3)));
        List<Block> result = instance.updateListDownBlocksToFindFigure(figure, figure.get(3), null);
        assertEquals(0, result.size());
    }

    /**
     * Test of updatePositionDownBlocksToFindFigure method, of class Manager
     */
    @Test
    public void testUpdatePositionDownBlocksToFindFigureWithBlockDownInList() {
        System.out.println("testUpdatePositionDownBlocksToFindFigureWithBlockDownInList");
        List<Block> listDownBlocks = new LinkedList<Block>();
        listDownBlocks.add(new Block(4, false, new Coordinate(defaultRows - 2, defaultColumns - 1)));
        listDownBlocks.add(new Block(0, false, new Coordinate(defaultRows - 3, defaultColumns - 1)));
        List<Block> result = instance.updatePositionDownBlocksToFindFigure(listDownBlocks);
        assertEquals(defaultRows-2, result.get(1).getCoordinate().getRow());
    }

    /**
     * Test of updatePositionDownBlocksToFindFigure method, of class Manager
     */
    @Test
    public void testUpdatePositionDownBlocksToFindFigureWithoutBlockDownInList() {
        System.out.println("testUpdatePositionDownBlocksToFindFigure");
        List<Block> listDownBlocks = new LinkedList<Block>();
        listDownBlocks.add(new Block(4, false, new Coordinate(defaultRows - 2, defaultColumns - 1)));
        listDownBlocks.add(new Block(0, false, new Coordinate(defaultRows - 3, defaultColumns - 1)));
        List<Block> result = instance.updatePositionDownBlocksToFindFigure(listDownBlocks);
        assertEquals(defaultRows-1, result.get(0).getCoordinate().getRow());
    }

    /**
     * Test of updateListMovingAndStaticBlocks method, of class Manager.
     */
    @Test
    public void testUpdateListMovingAndStaticBlocks() {
        System.out.println("testUpdateListMovingAndStaticBlocks");
        List<List<Block>> result = new LinkedList<List<Block>>();
        List<List<Block>> expResult = new LinkedList<List<Block>>();
        List<Block> movingBlocks = new LinkedList<Block>();
        List<Block> staticBlocks = new LinkedList<Block>();
        List<Block> auxMovingBlocks = new LinkedList<Block>();

        instance.setNumberNewBlocks(2);

        auxMovingBlocks.add(Block.factory(1, false, new Coordinate(14, 5)));
        auxMovingBlocks.add(Block.factory(1, true, new Coordinate(13, 5)));
        auxMovingBlocks.add(Block.factory(2, true, new Coordinate(12, 5)));
        auxMovingBlocks.add(Block.factory(3, true, new Coordinate(10, 5)));
        auxMovingBlocks.add(Block.factory(4, true, new Coordinate(0, 0)));
        auxMovingBlocks.add(Block.factory(1, true, new Coordinate(10, 3)));
        board.setBlocks(auxMovingBlocks);
        movingBlocks.add(auxMovingBlocks.get(3));
        movingBlocks.add(auxMovingBlocks.get(4));
        movingBlocks.add(auxMovingBlocks.get(5));
        staticBlocks.add(auxMovingBlocks.get(0));
        staticBlocks.add(auxMovingBlocks.get(1));
        staticBlocks.add(auxMovingBlocks.get(2));
        result = instance.updateListMovingAndStaticBlocks(auxMovingBlocks);
        expResult.add(movingBlocks);
        expResult.add(staticBlocks);
        assertEquals(expResult, result);
    }

    /**
     * Test of updatePositionMovingBlocks method, of class Manager.
     */
    @Test
    public void testUpdatePositionMovingBlocks() {
        System.out.println("testUpdatePositionMovingBlocks");
        List<Block> result = new LinkedList<Block>();
        List<Block> auxMovingBlocks = new LinkedList<Block>();

        auxMovingBlocks.add(Block.factory(1, true, new Coordinate(13, 5)));

        result = instance.updatePositionMovingBlocks(auxMovingBlocks);
        assertEquals(14, result.get(0).getCoordinate().getRow());
    }

    /**
     * Test of areSameColor method, of class Manager.
     */
    @Test
    public void testAreSameColorTrue() {
        System.out.println("testAreSameColorTrue");
        Block block1 = new Block(1, true, new Coordinate(0, 0));
        Block block2 = new Block(1, true, new Coordinate(0, 1));
        assertTrue(instance.areSameColor(block1, block2));
    }

    /**
     * Test of areSameColor method, of class Manager.
     */
    @Test
    public void testAreSameColorFalse() {
        System.out.println("testAreSameColorFalse");
        Block block1 = new Block(1, true, new Coordinate(0, 0));
        Block block2 = new Block(0, true, new Coordinate(0, 1));
        assertFalse(instance.areSameColor(block1, block2));
    }

    /**
     * Test of isTForm method, of class Manager.
     */
    @Test
    public void testIsTFormTrue() {
        System.out.println("testIsTFormTrue");
        List<Block> expResult = new LinkedList<Block>();
        Block blockToAdd = new Block(1, false, new Coordinate(defaultRows - 1, defaultColumns - 1));
        expResult.add(new Block(1, false, new Coordinate(defaultRows - 1, defaultColumns - 2)));
        expResult.add(new Block(1, false, new Coordinate(defaultRows - 1, defaultColumns - 3)));
        expResult.add(new Block(1, false, new Coordinate(defaultRows - 2, defaultColumns - 2)));
        board.setBlocks(expResult);
        assertTrue(instance.isTForm(blockToAdd, expResult));
    }

    /**
     * Test of isTForm method, of class Manager.
     */
    @Test
    public void testIsTFormFalse() {
        System.out.println("testIsTFormFalse");
        List<Block> expResult = new LinkedList<Block>();
        Block blockToAdd = new Block(1, false, new Coordinate(defaultRows - 2, defaultColumns - 3));
        expResult.add(new Block(1, false, new Coordinate(defaultRows - 1, defaultColumns - 2)));
        expResult.add(new Block(1, false, new Coordinate(defaultRows - 1, defaultColumns - 3)));
        expResult.add(new Block(1, false, new Coordinate(defaultRows - 2, defaultColumns - 2)));
        board.setBlocks(expResult);
        assertFalse(instance.isTForm(blockToAdd, expResult));
    }

    /**
     * Test of isGameOver method, of class Manager.
     */
    @Test
    public void testIsGameOverTrue() {
        System.out.println("testIsGameOverTrue");
        List<Block> listNewBlocks = new LinkedList<Block>();
        List<Block> list = new LinkedList<Block>();
        instance.setNumberNewBlocks(2);
        int numbBlocks = 2;
        for (int i = 0; i < board.getColumns(); i++) {
            list.add(new Block(1, false, new Coordinate(0, i)));
        }
        board.setBlocks(list);
        listNewBlocks = instance.generateNewBlocksSeries(numbBlocks);
        boolean result = instance.isGameOver(listNewBlocks);
        assertTrue(result);
    }

    /**
     * Test of isGameOver method, of class Manager.
     */
    @Test
    public void testIsGameOverFalse() {
        System.out.println("testIsGameOverFalse");
        List<Block> list = new LinkedList<Block>();
        int numbBlocks = 2;
        list.add(new Block(1, true, new Coordinate(0, 1)));
        list.add(new Block(1, true, new Coordinate(0, 2)));
        instance.generateNewBlocksSeries(numbBlocks);
        boolean result = instance.isGameOver(list);
        assertFalse(result);
    }

    /**
     * Test of isBlockInList method, of class Manager.
     */
    @Test
    public void testIsBlockInListTrue() {
        System.out.println("testIsBlockInListTrue");
        Block block = new Block(1, true, new Coordinate(1, 0));
        List<Block> list = new LinkedList<Block>();
        list.add(new Block(3, true, new Coordinate(5, 3)));
        list.add(block);
        boolean expResult = true;
        boolean result = instance.isBlockInList(block, list);
        assertEquals(expResult, result);
    }

    /**
     * Test of isBlockInList method, of class Manager.
     */
    @Test
    public void testIsBlockInListFalse() {
        System.out.println("testIsBlockInListFalse");
        Block block = new Block(1, true, new Coordinate(1, 0));
        List<Block> list = new LinkedList<Block>();
        list.add(new Block(3, true, new Coordinate(5, 3)));
        list.add(new Block(3, true, new Coordinate(5, 7)));
        list.add(new Block(3, true, new Coordinate(0, 0)));
        boolean expResult = false;
        boolean result = instance.isBlockInList(block, list);
        assertEquals(expResult, result);
    }

    /**
     * Test of areCoordinatesInList method, of class Manager.
     */
    @Test
    public void testAreCoordinatesInListTrue() {
        System.out.println("testAreCoordinatesInListTrue");
        List<Block> list = new LinkedList<Block>();
        list.add(new Block(3, true, new Coordinate(5, 3)));
        list.add(new Block(3, true, new Coordinate(5, 7)));
        assertTrue(instance.areCoordinatesInList(list, 5, 3));
    }

    /**
     * Test of areCoordinatesInList method, of class Manager.
     */
    @Test
    public void testAreCoordinatesInListFalse() {
        System.out.println("testAreCoordinatesInListFalse");
        List<Block> list = new LinkedList<Block>();
        list.add(new Block(3, true, new Coordinate(5, 3)));
        list.add(new Block(3, true, new Coordinate(5, 7)));
        assertFalse(instance.areCoordinatesInList(list, 3, 7));
    }
}
