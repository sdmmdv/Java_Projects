package snakegame;

import java.awt.*;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;



public class SnakeGameGUI {
    private final int X_DIMENSION = 605;
    private final int Y_DIMENSION = 517;
    private JFrame frame;
    private GameEngine gameEngine;
    private final int NumberOfLevels = 10;
    public static int currentLevel = 1;

    /**
     * initializes the main frame and its components
     */
    public SnakeGameGUI() {
        frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu menuGame = new JMenu("Game");
        JMenu menuNewGame = new JMenu("New Game");
        JMenuItem menuHighScore = new JMenuItem("HighScore Table");
        




        JMenuItem menuGameExit = new JMenuItem(new AbstractAction("Exit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        /**
         * Creates levels based on Arcade mode,
         * order indicator of levels always start from 1.
         * @param menu
         */
        JMenuItem arcade = new JMenuItem(new AbstractAction("Arcade") {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentLevel = 1;
                GameEngine.setGameMode(Mode.Arcade);
                frame.dispose();
                new SnakeGameGUI();
            }
        });


        menuNewGame.add(arcade);
        createGameLevelMenuItems(menuNewGame);


        menuGame.add(menuNewGame);
        menuGame.addSeparator();

        menuGame.add(menuHighScore);
        menuGame.addSeparator();

        menuHighScore.addActionListener(new ActionListener() {


            /**
             * Initializes the database table frame
             * Collecting from score information and fills the table
             * @param arg
             */
            @Override
            public void actionPerformed(ActionEvent arg) {
                JFrame tableFrame = new JFrame();
                int size = 0;
                try {
                    size = gameEngine.getHs().getHighScores().size();
                } catch (SQLException ex) {
                    Logger.getLogger(SnakeGameGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                tableFrame.setTitle("High Scores");
                String[] header = {"Timestamp","Name", "Score", "Mode", "Level"};
                String[][] body = new String[size][5];
                
                try {
                for (int i = 0; i < size; i++) {
                    body[i][0] = gameEngine.getHs().getHighScores().get(i).getTs().toString().substring(0, 19);
                    body[i][1] = gameEngine.getHs().getHighScores().get(i).getName();
                    body[i][2] = Integer.toString(gameEngine.getHs().getHighScores().get(i).getScore());
                    body[i][3] = gameEngine.getHs().getHighScores().get(i).getMode();
                    body[i][4] = Integer.toString(gameEngine.getHs().getHighScores().get(i).getLevel());
                }
                } catch (SQLException ex) {
                    Logger.getLogger(SnakeGameGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
               
                //Creation of table and Scroll object
                JTable table = new JTable(body, header);
                JScrollPane sp = new JScrollPane(table);
                tableFrame.add(sp);
                tableFrame.setSize(625, 300);
                tableFrame.setVisible(true);
                
            }
        });

        menuGame.add(menuGameExit);
        menuBar.add(menuGame);
        frame.setJMenuBar(menuBar);

        gameEngine = new GameEngine(currentLevel, GameEngine.getGameMode());
        frame.getContentPane().add(gameEngine.getTurnLabel(), BorderLayout.NORTH);
        frame.getContentPane().add(gameEngine, BorderLayout.CENTER);
        

        frame.setPreferredSize(new Dimension(X_DIMENSION, Y_DIMENSION));
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Creates levels based on Classic mode,
     * depending on the order indicator of levels.
     * @param menu
     */
    private void createGameLevelMenuItems(JMenu menu){
        JMenu classic = new JMenu("Classic");
        menu.add(classic);
            for (int i=1; i <= NumberOfLevels; i++){
                int finalI = i;
                JMenuItem item = new JMenuItem(new AbstractAction("Level-" + i) {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        GameEngine.setGameMode(Mode.Classic);
                        currentLevel = finalI;
                        frame.dispose();
                        new SnakeGameGUI();
                    }
                });
                classic.add(item);
            }
    }


}
