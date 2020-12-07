import java.awt.event.ActionListener;

/**
 * View interface for Tic-Tac-Toe option menu.
 *
 * @author Katie Heym
 *
 */
public interface TicTacToeMenuView extends ActionListener {

    /**
     * Register argument as observer/listener of this.
     *
     * @param controller
     *            controller to register
     */
    void registerObserver(TicTacToeMenuController controller);

}
