public class Polygon extends Shape{

    public Polygon(Point center, double side) {
        super(center, side);
    }

    @Override
    public String draw() {
        return "Polygon";
    }

    @Override
    public boolean isInside(Point point) {
        return false;
    }
}
