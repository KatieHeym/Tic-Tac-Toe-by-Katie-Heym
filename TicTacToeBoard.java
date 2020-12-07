/**
 * Board game interface.
 *
 * @author Katie Heym
 *
 * @param <T>
 *            the type used to mark the board
 */
public interface TicTacToeBoard<T> extends BoardGameKernel<T> {

    /**
     * Reports whether the current game board is full, i.e. no more moves can be
     * made.
     *
     * @return true if the board is full, false otherwise
     */
    boolean isFull();

    /**
     * Reports whether the game has been won for the given marker.
     *
     * @param marker
     *            the player in question
     * @return true if the player has won, false otherwise
     */
    boolean won(T marker);

    /**
     * This gets a copy of the source board.
     *
     * @replaces this
     * @param source
     *            the source board
     */
    void copyFrom(TicTacToeBoard<T> source);
}
