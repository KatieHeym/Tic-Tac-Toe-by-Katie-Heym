import components.map.Map;

/**
 * Board game interface.
 *
 * @author Katie Heym
 *
 *
 * @param <T>
 *            the type used to mark the board
 */
public interface BoardGameKernel<T> extends Map<Location, T> {

    /**
     * Reports whether the given location exists on the board.
     *
     * @param loc
     *            the location
     * @return true if the location is on the board, false otherwise
     */
    boolean isInBounds(Location loc);

    /**
     * Updates the board with a marker at loc.
     *
     * @param loc
     *            the place to play
     * @param marker
     *            indicates the marker to play
     * @updates this
     * @requires this.isInBounds(loc)
     */
    void playAt(Location loc, T marker);

    /**
     * Returns the marker at a certain location.
     *
     * @param loc
     *            the location
     * @return the marker
     */
    T markerAt(Location loc);

    @Override
    T value(Location key);
}
