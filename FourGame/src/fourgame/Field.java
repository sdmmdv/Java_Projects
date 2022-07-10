package fourgame;

import java.awt.Color;

public class Field {
    private Color color;
    private int number;

    /**
     * Initializes the private members to default values
     */
    public Field() {
        color = null;
        number = 0;
    }

    /**
    *  Getters and Setters
    */
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
