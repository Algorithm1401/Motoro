package provil.be.flexobjects;

public class Move {

    //<editor-fold desc="Defined items">
    String moveType;
    Points point;
    Tool tool;

    int speed;
    int anglecut;
    //</editor-fold>

    public Move(String moveType, Points point, int speed, int anglecut, Tool tool){
        this.moveType = moveType;
        this.point = point;
        this.speed = speed;
        this.anglecut = anglecut;
        this.tool = tool;
    }

    public static String toString(Move move){
        String moveString;
        if(move.getMoveType().equals("Linear")){
        moveString = "MoveJ " + move.getPoint().getPointName() + ", v" + move.getSpeed() + ", fine," + move.getTool().getToolName() + ";";
        }else{
            moveString = "MoveJ " + move.getPoint().getPointName() + ", v" + move.getSpeed() + ", z" + move.getAnglecut() + "," + move.getTool().getToolName() + ";";
        }
        return moveString;
    }

    //<editor-fold desc="All getters for the objects">
    public String getMoveType() {
        return moveType;
    }

    public Points getPoint() {
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
