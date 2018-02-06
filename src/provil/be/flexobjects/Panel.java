package provil.be.flexobjects;

import provil.be.flexobjects.variable.Variable;

public class Panel {

    /**
     *
     * Methode om te interacteren met het paneel van de robot.
     *
     */

    //<editor-fold desc="Panel methods">
    public static void requestNum(String text, Variable variable){
        ProgramFile.programActions.add("TPReadNum " + variable.getName() + ", \"" + text + "\";");
    }

    public static void erase(){
        ProgramFile.programActions.add("TPErase;");
    }

    public static void write(String text){
        ProgramFile.programActions.add("TPWrite \"" + text + "\";");
    }
    //</editor-fold>

}
