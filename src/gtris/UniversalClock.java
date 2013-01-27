package gtris;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @date Jul 15, 2012
 * @author Fco. Delgado
 */
public class UniversalClock extends Thread {

    private long time = 0;
    private Manager manager;

    /**
     * Save a manager instance
     * @param manager
     */
    public UniversalClock(Manager manager) {
        this.manager = manager;
    }

    /**
     * Run method. Update the universal clock and the time interval in manager class .
     */
    @Override
    public void run() {
        while (!manager.getGameOver()) {
            try {
                manager.pauseThread();
                time++;
                if (time % 200 == 0) {
                    manager.setTimeInterval(manager.getTimeInterval() - 1);
                }
                manager.setUniversalClock(time);
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(UniversalClock.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
