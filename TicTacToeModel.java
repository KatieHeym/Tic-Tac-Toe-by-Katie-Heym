/**
 * Model for tic-tac-toe.
 *
 * @author Katie Heym
 *
 */
public interface TicTacToeModel {

    /**
     * Plays the human marker at the location chosen.
     *
     * @param loc
     */
    void playHuman(Location loc);

    /**
     * Plays a move for the computer.
     */
    void playComputer();

    /**
     * Returns the current game board.
     *
     * @return the current game board
     */
    TicTacToeBoard<Integer> getBoard();

    /**
     * Returns the estimated goodness of the branch.
     *
     * @param isHumanTurn
     * @return the estimated goodness of the branch
     */
    double treeBranch(boolean isHumanTurn);

}
