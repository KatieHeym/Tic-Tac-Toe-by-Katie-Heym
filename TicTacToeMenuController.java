/**
 * Tic-tac-toe option menu controller interface.
 *
 * @author Katie Heym
 *
 */
public interface TicTacToeMenuController {

    /**
     * Process a change in marker.
     *
     * @param humanIsX
     */
    void processMarkerEvent(boolean humanIsX);

    /**
     * Process a change in who is first.
     *
     * @param humanFirst
     */
    void processFirstEvent(boolean humanFirst);

    /**
     * Process a change in difficulty.
     *
     * @param diff
     */
    void processDiffEvent(double diff);

    /**
     * Process confirm event. Go on to the game board.
     */
    void processConfirmEvent();

}
