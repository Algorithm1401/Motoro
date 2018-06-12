package provil.be.functions;

import provil.be.functions.stl.Triangle;

public class Vector {

    double x;
    double y;
    double z;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vector crossProduct(Vector v, Vector v2){

        Vector resultingVector = new Vector(0,0,0);

        resultingVector.setX((v.getY() * v2.getZ()) - (v2.getY() * v.getZ()));
        resultingVector.setY((v.getZ() * v2.getX()) - (v2.getZ() * v.getX()));
        resultingVector.setZ((v.getX() * v2.getY()) - (v2.getX() * v.getY()));

        return resultingVector;
    }

    public static Vector divideVectorBy(Vector v, double toDivide){
        Vector resultingVector = new Vector(0, 0, 0);

        resultingVector.setX(v.getX() / toDivide);
        resultingVector.setY(v.getY() / toDivide);
        resultingVector.setZ(v.getZ() / toDivide);

        return resultingVector;

    }

    public static double getLength(Vector v){
        double lenght = new Double(0);
        lenght = Math.sqrt(Math.pow(v.getX(), 2) + Math.pow(v.getY(), 2) + Math.pow(v.getZ(), 2));

        return lenght;
    }

    public static Vector inverseVector(Vector v){
        Vector vNew = new Vector(-v.getX(), -v.getY(), -v.getZ());
        return vNew;
    }

    public static Vector getVector(Coordinates c1, Coordinates c2){

        Vector v = new Vector(c2.getX2() - c1.getX2(), c2.getY2() - c1.getY2(), c2.getZ2() - c1.getZ2());

        return v;
    }

    public static Vector getTriangleVector(Triangle t){

        Vector v;

        Vector v12 = getVector(t.getP1(), t.getP2());
        Vector v23 = getVector(t.getP2(), t.getP3());

        v = inverseVector(crossProduct(v12, v23));

        return v;

    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
