package provil.be.flexobjects;

public class Wait {

    double time;

    public Wait(double time){
        this.time = time;
    }

    public static void set(Wait wait){
        ProgramFile.programActions.add(new String("WaitTime " + String.valueOf(wait.getTime()) + ";"));
    }

    public double getTime() {
        return time;
    }
}
