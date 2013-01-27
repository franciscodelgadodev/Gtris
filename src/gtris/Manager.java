/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gtris;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @date Jul 3, 2012
 * @author Fco. Delgado
 */
public class Manager extends Thread {

    private Board board;
    private long universalClock;//this attribute decreases every two minuts.//It's decimas
    private int timeIntervalToNewBlocksSeries;
    private int frequencyToDownBlocks;
    private int level;
    private long score;
    private int dimentionScore;
    private int numberNewBlocks;
    private int counterFoundFigures;
    private boolean gameover = false;
    private boolean pause = false;

    /**private List<Block> movingBlocks = new LinkedList<Block>();
    /**private List<Block> staticBlocks = new LinkedList<Block>();

    /**
     * Constructor of class Manager. Set the level value for game.
     * @param level Level that start game.
     */
    public Manager(int level) {
        this.level = level;
        defineEnvironmentVariables();
    }

    /**
     * Run method extended for Thread class. This function will have the control of all the changes in the board.
     * Also will control when the game is finish.
     */
    @Override
    public void run() {
        List<Block> population = new LinkedList<Block>();
        UniversalClock threadUniversalClock = new UniversalClock(this);
        threadUniversalClock.start();
        population = generatePopulation(level);
        board.setBlocks(population);
        runThreadsSearchers(population);
        while (!gameover) {
            try {
                pauseThread();
                Thread.sleep(timeIntervalToNewBlocksSeries * 100);
                runThreadNewBlockSeries(null);
            } catch (InterruptedException ex) {
                Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Define the environment variables for game depends of level.
     * This function set values to attributes of Manager class like the dimension score and time interval between each generation of new blocks.
     * @param level Level that start game.
     */
    private void defineEnvironmentVariables() {
        board = new Board(15, 15);
        score = 0;
        numberNewBlocks = 4;
        dimentionScore = level * 2;
        counterFoundFigures = 0;
        switch (level) {
            case 1:
                timeIntervalToNewBlocksSeries = 20;
                frequencyToDownBlocks = 100;
                break;
            case 2:
                timeIntervalToNewBlocksSeries = 12;
                frequencyToDownBlocks = 100;
                break;
            case 3:
                timeIntervalToNewBlocksSeries = 7;
                frequencyToDownBlocks = 100;
                break;
        }
    }

    /**
     * Pause thread while the user press resume.
     */
    public void pauseThread() {
        while (pause) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ManipulateNewBlockSeries.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Rotate the board 90º to right. Update the plot and run new Threads for down the new moving blocks.
     */
    public void rotateBoard() {
        List<Block> newMovingBlocks = new LinkedList<Block>();
        List<Block> newStaticBlocks = new LinkedList<Block>();
        Block currentBlock;
        boolean nullBlock = false;
        for (int row = board.getRows() - 1; row > -1; row--) {
            nullBlock = false;
            for (int column = board.getColumns() - 1; column > -1; column--) {
                currentBlock = board.getBlock(row, column);
                if (currentBlock == null) {
                    nullBlock = true;
                } else {
                    currentBlock.getCoordinate().setRow(column);
                    currentBlock.getCoordinate().setColumn((board.getRows() - 1) - row);
                    if (nullBlock) {
                        currentBlock.setMovement(true);
                        newMovingBlocks.add(currentBlock);
                    } else {
                        newStaticBlocks.add(currentBlock);
                    }
                }
            }
        }
        board.removeAll();
        board.setBlocks(newStaticBlocks);
        runThreadNewBlockSeries(newMovingBlocks);
    }

    /**
     * Generate a new blocks series.
     * This function is called when is necesary create new dynamic blocks.
     * @param numbBlocks Number of new blocks to create.
     * @return Blocks series with random colors.
     */
    public synchronized List<Block> generateNewBlocksSeries(int numbBlocks) {
        List<Block> listBlocks = new LinkedList<Block>();


        int columnPosition = new Random().nextInt(board.getColumns() - (numbBlocks - 1));


        for (int auxNumBlocks = 0; auxNumBlocks
                < numbBlocks; auxNumBlocks++) {
            if (!board.isColumnOverflow(columnPosition) && board.isNull(0, columnPosition)) {
                listBlocks.add(Block.factory(5, true, new Coordinate(0, columnPosition)));
                columnPosition++;

            }


        }
        return listBlocks;


    }

    /**
     * Generate a list of Blocks to populate the board. The number of blocks will be calculate of follow way.
     * The level that come for parameter will be multiply by three and the result number will be the maximun row to set values.
     * In the list may has Block instances or null values.
     * @param level The level of start game.
     * @return A list of Block objects whit null values. That list is the population for start game.
     */
    private List<Block> generatePopulation(int level) {
        List<Block> listPopulation = new LinkedList<Block>();
        int maxNumberRowPopulate = board.getRows() - (level * 3);
        for (int row = board.getRows() - 1; row>= maxNumberRowPopulate; row--) {
            for (int column = board.getColumns() - 1; column>= 0; column--) {
                if (board.isValidStaticPosition(row, column, listPopulation)) {
                    listPopulation.add(Block.factory(6, false, new Coordinate(row, column)));
                } else {
                    listPopulation.add(null);
                }
            }
        }
        return listPopulation;
    }

    /**
     * Start a thread. Generate a new block series.
     */
    public synchronized void runThreadNewBlockSeries(List<Block> movingBlocks) {
        ManipulateNewBlockSeries generatorNewBlocks = new ManipulateNewBlockSeries(this, movingBlocks);
        generatorNewBlocks.start();
    }

    /**
     * Start the search of figures. This method iterate over a list of static blocks and generate a thread each block. Each thread will search a Tetris figure.
     * This is a synchronized method because many threads are using.
     * @param newStaticBlocks List of static blocks for search Tetris figures.
     */
    public synchronized void runThreadsSearchers(List<Block> newStaticBlocks) {
        int row, column;
        for (Block currentBlock : newStaticBlocks) {
            if (currentBlock != null) {
                row = currentBlock.getCoordinate().getRow();
                column = currentBlock.getCoordinate().getColumn();
                //This validation is because maybe some block declarated static was eliminate for a neihgbor.
                //If a neighbor formed a figure with the current Block now the board in that position is null or a different block.
                if (!board.isNull(row, column) && currentBlock == board.getBlock(row, column)) {
                    FiguresSearcher threadSearcher = new FiguresSearcher(this, currentBlock);
                    threadSearcher.start();
                }
            }
        }
    }

    /**
     * Is the complete process for search and remove found Tetris figures.
     * If is necesary update the board.
     * Too update the total score and the counter found figures.
     * @param block Position where begin the search.
     */
    public synchronized void figuresSearcherAndBoardUpdaterAfterFoundFigures(Block block) {
        List<Block> figure = searchFigures(block, null);


        if (figure.size() == 4) {
            counterFoundFigures++;
            score += (((universalClock / 100) + 1) * dimentionScore) + ((counterFoundFigures / 10) * 5);


            try {
                Thread.sleep(200);
                board.removeBlocks(figure);
                downBlocksToFindFigure(
                        figure);



            } catch (InterruptedException ex) {
                Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
            }


        }
    }

    /**
     * Search a Tetris figure in the board. It's a recursive method. Begin with a static block and review your four main neighbors.
     * The secuence of search is northNeighbor-southNeigbor-leftNeighbor-rightNeigbor.
     * Search that some neighbor is the same color like the block that begin operation.
     * The T form isn't valid for game requeriment.
     * When this method found 4 blocks with the same color review if isn't a T form.If figure is T form continue the process. Else return the list of blocks.
     * @param block Block that begin the process.
     * @param figure Block list that form the figure. If the process is beginin send null.
     * @return A block list with the found figure. If list size is equal to 4 the figure is complete. Else figure not found.
     */
    public synchronized List<Block> searchFigures(Block currentBlock, List<Block> figure) {
        List<Block> listFigure = (figure == null) ? new LinkedList<Block>() : figure;


        if (listFigure.size() < 4) {
            if (listFigure.isEmpty() || (!currentBlock.getMovement() && areSameColor(currentBlock, listFigure.get(0)) && !isTForm(currentBlock, figure))) {
                listFigure.add(currentBlock);


                int currentRow = currentBlock.getCoordinate().getRow();


                int currentColumn = currentBlock.getCoordinate().getColumn();


                if (!board.isRowOverflow(currentRow - 1) && !board.isNull(currentRow - 1, currentColumn) && !listFigure.contains(board.getBlock(currentRow - 1, currentColumn))) {
                    listFigure = searchFigures(board.getBlock(currentRow - 1, currentColumn), listFigure);


                }
                if (!board.isRowOverflow(currentRow + 1) && !board.isNull(currentRow + 1, currentColumn) && !listFigure.contains(board.getBlock(currentRow + 1, currentColumn))) {
                    listFigure = searchFigures(board.getBlock(currentRow + 1, currentColumn), listFigure);


                }
                if (!board.isColumnOverflow(currentColumn - 1) && !board.isNull(currentRow, currentColumn - 1) && !listFigure.contains(board.getBlock(currentRow, currentColumn - 1))) {
                    listFigure = searchFigures(board.getBlock(currentRow, currentColumn - 1), listFigure);


                }
                if (!board.isColumnOverflow(currentColumn + 1) && !board.isNull(currentRow, currentColumn + 1) && !listFigure.contains(board.getBlock(currentRow, currentColumn + 1))) {
                    listFigure = searchFigures(board.getBlock(currentRow, currentColumn + 1), listFigure);


                }
            }
        }
        return listFigure;


    }

    /**
     * When a figure is found this method is called for go down the blocks that are above.
     * Iterate over a block list(a Tetris figure) and each block search if exist above blocks.
     * If there are above blocks make a list and down to a new position in the board.
     * The new static blocks have new position so the runThreadSearcher is called for start the search for new figures.
     * @param figure block list with a Tetris figure.
     */
    public synchronized void downBlocksToFindFigure(List<Block> figure) {
        List<Block> newStaticBlocks = new LinkedList<Block>();


        for (Block currentBlock : figure) {
            //This validation is because maybe another block in the same column that belong of figure down the above blocks.
            if (board.isNull(currentBlock.getCoordinate().getRow(), currentBlock.getCoordinate().getColumn())) {
                newStaticBlocks = updateListDownBlocksToFindFigure(figure, currentBlock, null);
                board.removeBlocks(newStaticBlocks);
                List<Block> auxNewStaticBlocks = updatePositionDownBlocksToFindFigure(newStaticBlocks);
                board.setBlocks(auxNewStaticBlocks);
                runThreadsSearchers(
                        auxNewStaticBlocks);


            }
        }
    }

    /**
     * Update the down block list after found some Tetris figure. Is a recursive function. Create a block list with the elements that can follow down.
     * Search above of given element. The element belong to some tetris figure found.
     * If there are some blocks avobe of that element create a list with those elements.
     * @param figure Block list with a Tetris figure.
     * @param currentBlock Block belong to tetris figure. This function will search above of this block.
     * @param listDownBLock Block list that can down some positions. If the process is beginin send null.
     * @return A block list with the block that can down.
     */
    public synchronized List<Block> updateListDownBlocksToFindFigure(List<Block> figure, Block currentBlock, List<Block> listDownBlocks) {
        List<Block> newStaticBlocks = (listDownBlocks == null) ? new LinkedList<Block>() : listDownBlocks;


        int row = currentBlock.getCoordinate().getRow();


        int column = currentBlock.getCoordinate().getColumn();


        int aboveRow = row - 1;


        while (!board.isRowOverflow(aboveRow) && board.isNull(aboveRow, column) && areCoordinatesInList(figure, aboveRow, column)) {
            aboveRow--;


        }
        if (!board.isRowOverflow(aboveRow) && !board.isNull(aboveRow, column)) {
            newStaticBlocks.add(board.getBlock(aboveRow, column));
            updateListDownBlocksToFindFigure(
                    figure, newStaticBlocks.get(newStaticBlocks.size() - 1), newStaticBlocks);


        }
        return newStaticBlocks;


    }

    /**
     * Update position of block list that can down after found a Tetris figures.
     * This funcion is called after to found a figure and update the block list that can follow down.
     * This process only down a block list while find null values down each block.
     * This process stop when each block in the list found a static block down.
     * @param listDownBlocks Block list to update a new static position.
     * @return Block list with the new static blocks and their new positions.
     */
    public synchronized List<Block> updatePositionDownBlocksToFindFigure(List<Block> listDownBlocks) {
        List<Block> listUpdateBlocksPosition = new LinkedList<Block>();


        int row, column;


        for (Block currentBlock : listDownBlocks) {
            row = currentBlock.getCoordinate().getRow();
            column = currentBlock.getCoordinate().getColumn();


            while (!board.isRowOverflow(row + 1) && board.isNull(row + 1, column) && !areCoordinatesInList(listUpdateBlocksPosition, row + 1, column)) {
                row++;


            }
            currentBlock.setMovement(false);
            currentBlock.getCoordinate().setRow(row);
            listUpdateBlocksPosition.add(currentBlock);


        }
        return listUpdateBlocksPosition;


    }

    /**
     * Update the list of static and dynamic blocks.
     * This function review if each block that moves can follow movings.
     * Create two list.
     * The first with the blocks that can follow moving.
     * The second with the blocks that can't follow moving. namely with the new static blocks.
     * @param movingBlocks List with the moving blocks.
     * @return List with the new moving and static blocks.
     */
    public synchronized List<List<Block>> updateListMovingAndStaticBlocks(List<Block> movingBlocks) {
        List<List<Block>> listMovingAndStaticBlocks = new LinkedList<List<Block>>();
        List<Block> newMovingBlocks = new LinkedList<Block>();
        List<Block> newStaticBlocks = new LinkedList<Block>();


        int row, column;


        for (Block currentBlock : movingBlocks) {
            row = currentBlock.getCoordinate().getRow();
            column = currentBlock.getCoordinate().getColumn();


            if (!board.isRowOverflow(row + 1) && (board.isNull(row + 1, column) || isBlockInList(board.getBlock(row + 1, column), newMovingBlocks))) {
                currentBlock.setMovement(true);
                newMovingBlocks.add(currentBlock);


            } else {
                currentBlock.setMovement(false);
                newStaticBlocks.add(currentBlock);


            }
        }
        listMovingAndStaticBlocks.add(newMovingBlocks);
        listMovingAndStaticBlocks.add(newStaticBlocks);


        return listMovingAndStaticBlocks;


    }

    /**
     * Update the position of moving blocks.
     * @param movingBlocks Block list with the moving blocks.
     * @return List with the new positions of moving blocks.
     */
    public synchronized List<Block> updatePositionMovingBlocks(List<Block> movingBlocks) {
        for (Block currentBlock : movingBlocks) {
            currentBlock.getCoordinate().setRow(currentBlock.getCoordinate().getRow() + 1);
            currentBlock.getCoordinate().setColumn(currentBlock.getCoordinate().getColumn());


        }
        return movingBlocks;


    }

    /**
     * Review if two block objects have the same color.
     * @param block1
     * @param block2
     * @return True if the two blocks have the same color. False if have different color.
     */
    public boolean areSameColor(Block block1, Block block2) {
        return (block1.getColor() == block2.getColor()) ? true : false;


    }

    /**
     * Review if a blocks group are Tetris figure T.
     * @param blockToAdd Block to analize together another blocks in the list.
     * @param figure Blocks list that may form some Tetris figure.
     * @return True if the block to add together with the figure list form a T figure.
     */
    public boolean isTForm(Block blockToAdd, List<Block> figure) {
        if (figure.size() == 3) {
            int sumatory = blockToAdd.getCoordinate().getRow() + blockToAdd.getCoordinate().getColumn();


            for (Block currentBlock : figure) {
                sumatory += (currentBlock.getCoordinate().getRow() + currentBlock.getCoordinate().getColumn());


            }
            if (sumatory % 2 == 1) {
                return true;


            }
        }
        return false;


    }

    /**
     * Review if the number of new dynamic blocks is the same of attribute numberNewBlocks.
     * If the number is the same continue game.
     * @param List with the new Dynamic blocks.
     * @return True if the number of new blocks is correct. If return false the new dynamic blocks were generate successfully.
     */
    public boolean isGameOver(List<Block> newBlocksSerie) {
        return (newBlocksSerie.size() == numberNewBlocks) ? false : true;


    }

    /**
     * Search if the given Block is in the given List.
     * @param block Block to search.
     * @param list List of Blocks where should search the block given.
     * @return Return true if the block is in the list.
     */
    public boolean isBlockInList(Block block, List<Block> list) {
        return (list.contains(block)) ? true : false;


    }

    /**
     * Review if the given coordinates don't belong to any Block in the list.
     * @param list Blocks list to search the given coordinates.
     * @param row Row number.
     * @param column Column number.
     * @return True if the given coordinates belong to some blockk in the list.
     */
    public boolean areCoordinatesInList(List<Block> list, int row, int column) {
        boolean value = false;


        for (Block currentBlock : list) {
            if (currentBlock.getCoordinate().getRow() == row && currentBlock.getCoordinate().getColumn() == column) {
                value = true;


                break;


            }
        }
        return value;


    }

    /**
     * Get gameover value.
     * @return Gameover value.
     */
    public boolean getGameOver() {
        return gameover;


    }

    /**
     * Set gameOver value.
     * @param gameOver
     */
    public void setGameOver(boolean gameOver) {
        this.gameover = gameOver;


    }

    /**
     * Get the universal time.
     * @return universal time.
     */
    public long getUniversalClock() {
        return universalClock;


    }

    /**
     * Set the universal time.
     * @param universalClock
     */
    public void setUniversalClock(long universalClock) {
        this.universalClock = universalClock;


    }

    /**
     * Get the number of found figures.
     * @return Number of found figures.
     */
    public int getCounterFoundFigures() {
        return counterFoundFigures;


    }

    /**
     * Set the number of found figures.
     * @param counterFoundFigures
     */
    public void setCounterFoundFigures(int counterFoundFigures) {
        this.counterFoundFigures = counterFoundFigures;


    }

    /**
     * Get the value of time interval.
     * @return Value of time interval.
     */
    public int getTimeInterval() {
        return timeIntervalToNewBlocksSeries;


    }

    /**
     * Set the value of timeIntervalToNewBlocksSeries.
     * @param timeIntervalToNewBlocksSeries.
     */
    public void setTimeInterval(int timeIntervalToNewBlocksSeries) {
        this.timeIntervalToNewBlocksSeries = timeIntervalToNewBlocksSeries;


    }

    /**
     * Get the level of game.
     * @return Level of game.
     */
    public int getLevel() {
        return level;


    }

    /**
     * Set the level of game.
     * @param level
     */
    public void setLevel(int level) {
        this.level = level;


    }

    /**
     * Get the actually score.
     * @return Actually score.
     */
    public long getScore() {
        return score;


    }

    /**
     * Set the actually score.
     * @param score
     */
    public void setScore(long score) {
        this.score = score;


    }

    /**
     * Get the sequency for down new blocks.
     * @return Actually score.
     */
    public int getFrequency() {
        return frequencyToDownBlocks;


    }

    /**
     * Set the sequency for down new blocks.
     * @param frequency
     */
    public void setFrequency(int frequencyToDownBlocks) {
        this.frequencyToDownBlocks = frequencyToDownBlocks;


    }

    /**
     * Get the dimention score.
     * @return Dimention score.
     */
    public int getDimentionScore() {
        return dimentionScore;


    }

    /**
     * Set the dimention score.
     * @param dimentionScore
     */
    public void setDimentionScore(int dimentionScore) {
        this.dimentionScore = dimentionScore;


    }

    /**
     * Get the number of new blocks to generate.
     * @return Number of new blocks to generate.
     */
    public int getNumberNewBlocks() {
        return numberNewBlocks;


    }

    /**
     * Set the number of new blocks to generate.
     * @param numberNewBlocks
     */
    public void setNumberNewBlocks(int numberNewBlocks) {
        this.numberNewBlocks = numberNewBlocks;


    }

    /**
     * Get the board of game.
     * @return Board of game.
     */
    public Board getBoard() {
        return board;


    }

    /**
     * Set the board of game.
     * @param board
     */
    public void setBoard(Board board) {
        this.board = board;


    }

    /**
     * Get pause.
     * @return Pause value.
     */
    public boolean getPause() {
        return pause;


    }

    /**
     * Set pause to game.
     * @param pause
     */
    public void setPause(boolean pause) {
        this.pause = pause;

    }
    /**
     * Show a menu in console with the received values and return a integer with the option selected.
     * @param menu Array of String with the menu options.
     * @return The menu option selected for the user.
     */
    /*public int menu(String[] menu) {
    int option;
    Scanner scanner = new Scanner(System.in);
    for (int i = 0; i < menu.length; i++) {
    System.out.println(menu[i]);
    }
    option = scanner.nextInt();
    return option;
    }*/
    /**
     * @param args the command line arguments
     */
    /*public static void main(String[] args) {
    Manager manager = new Manager();
    String submenu[] = {"Select a level", "1) Amateur", "2) Medium", "3) Expert", "4) Exit"};
    int option = manager.menu(submenu);
    switch (option) {
    case 1:
    case 2:
    case 3:
    manager.start(option);
    break;
    case 4:
    break;
    }
    }*/
}
