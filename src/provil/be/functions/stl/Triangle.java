package provil.be.functions.stl;

import provil.be.functions.Coordinates;

/**
 * Created by robin on 27/02/2018.
 */
public class Triangle {

    private Coordinates p1;
    private Coordinates p2;
    private Coordinates p3;
    private Coordinates midpoint;

    public Triangle(Coordinates p1, Coordinates p2, Coordinates p3, Coordinates midpoint){
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.midpoint = midpoint;
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

    public Coordinates getMidpoint() {
        return midpoint;
    }

    public Coordinates getMiddle(){
        Coordinates c = new Coordinates((p1.getX2() + p2.getX2() + p3.getX2()) / 3, (p1.getY2() + p2.getY2() + p3.getY2()) / 3, (p1.getZ2() + p2.getZ2() + p3.getZ2()));
        return c;
    }

    public String getNormal(){

        String function = new String();

        return function;
    }

}
