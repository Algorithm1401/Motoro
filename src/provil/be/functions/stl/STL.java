package provil.be.functions.stl;

import provil.be.functions.Coordinates;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by robin on 27/02/2018.
 */
public class STL {

    private static Set<Triangle> stlConverted = new HashSet<>();

    public static void parseSTL(File stl) {

        try {

            stlConverted = new HashSet<>();

            File stlToTXT = new File(stl.getPath().replace(".stl", ".txt"));
            FileInputStream in = null;
            FileOutputStream out = null;

            try {
                in = new FileInputStream(stl);
                out = new FileOutputStream(stlToTXT);
                int c;

                while ((c = in.read()) != -1) {
                    out.write(c);
                }
            } finally {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            }

            Scanner scanner;
            String nextLine;

            scanner = new Scanner(stlToTXT);
            nextLine = scanner.nextLine();

            Coordinates normal = null;
            List<Coordinates> vertexes = new ArrayList<>();


            while (!nextLine.contains("endsolid")) {
                List<Double> doubles;
                if (nextLine.contains("facet normal")) {
                    doubles = DoublesInString(nextLine);
                    normal = new Coordinates(doubles.get(0), doubles.get(1), doubles.get(2));
                } else if (nextLine.contains("vertex")) {
                    doubles = DoublesInString(nextLine);
                    Coordinates point = new Coordinates(doubles.get(0), doubles.get(1), doubles.get(2));
                    vertexes.add(point);
                } else if (nextLine.contains("endloop")) {
                    Triangle triangle = new Triangle(vertexes.get(0), vertexes.get(1), vertexes.get(2), normal);
                    stlConverted.add(triangle);
                    vertexes = new ArrayList<>();
                }
                nextLine = scanner.nextLine();

            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static List<Double> DoublesInString(String s){
        List<Double> doubles = new ArrayList<>();

            for(String stemp : s.split("\\s+")){
                if(stemp.matches(".*\\d+.*")){
                    doubles.add(Double.valueOf(stemp));
                }
                s = s.replace(stemp, "");
            }
            return doubles;
    }

    public static Set<Triangle> getStlConverted() {
        return stlConverted;
    }
}
