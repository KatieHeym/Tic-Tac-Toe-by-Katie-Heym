import components.map.Map;
import components.map.Map1L;

/**
 * An implementation of BoardGame for TicTacToe.
 *
 * @author Katie Heym
 *
 * @initially <pre>
 * ():
 *   ensures:
 *     the board created is an empty 3x3 tic-tac-toe board
 * </pre>
 *
 */
public final class TicTacToeBoard1 extends GenericBoard<Integer>
        implements TicTacToeBoard<Integer> {

    /**
     * The length and width of a tic-tac-toe board.
     */
    private static final int DIM = 3;

    /**
     * Generates a 3x3 tic-tac-toe board.
     */
    public TicTacToeBoard1() {
        super();
        Map<Location, Integer> setup = new Map1L<Location, Integer>();
        int row = 0;
        while (row < DIM) {
            int col = 0;
            while (col < DIM) {
                setup.add(new TicTacToeLoc(row, col), 0);
                col++;
            }
            row++;
        }
        this.combineWith(new GenericBoard<Integer>(setup));
    }

    /**
     * Checks to see if the board is won in the given direction from the
     * starting location.
     *
     * @param board
     *            the game board
     * @param locMark
     *            the starting location
     * @param direction
     *            the direction to look
     * @return true if there is a three-in-a-row, false otherwise
     */
    private boolean checkWonDirection(Map.Pair<Location, Integer> locMark,
            Location.Direction direction) {
        return (this).isInBounds(
                locMark.key().navigate(direction).navigate(direction))
                && locMark.value().intValue() == this
                        .markerAt(locMark.key().navigate(direction))
                && locMark.value().intValue() == this.markerAt(
                        locMark.key().navigate(direction).navigate(direction));
    }

    @Override
    public boolean won(Integer marker) {
        boolean won = false;
        for (Map.Pair<Location, Integer> locMark : this) {
            if (locMark.value().intValue() == marker) {
                for (Location.Direction direction : Location.Direction
                        .values()) {
                    won = won || this.checkWonDirection(locMark, direction);
                }
            }
        }
        return won;
    }

    @Override
    public boolean isFull() {
        return !this.hasValue(0);
    }

    @Override
    public void copyFrom(TicTacToeBoard<Integer> source) {
        for (Map.Pair<Location, Integer> locInt : this) {
            this.playAt(locInt.key(), source.markerAt(locInt.key()));
        }
    }
}
