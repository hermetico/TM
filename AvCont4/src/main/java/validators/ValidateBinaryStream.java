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
public class ValidateBinaryStream implements IParameterValidator {
    @Override
    public void validate(String name, String value) throws ParameterException{
        if(!value.matches("[01]+")){
            throw new ParameterException(value + " is not binary");

        }
        
    }
}
