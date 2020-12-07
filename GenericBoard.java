import components.map.Map;
import components.map.Map1L;

/**
 * An implementation of {@link BoardGameKernel}.
 *
 * @author Katie Heym
 *
 * @param <T>
 *            the type of the marker object
 *
 */
public class GenericBoard<T> extends Map1L<Location, T>
        implements BoardGameKernel<T> {

    /**
     * No-argument constructor.
     */
    public GenericBoard() {
        super();
    }

    /**
     * Constructor from an existing {@code Map}.
     *
     * @param setup
     *            the initial setup
     */
    public GenericBoard(Map<Location, T> setup) {
        super();
        this.combineWith(setup);
    }

    @Override
    public void playAt(Location loc, T marker) {
        this.replaceValue(loc, marker);
    }

    @Override
    public boolean isInBounds(Location loc) {
        return this.hasKey(loc);
    }

    @Override
    public T markerAt(Location loc) {
        return this.value(loc);
    }

//    /**
//     * Finds a reference in this which matches the input in parameter values.
//     *
//     * @param loc
//     * @return a match, or loc if no match
//     */
//    private Location findEqual(Location loc) {
//        Location find = loc;
//        for (Map.Pair<Location, T> pair : this.board) {
//            if (pair.key().contentEquals(loc)) {
//                find = pair.key();
//            }
//        }
//        return find;
//    }

}
