package snakegame;


import java.awt.*;
import java.util.ArrayList;

public class Snake extends Sprite {
    private Image imageTail;
    private ArrayList<Position> tail = new ArrayList<>();

    /**
     * Initializes the snake parameters
     * @param position
     * @param width
     * @param height
     * @param image
     * @param imageTail
     */
    public Snake(Position position, int width, int height, Image image, Image imageTail){
        super(position, width, height, image);
        this.tail.add(new Position(position.getX()-25,position.getY()));
        this.imageTail = imageTail;
    }

    @Override
    public Position getPosition() {
        return super.getPosition();
    }

    /**
     * Draws the snake components (head and tail)
     * @param g
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(image, position.getX(), position.getY(), width, height, null);
        for(int i=0; i<tail.size(); i++) {
            g.drawImage(imageTail, tail.get(i).getX(), tail.get(i).getY(), width, height, null);
        }
    }

    /**
     * Indicates the valid movement of snake object
     * @param direction
     */
    public void move(Direction direction){
        shift();
        switch(direction) {
            case LEFT: setPosition(new Position(position.getX() - 25, position.getY()));
                break;
            case RIGHT: setPosition(new Position(position.getX() + 25, position.getY()));
                break;
            case UP:  setPosition(new Position(position.getX(), position.getY() - 25));
                break;
            case DOWN: setPosition(new Position(position.getX(), position.getY() + 25));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + direction);
        }

    }

    /**
     * Follows velocity of the snake
     * head and body parts alltogether
     */
    public void shift(){
        for(int i = tail.size()-1; i >= 0; i--) {
            if( i != 0){
                tail.get(i).setX(tail.get(i - 1).getX());
                tail.get(i).setY(tail.get(i - 1).getY());
            }
            else {
                tail.get(i).setX(this.position.getX());
                tail.get(i).setY(this.position.getY());
            }
        }

    }

    public ArrayList<Position> getTail() {
        return tail;
    }
}
