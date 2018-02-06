package provil.be.flexobjects.statements;

import provil.be.flexobjects.ProgramFile;

public class For {

    /**
     *
     * For loop object for rapid
     *
     */

    public static void start(String numberStr, String startvalue, String endvalue){
        ProgramFile.programActions.add("FOR " + numberStr + " FROM " + startvalue + " TO " + endvalue + " DO");
    }

    public static void stop(){
        ProgramFile.programActions.add("ENDFOR");
    }

}
