package fourgame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;

public class FourgameGUI {

    private JFrame frame;
    private BoardGUI boardGUI;
    private final int InitialBoardSize = 5;

    /**
     * initializes the main frame and its components
     */
    public FourgameGUI() {
        frame = new JFrame("Four game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setIconImage(new ImageIcon(getClass().getResource("Icon.png")).getImage());

        boardGUI = new BoardGUI(InitialBoardSize);
        frame.getContentPane().add(boardGUI.getBoardPanel(), BorderLayout.CENTER);
        frame.getContentPane().add(boardGUI.getScoreLabel(), BorderLayout.SOUTH);
        frame.getContentPane().add(boardGUI.getTurnLabel(), BorderLayout.NORTH);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu gameMenu = new JMenu("Game");
        menuBar.add(gameMenu);
        JMenu newMenu = new JMenu("New");
        gameMenu.add(newMenu);


        //creation : new array of dimensions
        int[] boardSizes = new int[] {3, 5, 7};

        for(int boardSize : boardSizes) {
            JMenuItem sizeMenuItem = new JMenuItem(boardSize + " x " + boardSize);
            newMenu.add(sizeMenuItem);
            sizeMenuItem.addActionListener(new ActionListener() {
                /**
                 * @param e recognized as new game(frame) event
                 * old components of the main frame is removed,
                 * instead new components introduced with a given dimension(boardSize)
                 * and shrinks frame to the preferred size of components.
                 */
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.getContentPane().remove(boardGUI.getBoardPanel());
                    frame.getContentPane().remove(boardGUI.getTurnLabel());
                    frame.getContentPane().remove(boardGUI.getScoreLabel());
                    boardGUI = new BoardGUI(boardSize);
                    frame.getContentPane().add(boardGUI.getBoardPanel(), BorderLayout.CENTER);
                    frame.getContentPane().add(boardGUI.getTurnLabel(), BorderLayout.NORTH);
                    frame.getContentPane().add(boardGUI.getScoreLabel(), BorderLayout.SOUTH);
                    frame.pack();
                }
            });

        }

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        gameMenu.add(exitMenuItem);
        exitMenuItem.addActionListener(new ActionListener() {
            /**
             * @param ae recognized as an exit event
             */
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });

        frame.pack();
        frame.setVisible(true);

    }

    /**
     * Getters and Setters
     */
    public JFrame getFrame() {
        return frame;
    }
}
