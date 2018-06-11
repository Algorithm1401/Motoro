package provil.be.flexobjects;

import provil.be.flexobjects.move.Move;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProgramFile {

    //<editor-fold desc="Defined items">
    public static List<String> programActions = new ArrayList<>();
    public static List<String> initProgram = new ArrayList<>();
    List<Move> move;
    List<Points> pointsList;
    Tool tool;
    //</editor-fold>

    public ProgramFile(List<Move> move, List<Points> pointsList, Tool tool){
        this.move = move;
        this.pointsList = pointsList;
        this.tool = tool;
    }

    /**
     *
     * Methode die effectief alle FlexObjects gaat samennemen en gaat parsen naar een RAPID code bestand en het gaat opslaan
     * op het gedefinieerde pad.
     *
     */

    public static void createProgramFiles(ProgramFile programFile, String programName, String path){

        List<String> toWrite = new ArrayList<>();
        List<String> initProgram2 = new ArrayList<>();
        List<String> endProgram = new ArrayList<>();

        initProgram2.add("MODULE " + programName);

        initProgram2.add(Tool.set(programFile.getTool()));

        // Create all points variables
        for(int i = 0; i<programFile.getPointsList().size(); i++){
            initProgram2.add(Points.set(programFile.getPointsList().get(i)));
        }

        initProgram2.addAll(initProgram);

        initProgram2.add("TASK PERS wobjdata freespl:=[FALSE,TRUE,\"\",[[41.6683,-452.919,55.20221],[0,0,-1,0]],[[0,0,0],[1,0,0,0]]];");

        initProgram2.add("PROC main()");

        endProgram.add("ENDPROC");
        endProgram.add("ENDMODULE");

        toWrite.addAll(initProgram2);
        toWrite.addAll(programActions);
        toWrite.addAll(endProgram);

        try {

            File directory = new File(path + programName);
            directory.mkdirs();

            File mod = new File(path + programName + "//" + programName + ".mod");
            mod.createNewFile();

            File pgf = new File(path + programName + "//" + programName + ".pgf");
            pgf.createNewFile();

            FileWriter fw2 = new FileWriter(pgf);
            BufferedWriter bw2 = new BufferedWriter(fw2);
            bw2.write("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" ?>\n");
            bw2.write("<Program>\n");
            bw2.write("<Module>FirstCommunication.mod</Module>\n");
            bw2.write("</Program>\n");

            bw2.close();

            FileWriter fw = new FileWriter(mod);
            BufferedWriter bw = new BufferedWriter(fw);
            for(String s : toWrite) {
                bw.write(s + "\n");
            }

            bw.close();

        }catch(IOException e){
            e.printStackTrace();
        }

    }

    //<editor-fold desc="All getters for the objects">
    public List<Move> getMove() {
        return move;
    }

    public List<Points> getPointsList() {
        return pointsList;
    }

    public Tool getTool() {
        return tool;
    }
    //</editor-fold>

}
