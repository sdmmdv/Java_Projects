/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.logging.Logger;
import javax.swing.*;


public class Board {
    private final int SCALE = 25;
    private ArrayList<Rock> rocks;
    private Apple apple;
    private Snake snake;
    private Random random = new Random();

    /**
     * Triggers Level loading from the files
     * @param levelPath
     * @throws IOException
     */
    public Board(String levelPath) throws IOException {
        loadLevel(levelPath);
    }

    /**
     * Setters and Getters
     */
    public Apple getApple() {
        return apple;
    }

    public Snake getSnake() {
        return snake;
    }

    /**
     * Creates main objects due to the pattern read from the files
     * Establishes the screen prototype, and location of objects
     * @param levelPath
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void loadLevel(String levelPath) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(levelPath));
        rocks = new ArrayList<>();
        Image image = new ImageIcon("data/images/rock.png").getImage();
        Image appleImage = new ImageIcon("data/images/apple.png").getImage();
        Image snakeHeadImage = new ImageIcon("data/images/snakehead.png").getImage();
        Image snakeTailImage = new ImageIcon("data/images/snakepart.png").getImage();
        int yCor = 0;
        String line;
        while ((line = br.readLine()) != null) {
            int xCor = 0;
            for (char blockType : line.toCharArray()) {
                switch(blockType){
                    case 'R' :
                        rocks.add(new Rock(new Position(xCor*SCALE,yCor*SCALE), SCALE, SCALE, image));
                    break;
                    case 'A' :
                        apple = new Apple(new Position(xCor*SCALE, yCor*SCALE), SCALE, SCALE, appleImage);
                    break;
                    case 'S' :
                        snake = new Snake(new Position(xCor*SCALE, yCor*SCALE), SCALE, SCALE, snakeHeadImage, snakeTailImage);
                    break;
                }
                xCor++;
            }
            yCor++;
        }
    }

    /**
     * Draws  elements of collection of rocks to the screen
     * @param g
     */
    public void drawRocks(Graphics g) {
        for (Rock rock : rocks) {
            rock.draw(g);
        }
    }

    /**
     * Relocate the next position of apple avoiding collision to any
     * of the objects exist, generates the random coordinates correlated
     * to the screen dimensions implemented by recursive algorithm
     */
    public void relocateApple(){
        apple.setPosition(new Position(SCALE * (random.nextInt(23) + 1), SCALE * (random.nextInt(17) + 1)));
        for(int i=0; i<rocks.size();i++){
            if(apple.getPosition().getX() == rocks.get(i).getPosition().getX() &&
                    apple.getPosition().getY() == rocks.get(i).getPosition().getY()){
                relocateApple();
            }
        }

        for(int i=0; i<snake.getTail().size();i++){
            if(apple.getPosition().getX() == snake.getTail().get(i).getX() && apple.getPosition().getY()
                    == snake.getTail().get(i).getY()){
                relocateApple();
            }
        }

        if(apple.getPosition().getX() == snake.getPosition().getX() && apple.getPosition().getY()
                == snake.getPosition().getY()){
            relocateApple();
        }
    }

    /**
     * Determines the end of game (fail status) by checking intersection
     * of snake head against rocks, borders of board and body part of snake
     * @return boolean
     */
    public boolean isOver(){

        boolean isTheRock = false;
        boolean isTheTail = false;

        for(int i=0; i < rocks.size() && !isTheRock; i++){
            isTheRock = snake.getPosition().getX() == rocks.get(i).getPosition().getX()
                    && snake.getPosition().getY() == rocks.get(i).getPosition().getY();

        }

        for(int i=0; i< snake.getTail().size() && !isTheTail; i++){
            isTheTail = snake.getPosition().getX() == snake.getTail().get(i).getX()
                    && snake.getPosition().getY()== snake.getTail().get(i).getY();

        }

        boolean isOutOfBoard = (snake.getPosition().getX() > 575 || snake.getPosition().getX() < 0)
                        || (snake.getPosition().getY() > 425 || snake.getPosition().getY() < 0);

        return (isTheRock || isTheTail || isOutOfBoard);
    }

}

