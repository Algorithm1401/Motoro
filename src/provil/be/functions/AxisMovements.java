package provil.be.functions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robin on 6/11/2017.
 */

public class AxisMovements {

    //<editor-fold desc="Defined items">
    // Gear ratio of the timing pulley on the motor axis and the timing belt
    private List<Double> gearRatio;

    // Current axis positions of the arm
    private List<Double> axisRotated;

    private List<Double> axisDimensions;
    //</editor-fold>

    /**
     * Methodes voor de programmatie als er geen gebruik gemaakt zou gehad kunnen worden van de RAPID programmeertaal.
     */

    public AxisMovements(List<Double> gearRatio, List<Double> axisDimensions, List<Double> axisRotated){
        this.gearRatio = gearRatio;
        this.axisDimensions = axisDimensions;
        this.axisRotated = axisRotated;
    }

    public double angleToPositionAxis1(Coordinates c){

        //<editor-fold desc="Defined items">
        // x and z coordinates of the objective

        int x = c.getX();
        int z = c.getZ();

        double xz = (double) x/z;
        //</editor-fold>

        //<editor-fold desc="Axis 1">
        // get the angle to turn if the start position is 0,0,0
        double angle = Math.toDegrees(Math.atan(xz));

        // Calculate the angle the motor needs to turn in order to get to the real angle.
        double gearedAngle = gearRatio.get(0) * angle;

        // Decrement the geared angle with the current rotated geared angle of the motor.
        gearedAngle -= (axisRotated.get(0) * gearRatio.get(0));

        axisRotated.set(0, angle);
        //</editor-fold>

        return gearedAngle;

    }

    public List<Double> angleToPositionAxis2And3(Coordinates c){

        //<editor-fold desc="Defined items">
        // Create a list for the second and thirth axis geared angles
        List<Double> gearedAngles = new ArrayList<>();

        // x and z position of the objective
        int x = c.getX();
        int z = c.getZ();
        //</editor-fold>

        //<editor-fold desc="Axis 2">
        // the distance between 0,0,0 and the objective
        double distance = Math.sqrt(Math.pow(x, 2) + Math.pow(z, 2));

        double lengthAxis1 = axisDimensions.get(0);
        double lengthAxis2 = axisDimensions.get(1);

        // cos phi to turn of the second axis from position 0,0,0
        double cosAxis1 = ((Math.pow(lengthAxis1, 2) + Math.pow(distance, 2) - Math.pow(lengthAxis2,2)) / (2*lengthAxis1*distance));

        // Actual angle of first axis
        double angle = 90 - Math.toDegrees(Math.acos(cosAxis1));

        // geared angle from current rotation and add to the list.
        double gearedangle = gearRatio.get(1) * angle;
        gearedangle -= axisRotated.get(1) * gearRatio.get(1);
        gearedAngles.add(gearedangle);
        //</editor-fold>

        //<editor-fold desc="Axis 3">
        // cosine of tetha from position 0,0,0
        double cosAxis2 = ((Math.pow(lengthAxis1, 2) + Math.pow(lengthAxis2, 2) - Math.pow(distance,2)) / (2*lengthAxis1*lengthAxis2));

        // Actual angle of second axis
        double angle2 = 180 - Math.toDegrees(Math.acos(cosAxis2));

        // geared angle from current rotation and add to the list
        double gearedangle2 = gearRatio.get(2) * angle2;
        gearedangle2 -= axisRotated.get(2) * gearRatio.get(2);
        gearedAngles.add(gearedangle2);
        //</editor-fold>

        return gearedAngles;
    }

    public List<Double> getAllAnglesToPosition(Coordinates coordinates){

        List<Double> allAngles = new ArrayList<>();
        allAngles.add(angleToPositionAxis1(coordinates));
        allAngles.addAll(angleToPositionAxis2And3(coordinates));

        return allAngles;

    }

}
