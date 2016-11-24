
import com.beust.jcommander.Parameter;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class ArgParser {
    @Parameter(names= "-m", description = "Enter m value", validateWith=numValidator.class)
    public int m;
    @Parameter(names= "-n", description = "n range value", arity=2, validateWith=numValidator.class)
    public List<String> n;
    
}
