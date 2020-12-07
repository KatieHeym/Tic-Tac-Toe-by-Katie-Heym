/**
 * A program to run the Tic-Tac-Toe GUI.
 *
 * @author Katie Heym
 *
 */
public final class TicTacToeGUI {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private TicTacToeGUI() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        TicTacToeMenuModel model = new TicTacToeMenuModel1();
        TicTacToeMenuView view = new TicTacToeMenuView1();
        TicTacToeMenuController controller = new TicTacToeMenuController1(model,
                view);
        view.registerObserver(controller);
    }

}
