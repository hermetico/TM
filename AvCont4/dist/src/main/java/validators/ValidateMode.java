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
public class ValidateMode implements IParameterValidator {
    @Override
    public void validate(String name, String value) throws ParameterException {
        
        // check if string has only one character and if character is 'c' (compress) or 'd' (decompress)
        if ((value.length()!=1)||((value.charAt(0)!= 'c')&&(value.charAt(0)!= 'd'))){
            throw new ParameterException(value + " valid options for mode: c, d");         
        }
    }
    
}
