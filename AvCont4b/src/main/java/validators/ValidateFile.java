/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validators;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;
import java.io.File;

public class ValidateFile implements IParameterValidator {
    @Override
    public void validate(String name, String value) throws ParameterException{
        File f = new File(value);
        if(!f.exists() && f.isDirectory()) { 
            throw new ParameterException(value + " not found");
        }
        
    }
}
