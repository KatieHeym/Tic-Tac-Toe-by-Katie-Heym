import components.array.Array;
import components.array.Array1L;

/**
 * An implementation of Location for use with TicTacToeBoard.
 *
 * @author Katie Heym
 *
 */
public final class TicTacToeLoc implements Location {

    /**
     * Array containing information about the loc.
     */
    private Array<Integer> loc;

    /**
     * The info available and the location in the {@code Array}.
     */
    enum Info {
        /**
         * Info bits.
         */
        ROW(0), COLUMN(1);

        /**
         * Index value.
         */
        private final int ind;

        /**
         * Constructor for adding index value.
         *
         * @param index
         */
        Info(int index) {
            this.ind = index;
        }

        /**
         * Access method to get the index.
         *
         * @return the index
         */
        public int index() {
            return this.ind;
        }
    }

    /**
     * Constructor specifying the row and column.
     *
     * @param row
     * @param column
     *
     * @requires 0 <= row <= 2 0 <= column <= 2
     */
    public TicTacToeLoc(int row, int column) {
        this.loc = new Array1L<Integer>(2);
        this.loc.setEntry(Info.ROW.ind, row);
        this.loc.setEntry(Info.COLUMN.ind, column);
    }

    @Override
    public Location navigate(Direction direction) {
        int row = this.getInfo().entry(Info.ROW.ind);
        int column = this.getInfo().entry(Info.COLUMN.ind);
        if (direction.goesDown()) {
            row++;
        } else if (direction.goesUp()) {
            row--;
        }
        if (direction.goesRight()) {
            column++;
        } else if (direction.goesLeft()) {
            column--;
        }
        return new TicTacToeLoc(row, column);
    }

    @Override
    public Array<Integer> getInfo() {
        return this.loc;
    }

//    @Override
//    public boolean contentEquals(Location loc) {
//        boolean equality = this.getInfo().length() == loc.getInfo().length();
//        int counter = 0;
//        while (equality && counter < this.getInfo().length()) {
//            if (this.getInfo().entry(counter) != loc.getInfo().entry(counter)) {
//                equality = false;
//            }
//            counter++;
//        }
//        return equality;
//    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Location)) {
            return false;
        }
        Location loc = (Location) obj;
        return this.getInfo().equals(loc.getInfo());
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return this.getInfo().toString();
    }

}
