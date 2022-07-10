package fourgame;


import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class BoardGUI {
    private JButton[][] buttons;
    private Board board;
    private JPanel boardPanel;
    private JLabel scoreLabel;
    private JLabel turnLabel;

    /**
     * initializes the board panels and labels of the frame
     * @param boardSize : n row or column length of the board (n X n)
     */
    public BoardGUI(int boardSize){
        board = new Board(boardSize);
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(board.getBoardSize(), board.getBoardSize()));
        buttons = new JButton[board.getBoardSize()][board.getBoardSize()];
        for (int i = 0; i < board.getBoardSize(); i++) {
            for (int j = 0; j < board.getBoardSize(); j++) {
                JButton button = new JButton();
                button.addActionListener(new ButtonListener(i, j));
                button.setPreferredSize(new Dimension(100, 60));
                buttons[i][j] = button;
                boardPanel.add(button);
            }
        }

        turnLabel = new JLabel("Move turn : " + board.preMoveTurn());
        turnLabel.setHorizontalAlignment(JLabel.RIGHT);

        scoreLabel = new JLabel("Score : Player1  " + board.getScorePlayer1() +  " - " + board.getScorePlayer2() + "Player2");
        scoreLabel.setHorizontalAlignment(JLabel.RIGHT);

    }

    /**
     * Getters and Setters
     */
    public JPanel getBoardPanel() {
        return boardPanel;
    }


    public JLabel getScoreLabel() {
        return scoreLabel;
    }

    public JLabel getTurnLabel() {
        return turnLabel;
    }


    class ButtonListener implements ActionListener {
        private int x;
        private int y;

        /**
         * Initializes button coordinates on board
         * @param x : row wise coordinate of a button on board
         * @param y : column wise coordinate of a button on board
         */
        public ButtonListener(int x, int y) {
            this.x = x;
            this.y = y;
        }
        /**
         * sets the prerequisite to perform the action
         * In case of valid action, further operations
         * applied respectively switchActualPlayer(),setText(), refresh(_, _)
         * @param e : recognized as click action on button spot
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if(board.getValue(x,y).getColor() == null) {
                board.switchActualPlayer();
                turnLabel.setText("Move turn : " + board.preMoveTurn());
                refresh(x, y);
            }

        }

    }

    /**
     * After the performed action(click on the button), corresponding neighbor buttons are triggered ,
     * and their values on the board are incremented and button text indicates those values, and in case values reach 4,
     * the color values of board changes and are set to the background color of the corresponding button. Simultaneously,
     * players' scores change, and are indicated on the score label. Upon to the end of game, the winner is exhibited
     * through the dialog and automatically, new game started.
     * @param indexI : row wise button coordinate
     * @param indexJ : column wise button coordinate
     */
    public void refresh(int indexI, int indexJ) {
        for (int i = 0; i < board.getBoardSize(); i++) {
            for (int j = 0; j < board.getBoardSize(); j++) {
                Field field = board.getValue(i, j);
                JButton button = buttons[i][j];


                if ( Math.abs(indexI - i) <= 1 && Math.abs(indexJ - j) <= 1 && board.getValue(i,j).getColor() == null) {
                        board.getValue(i, j).setNumber(board.getValue(i, j).getNumber() + 1);
                        button.setText(String.valueOf(field.getNumber()));

                        if(board.getValue(i,j).getNumber() == 4) {
                            board.applyColor(i, j);
                            board.applyScore();
                            button.setBackground(field.getColor());
                            scoreLabel.setText("Score : Player1  " + board.getScorePlayer1() +  " - " + board.getScorePlayer2() +  " Player2");
                        }
                }
            }
        }

        if (board.isOver()) {
            System.out.println();
            JOptionPane.showMessageDialog(boardPanel, board.findWinner() + " Won!", "Announcement", JOptionPane.PLAIN_MESSAGE);
            restart();

        }


    }

    /**
     * Sets all the components of board panels and labels, as well as
     * the actual board values, to their initial values.
     *
     */
    public void restart() {
        for (int i = 0; i < board.getBoardSize(); i++) {
            for (int j = 0; j < board.getBoardSize(); j++) {
                board.getValue(i,j).setColor(null);
                board.getValue(i,j).setNumber(0);
                board.setScorePlayer1(0);
                board.setScorePlayer2(0);
                board.setActualPlayer(null);
                buttons[i][j].setBackground(null);
                buttons[i][j].setText("");
                scoreLabel.setText("Score : Player1  " + board.getScorePlayer1() +  " - " + board.getScorePlayer2() +  " Player2");
                turnLabel.setText("Move turn : " + board.preMoveTurn());
            }
        }

    }


}
