package JGrap;

public class Vector2D {

    private final double[] components = new double[2];
    private Point2D xx;
    private Point2D yy;
    public Vector2D(double x, double y) {
        this.components[0] = x;
        this.components[1] = y;
    }

    public Vector2D(Point2D a, Point2D b) {
        this.components[0] = a.getX()-b.getX();
        this.components[1] = a.getY()-b.getY();
        this.xx = a;
        this.yy = b;
    }
    public Point2D getXX(){
        return xx;
    }
    public Point2D getYY(){
        return yy;
    }

    public Vector2D sumarVectores(Vector2D a, Vector2D b) {
        double e = a.getX() + b.getX();
        double d = b.getY() + a.getY();
        Vector2D res = new Vector2D(e, d);
        return res;
    }

    public Vector2D restarVectores(Vector2D a, Vector2D b) {
        double e = a.getX() - b.getX();
        double d = b.getY() - a.getY();
        Vector2D res = new Vector2D(e, d);
        return res;
    }

    public Vector2D multVectScalar(Vector2D a, int b) {
        double d = a.getX() * b;
        double e = a.getY() * b;
        Vector2D res = new Vector2D(d, e);
        return res;
    }

    public double productoPunto(Vector2D a, Vector2D b) {
        double res = (a.getX() * b.getX()) + (a.getY() * b.getY());
        return res;
    }

    public Vector3D productoCruz(Vector2D a, Vector2D b) {
        double i = a.getY() * 0 - b.getY() * 0;
        double j = -(a.getX() * 0 - b.getX() * 0);
        double k = a.getX() * b.getY() - b.getX() * a.getY();
        Vector3D res = new Vector3D(i, j, k);
        return res;
    }

    public double getX() {
        return this.components[0];
    }

    public double getY() {
        return this.components[1];
    }

}
