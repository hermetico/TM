/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avcont4;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

/**
 *
 * @author ferran
 */
class ValidateBinaryStream implements IParameterValidator {
    @Override
    public void validate(String name, String value) {
        
        if(!value.matches("[01]+")){
            throw new ParameterException("Not binary");
        }
        
    }
}
