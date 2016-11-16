/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.validators;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author ferran
 */
public class ConvolutionalFilterTypeValidator implements IParameterValidator{
    List<String> validFilters = Arrays.asList("prewitt_x", "prewitt_y", "sobel_x", "sobel_y", "laplacian", "unsharp", "sharpen", "blur", "emboss");
    
    @Override
    public void validate(String name, String value) throws ParameterException {         
         if (!validFilters.contains(value)){
                throw new ParameterException(" Not allowed convolutional filter option: " + value);
            }
        
    }
    
}
