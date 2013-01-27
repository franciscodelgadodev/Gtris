package gtris;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @date Jul 15, 2012
 * @author Fco. Delgado
 */
public class PanelBoard extends JPanel implements KeyListener, ActionListener {

    private Manager manager;
    private Cursor cursor1, cursor2;
    private JButton resume, restart, exit;

    /**
     * Initialize  the size and Keylistener.
     * @param level
     */
    public PanelBoard(int level) {
        setLayout(null);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        setSize(480, 630);
        setFocusable(true);
        addKeyListener(this);
        initializeButtonsPropiertes();
        startControlThreads(level);
    }

    private void initializeButtonsPropiertes() {
        resume = new JButton("Resume");
        restart = new JButton("Restart");
        exit = new JButton("Exit");
        exit.setBounds(18, 90, 150, 150);
        restart.setBounds(18, 240, 150, 150);
        resume.setBounds(18, 390, 150, 150);
        resume.setBackground(Color.green);
        restart.setBackground(Color.blue);
        exit.setBackground(Color.red);
        resume.addActionListener(this);
        restart.addActionListener(this);
        exit.addActionListener(this);
    }

    /**
     * Start the control threads.
     * Start manager Thread and board updater tread.
     */
    private void startControlThreads(int level) {
        manager = new Manager(level);
        manager.start();
        cursor1 = new Cursor(manager, 0, 0);
        cursor2 = new Cursor(manager, manager.getBoard().getRows() - 1, manager.getBoard().getColumns() - 1);
        BoardUpdater boardUpdater = new BoardUpdater(manager, this);
        boardUpdater.start();
    }

    /**
     * Method of superclass. Used for repaint JPanel.
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (manager.getBoard() instanceof Board) {
            printBoardGame(g);
            printDataGame(g);
            if (manager.getGameOver()) {
                printGameOver(g);
            }
            if (manager.getPause()) {
                printPause(g);
            }
        }
    }

    /**
     * Print a Game over message at screen center with game options.
     * @param g
     */
    private void printGameOver(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Helvetica", Font.BOLD, 25));
        g.drawString("GAME OVER", 10, 40);
        this.add(restart);
        this.add(exit);
    }

    /**
     * Print a Pause message at screen center with game options.
     * @param g
     */
    private void printPause(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Helvetica", Font.BOLD, 25));
        g.drawString("PAUSE", 50, 40);
        this.add(resume);
        this.add(restart);
        this.add(exit);
    }

    /**
     * Print all data of gane like Time, Score, Nuber of figures and cursor status
     * @param g
     */
    private void printDataGame(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("Time: ", 10, 500);
        g.drawString("Score: ", 10, 520);
        g.drawString("Figures: ", 10, 540);
        g.drawString("Cursor 1: ", 10, 560);
        g.drawString("Cursor 2: ", 10, 580);
        g.drawString(convertClockToString(), 70, 500);
        g.drawString("" + manager.getScore() + "", 70, 520);
        g.drawString("" + manager.getCounterFoundFigures() + "", 70, 540);
        printCursor(cursor1, g, 70, 550);
        printCursor(cursor2, g, 70, 570);
    }

    /**
     * Print cursor in board of game.
     * @param currentCursor Cursor to paint.
     * @param g Graphics object.
     * @param coordinateX Position X in coordinate plot.In pixels.
     * @param coordinateY Position Y in coordinate plot.In pixels.
     */
    private void printCursor(Cursor currentCursor, Graphics g, int coordinateX, int coordinateY) {
        if (currentCursor.isFree()) {
            g.setColor(Color.WHITE);
            g.drawRect(coordinateX, coordinateY, 12, 12);
        } else {
            switch (currentCursor.getListBlockSwitch().get(0).getColor()) {
                case 0:
                    g.setColor(Color.RED);
                    break;
                case 1:
                    g.setColor(Color.MAGENTA);
                    break;
                case 2:
                    g.setColor(Color.GREEN);
                    break;
                case 3:
                    g.setColor(Color.YELLOW);
                    break;
                case 4:
                    g.setColor(Color.BLUE);
                    break;
            }
            g.fillRect(coordinateX, coordinateY, 12, 12);
        }
    }

    /**
     * Parse the universalclock to a String
     * @return UniversalTime like String
     */
    private String convertClockToString() {
        Long clock = manager.getUniversalClock();
        String clockString = "";
        long seconds = 0;
        long minuts = 0;
        long hours = 0;
        if (clock > 10) {
            seconds = clock / 10;
            minuts = seconds / 60;
            hours = minuts / 60;
            seconds -= (minuts * 60);
            minuts -= (hours * 60);
        }
        if (hours < 10) {
            clockString += "0" + hours + " : ";
        } else {
            clockString += "" + hours + " : ";
        }
        if (minuts < 10) {
            clockString += "0" + minuts + " : ";
        } else {
            clockString += "" + minuts + " : ";
        }
        if (seconds < 10) {
            clockString += "0" + seconds + "";
        } else {
            clockString += "" + seconds + "";
        }
        return clockString;
    }

