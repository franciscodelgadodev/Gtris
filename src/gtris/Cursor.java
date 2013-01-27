package gtris;

import java.util.LinkedList;
import java.util.List;

/**
 * @date Jul 2, 2012
 * @author Fco. Delgado
 */
public class Cursor {

    private Manager manager;
    private Coordinate coordinate;
    private List<Block> listBlocksSwitch = new LinkedList<Block>();

    /**
     * Constructor of class. Define the image for the block and a coordenate.
     * @param manager
     */
    public Cursor(Manager manager,int initialRow,int initialColumn) {
        this.manager = manager;
        coordinate = new Coordinate(initialRow, initialColumn);
    }

    /**
     * Get cursor coordinate coordinate.
     * @return Cursor coordinate.
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * Set cursor coordinate.
     * @param Cursor coordinate.
     */
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    /**
     * Get cursor status.
     * @return true if it's free.
     */
    public boolean isFree() {
        return (listBlocksSwitch.isEmpty()) ? true : false;
    }


    /**
     * Get list of block to switch.
     * @return Cursor coordinate.
     */
    public List<Block> getListBlockSwitch() {
        return listBlocksSwitch;
    }

    /**
     * Set list of blocks to switch.
     * @param Lis of block to switch.
     */
    public void setListBlockSwitch(List<Block> listBlocksSwitch) {
        this.listBlocksSwitch = listBlocksSwitch;
    }

    /**
     * Change the coordinates of cursor if not is overflow and there isn't other cursor in the same coordinate.
     * @param row New row position.
     * @param column New column position.
     * @param rowBrother Row position of another cursor.
     * @param columnBrother Column position of another cursor.
     */
    public void moveCursor(int row, int column,int rowBrother,int columnBrother) {
        Board board = manager.getBoard();
        if (!board.isRowOverflow(row) && !board.isColumnOverflow(column) && !areSameCoordinate(row, column, rowBrother, columnBrother)) {
            coordinate.setRow(row);
            coordinate.setColumn(column);
        }
    }

    /**
     * Switch two blocks if are the same color, are static blocks and current block don't belong another cursor.
     * @param cursorBrother 
     */
    public void switchBlocks(Cursor cursorBrother) {
        Board board = manager.getBoard();
        int currentRow = coordinate.getRow();
        int currentColumn = coordinate.getColumn();
        Block currentBlock = board.getBlock(currentRow, currentColumn);
        if (!board.isNull(currentRow, currentColumn) && !currentBlock.getMovement() && !manager.isBlockInList(currentBlock, cursorBrother.getListBlockSwitch())) {
            if (listBlocksSwitch.isEmpty()) {
                listBlocksSwitch.add(currentBlock);
            } else {
                if (/*isNeighborValidPosition(currentRow, currentColumn) && */ !manager.areSameColor(currentBlock, listBlocksSwitch.get(0))) {
                    int auxColor = currentBlock.getColor();
                    currentBlock.setColor(listBlocksSwitch.get(0).getColor());
                    listBlocksSwitch.get(0).setColor(auxColor);
                    listBlocksSwitch.add(currentBlock);
                    manager.getBoard().setBlocks(listBlocksSwitch);
                    manager.runThreadsSearchers(listBlocksSwitch);
                }
                listBlocksSwitch.clear();
            }
        }
    }

    /**
     * Review if a neighbor position is valid.
     * @param neighborRow
     * @param neighborColumn
     * @return True if is valid position.
     */
    private boolean isNeighborValidPosition(int neighborRow, int neighborColumn) {
        int currentRow = listBlocksSwitch.get(0).getCoordinate().getRow();
        int currentColumn = listBlocksSwitch.get(0).getCoordinate().getColumn();
        if (currentRow + 1 == neighborRow && currentColumn == neighborColumn) {
            return true;
        }
        if (currentRow - 1 == neighborRow && currentColumn == neighborColumn) {
            return true;
        }
        if (currentRow == neighborRow && currentColumn + 1 == neighborColumn) {
            return true;
        }
        if (currentRow == neighborRow && currentColumn - 1 == neighborColumn) {
            return true;
        }
        return false;
    }

    /**
     * Review if the two coordinate's pair are the same.
     * @param row
     * @param column
     * @param rowBrother
     * @param columnBrother
     * @return True if the coordinates are the same.
     */
    private boolean areSameCoordinate(int row, int column,int rowBrother,int columnBrother){
        return (row == rowBrother && column== columnBrother)?true:false;
    }
}
