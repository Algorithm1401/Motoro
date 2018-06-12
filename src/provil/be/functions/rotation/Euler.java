package provil.be.functions.rotation;

import provil.be.functions.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Euler {

    // Standaard assenstelsel van de base van de robot.
    private Vector x1,y1;
    private static Vector x = new Vector(1, 0, 0);
    private static Vector y = new Vector(0, 1, 0);
    private static Vector z = new Vector(0, 0, 1);

    public Euler() {
    }

    /**
     *
     * @param z1 De vector die vertrekt vanuit de robot toolkop die berekend moet worden met behulp van de rechte vanuit het middelpunt van de STL driehoek.
     * @return de euler hoeken van een verschoven assenstelsel
     */

    // LET OP, TOEVOEGEN UITZONDERING ALS Z AS GELIJK LIGT MET Z1 AS!!

    public List<Double> getResults(Vector z1){

        List<Double> angles = new ArrayList<>();

        double alpha;
        double beta;
        double omega;

        // Resulterende vector van de z1 x component en z1 y component
        double Zxy = Math.sqrt(Math.pow(z1.getX(), 2) + Math.pow(z1.getY(), 2));

        // De node vector wordt berekend door de resulterende vector van de z en z1 as
        Vector node = Vector.crossProduct(z1, z);
        System.out.println("node " + node.getX() + ", " +  node.getY() + ", " + node.getZ());

        // Resulterende vectoren met de gekende vectoren.
        y1 = Vector.divideVectorBy(node, Vector.getLength(node));
        System.out.println(Vector.getLength(node) + " node length");
        System.out.println(y1.getX() + ", " + y1.getY() + ", " + y1.getZ() + " >> Y1");
        x1 = Vector.crossProduct(z1, y1);
        System.out.println(x1.getX() + ", " + x1.getY() + ", " + x1.getZ() + " >> X1");

        // Omrekening van de vectoren naar de euler hoeken.
        alpha = 90;
        beta = Math.atan2(Zxy, z1.getZ()) * (180/Math.PI);
        omega = (-Math.atan2(-z1.getX(), z1.getY())) * (180/Math.PI);
        System.out.println((-z1.getX()) + " atan2 first arg, " + z1.getY() + " second arg");

        angles.addAll(Arrays.asList(alpha, beta, omega));

        return angles;
    }

    public static List<Double> toQuaternions(List<Double> eulerangles){
        List<Double> quaternions = new ArrayList<>();

        double yaw = eulerangles.get(1);
        double roll = eulerangles.get(0);
        double pitch = eulerangles.get(2);

        double cy = Math.cos(yaw * 0.5);
        double sy = Math.sin(yaw * 0.5);
        double cr = Math.cos(roll * 0.5);
        double sr = Math.sin(roll * 0.5);
        double cp = Math.cos(pitch * 0.5);
        double sp = Math.sin(pitch * 0.5);

        quaternions.add(cy * cr * cp + sy * sr * sp);
        quaternions.add(cy * sr * cp - sy * cr * sp);
        quaternions.add(cy * cr * sp + sy * sr * cp);
        quaternions.add(sy * cr * cp - cy * sr * sp);
        return quaternions;

    }

}
