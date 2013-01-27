package gtris;

import java.util.Random;

/**
 * @date Jul 3, 2012
 * @author Fco. Delgado
 */
public class Block {

    private int color;
    private boolean movement;
    private Coordinate coordinate;

    /**
     * Constructor method. Initialize values for color,movement and coordinates.
     * @param color Identify a block with a color in the game. 0-Red, 1-Pink, 2-Green, 3-Yellow, 4-Blue.
     * @param movement Identify if a block is moving. True is moving. False is static.
     * @param coordinate Coordinate of a block in the plot.
     */
    public Block(int color, boolean movement, Coordinate coordinate) {
        this.color = color;
        this.movement = movement;
        this.coordinate = coordinate;
    }

    /**
     * Get the block color.
     * @return Block color. It's a int value.0-Red, 1-Pink, 2-Green, 3-Yellow, 4-Blue.
     */
    public int getColor() {
        return color;
    }

    /**
     * Set the color value.
     * @param color Define the block color.0-Red, 1-Pink, 2-Green, 3-Yellow, 4-Blue.
     */
    public void setColor(int color) {
        this.color = color;
    }

    /**
     * Get true if the block is Moving.
     * @return True if the block is moving. False if is a static block.
     */
    public boolean getMovement() {
        return movement;
    }

    /**
     * If a block is moving set true value. If a block isn't moving set false value.
     * @param movement
     */
    public void setMovement(boolean movement) {
        this.movement = movement;
    }

    /**
     * Get block coordinate coordinate.
     * @return Block coordinate.
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * Set block coordinate.
     * @param Block coordinate.
     */
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    /**
     * The static factory method. This method is a factory of blocks. Create a block whit the propiertes given.
     * @param maxColor Maximum color range. The method generate a block with a random color between 0 and this parameter. The maximum value is 5. 6 or more return null.
     * @param movement If the new block is moving set true. If the new block is static set false.
     * @param coordinate A instance of Coordinate. That's the position in board.
     * @return A new block whit random color, whit a status and a coordinate in the board.
     */
    public static Block factory(int maxColor, boolean movement, Coordinate coordinate) {
        int color = new Random().nextInt(maxColor);
        if (color < 5 && color > -1) {
            return new Block(color, movement, coordinate);
        }
        return null;
    }
}
