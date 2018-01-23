package provil.be.flexobjects.variable;

import provil.be.flexobjects.ProgramFile;

public class Variable {

    //<editor-fold desc="Objects">
    VariableType type;
    String initData;
    String name;
    //</editor-fold>

    public Variable(VariableType type, String name, String initData){
        this.type = type;
        this.name = name;
        this.initData = initData;
    }

    //<editor-fold desc="Variable methods">
    public static void define(Variable variable){
        ProgramFile.initProgram.add("VAR " + variable.getType().toString() + " " + variable.getName() + ":=" + variable.getInitData() + ";");
    }

    public static void setEqual(Variable variable, String value){
        ProgramFile.programActions.add(variable.getName() + " := " + value + ";");
    }

    public static String getEqual(Variable variable, String value){
        return variable.getName() + " := " + value + ";";
    }

    public static String NumToString(Variable numVar, Variable stringVar){
         return ("\" + NumToStr(" + numVar.getName() + "," + stringVar.getName() + ") + \"");
    }
    //</editor-fold>

    //<editor-fold desc="All getters">
    public VariableType getType() {
        return type;
    }

    public String getInitData() {
        return initData;
    }

    public String getName() {
        return name;
    }
    //</editor-fold>

}
