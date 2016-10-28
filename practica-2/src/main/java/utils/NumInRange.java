/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;
import java.io.File;

/**
 *
 * @author hermetico
 */
public class NumInRange implements IParameterValidator {

    @Override
    public void validate(String name, String value) throws ParameterException {
       int num = Integer.parseInt(value); //ojo
       if (num < 0 || num > 100){
           throw new ParameterException(name  +  " ha de ser entre 0 y 100");
       }

    }
    
}
