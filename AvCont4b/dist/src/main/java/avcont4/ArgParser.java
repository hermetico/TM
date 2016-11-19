/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avcont4;

import validators.ValidateBinaryStream;
import validators.ValidateWindows;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import validators.ValidateMode;
/**
 *
 * @author ferran
 */
public class ArgParser {
    
    @Parameter(names={"--test", "-t"}, description = "Enter test mode")
    private int test = 0;
    @Parameter(names = {"--help","-h"}, description= "Imprime este mensaje de ayuda", help = true)
    public boolean help;
    @Parameter(names = {"--mode"}, description = "Compression mode, c: compress(default), d: decompress", validateWith = ValidateMode.class)
    public String mode = "c";
    @Parameter(names = {"--inputBinary","-i"}, description = "required binary number argument", required = true, validateWith = ValidateBinaryStream.class)
    private String binaryInput;
    @Parameter(names = {"--inputWindow","-w"}, description = "required input window argument", required = true, validateWith = ValidateWindows.class)
    // length of input Window
    private int Ment = 0;
    @Parameter(names = {"--slideWindow","-s"}, description = "required input slide window argument", required = true, validateWith = ValidateWindows.class)
    //length of Slide window
    private int Mdes = 0;
    
   
    public int getTest(){
        return test;
    }
    public String getBinaryInput(){
        return binaryInput;
    }
    public void setBinaryInput(String number){
        binaryInput = number;
    }
    public void setInputWindow(int number){
        Ment = number;
    }
    public void setSlideWindow(int number){
        Mdes = number;
    }
    
    public int getInputWindow(){
        return Ment;
    }
    public int getSlideWindow(){
        return Mdes;
    }
    public String getMode(){
        return mode;
    }
    public void checkRelatedParameters(){
        // check slide and input window size are rigth
        if(this.getInputWindow() >= this.getSlideWindow()){
            System.out.println();
            throw new ParameterException("Slide window must be greater than Input Window");
        }
        if (this.getInputWindow() + this.getSlideWindow() > this.getBinaryInput().length()){
            throw new ParameterException ("sum of Input window and Slide window must be lower than input binary length");
        }
    }
}
