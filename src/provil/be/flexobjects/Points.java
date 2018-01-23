package provil.be.flexobjects;

public class Points {

    //<editor-fold desc="Defined items">
    String pointName;

    double x;
    double y;
    double z;

    double q1;
    double q2;
    double q3;
    double q4;

    int rotAxis1;
    int rotAxis4;
    int rotAxis6;
    //</editor-fold>

    public Points(String pointName, double x, double y, double z, double q1, double q2, double q3, double q4, int rotAxis1, int rotAxis4, int rotAxis6){
        this.pointName = pointName;
        this.x = x;
        this.y = y;
        this.z = z;
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
        this.q4 = q4;
        this.rotAxis1 = rotAxis1;
        this.rotAxis4 = rotAxis4;
        this.rotAxis6 = rotAxis6;
    }

    public static String set(Points point){

        String pointString = "CONST robtarget " + point.getPointName() + ":=[[" +
                point.getX() + "," + point.getY() + "," + point.getZ() + "],[" +
                point.getQ1() + "," + point.getQ2() + "," + point.getQ3() + "," + point.getQ4() + "],[" +
                point.getRotAxis1() + "," + point.getRotAxis4() + "," + point.getRotAxis6() + ",0],[9E+09,9E+09,9E+09,9E+09,9E+09,9E+09]];";

        return pointString;
    }

    //<editor-fold desc="All object getters">
    public String getPointName() {
        return pointName;
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

    public double getQ1() {
        return q1;
    }

    public double getQ2() {
        return q2;
    }

    public double getQ3() {
        return q3;
    }

    public double getQ4() {
        return q4;
    }

    public int getRotAxis1() {
        return rotAxis1;
    }

    public int getRotAxis4() {
        return rotAxis4;
    }

    public int getRotAxis6() {
        return rotAxis6;
    }
    //</editor-fold>
}
