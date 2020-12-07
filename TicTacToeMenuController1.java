/**
 * Implementation of the tic-tac-toe menu controller.
 *
 * @author Katie Heym
 *
 */
public final class TicTacToeMenuController1 implements TicTacToeMenuController {

    /**
     * The model.
     */
    private TicTacToeMenuModel model;

    /**
     * The view.
     */
    private TicTacToeMenuView view;

    /**
     * Constructor.
     *
     * @param model
     * @param view
     */
    public TicTacToeMenuController1(TicTacToeMenuModel model,
            TicTacToeMenuView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void processMarkerEvent(boolean humanIsX) {
        this.model.setHumanIsX(humanIsX);
    }

    @Override
    public void processFirstEvent(boolean humanFirst) {
        this.model.setHumanFirst(humanFirst);
    }

    @Override
    public void processDiffEvent(double diff) {
        this.model.setDiff(diff);
    }

    @Override
    public void processConfirmEvent() {
        int human = 1;
        if (this.model.getHumanIsX()) {
            human = -1;
        }
        TicTacToeModel gameModel = new TicTacToeModel1(human,
                this.model.getDiff());
        TicTacToeView gameView = new TicTacToeView1(human);
        TicTacToeController gameController = new TicTacToeController1(gameModel,
                gameView, this.model.getHumanFirst());
        gameView.registerObserver(gameController);
    }

}
