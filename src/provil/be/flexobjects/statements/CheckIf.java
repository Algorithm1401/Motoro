package provil.be.flexobjects.statements;

import provil.be.flexobjects.ProgramFile;
import provil.be.flexobjects.variable.Variable;

import java.util.ArrayList;
import java.util.List;

public class CheckIf {

    public static void setOptions(Variable variable, List<String> optionValues){

        List<String> ifStrings = new ArrayList<>();

        for(int i = 0; i<optionValues.size(); i += 2){

            if(i!=0){

                ifStrings.add("ELSEIF " + variable.getName() + " = " + optionValues.get(i) + " THEN");
                ifStrings.add(optionValues.get(i + 1));

            }else{

                ifStrings.add("IF " + variable.getName() + " = " + optionValues.get(i) + " THEN");
                ifStrings.add(optionValues.get(i + 1));

            }

        }

        ifStrings.add("ENDIF");

        ProgramFile.programActions.addAll(ifStrings);
    }

}
