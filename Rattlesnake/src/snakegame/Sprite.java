package snakegame;

import java.awt.Graphics;
import java.awt.Image;

public class Sprite {
    protected Position position;
    protected int width;
    protected int height;
    protected Image image;

    /**
     * Initializes sprite object parameters
     * @param position
     * @param width
     * @param height
     * @param image
     */
    public Sprite(Position position, int width, int height, Image image) {
        this.position = position;
        this.width = width;
        this.height = height;
        this.image = image;
    }

    /**
     * Getters and Setters
     */

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Draws generic sprite object
     * @param g
     */
    public void draw(Graphics g) {
        g.drawImage(image, position.getX(), position.getY(), width, height, null);
    }


}
