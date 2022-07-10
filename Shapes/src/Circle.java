public class Circle extends Shape{

    private double radius = super.getSide();

    public Circle(Point center, double radius) {

        super(center,radius);

    }

    @Override
    /*
    *  Method draw()  overriding method from super class Shape ,
    *  illustrates the kind of object by name returning String value “Circle”.
    */
    public final String draw() {
        return "Circle";
    }

    public double getRadius() {
        return radius;
    }

    public double getCenterX() {
        return center.getX();
    }

    public double getCenterY() {
        return center.getY();
    }

    // distance((x1, y1), (x2, y2)) = √(x1 - x2)² + (y1 - y2)²
    /*
    * Method distance()  method of type double, calculates the distance between
    * the center of coordinate system and given arbitrary point.
    */
    public double distance(Point point) {
        return Math.sqrt((center.getX() - point.getX()) * (center.getX() - point.getX())
                + (center.getY() - point.getY()) * (center.getY() - point.getY()));
    }


    @Override

    /*
    * Method isInside()  overriding method from super class Shape, checks if the point entered by user is indeed inside the shape.
    *  Returns true, if the distance is less than the radius of circle
     */
    public final boolean isInside(Point point) {
        return distance(point) <= radius;
    }

}
