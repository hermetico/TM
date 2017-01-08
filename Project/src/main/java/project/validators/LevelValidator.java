/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.validators;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

/**
 *
 * @author ferran
 */
public class LevelValidator implements IParameterValidator{
    

    @Override
    public void validate(String name, String value) throws ParameterException {
        
        //check if number
        int val;
        try{
            val = Integer.parseInt(value);
        }catch(NumberFormatException e){
            throw new ParameterException(name + " Is not a valid integer: " + value);
        }
        
        
        if(name.equals("--quality")){
            if(!value.matches("1|2|3|4|5|6|7|8")){
                throw new ParameterException(name + " value must be 1,2,3,4,5,6 or 7: " + value + "not allowed");
            }
        }
        
    }
    
    
}
