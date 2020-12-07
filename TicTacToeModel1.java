import java.util.Random;

import components.map.Map;

/**
 * Implementation class for the tic-tac-toe model.
 *
 * @author Katie Heym
 *
 */
public final class TicTacToeModel1 implements TicTacToeModel {

    /**
     * Model variable game board.
     */
    private TicTacToeBoard<Integer> board;

    /**
     * Model variable markers.
     */
    private int human, comp;

    /**
     * Model difficulty.
     */
    private double diff;

    /**
     * Dimension of the board.
     */
    private static final int DIM = 3;

    /**
     * Constructor; initializes important variables.
     *
     * @param humanMarker
     *            the int used to mark human spots; computer marker is
     *            automatically determined
     * @param diff
     *            the difficulty, from 0 to 1.
     */
    public TicTacToeModel1(int humanMarker, double diff) {
        this.board = new TicTacToeBoard1();
        this.human = humanMarker;
        this.comp = -1 * humanMarker;
        this.diff = diff;
    }

    @Override
    public void playHuman(Location loc) {
        this.board.playAt(loc, this.human);
    }

    @Override
    public void playComputer() {
        Random rand = new Random();
        if (rand.nextDouble() < this.diff) {
            this.playSmart();
        } else {
            this.playRandom();
        }
    }

    /**
     * Plays a random move for the computer.
     */
    private void playRandom() {
        Random rand = new Random();
        boolean done = false;
        while (!done) {
            Location loc = new TicTacToeLoc(rand.nextInt(DIM),
                    rand.nextInt(DIM));
            if (this.board.markerAt(loc) == 0) {
                done = true;
                this.board.playAt(loc, this.comp);
            }
        }
    }

    /**
     * Plays a smart move for the computer. Uses a recursive search tree.
     */
    private void playSmart() {
        Location loc;
        if (!this.board.hasValue(this.comp)
                && !this.board.hasValue(this.human)) {
            loc = new TicTacToeLoc(1, 1);
        } else {
            double max = -1 - 1;
            loc = new TicTacToeLoc(-1, -1);
            for (Map.Pair<Location, Integer> locPair : this.board) {
                if (locPair.value() == 0) {
                    TicTacToeModel test = new TicTacToeModel1(this.human, 1);
                    test.getBoard().copyFrom(this.board);
                    test.getBoard().playAt(locPair.key(), this.comp);
                    if (test.getBoard().won(this.comp)) {
                        max = 2;
                        loc = locPair.key();
                    } else if (max < 2) {
                        test.getBoard().copyFrom(this.board);
                        test.getBoard().playAt(locPair.key(), this.human);
                        if (test.getBoard().won(this.human)) {
                            max = 1;
                            loc = locPair.key();
                        } else if (max < 1) {
                            test.getBoard().copyFrom(this.board);
                            test.getBoard().playAt(locPair.key(), this.comp);
                            double result = test.treeBranch(true);
                            if (result > max) {
                                max = result;
                                loc = locPair.key();
                            }
                        }
                    }
                }
            }
        }
        this.board.playAt(loc, this.comp);
    }

    @Override
    public double treeBranch(boolean isHumanTurn) {
        double result;
        if (this.board.won(this.comp)) {
            result = 1;
        } else if (this.board.won(this.human)) {
            result = -1;
        } else if (this.board.isFull()) {
            result = 0;
        } else {
            int trials = 0;
            double results = 0;
            int marker = this.comp;
            if (isHumanTurn) {
                marker = this.human;
            }
            for (Map.Pair<Location, Integer> pair : this.board) {
                if (pair.value() == 0) {
                    TicTacToeModel test = new TicTacToeModel1(this.human, 1);
                    test.getBoard().copyFrom(this.board);
                    test.getBoard().playAt(pair.key(), marker);
                    results += test.treeBranch(!isHumanTurn);
                    trials++;
                }
            }
            result = results / trials;
        }
        return result;
    }

    @Override
    public TicTacToeBoard<Integer> getBoard() {
        return this.board;
    }

}
