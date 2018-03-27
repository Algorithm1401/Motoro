package provil.be;

import org.apache.commons.net.ftp.FTPClient;
import provil.be.flexobjects.*;
import provil.be.flexobjects.move.Move;
import provil.be.flexobjects.move.MoveType;
import provil.be.flexobjects.statements.CheckIf;
import provil.be.flexobjects.statements.For;
import provil.be.flexobjects.variable.Variable;
import provil.be.flexobjects.variable.VariableType;
import provil.be.functions.Connection;
import provil.be.gui.Login;

import java.io.*;
import java.util.*;

/**
 * Created by robin on 6/11/2017.
 */

public class Main {

    public static File propertiesConfig = new File(System.getProperty("user.home") + "/Desktop" + "/config.properties");

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

    public static void setPropertyValue(Map<String, ?> propertyValues){

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

    /**

    Methode om het programma aan te geven waar
    de properties file zich bevindt.

    @param properties pad naar het properties bestand.

     */

    public static void setConfigPath(File properties){
        propertiesConfig = properties;
        System.out.println(properties.getPath() + " path selected properties file");
    }

    /**

    Deze methode maakt een bestand aan met een programma
    dat ik gemaakt heb. Dit bestand komt te staan op het pad
    van string path + programname. Daarna probeert hij
    het programma te verzenden via FTP naar het ingesteld
    IP adres.

     */

    public static void generateRobotFiles(){
        // Temporary inputs for the path and name of the program
        String path = "C:\\Users\\robin.peeters.PROVDOM\\Desktop\\";
        String programName = "FirstCommunication";

        // Tool object with all parameters
        Tool tool = new Tool("Tool", true,122.5,0,97.5,1,0,0,0,0.2,-5,0,8,1,0,0,0,0,0,0);

        //<editor-fold desc="All points in program">
        // All points that need to be defined in the program
        List<Points> pointsList = new ArrayList<>();
        // Default for quaternion: 0, 0, 1, 0

        // Rectangle Location
        pointsList.add(new Points("p1", 500, 0, 400, 0, 0, 1, 0, 0, 0, -1));
        pointsList.add(new Points("p2", 400, 0, 400, 0, 0, 1, 0, 0, 0, -1));
        pointsList.add(new Points("p3", 400, 100, 400, 0, 0, 1, 0, 0, 0, -1));
        pointsList.add(new Points("p4", 500, 100, 400, 0, 0, 1, 0, 0, 0, -1));

        // Tool Locations
        pointsList.add(new Points("p5", 281.97,-296.06,84.77,0.127824,0.432438,0.891297,-0.0474109,-1,0,0));
        pointsList.add(new Points("p6", 222.66,-291.19,80,0.0646644,0.232213,0.970512,0.00153354,-1,0,-1));

        // Remove tool locations
        pointsList.add(new Points("p7", 410.48,-280.53,106.56,0.133331,0.842672,0.520881,0.0284548,-1,0,0));
        pointsList.add(new Points("p8", 441.99,-353.37,95,0.0419293,0.998075,-0.012311,0.044013,-1,0,1));
        pointsList.add(new Points("p9", 588.79,151.26,625.32,0.506835,0.417602,0.654986,0.37379,0,0,0));

        //</editor-fold>

        //<editor-fold desc="All movements in program">
        // All the movements that have to be made.
        List<Move> moveList = new ArrayList<>();
        //moveList.add(new Move("Linear", pointsList.get(0), 500, 0, tool));
        moveList.add(new Move(MoveType.LINEAR, pointsList.get(0), 500, 0, tool));
        moveList.add(new Move(MoveType.LINEAR, pointsList.get(1), 500, 0, tool));
        moveList.add(new Move(MoveType.LINEAR, pointsList.get(2), 500, 0, tool));
        moveList.add(new Move(MoveType.LINEAR, pointsList.get(3), 500, 0, tool));

        // Taketool movements
        moveList.add(new Move(MoveType.LINEAR, pointsList.get(4), 500, 0, tool));
        moveList.add(new Move(MoveType.LINEAR, pointsList.get(5), 500, 0, tool));

        // Removetool movements
        moveList.add(new Move(MoveType.LINEAR, pointsList.get(6), 500, 0, tool));
        moveList.add(new Move(MoveType.LINEAR, pointsList.get(7), 500, 0, tool));

        moveList.add(new Move(MoveType.LINEAR, pointsList.get(8), 500, 0, tool));

        //</editor-fold>

        Clock clock1 = new Clock("clock1");
        Wait wait1s = new Wait(1);
        Variable speedNum = new Variable(VariableType.NUM, "speedNum", "0");
        Variable totalLoops = new Variable(VariableType.NUM, "totalLoops", "0");
        Variable totalLoopsS = new Variable(VariableType.NUM, "totalLoopsS", "0");
        Variable speedNumS = new Variable(VariableType.NUM, "speedNumS", "0");
        Variable speed = new Variable(VariableType.SPEED, "speed", "[1000,500,5000,1000]");
        Variable time = new Variable(VariableType.NUM, "time", "0");
        Variable timeS = new Variable(VariableType.NUM, "timeS", "0");

        List<String> speedNumOptionValues = Arrays.asList(

                "1", Variable.getEqual(speed, "v500"),
                "2", Variable.getEqual(speed, "v1500"),
                "3", Variable.getEqual(speed, "v2500")

        );

        Variable.define(speedNum);
        Variable.define(totalLoops);
        Variable.define(time);
        Variable.define(speed);
        Variable.define(totalLoopsS);
        Variable.define(speedNumS);
        Variable.define(timeS);

        Panel.erase();
        Panel.requestNum("Hoeveel loops?", totalLoops);
        Panel.erase();
        Panel.requestNum("Snelheid 1, 2 of 3?", speedNum);
        Panel.erase();
        Panel.write("Total loops: " + Variable.NumToString(totalLoops, totalLoopsS));

        CheckIf.setOptions(speedNum, speedNumOptionValues);

        Panel.write("Speed: " + Variable.NumToString(speedNum, speedNumS));

        Clock.start(clock1);

        Move.set(moveList.get(4));
        Wait.set(wait1s);

        Move.set(moveList.get(5));
        Panel.write("Tool attached");
        Wait.set(wait1s);

        For.start("i", "1", "totalLoops");

        // Rectangle movement
        Move.set(moveList.get(0));
        Wait.set(wait1s);

        Move.set(moveList.get(1));
        Wait.set(wait1s);

        Move.set(moveList.get(2));
        Wait.set(wait1s);

        Move.set(moveList.get(3));
        Wait.set(wait1s);

        For.stop();

        Move.set(moveList.get(0));
        Move.set(moveList.get(6));
        Move.set(moveList.get(7));
        Wait.set(wait1s);
        Move.set(moveList.get(8));

        Clock.stop(clock1);
        Variable.setEqual(time, Clock.read(clock1));
        Panel.write("Time elapsed: " + Variable.NumToString(time, timeS) + " seconds");
        Wait.set(new Wait(3));
        Clock.reset(clock1);

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
