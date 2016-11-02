/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avcont4;

import validators.ValidateBinaryStream;
import validators.ValidateWindows;
import com.beust.jcommander.Parameter;
/**
 *
 * @author ferran
 */
public class ArgParser {
    @Parameter(names="--debug", description = "Enter debug mode")
    private boolean debug = false;    
    @Parameter(names = {"--help","-h"}, description= "Imprime este mensaje de ayuda", help = true)
    public boolean help;
    @Parameter(names = {"--verbose", "-v"}, description = "verbosity level" )
    private int verbose = 1;
    @Parameter(names = {"--inputBinary","-i"}, description = "required binary number argument", required = true, validateWith = ValidateBinaryStream.class)
    private String binaryInput;
    @Parameter(names = {"--inputWindow","-w"}, description = "required input window argument", required = true, validateWith = ValidateWindows.class)
    // length of input Window
    private int Ment = 0;
    @Parameter(names = {"--slideWindow","-s"}, description = "required input slide window argument", required = true, validateWith = ValidateWindows.class)
    //length of Slide window
    private int Mdes = 0;
    
    public boolean getDebug(){
        return debug;
    }
    public int getVerbose(){
        return verbose;
    }
    public String getBinaryInput(){
        return binaryInput;
    }
    public int getInputWindow(){
        return Ment;
    }
    public int getSlideWindow(){
        return Mdes;
    }
}
