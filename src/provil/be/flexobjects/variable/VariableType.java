package provil.be.flexobjects.variable;

public enum VariableType {

    /**
     *
     * Rapid code variables omgezet naar java en vice versa.
     *
     */

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
