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

    public Coordinates(int x, int y, int z){

        this.x = x;
        this.y = y;
        this.z = z;

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

}
