package provil.be.flexobjects.move;

import provil.be.flexobjects.Points;
import provil.be.flexobjects.ProgramFile;
import provil.be.flexobjects.Tool;

import java.util.ArrayList;
import java.util.List;

public class Move {

    //<editor-fold desc="Defined items">
    MoveType moveType;
    List<Points> point = new ArrayList<>();
    Tool tool;

    int speed;
    int anglecut;
    //</editor-fold>

    //<editor-fold desc="Movement constructors">
    public Move(MoveType moveType, Points point, int speed, int anglecut, Tool tool){
        this.moveType = moveType;
        this.point.add(point);
        this.speed = speed;
        this.anglecut = anglecut;
        this.tool = tool;
    }

    public Move(MoveType moveType, List<Points> point, int speed, int anglecut, Tool tool){
        this.moveType = moveType;
        this.point = point;
        this.speed = speed;
        this.anglecut = anglecut;
        this.tool = tool;
    }
    //</editor-fold>

    /**
     * Methode om de huidige move type aan te geven.
     */

    public static void set(Move move){
        String moveString = new String();
        if(move.getMoveType().equals(MoveType.LINEAR)){
        moveString = "MoveJ " + move.getPoint().get(0).getPointName() + ", v" + move.getSpeed() + ", fine," + move.getTool().getToolName() + ";";
        }else if(move.getMoveType().equals(MoveType.CIRCULAR)){
        moveString = "MoveC " + move.getPoint().get(0).getPointName() + "," + move.getPoint().get(1).getPointName() + ",z" + move.getAnglecut() + "," + move.getTool().getToolName() + ";";
        }else{
            moveString = "MoveJ " + move.getPoint().get(0).getPointName() + ", v" + move.getSpeed() + ", z" + move.getAnglecut() + "," + move.getTool().getToolName() + ";";
        }
        System.out.println(moveString);
        ProgramFile.programActions.add(moveString);
    }

    //<editor-fold desc="All getters for the objects">
    public MoveType getMoveType() {
        return moveType;
    }

    public List<Points> getPoint() {
        return point;
    }

    public Tool getTool() {
        return tool;
    }

    public int getAnglecut() {
        return anglecut;
    }

    public int getSpeed() {
        return speed;
    }
    //</editor-fold>

}
