import java.util.Random;

/**
 * Implementation of the menu model.
 *
 * @author Katie Heym
 *
 */
public final class TicTacToeMenuModel1 implements TicTacToeMenuModel {

    /**
     * No-argument constructor; initial values.
     */
    public TicTacToeMenuModel1() {
        this.humanIsX = true;
        this.diff = 0;
        Random rand = new Random();
        this.humanFirst = rand.nextBoolean();
    }

    /**
     * Model variables.
     */
    private boolean humanIsX, humanFirst;

    /**
     * Model variable.
     */
    private double diff;

    @Override
    public void setHumanIsX(boolean humanIsX) {
        this.humanIsX = humanIsX;
    }

    @Override
    public boolean getHumanIsX() {
        return this.humanIsX;
    }

    @Override
    public void setHumanFirst(boolean humanFirst) {
        this.humanFirst = humanFirst;
    }

    @Override
    public boolean getHumanFirst() {
        return this.humanFirst;
    }

    @Override
    public void setDiff(double diff) {
        this.diff = diff;
    }

    @Override
    public double getDiff() {
        return this.diff;
    }

}
