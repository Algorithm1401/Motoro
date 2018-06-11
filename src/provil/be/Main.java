package provil.be;

import org.apache.commons.net.ftp.FTPClient;
import provil.be.flexobjects.Points;
import provil.be.flexobjects.ProgramFile;
import provil.be.flexobjects.Tool;
import provil.be.flexobjects.Wait;
import provil.be.flexobjects.move.Move;
import provil.be.flexobjects.move.MoveType;
import provil.be.functions.Connection;
import provil.be.functions.Coordinates;
import provil.be.functions.PreMill;
import provil.be.functions.Vector;
import provil.be.functions.rotation.Euler;
import provil.be.functions.stl.STL;
import provil.be.functions.stl.Triangle;
import provil.be.gui.Login;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by robin on 6/11/2017.
 */

public class Main {

    public static File propertiesConfig = new File(System.getProperty("user.home") + "/AppData/Roaming/Motoro/config.properties");

    /**
     *
     * Maakt een connectie met de MySQL Database die ingegeven is
     *
     */

    public static void setupMySQLDriver(){

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            System.out.println("Setup error:");
            ex.printStackTrace();
        }

    }

    /**
     *
     * @param property
     * @return De bijhorende waarde van de property
     *
     */

    public static String getPropertyValue(String property){

        Properties properties = new Properties();
        FileReader fileReader;

        String value = null;

        try{

            System.out.println(propertiesConfig.getPath() + " properties config path.");

            fileReader = new FileReader(propertiesConfig);
            properties.load(fileReader);
            value = properties.getProperty(property);

            System.out.println(propertiesConfig.getName());
            System.out.println(property);
            System.out.println(value);

            fileReader.close();

        }catch (IOException e){
            e.printStackTrace();
        }

        return value;
    }

    /**

    Als deze methode opgeroepen wordt met de opgegeven parameters, zal hij
     de string value instellingen bij de string property in het properties bestand.

     @param propertyValues Hierbij is de key in de map de property naam en de wildcard
     is de waarde die bij de bijhorende key oftewel property ingevoerd moet worden.

     */

    public static void setPropertyValue(Map<String, String> propertyValues){

        Properties properties = new Properties();



        try{

            for(int i = 0; i<propertyValues.size(); i++) {
                String property = (String) propertyValues.keySet().toArray()[i];
                properties.setProperty(property, propertyValues.get(property).toString());
            }
            OutputStream out = new FileOutputStream(propertiesConfig);
            properties.store(out, "");

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static boolean ConfigExists(){
        return propertiesConfig != null;
    }

    public static void createDefaultConfig(){

        if(!ConfigExists()){
            File file = new File(propertiesConfig.getPath());
            try{

                file.mkdirs();

            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    /**

    Deze methode maakt een bestand aan met een programma
    dat ik gemaakt heb. Dit bestand komt te staan op het pad
    van string path + programname. Daarna probeert hij
    het programma te verzenden via FTP naar het ingesteld
    IP adres.

     */

    // x 438.02 y -57.81 z 483.09 q1=0.534746 q2=-0.476851 q3=0.625043 q4=-0.309809 cf1 = 0 cf4 = -1 cf6 = -1 cfx = 0

    public static void generateRobotFiles(){
        // Temporary inputs for the path and name of the program
        String path = "C:\\Users\\robin.peeters.PROVDOM\\Desktop\\";
        String programName = "FirstCommunication";

        // Tool object with all parameters
        // true, -7.24743, -10.0201, 127.232, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0
        Tool tool = new Tool("Tool", true,-7.24743,-10.0201,127.232,1,0,0,0,1,0,0,4,1,0,0,0,0,0,0);

        //<editor-fold desc="All points in program">
        // All points that need to be defined in the program
        List<Points> pointsList = new ArrayList<>();
        // Default for quaternion: 0, 0, 1, 0

        int loop = 0;
        System.out.println(PreMill.getCubikPoints().toString());
        for(Coordinates p : PreMill.getCubikPoints()){
            pointsList.add(new Points("p" + loop, p.getX2(), p.getY2(), p.getZ2(), 1, 0, 0, 0,-1, -1, -1, 0));
            System.out.println("premill point: " + p.getX2() + "," + p.getY2() + "," + p.getZ2());
            loop++;
        }

        List<Triangle> triangles = STL.getStlConverted();
        for(int i = loop; i < triangles.size(); i++){
            Triangle t = triangles.get(i);
            Euler euler = new Euler();
            List<Double> quaternions = Euler.toQuaternions(euler.getResults(Vector.getTriangleVector(t)));
            System.out.println(quaternions.toString() + " Quaternions");
            //pointsList.add(new Points("p" + i, t.getMiddle().getX2() * 100 + 250, t.getMiddle().getY2() * 100 + 20, t.getMiddle().getZ2() * 100 + 10, quaternions.get(0), quaternions.get(1), quaternions.get(2), quaternions.get(3), 0, 0, -1));
            pointsList.add(new Points("p" + i, t.getMiddle().getX2() * 10, t.getMiddle().getY2() * 10, t.getMiddle().getZ2() * 10 - 200, quaternions.get(0), quaternions.get(1), quaternions.get(2), quaternions.get(3), 0, -1, -1, 1));
        }

            //pointsList.add(new Points("p9", 588.79,151.26,625.32,0.506835,0.417602,0.654986,0.37379,0,0,0));

        //</editor-fold>

        //<editor-fold desc="All movements in program">
        // All the movements that have to be made.
        List<Move> moveList = new ArrayList<>();
        for(Points p : pointsList){
            moveList.add(new Move(MoveType.LINEAR, p, 200, 0, tool, "freespl"));
        }

            //moveList.add(new Move(MoveType.LINEAR, pointsList.get(8), 500, 0, tool));

        //</editor-fold>

        Wait wait1s = new Wait(1);

        for(Move m : moveList){
            Move.set(m);
            //Wait.set(wait1s);
        }

            //Wait.set(wait1s);
            //Move.set(moveList.get(8));


        // Create the program files in a folder on the desktop.
        ProgramFile.createProgramFiles(new ProgramFile(moveList, pointsList, tool), programName, path);

        File filesToSend = new File(path + programName);

        Connection.sendFile(new FTPClient(), "192.168.125.1", "anonymous", "", filesToSend);

    }

    /**

    In de main method gaat hij eerst een connectie met de MySQL database
    proberen te maken die thuis bij mij opstaat zodanig dat hij kan
    valideren of de gebruiker mag inloggen met zijn gegevens. Vervolgens
    opent hij de window voor in te loggen.

     */

    public static void main(String[] args) {

        setupMySQLDriver();

        Login.startGUI(args);
    }



}
