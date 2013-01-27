package gtris;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @date Jul 16, 2012
 * @author Fco. Delgado
 */
public class ManipulateNewBlockSeries extends Thread {

    private Manager manager;
    private List<Block> newBlocks;
    private List<List<Block>> movingAndStaticBlocks;
    private List<Block> movingBlocks;
    private List<Block> staticBlocks;

    /**
     * Save manager object and movingBlocks.
     * @param manager
     * @param movingBlocks Blocks to move. It can be null.
     */
    public ManipulateNewBlockSeries(Manager manager, List<Block> movingBlocks) {
        this.manager = manager;
        this.movingBlocks = movingBlocks;
    }

    /**
     * Run methos. Create new blocks and move another.
     */
    @Override
    public void run() {
        if (movingBlocks == null) {
            int tries = tryGenerateBlockSeries();
            if (tries == 10) {
                manager.setGameOver(true);
            } else {
                movingBlocks = newBlocks;
            }
        }
        while (!manager.getGameOver()) {
            try {
                manager.pauseThread();
                Thread.sleep(manager.getFrequency());
                updatePositionBlockSeries();
                manager.runThreadsSearchers(staticBlocks);
            } catch (InterruptedException ex) {
                Logger.getLogger(ManipulateNewBlockSeries.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Call to manager and use her methods for update the position of moving blocks.
     */
    private void updatePositionBlockSeries() {
        movingAndStaticBlocks = manager.updateListMovingAndStaticBlocks(movingBlocks);
        movingBlocks = movingAndStaticBlocks.get(0);
        staticBlocks = movingAndStaticBlocks.get(1);
        manager.getBoard().removeBlocks(movingBlocks);
        movingBlocks = manager.updatePositionMovingBlocks(movingBlocks);
        manager.getBoard().setBlocks(movingBlocks);
        manager.getBoard().setBlocks(staticBlocks);
    }

    /**
     * Try generate a new block series in diferent positions to find a valid position.
     * @return tries.
     */
    private int tryGenerateBlockSeries() {
        int tries = 0;
        while (tries < 10) {
            newBlocks = manager.generateNewBlocksSeries(manager.getNumberNewBlocks());
            if (!manager.isGameOver(newBlocks)) {
                break;
            } else {
                tries++;
            }
        }
        return tries;
    }
}
