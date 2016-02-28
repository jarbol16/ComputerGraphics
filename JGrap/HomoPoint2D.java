package JGrap;

public class HomoPoint2D {

    private final double[][] point = new double[3][3];

    public HomoPoint2D(double x, double y, double w) {
        this.point[0][0] = x;
        this.point[0][1] = y;
        this.point[0][2] = w;
    }

    public double getX() {
        return this.point[0][0];
    }

    public double getY() {
        return this.point[0][1];
    }

    public double getW() {
        return this.point[0][2];
    }

    public double[][] getCoordinates() {
        return this.point;
    }

    @Override
    public String toString() {
        return "P(" + this.point[0][0] + "," + this.point[0][1] + "," + this.point[0][2] + ")";
    }

}
