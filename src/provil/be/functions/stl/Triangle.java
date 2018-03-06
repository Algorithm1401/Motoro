package provil.be.functions.stl;

import provil.be.functions.Coordinates;

/**
 * Created by robin on 27/02/2018.
 */
public class Triangle {

    private Coordinates p1;
    private Coordinates p2;
    private Coordinates p3;
    private Coordinates normal;

    public Triangle(Coordinates p1, Coordinates p2, Coordinates p3, Coordinates normal){
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.normal = normal;
    }

    public Coordinates getP1() {
        return p1;
    }

    public Coordinates getP2() {
        return p2;
    }

    public Coordinates getP3() {
        return p3;
    }

    public Coordinates getNormal() {
        return normal;
    }
}
