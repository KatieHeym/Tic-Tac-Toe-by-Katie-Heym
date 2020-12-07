/**
 * Tic-tac-toe controller interface.
 *
 * @author Katie Heym
 *
 */
public interface TicTacToeController {

    /**
     * Processes a human play and then a computer turn if necessary.
     */
    void processPlayEvent(Location loc);

    /**
     * Processes a decision to play again.
     */
    void processPlayAgainEvent();

}
