/**
 * Implementation class for the tic-tac-toe game controller.
 *
 * @author Katie Heym
 *
 */
public final class TicTacToeController1 implements TicTacToeController {

    /**
     * The model.
     */
    private TicTacToeModel model;

    /**
     * The view.
     */
    private TicTacToeView view;

    /**
     * Turn tracker.
     */
    private boolean isHumanTurn;

    /**
     * Constructor.
     *
     * @param model
     * @param view
     * @param isHumanTurn
     */
    public TicTacToeController1(TicTacToeModel model, TicTacToeView view,
            boolean isHumanTurn) {
        this.model = model;
        this.view = view;
        this.isHumanTurn = isHumanTurn;
        this.view.updateView(this.model.getBoard());
        this.view.updateMessageBox(this.isHumanTurn, false, false);
        if (!this.isHumanTurn) {
            this.model.playComputer();
            this.afterTurnUpdate();
        }
        assert this.isHumanTurn : "Turn order mishap.";
    }

    @Override
    public void processPlayEvent(Location loc) {
        assert this.isHumanTurn : "Turn order mishap.";
        this.model.playHuman(loc);
        this.afterTurnUpdate();
        if (!this.isEnd()) {
            this.model.playComputer();
            this.afterTurnUpdate();
        }
    }

    /**
     * Updates the view after a turn has been played.
     */
    private void afterTurnUpdate() {
        if (!this.isEnd()) {
            this.isHumanTurn = !this.isHumanTurn;
            this.view.updateView(this.model.getBoard());
            this.view.updateMessageBox(this.isHumanTurn, false, false);
        } else {
            this.processEnd();
        }
    }

    /**
     * Processes a game end.
     */
    private void processEnd() {
        this.view.updateView(this.model.getBoard());
        boolean isWon = this.model.getBoard().won(1)
                || this.model.getBoard().won(-1);
        this.view.updateMessageBox(this.isHumanTurn, isWon,
                !isWon && this.model.getBoard().isFull());
    }

    /**
     * Checks if the game has ended.
     *
     * @return true if tie or win
     */
    private boolean isEnd() {
        return this.model.getBoard().won(1) || this.model.getBoard().won(-1)
                || this.model.getBoard().isFull();
    }

    @Override
    public void processPlayAgainEvent() {
        TicTacToeMenuModel model = new TicTacToeMenuModel1();
        TicTacToeMenuView view = new TicTacToeMenuView1();
        TicTacToeMenuController controller = new TicTacToeMenuController1(model,
                view);
        view.registerObserver(controller);
    }

}
