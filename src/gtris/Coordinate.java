package gtris;

/**
 * @date Jul 3, 2012
 * @author Fco. Delgado
 */
public class Coordinate {

    private int row, column;

    /**
     * Contructor methos. Save a number row and a number column.
     * @param row
     * @param column
     */
    public Coordinate(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Get methos to row.
     * @return row.
     */
    public int getRow() {
        return row;
    }

    /**
     * Set method to row.
     * @param row
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Get method to Column.
     * @return column.
     */
    public int getColumn() {
        return column;
    }

    /**
     * Set method to column.
     * @param column
     */
    public void setColumn(int column) {
        this.column = column;
    }
}
