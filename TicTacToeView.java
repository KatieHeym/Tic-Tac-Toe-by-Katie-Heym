import java.awt.event.ActionListener;

/**
 * View interface for Tic-Tac-Toe.
 *
 * @author Katie Heym
 *
 */
public interface TicTacToeView extends ActionListener {

    /**
     * Register argument as observer/listener of this.
     *
     * @param controller
     *            controller to register
     */
    void registerObserver(TicTacToeController controller);

    /**
     * Updates the view given the board to represent.
     *
     * @param board
     *            the board
     */
    void updateView(TicTacToeBoard<Integer> board);

    /**
     * Updates the message box.
     *
     * @param isHumanTurn
     *            whether it is the human turn or not
     * @param won
     *            whether the game board is won
     * @param isTie
     *            whether the game board is tied
     */
    void updateMessageBox(boolean isHumanTurn, boolean won, boolean isTie);
}
