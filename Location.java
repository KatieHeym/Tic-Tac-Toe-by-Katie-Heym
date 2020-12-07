import components.array.Array;

/**
 * An interface for locations on a game board.
 *
 * @author Katie Heym
 *
 */
public interface Location {

    /**
     * Directions for navigation.
     */
    enum Direction {
        /**
         * The desired directions.
         */
        RIGHT(false, false, false, true), DOWN_RIGHT(false, true, false,
                true), DOWN(false, true, false, false), DOWN_LEFT(false, true,
                        true, false), LEFT(false, false, true, false), UP_LEFT(
                                true, false, true,
                                false), UP(true, false, false, false), UP_RIGHT(
                                        true, false, false, true);

        private final boolean goesUp;
        private final boolean goesDown;
        private final boolean goesLeft;
        private final boolean goesRight;

        private Direction(boolean up, boolean down, boolean left,
                boolean right) {
            this.goesUp = up;
            this.goesDown = down;
            this.goesLeft = left;
            this.goesRight = right;
        }

        public boolean goesUp() {
            return this.goesUp;
        }

        public boolean goesDown() {
            return this.goesDown;
        }

        public boolean goesLeft() {
            return this.goesLeft;
        }

        public boolean goesRight() {
            return this.goesRight;
        }

    };

    /**
     * Returns the location in the given direction relative to this.
     *
     * @param direction
     *            the given direction
     * @return the new location
     */
    Location navigate(Direction direction);

    /**
     * returns an array containing information about the location.
     *
     * @return the array with information
     */
    Array<Integer> getInfo();

//    /**
//     * Object equality.
//     *
//     * @param loc
//     * @return true if the object parameters are equal, false otherwise
//     */
//    boolean contentEquals(Location loc);
}
