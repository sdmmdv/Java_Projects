public class RegularHexagon extends Polygon {

    public RegularHexagon(Point center, double side){
        super(center, side);
    }

    @Override
    public final String draw() {
        return "RegularHexagon";
    }

    public double distance(Point point) {
            return Math.sqrt((center.getX() - point.getX()) * (center.getX() - point.getX())
                    + (center.getY() - point.getY()) * (center.getY() - point.getY()));
    }
/* Method isInside()  overriding method from super class Shape, checks
* if the point entered by user is indeed inside the shape.
* Initially calculates the inscribed circle’s radius, and returns true, if the distance
*  is less than the radius of inscribed circle with a slight inaccuracy.
 */
    @Override
    //with slight inaccuracy
    public final boolean isInside(Point point) {
        double IncircleRadius = Math.sqrt(3) * side / 2.0;
        return distance(point) <= IncircleRadius;
    }
}
