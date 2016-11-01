/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avcont4;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;


/**
 *
 * @author ferran
 */
public class Tester {
    
    public static void main(String[] args) {
        
        ArgParser parser = new ArgParser();
        JCommander jcomm;
        try{
            jcomm = new JCommander(parser, args);
            jcomm.setProgramName("AvCont4.jar");
            if (parser.help){ 
                jcomm.usage();
            }
            // check slide and input window size are rigth
            if(parser.getInputWindow() >= parser.getSlideWindow()){
                throw new ParameterException("Slide window must be greater than Input Window");
            }
            if (parser.getInputWindow() + parser.getSlideWindow() > parser.getBinaryInput().length()){
                throw new ParameterException ("sum of Input window and Slide window must be lower than input binary length");
            }
            
            //System.out.println("Debug Mode: " + parser.getDebug());
            //System.out.println("Help Mode: " + parser.help);
            
        }catch(ParameterException e){
            System.out.println(e.getMessage());
            System.err.println(" Try --help or -h for help");
        }
        
        
        
        
    }
    
}
