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
public class numericValidator implements IParameterValidator{
    
    @Override
    public void validate(String name, String value) throws ParameterException {
        //check if number
        int val;
       
        try{
            val = Integer.parseInt(value);
        }catch(NumberFormatException e){
            throw new ParameterException(name + " is not a valid integer: " + value);
        }
        
        
        // check valid range
        if(name.equals("fps")){         
            if (val<2 || val>200){
                throw new ParameterException(name + " valid range is 1-200: " + value + "not allowed");
            }
        }else if(name.equals("binValue")){
            if (val<1 || val>254){
                throw new ParameterException(name + " valid range is 1-254: " + value + "not allowed");
            }
        }else if (name.equals("avgValue")){
            if (val<1 || val>254){
                throw new ParameterException(name + " valid range is 1-254: " + value + "not allowed");
            }
        }else if(name.equals("seekRange")){
            if (val<0 ){
                throw new ParameterException(name + " must be greater than 0: " + value + "not allowed");
            }
        }else if (name.equals("GOP")){
            if (val<1 || val> 100 ){
                throw new ParameterException(name + " valid range is 1-100: " + value + "not allowed");
            }
        }else if (name.equals("quality")){
            if (val<1 || val> 10 ){
                throw new ParameterException(name + " valid range is 1-10: " + value + "not allowed");
            }
        }  
        
                
    }
    
}
