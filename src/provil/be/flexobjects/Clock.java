package provil.be.flexobjects;

public class Clock {

    String name;

    public Clock(String name){
        this.name = name;
    }

    //<editor-fold desc="Clock methods">
    public static void start(Clock clock){
        ProgramFile.programActions.add("ClkStart " + clock.getName() + ";");
    }

    public static void stop(Clock clock){
        ProgramFile.programActions.add("ClkStop " + clock.getName() + ";");
    }

    public static void reset(Clock clock){
        ProgramFile.programActions.add("ClkReset " + clock.getName() + ";");
    }

    public static String read(Clock clock){
        return "ClkRead(" + clock.getName() + ")";
    }
    //</editor-fold>

    public String getName() {
        return name;
    }
}
