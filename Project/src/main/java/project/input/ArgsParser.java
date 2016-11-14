/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.input;

import com.beust.jcommander.Parameter;
import project.validators.numericValidator;

public class ArgsParser {
    
    @Parameter(names = {"--input", "-i"}, description = "input File <path to file.zip>", required = true)
    private String inputFile;
    
    @Parameter(names = {"--output", "-o"}, description = "output File <path to output file>")
    private String outputFile;
    
    @Parameter(names = {"--encode", "-e"}, description = "Reads image sequence from input File, apply selected filters, convert to jpeg and play sequence")
    private boolean encode = false ;
    
    @Parameter(names = {"--decode", "-d"}, description = "decode image sequence from input coded File, play decoded sequence")
    private boolean decode = false ;
    
    @Parameter(names = {"--fps"}, description = "selects frame rate speed", validateWith = numericValidator.class)
    private int fps = 24;
    
    @Parameter(names = {"--binarization"}, description = "binarization filter treshold value", validateWith = numericValidator.class)
    private int binValue;
    
    @Parameter(names = {"--negative"}, description = "Apply negative filter")
    private boolean negative = false;
    
    @Parameter(names = {"--averaging"}, description = "Apply average filter", validateWith = numericValidator.class)
    private int avgValue;
    
    @Parameter(names = {"--Tiles"}, description = " ")
    private int numTiles;
     
    @Parameter(names = {"--seekRange"}, description = "max displacement in matching tile search", validateWith = numericValidator.class)
    private int seekRange = 0;
    
    @Parameter(names = {"--GOP"}, description = "number of frames between two adjacent reference images", validateWith = numericValidator.class)
    private int GOP = 0;
    
    @Parameter(names = {"--quality"}, description = "quality factor determine when two tiles match ")
    private int quality = 0;
    
    @Parameter(names = {"--batch", "-b"}, description = "batch mode")
    private boolean batch = false ;
    
    private String getMode(){
        String mode;
        if(encode){mode = "encode";}
        else if(decode){mode = "decode";}
        else{mode = null;}
        return mode;
    }
    
    public String getInputFile(){
        return inputFile;
    }
    public String getOutputFile(){
        return outputFile;
    }
    
    public int getFps(){
        return fps;
    }
    public int getBinValue(){
        return binValue;
    }
    public boolean isNegativeFilterEnabled(){
        return negative;
    }
    public boolean isAverageFilterEnabled(){
        boolean enabled;
        if (avgValue!=0){
            enabled = true; 
        }
        else{ 
            enabled = false;
        }
        return enabled;
    }
    // returns average filter value;
    public int getAvgValue(){
        return avgValue;
    }
    public int getSeekRange(){
        return seekRange;
    }
    public int getGOP(){
        return GOP;
    }
    public int getQuality(){
        return quality;
    }
    public boolean isBatchModeEnabled(){
        return batch;
    }
}