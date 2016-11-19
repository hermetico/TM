/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.validators;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;
import static java.lang.Integer.parseInt;


public class TileValueValidator implements IParameterValidator{
    private int val;
    private static final int MAXTILENUMBER = 100; 
    @Override
    public void validate(String name, String value) throws ParameterException {
        // check if string ends with p ( pixel mode ) and has valid number
        String last = value.substring(value.length() - 1);
        if (last.toUpperCase().equals("P")){
            try{
                val = parseInt(value.substring(0, value.length() - 2));
            }catch(ParameterException e){
                throw new ParameterException(name + " is not a valid integer: " + value);
            }
        // checj if user sets valid integer for tile number
        }else{
            try{
                val = parseInt(value);
                if(val<1 || val > MAXTILENUMBER){
                    throw new ParameterException(name + " valid range is 1-" + MAXTILENUMBER + ": " + value + " not allowed");
                }
            }catch(ParameterException e){
                throw new ParameterException(name + " is not a valid integer: " + value);
            }
        }
            
        
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
