package gtris;

import java.util.List;

/**
 * @date Jul 3, 2012
 * @author Fco. Delgado
 */
public class Board {

    private int rows;
    private int columns;
    private Block board[][];

    /**
     * Constructor of Board class. Initialize number of rows and columns that will have the board.
     * @param rows Number of rows for the board.
     * @param columns Number of columns for the board.
     */
    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        board = new Block[rows][columns];
    }

    /**
     * Valid if a position is valid when the board is populating.
     * Review if the position is't overflow.
     * Review if the given coordinate is in the last row of board and thath position is null.
     * Review that a position below of the given coordinate is a instance of Block.
     * @param row Row number.
     * @param column Column number.
     * @param list List of the Blocks that was crated.
     * @return Return a boolean value. The value is true if the position is Valid.
     */
    public boolean isValidStaticPosition(int row, int column, List<Block> list) {
        if (isRowOverflow(row) || isColumnOverflow(column)) {
            return false;
        }
        if (isLastRow(row)) {
            return isNull(row, column);
        }
        //Review in the list if the down position isn't null.
        return (list.get(list.size() - columns) != null) ? true : false;
    }

    /**
     * Review if a Row position is Overflow in Board.
     * @param row Row number.
     * @return If the given row is out of board return True. Namely that position don't exist in board. If return False, is a correct position.
     */
    public boolean isRowOverflow(int row) {
        return (row >= rows || row < 0) ? true : false;
    }

    /**
     * Review if a Column is Overflow in Board.
     * @param column Column number.
     * @return If the given column is out of board return True. Namely that position don't exist in board. If return False, is a correct position.
     */
    public boolean isColumnOverflow(int column) {
        return (column >= columns || column < 0) ? true : false;
    }

    /**
     * Review if a position in board is Null value.
     * @param row Row number.
     * @param column Column number.
     * @return If the content of board in the given coordinates the value is Null reeturn True. If is a instance of Block return false.
     */
    public boolean isNull(int row, int column) {
        return (board[row][column] == null) ? true : false;
    }

    /**
     * Review if a Row is in the border of board.
     * @param row Row number.
     * @return Return True is the given Row is in the border. If the given Row is in another position return False.
     */
    public boolean isLastRow(int row) {
        return (row + 1 == rows) ? true : false;
    }

    /**
     * Review if a Column is in the border of board.
     * @param column Column number.
     * @return Return True is the given Column is in the border. If the given Column is in another position return False.
     */
    public boolean isLastColumn(int column) {
        return (column + 1 == columns) ? true : false;
    }

    /**
     * Return a block from board. Go to board in the given coordinates and take the value.
     * @param row Row number.
     * @param column Column number
     * @return The value of position given. It's can a instance of Block or a null value.
     */
    public Block getBlock(int row, int column) {
        return board[row][column];
    }

    /**
     * Set a list of Block instances in the board.
     * @param listBlocks List of Block instances to save in board.
     */
    public synchronized void setBlocks(List<Block> listBlocks) {
        for (Block currentBlock : listBlocks) {
            if (currentBlock instanceof Block) {
                board[currentBlock.getCoordinate().getRow()][currentBlock.getCoordinate().getColumn()] = currentBlock;
            }
        }
    }

    /**
     * Remove All Block Objects in board contains in the list. Set null values.
     * @param listBlocks List of Blocks to remove from board.
     */
    public synchronized void removeBlocks(List<Block> listBlocks) {
        for (Block currentBlock : listBlocks) {
            if (currentBlock instanceof Block) {
                board[currentBlock.getCoordinate().getRow()][currentBlock.getCoordinate().getColumn()] = null;
            }
        }
    }

    /**
     * Remove all blocks of Board. Set Null values.
     */
    public void removeAll() {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                board[row][column] = null;
            }
        }
    }

    /**
     * Get the board. Contains all Blocks.
     * @return The board of game. Contains All Blocks.
     */
    public Block[][] getBoard() {
        return board;
    }

    /**
     * Get the number of board's rows;
     * @return Number of board's rows.
     */
    public int getRows() {
        return rows;
    }

    /**
     * Set a value for rows attribute.Define the number of board's rows.
     * @param rows Number of rows for board.
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * Get the number of board's columns;
     * @return Number of board's columns.
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Set a value for columns attribute.Define the number of board's columns.
     * @param columns Number of columns for board.
     */
    public void setColumns(int columns) {
        this.columns = columns;
    }

    /**
     * Print board console mode.
     */
    public void printboard() {
        String boardString = "";
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                boardString += (board[row][column] != null) ? board[row][column].getColor() + " " : "  ";
            }
            boardString += "\n";
        }
        System.out.println(boardString);
    }
}
