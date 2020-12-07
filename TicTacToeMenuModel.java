/**
 * Interface for the tic-tac-toe menu model.
 *
 * @author Katie Heym
 *
 */
public interface TicTacToeMenuModel {

    /**
     * Set whether the human player will play with X markers.
     *
     * @param humanIsX
     */
    void setHumanIsX(boolean humanIsX);

    /**
     * Get whether the human player will play with X markers.
     *
     * @return whether the human player will play with X markers.
     */
    boolean getHumanIsX();

    /**
     * Set whether the human player will go first.
     *
     * @param humanFirst
     */
    void setHumanFirst(boolean humanFirst);

    /**
     * Get whether the human player will go first.
     *
     * @return whether the human player will go first.
     */
    boolean getHumanFirst();

    /**
     * Set the difficultly on a scale from 0 to 1.
     *
     * @param diff
     */
    void setDiff(double diff);

    /**
     * Get the difficultly on a scale from 0 to 1.
     *
     * @return the difficultly on a scale from 0 to 1.
     */
    double getDiff();

}
