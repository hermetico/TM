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
public class FileExists implements IParameterValidator{
    
    @Override
    public void validate(String name, String value) throws ParameterException{
        File f = new File(value); // ojo aqui
        if(!f.exists() || f.isDirectory()){
            throw new ParameterException("File expected");
        }
    }
}
