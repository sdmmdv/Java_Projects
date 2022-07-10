package snakegame;

import java.awt.Image;

public class Rock extends Sprite {

    /**
     * Initializes Rock object parameters
     * @param position
     * @param width
     * @param height
     * @param image
     */
    public Rock(Position position, int width, int height, Image image){
        super(position, width, height, image);
    }

    /**
     * Getters and Setters
     */
    @Override
    public Position getPosition() {
        return super.getPosition();
    }
}
