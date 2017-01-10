/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.input;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;
import project.validators.ConvolutionalFilterTypeValidator;
import project.validators.LevelValidator;
import project.validators.NumericValidator;



public class ArgsParser {
    
    @Parameter(names = {"--input", "-i"}, description = "input File <path to file>", required = true)
    private String inputFile;
    
    @Parameter(names = {"--output", "-o"}, description = "output File <path to output file>")
    private String outputFile;
    
    @Parameter(names = {"--encode", "-e"}, description = "Reads image sequence from input File, apply selected filters, convert to jpeg and play sequence")
    private boolean encode = false ;
    
    @Parameter(names = {"--decode", "-d"}, description = "decode image sequence from input coded File, play decoded sequence")
    private boolean decode = false ;
    
    @Parameter(names = {"--fps"}, description = "selects frame rate speed", validateWith = NumericValidator.class)
    private int fps = 24;
    
    @Parameter(names = {"--binarization"}, description = "binarization filter treshold value", validateWith = NumericValidator.class)
    private int binValue = 0;
    
    @Parameter(names = {"--negative"}, description = "Apply negative filter")
    private boolean negative = false;
    
    @Parameter(names = {"--averaging"}, description = "Apply average filter", validateWith = NumericValidator.class)
    private int avgValue;
    
    @Parameter(names = "--nTiles", arity = 2, description = "Number of tiles: nTiles <Tiles x> <Tiles y> or pixels per tile: <num>px <num>py")
    private List<String> numTiles = new ArrayList(Arrays.asList("16px", "16py"));
     
    @Parameter(names = {"--seekRange"}, description = "max displacement in matching tile search", validateWith = NumericValidator.class)
    private int seekRange = 8;
    
    @Parameter(names = {"--fastSearch"}, description = "If tile that match our quality criteria is found search it stopped .")
    private boolean fastSearch = false;
    
    @Parameter(names = {"--fullSearch"}, description = "Default full search mode")
    private boolean fullSearch = false;
    
    @Parameter(names = {"--pixelSearch"}, description = "Search of best matching tile pixel by pixel, normal mode is tile by tile")
    private boolean pixelSearch = false;
    
    @Parameter(names = {"--tileSearch"}, description = "Default tile search mode")
    private boolean tileSearch = false;
    
    @Parameter(names = {"--GOP"}, description = "number of frames between two adjacent reference images", validateWith = NumericValidator.class)
    private int GOP = 5;
    
    @Parameter(names = {"--quality"}, description = "quality factor determine when two tiles match, Quality levels : 1, 2, 3, 4, 5, 6, 7 ( the higher the best, def: 5 ) ", validateWith= LevelValidator.class)
    private String quality = "5" ;
    
    @Parameter(names = {"--batch", "-b"}, description = "batch mode")
    private boolean batch = false ;
    
    @Parameter(names = {"--loop"}, description = "Loop mode for player")
    private boolean loop = false ;
    
    @Parameter(names = {"--conv"}, 
            description = "Apply convolutional filter, valid tipes: prewitt_x, prewitt_y, sobel_x, sobel_y, laplacian, unsharp, sharpen, blur, emboss",
            validateWith = ConvolutionalFilterTypeValidator.class)
    private String type;
    
    @Parameter(names = {"--help","-h"}, description = "help", help = true)
    public boolean help;
    
    @Parameter(names = {"--ssd"}, description = "Compute tile differencees using mean sum of square differences method. Default method: MAD (mean absolute differences)")
    private boolean ssd = false ;
    
    @Parameter(names = {"--mad"}, description = "Default mean absolute differences")
    private boolean mad = false ;
    
    @Parameter(names = {"--verbosity"}, description = "Use it to retrieve more info from application and compression process")
    private boolean verbosity  = false ;
    
    @Parameter(names = {"--realistic"}, description = "Realisitc encoding encodes a frame based on the previous decoded frame instead of the original one")
    private boolean realistic  = false ;
    
    @Parameter(names = {"--regular"}, description = "Default encoding, encodes a frame based on the original previous one")
    private boolean regular  = false ;
    
    // parsed tile values set local variables
    private int nTilesX;
    private int nTilesY;
    private int nPixelsPerTileX;
    private int nPixelsPerTileY;
    
    
    // return tiles list ( two strings )
    public List<String> getTiles(){ return numTiles; }
    
    public boolean isEncodeEnabled(){ return encode; }
    
    public String getInputFile(){ return inputFile; }
    
    public String getOutputFile(){
        return outputFile;
    }
    
    public int getFps(){ return fps; }
    // treshold value to binarize
    public int getBinValue(){ return binValue; }
    
    public boolean isBinarizeFilterEnabled(){
        boolean enabled;
        if (binValue!=0){ enabled = true; }
        else{ enabled = false; }
        return enabled;
    }
    public boolean isNegativeFilterEnabled(){ return negative; }
    
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
    public boolean isPixelSearchEnabled(){ return pixelSearch; }
    
    public boolean isFastSearchEnabled(){ return fastSearch; }
    
    // Average value computed
    public int getAvgValue(){ return avgValue; }
    
    public int getSeekRange(){ return seekRange; }
    
    public int getGOP(){ return GOP; }
    
    public int getQuality(){ return Integer.parseInt(quality); }
    
    public boolean isBatchModeEnabled(){ return batch; }
    
    public boolean isConvolutionalFilterEnabled(){
        boolean enabled = false;
        if(type!=null){
            enabled = true;
        }
        return enabled;
    }
    public String getConvolutionalFilterType(){ return type; }

    public boolean isLoop() { return loop; }

    public boolean isDecodeEnabled() { return decode; }
    
    public boolean isSSDEnabled() { return ssd; }
    
    public boolean isVerbosityEnabled() { return verbosity; }
    
    public boolean isRealisitcEnabled(){return realistic;}
}
