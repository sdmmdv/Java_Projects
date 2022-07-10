public class Point {
    private double x;
    private double y;

    Point() {
        this.x = 0.0;
        this.y = 0.0;
    }

    Point(double x1, double y1) {
        this.x = x1;
        this.y = y1;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
