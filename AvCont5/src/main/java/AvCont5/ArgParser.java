package AvCont5;


import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class ArgParser {
    @Parameter(names= "-m", description = "Enter m value", validateWith=numValidator.class, required = true)
    public int m;
    @Parameter(names= "-n", description = "n range value", arity=2, validateWith=numValidator.class, required = true)
    public List<Integer> n;
    @Parameter(names = {"--help","-h"}, description= "Imprime este mensaje de ayuda", help = true)
    public boolean help;
    
    public void checkRange(){
        // check slide and input window size are rigth
        if(n.get(0) >= n.get(1)){
            System.out.println();
            throw new ParameterException("Range error," + n.get(0) + " must be lower than "+ n.get(1));
        }
    }
}
