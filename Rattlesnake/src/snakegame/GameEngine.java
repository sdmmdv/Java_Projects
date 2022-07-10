package snakegame;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author bli
 */
public class GameEngine extends JPanel {
    //private static ScoreManager sm = new ScoreManager();
    private HighScores hs;
    private final int FPS = 240;
    private final int MAX_SCORE = 50;
    private static Mode gameMode = Mode.Arcade;
    private int levelScore = 5;
    private Board board;
    private Direction actualDirection;
    private int levelNum;
    private int score;
    private boolean paused = true;
    private Image background;
    private Timer newFrameTimer;
    private JLabel scoreLabel;

    /**
     * Initializes the graphic components, key listeners of
     * the main frame , controls the main frame
     * @param anyNum
     * @param mode
     */
    public GameEngine(int anyNum, Mode mode) {
        super();
        this.levelNum = anyNum;
        gameMode = mode;
        try {   
            hs = new HighScores(10);
        } catch (SQLException ex) {
            Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        restart();
        scoreLabel = new JLabel("Level : " + levelNum + "  " + "Score : "+ score + "  ");
        scoreLabel.setHorizontalAlignment(JLabel.RIGHT);
        background = new ImageIcon("data/images/desert1.jpg").getImage();


        this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "pressed left");

        this.getActionMap().put("pressed left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if((actualDirection == Direction.UP || actualDirection == Direction.DOWN)
                        && board.getSnake().getPosition().getY() != board.getSnake().getTail().get(0).getY()) {
                    actualDirection = Direction.LEFT;
                }
            }
        });

        this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "pressed right");
        this.getActionMap().put("pressed right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if((actualDirection == Direction.UP || actualDirection == Direction.DOWN)
                        && board.getSnake().getPosition().getY() != board.getSnake().getTail().get(0).getY()){
                    actualDirection = Direction.RIGHT;
                }
            }
        });


        this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "pressed down");
        this.getActionMap().put("pressed down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if((actualDirection == Direction.LEFT || actualDirection == Direction.RIGHT)
                        && board.getSnake().getPosition().getX() != board.getSnake().getTail().get(0).getX()){
                    actualDirection = Direction.DOWN;
                }
            }
        });

        this.getInputMap().put(KeyStroke.getKeyStroke("UP"), "pressed up");
        this.getActionMap().put("pressed up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if((actualDirection == Direction.LEFT || actualDirection == Direction.RIGHT)
                        && board.getSnake().getPosition().getX() != board.getSnake().getTail().get(0).getX())  {
                    actualDirection = Direction.UP;
                }
            }
        });


        this.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "space");
        this.getActionMap().put("space", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               paused = !paused;
            }
        });

        newFrameTimer = new Timer(20000 / FPS, new NewFrameListener());
        newFrameTimer.start();
    }

    /**
     * Getters and Setters
     */

    public static Mode getGameMode() {
        return gameMode;
    }

   // public static ScoreManager getSm() {
   //     return sm;
   // }

    public static void setGameMode(Mode gameMode) {
        GameEngine.gameMode = gameMode;
    }

    public JLabel getTurnLabel() {
        return scoreLabel;
    }

    public void setLevelScore(int levelScore) {
        this.levelScore = levelScore;
    }

    public void setLevelNum(int levelNum) {
        this.levelNum = levelNum;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void restart() {
        try {
            board = new Board("data/levels/level" + levelNum + ".txt");
        } catch (IOException ex) {
            Logger.getLogger(GameEngine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        actualDirection = Direction.RIGHT;
    }


    /**
     * Paints background and objects using actual graphics,
     * for pause mode additional texts provided
     * @param grphcs
     */
    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        grphcs.drawImage(background, 0, 0, 600, 450, null);
        board.drawRocks(grphcs);
        board.getSnake().draw(grphcs);
        board.getApple().draw(grphcs);

        if(paused){
            grphcs.setFont(new Font("arial", Font.BOLD, 30));
            grphcs.drawString("Press Space to Start", 150, 400);
        }
    }

    /**
     * Implements collecting apples, as length of the snake advances
     */
    public void grows(){
        if(board.getSnake().getPosition().getX() == board.getApple().getPosition().getX()
                && board.getSnake().getPosition().getY() == board.getApple().getPosition().getY()){
            score++;
            board.relocateApple();
            board.getSnake().getTail().add(new Position(board.getApple().getPosition().getX(),
                    board.getApple().getPosition().getY()));

        }
    }


    /**
     * Conducts the refreshing after the end of current game
     */
    public void update(){
        paused = !paused;
        registerPlayer();
        if(gameMode == Mode.Arcade) {
            setLevelNum(1);
        }
        setLevelScore(5);
        setScore(0);
        scoreLabel.setText("Level : " + levelNum + "  " + "Score : "+ score + "  ");
    }

    public HighScores getHs() {
        return hs;
    }

    /**
     * Shows the input message, and registers
     * player's details to the table
     */
    public void registerPlayer(){
        
            String nameToSave = "Your score is: " + (score) + "\nType your name: ";
            if(score < MAX_SCORE)
                nameToSave = JOptionPane.showInputDialog("Game Over! " + nameToSave);
            else
                nameToSave = JOptionPane.showInputDialog("Congratulations You Won! " + nameToSave);
            
         try {   
            hs.putHighScore((new Timestamp(System.currentTimeMillis())).toString(),nameToSave, score, gameMode.name(), levelNum);
        } catch (SQLException ex) {
            Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void connectDB(){
    
    }


    /**
     * Implements the Active Flow of the game both visually and logically
     * Conducts corresponding events based on different situations
     */
    class NewFrameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if(!paused) {
                if(score == levelScore && gameMode == Mode.Arcade){
                    paused = !paused;
                    levelScore += 5;
                    levelNum++;
                    restart();
                }
                if (!board.isOver() && score < MAX_SCORE) {
                    board.getSnake().move(actualDirection);
                    scoreLabel.setText("Level : " + levelNum + "  " + "Score : "+ score + "  ");
                    grows();
                    if (!board.isOver()) {
                        repaint();
                    }
                } else {
                    update();
                    restart();
                }
            }
            else {
                if(!board.isOver())
                repaint();
            }


        }

    }

}
