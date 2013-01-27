package gtris;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @date Jul 15, 2012
 * @author Fco. Delgado
 */
public class BoardUpdater extends Thread {

    Manager manager;
    PanelBoard panelToRepaint;

    /**
     * Initialize the manager and panel to repaint.
     * @param manager
     * @param panelBoard
     */
    public BoardUpdater(Manager manager, PanelBoard panelToRepaint) {
        this.manager = manager;
        this.panelToRepaint = panelToRepaint;
    }

    /**
     * Run method. Repaint the Jpanel.
     */
    @Override
    public void run() {
        while (!manager.getGameOver()) {
            panelToRepaint.repaint();
            manager.pauseThread();
        }
    }
}
