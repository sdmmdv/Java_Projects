/*
* Contains an abstract shape, and consists of two private fields side(double) and center Point(double ,double)
*  where point is the data type of class Point defined by attributes x and y.
* Shape class plays the role of a super class so all the other different shapes will inherit from this.
 */


public abstract class Shape {
        protected Point center;
        protected double side;


        public Shape(Point center, double side){
            this.center = center;
            this.side = side;
        }

    public double getSide() {
        return side;
    }

   /*
   * *Method draw()  abstract method of type void, illustrates the kind of object by name returning String value.
   */
    abstract public String draw();
    /*
    *Method isInside()  abstract method of type Boolean, checks if the point entered by user is indeed inside the shape.
    */
    abstract public boolean isInside (Point point);

    /*
    *Method toString()  method of type String, overrides Object class’s method to exhibit attributes’ values to the System output.
    */
    @Override
    public String toString() {
        return draw() +  " --> Center: (" + center.getX() + ", " + center.getY() + ") , side: " + side + " ";
    }

}
