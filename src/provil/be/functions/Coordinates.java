package provil.be.functions;

/**
 * Created by robin on 6/11/2017.
 */
public class Coordinates {

    /**
     * Coordinaten object om op een simpele manier de coordinaten door te geven aan de robot.
     */

    private int x;
    private int y;
    private int z;

    private double x2;
    private double y2;
    private double z2;

    public Coordinates(int x, int y, int z){

        this.x = x;
        this.y = y;
        this.z = z;

    }

    public Coordinates(double x2, double y2, double z2){
        this.x2 = x2;
        this.y2 = y2;
        this.z2 = z2;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getZ(){
        return this.z;
    }

    public double getX2() {
        return x2;
    }

    public double getY2() {
        return y2;
    }

    public double getZ2() {
        return z2;
    }
}
