public class Square extends Polygon {

    public Square(Point center, double side){
        super(center, side);
    }

    /* Method draw()  overriding method from super class Shape ,
     * illustrates the kind of object by name returning String value “Square”.
     */

    public final String draw() {
        return "Square";
    }

    /* Method isInside()  overriding method from super class Shape, checks if the point entered by user is indeed
     * inside the shape. Initially discovers the bottom left and top right vertices of square on the coordinate system.
     * And returns true, if the point lies in the border of vertical and horizontal slicing.
     */
    @Override
    public final boolean isInside(Point point) {
        return (point.getX() <= center.getX() + side / 2.0 && point.getX() >= center.getX() - side / 2.0)
                && (point.getY() <= center.getY() + side / 2.0 && point.getY() >= center.getY() - side / 2.0);
    }
}
