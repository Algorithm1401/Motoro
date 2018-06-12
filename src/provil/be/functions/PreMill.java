package provil.be.functions;

import provil.be.Main;
import provil.be.functions.stl.STL;
import provil.be.functions.stl.Triangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PreMill {

    private static double xmax = 0;
    private static double xmin = 0;

    private static double ymax = 0;
    private static double ymin = 0;

    private static double zmax = 0;
    private static double zmin = 0;

    public static double millDiameter = Double.valueOf(Main.getPropertyValue("Milling diameter"));
    public static double objectLength = Double.valueOf(Main.getPropertyValue("Object length"));
    public static double objectWidth = Double.valueOf(Main.getPropertyValue("Object width"));
    public static double objectHeigth = -Double.valueOf(Main.getPropertyValue("Object heigth"));
    public static double millDepth = Double.valueOf(Main.getPropertyValue("Milling depth"));

    public static void findCubikDimension(List<Triangle> triangles) {

        List<Double> xValues = new ArrayList<>();
        List<Double> yValues = new ArrayList<>();
        List<Double> zValues = new ArrayList<>();

        triangles.forEach(t -> {

            xValues.addAll(Arrays.asList(t.getP1().getX2(), t.getP2().getX2(), t.getP3().getX2()));
            yValues.addAll(Arrays.asList(t.getP1().getY2(), t.getP2().getY2(), t.getP3().getY2()));
            zValues.addAll(Arrays.asList(t.getP1().getZ2(), t.getP2().getZ2(), t.getP3().getZ2()));

        });

        Collections.sort(xValues);
        Collections.sort(yValues);
        Collections.sort(zValues);

        xmax = xValues.get(xValues.size() - 1);
        xmin = xValues.get(0);

        ymax = yValues.get(yValues.size() - 1);
        ymin = yValues.get(0);

        zmax = zValues.get(zValues.size() - 1);
        zmin = zValues.get(0);

    }

    public static boolean between(double i, double minValueInclusive, double maxValueInclusive) {

        if (minValueInclusive > maxValueInclusive) {
            double tempVal = minValueInclusive;
            minValueInclusive = maxValueInclusive;
            maxValueInclusive = tempVal;
        }

        return (i >= minValueInclusive && i <= maxValueInclusive);
    }

    public static boolean inObject(Coordinates cubeCoord, double millDiameter){

        Vector v12 = Vector.getVector(cubeCoord, new Coordinates(cubeCoord.getX2(), cubeCoord.getY2(), cubeCoord.getZ2() + millDiameter));
        Vector v34 = Vector.getVector(new Coordinates(cubeCoord.getX2() - millDiameter, cubeCoord.getY2(), cubeCoord.getZ2()),
                new Coordinates(cubeCoord.getX2() - millDiameter, cubeCoord.getY2(), cubeCoord.getZ2() + millDiameter));

        Vector v56 = Vector.getVector(new Coordinates(cubeCoord.getX2(), cubeCoord.getY2() - millDiameter, cubeCoord.getZ2()),
                new Coordinates(cubeCoord.getX2(), cubeCoord.getY2() - millDiameter, cubeCoord.getZ2() + millDiameter));

        Vector v78 = Vector.getVector(new Coordinates(cubeCoord.getX2() - millDiameter, cubeCoord.getY2() - millDiameter, cubeCoord.getZ2()),
                 new Coordinates(cubeCoord.getX2() - millDiameter, cubeCoord.getY2() - millDiameter, cubeCoord.getZ2() + millDiameter));

        for(Triangle t : STL.getStlConverted()){



        }
        return false;
    }

    public static boolean isObstructed(Coordinates cubeCoord, double millDiameter, Coordinates p) {
        if (between(p.getX2(), cubeCoord.getX2(), cubeCoord.getX2() - millDiameter)) {

            if (between(p.getY2(), cubeCoord.getY2(), cubeCoord.getY2() - millDiameter)) {

                if (between(p.getZ2(), cubeCoord.getZ2(), cubeCoord.getZ2() - millDiameter)) {

                    return true;

                }

            }

        }
        return false;
    }

    // Als een punt van de driehoek als niet obstructed wordt gezien kan het zijn dat de kubus nog in het object zit,
    // en dit wordt dan niet gezien.
    public static boolean anyObstructed(Coordinates cubeCoord, double millDiameter) {
        for (Triangle t : STL.getStlConverted()) {

            if (isObstructed(cubeCoord, millDiameter, t.getP1()) || isObstructed(cubeCoord, millDiameter, t.getP2())
                    || isObstructed(cubeCoord, millDiameter, t.getP3())) {
                return true;
            }

        }
        return false;
    }

    public static List<Coordinates> getCubikPoints() {

        findCubikDimension(STL.getStlConverted());

        List<Coordinates> PreMillpoints = new ArrayList<>();

        if ((Double.valueOf(Main.getPropertyValue("Object length")) > (xmax - xmin)) && (Double.valueOf(Main.getPropertyValue("Object width")) > (ymax - ymin))
                && (Double.valueOf(Main.getPropertyValue("Object heigth")) > (zmax - zmin))) {

            System.out.println("Config contains object dimensions");

            if (Main.getPropertyValue("Milling diameter") != null) {

                System.out.println("config contains milling diameter");

                // Starting point
                Coordinates startPoint = new Coordinates(-5, 0, objectHeigth + millDiameter);
                PreMillpoints.add(startPoint);

                System.out.println("START POINT: " + startPoint.getX2() + "," + startPoint.getY2() + "," + startPoint.getZ2());

                // Create the zero coordinate of the cube
                Coordinates cubeCoord = new Coordinates(0, 0, startPoint.getZ2() - millDepth);

                System.out.println("CUBE COORDS: " + cubeCoord.getX2() + "," + cubeCoord.getY2() + "," + cubeCoord.getZ2());

                boolean isLast = false;

                List<Coordinates> cubeCoords = new ArrayList<>();
                int currentInside = 0;
                int currentLayer = 0;

                while (!isLast) {


                    // Check of de x component van de kubus kleiner is dan de breedte van het object
                    while (cubeCoord.getX2() < objectWidth - (currentInside * millDiameter)) {
                        cubeCoords.add(cubeCoord);
                        cubeCoord = new Coordinates(cubeCoord.getX2() + millDiameter, cubeCoord.getY2(), cubeCoord.getZ2());
                    }

                    while (cubeCoord.getY2() < objectLength - (currentInside * millDiameter)) {
                        cubeCoords.add(cubeCoord);
                        cubeCoord = new Coordinates(cubeCoord.getX2(), cubeCoord.getY2() + millDiameter, cubeCoord.getZ2());
                    }

                    while (cubeCoord.getX2() > currentInside * millDiameter) {
                        cubeCoords.add(cubeCoord);
                        cubeCoord = new Coordinates(cubeCoord.getX2() - millDiameter, cubeCoord.getY2(), cubeCoord.getZ2());
                    }

                    while (cubeCoord.getY2() > millDiameter + (currentInside * millDiameter)) {
                        cubeCoords.add(cubeCoord);
                        cubeCoord = new Coordinates(cubeCoord.getX2(), cubeCoord.getY2() - millDiameter, cubeCoord.getZ2());
                    }

                    currentInside++;

                    if (objectLength > objectWidth) {

                        if (currentInside >= (objectLength / 2) + 1) {

                            currentLayer++;
                            if (currentLayer < (-objectHeigth / millDepth)) {
                                System.out.println("Currentlayer: " + currentLayer + " is smaller than " + (-objectHeigth / millDepth));
                                cubeCoords.add(new Coordinates(0, 0, cubeCoord.getZ2()));

                                cubeCoord = new Coordinates(0, 0, cubeCoord.getZ2() + millDepth);
                                currentInside = 0;
                            } else {
                                System.out.println("Is last cube");
                                isLast = true;
                            }
                        }

                    } else {

                        if (currentInside >= (objectWidth / 2) + 1) {
                            currentLayer++;
                            if (currentLayer < (-objectHeigth / millDepth)) {
                                System.out.println("Currentlayer: " + currentLayer + " is smaller than " + (-objectHeigth / millDepth));
                                cubeCoords.add(new Coordinates(0, 0, cubeCoord.getZ2()));

                                cubeCoord = new Coordinates(0, 0, cubeCoord.getZ2() + millDepth);
                                currentInside = 0;
                            } else {
                                System.out.println("Is last cube");
                                isLast = true;
                            }
                        }

                    }

                }

                for (Coordinates c : cubeCoords) {

                    if (!anyObstructed(c, millDiameter)) {

                        PreMillpoints.add(new Coordinates(c.getX2() - (millDiameter / 2), c.getY2() - (millDiameter / 2), c.getZ2()));

                    }

                }

            }

        }
        return PreMillpoints;
    }
}


