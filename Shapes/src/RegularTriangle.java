public class RegularTriangle extends Shape {

    RegularTriangle(Point center, double side) {
        super(center, side);
    }
/*
* Method draw()  overriding method from super class Shape , illustrates
*  the kind of object by name returning String value “RegularTriangle”.
*/

    @Override
    public final String draw() {
        return "RegularTriangle";
    }

    /*
    * Method isInside()  overriding method from super class Shape, checks if the point entered
    *  by user is indeed inside the shape. Initially discovers all the vertices of regular
    *  triangle on the coordinate system. Then makes conversion into Barycentric coordinates in which
    *  returns true if the inequalities  0 <= s <= 1 and 0 <= t <= 1 and s + t <= 1 hold true.
    *
     */

    @Override
    public final boolean isInside(Point point) {

        double p0_y = (center.getY() - (side * Math.sqrt(3) / 6.0));
        double p0_x = (center.getX() - (side / 2.0));

        double p1_y = (center.getY() + (side * Math.sqrt(3) / 3.0));
        double p1_x = (center.getX());

        double p2_y = (center.getY() - (side * Math.sqrt(3) / 6.0));
        double p2_x = (center.getX() + (side / 2.0));

        //System.out.println(p0_y + " " + p0_x + " "+ p1_y + " " + p1_x + " "+ p2_y + " " + p2_x);
        //Using Barycentric coordinate system

        double denominator = ((p1_y - p2_y)*(p0_x - p2_x) + (p2_x - p1_x)*(p0_y - p2_y));
        double s =  ((p1_y - p2_y)*(point.getX() - p2_x) + (p2_x - p1_x)*(point.getY() - p2_y)) / denominator;
        double t = ((p2_y - p0_y)*(point.getX() - p2_x) + (p0_x - p2_x)*(point.getY() - p2_y)) / denominator;

        //returns true if 0 <= s <= 1 and 0 <= t <= 1 and s + t <= 1.
       // System.out.println(s + " " + t);

        return (s >= 0 && s <= 1) && (t >= 0 && t <= 1) && (s + t <= 1);



    }

}
