import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

/**
 * Implements the tic-tac-toe menu view.
 *
 * @author Katie Heym
 *
 */
@SuppressWarnings("serial")
public final class TicTacToeMenuView1 extends JFrame
        implements TicTacToeMenuView {

    /**
     * The controller object.
     */
    private TicTacToeMenuController controller;

    /**
     * Buttons.
     */
    private JButton xButton, oButton, humanButton, compButton, randButton,
            cancelButton, okButton;

    /**
     * Number of difficulty settings.
     */
    private static final int DIFF_SETS = 6;

    /**
     * Difficulty selection button array.
     */
    private JButton[] diffArray;

    /**
     * Sizing constants.
     */
    private static final int NORM_ROWS = 3;

    /**
     * No-argument constructor. Sets the initial view.
     */
    public TicTacToeMenuView1() {

        super("Option Menu");

        /**
         * Marker options.
         */
        JLabel markerLabel = new JLabel("Choose your marker:");
        this.xButton = new JButton("X");
        this.xButton.addActionListener(this);
        this.xButton.setEnabled(false);
        this.oButton = new JButton("O");
        this.oButton.addActionListener(this);
        JPanel markerButtons = new JPanel();
        markerButtons.add(this.xButton);
        markerButtons.add(this.oButton);
        JPanel markerPanel = new JPanel();
        markerPanel.setLayout(new GridLayout(NORM_ROWS, 1));
        markerPanel.add(markerLabel);
        markerPanel.add(markerButtons);
        markerPanel.add(new JSeparator(SwingConstants.HORIZONTAL));

        /**
         * First player options.
         */
        JLabel firstLabel = new JLabel("Choose who goes first:");
        this.randButton = new JButton("Random");
        this.randButton.addActionListener(this);
        this.randButton.setEnabled(false);
        this.humanButton = new JButton("Human");
        this.humanButton.addActionListener(this);
        this.compButton = new JButton("Computer");
        this.compButton.addActionListener(this);
        JPanel firstButtons = new JPanel();
        firstButtons.add(this.randButton);
        firstButtons.add(this.humanButton);
        firstButtons.add(this.compButton);
        JPanel firstPanel = new JPanel();
        firstPanel.setLayout(new GridLayout(NORM_ROWS, 1));
        firstPanel.add(firstLabel);
        firstPanel.add(firstButtons);
        firstPanel.add(new JSeparator(SwingConstants.HORIZONTAL));

        /**
         * Difficulty options.
         */
        int maxDiff = DIFF_SETS - 1;
        JLabel diffLabel = new JLabel("Choose your difficulty. 0 is easiest; "
                + maxDiff + " is hardest:");
        this.diffArray = new JButton[DIFF_SETS];
        JPanel diffButtons = new JPanel();
        Integer counter = 0;
        while (counter < DIFF_SETS) {
            this.diffArray[counter] = new JButton(counter.toString());
            diffButtons.add(this.diffArray[counter]);
            this.diffArray[counter].addActionListener(this);
            counter++;
        }
        this.diffArray[0].setEnabled(false);
        JPanel diffPanel = new JPanel();
        diffPanel.setLayout(new GridLayout(NORM_ROWS, 1));
        diffPanel.add(diffLabel);
        diffPanel.add(diffButtons);
        diffPanel.add(new JSeparator(SwingConstants.HORIZONTAL));

        /**
         * Cancel and okay panel.
         */
        JPanel coPanel = new JPanel();
        this.cancelButton = new JButton("Cancel");
        this.cancelButton.addActionListener(this);
        this.okButton = new JButton("Confirm");
        this.okButton.addActionListener(this);
        coPanel.add(this.cancelButton);
        coPanel.add(this.okButton);

        /**
         * Assemble the JFrame.
         */
        this.getContentPane().setLayout(
                new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.add(markerPanel);
        this.add(firstPanel);
        this.add(diffPanel);
        this.add(coPanel);

        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Sets the button to enabled if the source of the event is not the button.
     *
     * @param source
     * @param button
     */
    private static void setButton(Object source, JButton button) {
        button.setEnabled(source != button);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        Object source = event.getSource();
        if (source == this.xButton || source == this.oButton) {
            setButton(source, this.xButton);
            setButton(source, this.oButton);
            this.controller.processMarkerEvent(source == this.xButton);
        } else if (source == this.randButton || source == this.humanButton
                || source == this.compButton) {
            setButton(source, this.randButton);
            setButton(source, this.humanButton);
            setButton(source, this.compButton);
            boolean humanFirst = source == this.humanButton;
            if (source == this.randButton) {
                Random rand = new Random();
                humanFirst = rand.nextBoolean();
            }
            this.controller.processFirstEvent(humanFirst);
        } else if (source == this.cancelButton || source == this.okButton) {
            this.dispose();
            if (source == this.okButton) {
                this.controller.processConfirmEvent();
            }
        } else {
            int counter = 0;
            double ind = 0;
            while (counter < this.diffArray.length) {
                setButton(source, this.diffArray[counter]);
                if (source == this.diffArray[counter]) {
                    ind = counter;
                }
                counter++;
            }
            this.controller.processDiffEvent(ind / (DIFF_SETS - 1));
        }

        this.setCursor(Cursor.getDefaultCursor());
    }

    @Override
    public void registerObserver(TicTacToeMenuController controller) {
        this.controller = controller;
        this.setVisible(true);
    }

}
