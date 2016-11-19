/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validators;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

/**
 *
 * @author ferran
 */
public class ValidateWindows implements IParameterValidator{
    
    @Override
    public void validate(String name, String value) throws ParameterException {
        // check if number 
        int val;
        try{
            val = Integer.parseInt(value);
        }catch(NumberFormatException e){
            throw new ParameterException(name + " is not a valid integer: " + value);
 
        }
        // check if power of two
        if(!IsPowerOfTwo(val)){
            throw new ParameterException(name + " must be power of two: " + value);
        }
    }
     public boolean IsPowerOfTwo(int x){
         return (x != 0) && ((x & (x - 1)) == 0);
     }   
     
}
