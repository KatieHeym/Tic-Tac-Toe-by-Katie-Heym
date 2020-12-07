import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import components.map.Map;
import components.map.Map1L;

/**
 * Tic-tac-toe view class.
 *
 * @author Katie Heym
 *
 */
@SuppressWarnings("serial")
public final class TicTacToeView1 extends JFrame implements TicTacToeView {

    /**
     * Controller object.
     */
    private TicTacToeController controller;

    /**
     * Location to button map.
     */
    private Map<Location, JButton> locBut;

    /**
     * Location to label map.
     */
    private Map<Location, JLabel> locLab;

    /**
     * Message label.
     */
    private JLabel message;

    /**
     * Message panel.
     */
    private JPanel mPanel;

    /**
     * Message panel buttons.
     */
    private JButton exitButton, playAgainButton;

    /**
     * Panel dimensions.
     */
    private static final int LABEL_PANEL_DIM = 5, BUTTON_PANEL_DIM = 3, DIM = 3,
            MAIN_PANEL_DIM = 5;

    /**
     * Create a row of JLabels loaded into the locLab map by location, separated
     * by vertical separators.
     *
     * @param row
     *            the row number
     * @return a JPanel containing these items
     */
    private JPanel createLabelRow(int row) {
        JPanel panelRow = new JPanel(new GridLayout(1, LABEL_PANEL_DIM));
        int col = 0;
        while (col < DIM) {
            JLabel label = new JLabel(" ", SwingConstants.CENTER);
            panelRow.add(label);
            this.locLab.add(new TicTacToeLoc(row, col), label);
            if (col < DIM - 1) {
                JPanel panel = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridheight = GridBagConstraints.REMAINDER;
                gbc.weighty = 1;
                gbc.fill = GridBagConstraints.VERTICAL;
                panel.add(new JSeparator(SwingConstants.VERTICAL), gbc);
                panelRow.add(panel);
            }
            col++;
        }
        return panelRow;
    }

    /**
     * Build the initial view.
     *
     * @param humanMarker
     *            -1 for "X", 1 for "O"
     */
    public TicTacToeView1(int humanMarker) {
        super("Tic-Tac-Toe");
        this.locBut = new Map1L<Location, JButton>();
        this.locLab = new Map1L<Location, JLabel>();

        JPanel topView = new JPanel(new GridLayout(LABEL_PANEL_DIM, 1));
        int row = 0;
        while (row < DIM) {
            topView.add(this.createLabelRow(row));
            if (row < DIM - 1) {
                JPanel panel = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.weightx = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                panel.add(new JSeparator(SwingConstants.HORIZONTAL), gbc);
                topView.add(panel);
            }
            row++;
        }

        JPanel bottomButtons = new JPanel(
                new GridLayout(BUTTON_PANEL_DIM, BUTTON_PANEL_DIM));
        row = 0;
        while (row < DIM) {
            int col = 0;
            while (col < DIM) {
                JButton button = new JButton(markerKey(humanMarker));
                bottomButtons.add(button);
                this.locBut.add(new TicTacToeLoc(row, col), button);
                button.addActionListener(this);
                col++;
            }
            row++;
        }

        this.message = new JLabel(" ", SwingConstants.CENTER);
        this.mPanel = new JPanel(new GridLayout(1, 1));
        this.mPanel.add(this.message);

        this.setLayout(new GridLayout(MAIN_PANEL_DIM, 1));
        this.add(topView);
        this.add(new JSeparator(SwingConstants.HORIZONTAL));
        this.add(this.mPanel);
        this.add(new JSeparator(SwingConstants.HORIZONTAL));
        this.add(bottomButtons);

        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        Object source = event.getSource();
        boolean isPlayButton = false;
        for (Map.Pair<Location, JButton> candidate : this.locBut) {
            if (candidate.value() == source) {
                isPlayButton = true;
                this.controller.processPlayEvent(candidate.key());
            }
        }
        if (!isPlayButton) {
            this.dispose();
            if (source == this.playAgainButton) {
                this.controller.processPlayAgainEvent();
            }
        }

        this.setCursor(Cursor.getDefaultCursor());
    }

    @Override
    public void updateView(TicTacToeBoard<Integer> board) {
        for (Map.Pair<Location, Integer> spot : board) {
            this.locBut.value(spot.key()).setEnabled(spot.value() == 0);
            this.locLab.value(spot.key()).setText(markerKey(spot.value()));
        }
        this.setVisible(true);
    }

    /**
     * Given a marker, returns the appropriate text.
     *
     * @param marker
     * @return "O" for 1, "X" for -1, " " otherwise.
     */
    private static String markerKey(int marker) {
        String key = " ";
        if (marker == 1) {
            key = "O";
        } else if (marker == -1) {
            key = "X";
        }
        return key;
    }

    @Override
    public void registerObserver(TicTacToeController controller) {
        this.controller = controller;
    }

    @Override
    public void updateMessageBox(boolean isHumanTurn, boolean won,
            boolean isTie) {
        if (!won && !isTie) {
            if (isHumanTurn) {
                this.message
                        .setText("Your turn! Use the buttons to make a move.");
            } else {
                for (Map.Pair<Location, JButton> pair : this.locBut) {
                    pair.value().setEnabled(false);
                }
                this.message.setText("Please wait. The computer is thinking.");
            }
        } else {
            for (Map.Pair<Location, JButton> pair : this.locBut) {
                pair.value().setEnabled(false);
            }
            this.mPanel.removeAll();
            this.mPanel.setLayout(new GridLayout(2, 1));
            if (isTie) {
                this.message.setText("It's a tie! Cat's Game!");
            } else if (isHumanTurn) {
                this.message.setText("Congratulations! You won!");
            } else {
                this.message
                        .setText("The computer won. Better luck next time!");
            }
            this.mPanel.add(this.message);

            JPanel optionButtons = new JPanel();
            this.exitButton = new JButton("Exit");
            this.playAgainButton = new JButton("Play Again");
            this.exitButton.addActionListener(this);
            this.playAgainButton.addActionListener(this);
            optionButtons.add(this.exitButton);
            optionButtons.add(this.playAgainButton);
            this.mPanel.add(optionButtons);
        }
        this.pack();
    }

}
