package provil.be.flexobjects.variable;

public enum VariableType {

    NUM("num"),
    STRING("string"),
    SPEED("speeddata");

    String type;

    VariableType(String type){
    this.type = type;
    }

    public String toString() {
        return type;
    }
}