    /**
     * Print the board of game and the cursor.
     * @param g
     */
    private void printBoardGame(Graphics g) {
        Block currentBlock;
        Toolkit t;
        Image image = null;
        String color;
        for (int row = 0; row < manager.getBoard().getRows(); row++) {
            for (int column = 0; column < manager.getBoard().getColumns(); column++) {
                currentBlock = manager.getBoard().getBlock(row, column);
                t = Toolkit.getDefaultToolkit();
                if (currentBlock != null) {
                    switch (currentBlock.getColor()) {
                        case 0:
                            color = "/images/red.gif";
                            break;
                        case 1:
                            color = "/images/pink.gif";
                            break;
                        case 2:
                            color = "/images/green.gif";
                            break;
                        case 3:
                            color = "/images/yellow.gif";
                            break;
                        case 4:
                            color = "/images/blue.gif";
                            break;
                        default:
                            color = "";
                            break;

                    }
                    image = t.getImage(getClass().getResource(color));
                    g.drawImage(image, column * 32, row * 32, 32, 32, this);
                    image = t.getImage(getClass().getResource("/images/cursor.gif"));
                    g.drawImage(image, cursor1.getCoordinate().getColumn() * 32, cursor1.getCoordinate().getRow() * 32, 32, 32, this);
                    g.drawImage(image, cursor2.getCoordinate().getColumn() * 32, cursor2.getCoordinate().getRow() * 32, 32, 32, this);
                }
            }
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT) {
            cursor1.moveCursor(cursor1.getCoordinate().getRow(), cursor1.getCoordinate().getColumn() + 1, cursor2.getCoordinate().getRow(), cursor2.getCoordinate().getColumn());
        }
        if (key == KeyEvent.VK_LEFT) {
            cursor1.moveCursor(cursor1.getCoordinate().getRow(), cursor1.getCoordinate().getColumn() - 1, cursor2.getCoordinate().getRow(), cursor2.getCoordinate().getColumn());
        }
        if (key == KeyEvent.VK_UP) {
            cursor1.moveCursor(cursor1.getCoordinate().getRow() - 1, cursor1.getCoordinate().getColumn(), cursor2.getCoordinate().getRow(), cursor2.getCoordinate().getColumn());
        }
        if (key == KeyEvent.VK_DOWN) {
            cursor1.moveCursor(cursor1.getCoordinate().getRow() + 1, cursor1.getCoordinate().getColumn(), cursor2.getCoordinate().getRow(), cursor2.getCoordinate().getColumn());
        }
        if (key == KeyEvent.VK_S) {
            cursor2.moveCursor(cursor2.getCoordinate().getRow(), cursor2.getCoordinate().getColumn() + 1, cursor1.getCoordinate().getRow(), cursor1.getCoordinate().getColumn());
        }
        if (key == KeyEvent.VK_A) {
            cursor2.moveCursor(cursor2.getCoordinate().getRow(), cursor2.getCoordinate().getColumn() - 1, cursor1.getCoordinate().getRow(), cursor1.getCoordinate().getColumn());
        }
        if (key == KeyEvent.VK_W) {
            cursor2.moveCursor(cursor2.getCoordinate().getRow() - 1, cursor2.getCoordinate().getColumn(), cursor1.getCoordinate().getRow(), cursor1.getCoordinate().getColumn());
        }
        if (key == KeyEvent.VK_Z) {
            cursor2.moveCursor(cursor2.getCoordinate().getRow() + 1, cursor2.getCoordinate().getColumn(), cursor1.getCoordinate().getRow(), cursor1.getCoordinate().getColumn());
        }
        if (key == KeyEvent.VK_P) {
            manager.setPause(true);
        }
        if (key == KeyEvent.VK_M) {
            cursor1.switchBlocks(cursor2);
        }
        if (key == KeyEvent.VK_N) {
            cursor2.switchBlocks(cursor1);
        }
        if (key == KeyEvent.VK_R) {
            manager.rotateBoard();
        }

    }

    public void keyReleased(KeyEvent e) {
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Resume")) {
            manager.setPause(false);
            removeButtonsOfScreen();
        }
        if (e.getActionCommand().equals("Restart")) {
            startControlThreads(manager.getLevel());
            removeButtonsOfScreen();
        }
        if (e.getActionCommand().equals("Exit")) {
            System.exit(0);
        }
    }

    /**
     * Remove resume,restart and exit buttons of screen.
     */
    private void removeButtonsOfScreen() {
        remove(resume);
        remove(restart);
        remove(exit);
    }
}
