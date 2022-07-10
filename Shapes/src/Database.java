import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/* class Database
* stores collection of reference type Shapes to the ArrayList
*  and reserves private member given point of type  Point(double ,double).
*/

public class Database {

    private ArrayList<Shape> shapes;
    private Point givenPoint;

    public Database() {
        shapes = new ArrayList<>();
    }
/*
*  Method read()  reads the data from the file using class Scanner,
*  and after initializes the objects and stores them into collection.
*/
    public void read(String filename) throws FileNotFoundException, InputMismatchException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(filename)));
        int numOfShapes = sc.nextInt();
        double centerX = sc.nextDouble();
        double centerY = sc.nextDouble();
        givenPoint = new Point(centerX, centerY);
        Point point;
        while (sc.hasNext()) {
            Shape shape;
            String type = sc.next();
            centerX = sc.nextDouble();
            centerY = sc.nextDouble();
            double sideLength = sc.nextDouble();
            point = new Point(centerX, centerY);
            //System.out.println(type + " " + centerX + " " + centerY + " " + sideLength);

            switch (type) {
                case "C":
                    shape = new Circle(point, sideLength);
                    break;
                case "T":
                    shape = new RegularTriangle(point, sideLength);
                    break;
                case "S":
                    shape = new Square(point, sideLength);
                    break;
                case "H":
                    shape = new RegularHexagon(point, sideLength);
                    break;
                default:
                    throw new FileNotFoundException();
            }

            shapes.add(shape);
           // System.out.println(shape.getCenter().getX() + " " +  shape.getCenter().getY());
        }
    }

    /*
     * Method report()  outputs the properties of derived classes stored in collection.
     */
    public void report() {
        System.out.println("Shapes in the database:");
        for (Shape s : shapes) {
            System.out.println(s);
        }
    }

    /*
    * Method countPointHolder()  counts the total number of a given point occurrence in the shapes
    * and returns the integer type of count using the standard count algorithm.
    */
    public int countPointHolder () {
        int count = 0;
        for(Shape s : shapes) {
            if(s.isInside(givenPoint)) {
                System.out.println(s.draw());
                count++;
            }
        }
        return count;
    }

}
