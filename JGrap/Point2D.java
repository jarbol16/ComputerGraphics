package JGrap;

public class Point2D {
    private double x;
    private double y;
    private final double[] coordinates = new double[2];

    public Point2D(double x, double y) {
        this.coordinates[0] = x;
        this.coordinates[1] = y;
        this.x = x;
        this.y = y;
        
        
    }

    public int getX() {
        return (int)this.coordinates[0];
    }

    public int getY() {
        return (int)this.coordinates[1];
    }

    public double[] getCoordinates() {
        return this.coordinates;
    }

    @Override
    public String toString() {
        String rta = "P(" + this.coordinates[0] + "," + this.coordinates[1] + ")";
        return rta;
    }

}
