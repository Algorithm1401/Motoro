package provil.be.functions;

public class planeFunction {

    // x+y+z+d = 0

    double x;
    double y;
    double z;
    double d;

    public planeFunction(double x, double y, double z, double d){
        this.x = x;
        this.y = y;
        this.z = z;
        this.d = d;

    }

        /*/
    ℓ:⎧⎩⎨x=xA+xUty=yA+yUtz=zA+zUt
    Thus:

    ℓ:⎧⎩⎨x=3t+2y=5t+4z=6t−3
    Let M=(a,b,c) be the intersection point between P and ℓ. M satisfies the equations of each: P and ℓ, so that for some t: a=3t+2, b=5t+4 and c=6t−3, and a+2b−2c=9. Hence, 3t+2+10t+8−12t+6=9, so t=−7.

    Therefore, M=(−19,−31,−45).
     */



    public static Coordinates getIntersectionVectorplane(Coordinates c1, Coordinates c2, planeFunction planeFunction){
        Coordinates cIntersection = new Coordinates(0, 0, 0);


        return cIntersection;
    }

    public static planeFunction definePlane(Coordinates c1, Coordinates c2, Coordinates c3){

        planeFunction planeFunction = new planeFunction(0, 0, 0, 0);

        Vector v12 = Vector.getVector(c1, c2);
        Vector v13 = Vector.getVector(c1, c3);

        Vector normal = Vector.crossProduct(v12, v13);

        planeFunction.setX(normal.getX());
        planeFunction.setY(normal.getY());
        planeFunction.setZ(normal.getZ());

        // d = -x -y -z

        planeFunction.setD(-normal.getX() -normal.getY() -normal.getZ());

        return planeFunction;

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

    public double getD() {
        return d;
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

    public void setD(double d) {
        this.d = d;
    }
}
