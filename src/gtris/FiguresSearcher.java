package gtris;

/**
 * @date Jul 12, 2012
 * @author Fco. Delgado
 */
public class FiguresSearcher extends Thread {

    Manager manager;
    Block blockStart;

    /**
     * Save a manager object and the block for start the search.
     * @param manager
     * @param blockStart
     */
    public FiguresSearcher(Manager manager, Block blockStart) {
        this.manager = manager;
        this.blockStart = blockStart;
    }

    /**
     * Run method. Call to function for initialize the figures search.
     */
    @Override
    public void run() {
        manager.figuresSearcherAndBoardUpdaterAfterFoundFigures(blockStart);
    }
}
