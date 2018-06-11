package provil.be.flexobjects;

public class Tool {

    //<editor-fold desc="Defined items">
    String toolName;
    boolean robhold;

    // x,y and z coordinate of the tool
    double x;
    double y;
    double z;

    // Quaterion??
    double q1;
    double q2;
    double q3;
    double q4;

    // Mass of the tool and the mass mid points of it.
    double mass;
    double massMidPointX;
    double massMidPointY;
    double massMidPointZ;

    double q1_1;
    double q2_1;
    double q3_1;
    double q4_1;

    // Dimensions of the tool itself
    double xi;
    double yi;
    double zi;
    //</editor-fold>

    /**
     *
     * Object om de tool te definiëren en om te zetten naar RAPID code
     *
     *
     */

    public Tool(String toolName, boolean robhold, double x, double y, double z, double q1, double q2, double q3, double q4,
                double mass, double massMidPointX, double massMidPointY, double massMidPointZ, double q1_1, double q2_1, double q3_1, double q4_1, double xi, double yi, double zi){

        this.toolName = toolName;
        this.robhold = robhold;
        this.x = x;
        this.y = y;
        this.z = z;
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
        this.q4 = q4;

        this.mass = mass;
        this.massMidPointX = massMidPointX;
        this.massMidPointY = massMidPointY;
        this.massMidPointZ = massMidPointZ;
        this.q1_1 = q1_1;
        this.q2_1 = q2_1;
        this.q3_1 = q3_1;
        this.q4_1 = q4_1;
        this.xi = xi;
        this.yi = yi;
        this.zi = zi;

    }

    public static String set(Tool tool){
        String toolString = "TASK PERS tooldata " + tool.getToolName() + ":=[" + String.valueOf(tool.isRobhold()).toUpperCase() + ",[["
                + tool.getX() + "," + tool.getY() + "," + tool.getZ() + "],[" + tool.getQ1() + "," + tool.getQ2() + "," + tool.getQ3() + "," + tool.getQ4() +
        "]],[" + tool.getMass() + ",[" + tool.getMassMidPointX() + "," + tool.getMassMidPointY() + "," + tool.getMassMidPointZ() + "],[" + tool.getQ1_1() + "," +
                tool.getQ2_1() + "," + tool.getQ3_1() + "," + tool.getQ4_1() + "]," + tool.getXi() + "," + tool.getYi() + "," + tool.getZi() + "]];";

        return toolString;
    }

    //<editor-fold desc="All getters for the objects">
    public String getToolName() {
        return toolName;
    }

    public boolean isRobhold() {
        return robhold;
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

    public double getMass() {
        return mass;
    }

    public double getMassMidPointX() {
        return massMidPointX;
    }

    public double getMassMidPointY() {
        return massMidPointY;
    }

    public double getMassMidPointZ() {
        return massMidPointZ;
    }

    public double getQ1_1() {
        return q1_1;
    }

    public double getQ2_1() {
        return q2_1;
    }

    public double getQ3_1() {
        return q3_1;
    }

    public double getQ4_1() {
        return q4_1;
    }

    public double getXi() {
        return xi;
    }

    public double getYi() {
        return yi;
    }

    public double getZi() {
        return zi;
    }
    //</editor-fold>
}
