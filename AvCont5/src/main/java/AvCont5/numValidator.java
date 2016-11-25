package AvCont5;


import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class numValidator implements IParameterValidator{

    @Override
    public void validate(String name, String value) throws ParameterException {
        int val;
        try{
            val = Integer.parseInt(value);
        }catch(NumberFormatException e){
            throw new ParameterException(name + " is not a valid integer: " + value);
        }
        if (name.equals("-m")){
            if(!IsPowerOfTwo(val)){
                throw new ParameterException(name + " must be power of two: " + value);
            }
        }      
    }
    
    public boolean IsPowerOfTwo(int x){
         return (x != 0) && ((x & (x - 1)) == 0);
     }   
}
