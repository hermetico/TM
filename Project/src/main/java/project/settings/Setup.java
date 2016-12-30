/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.settings;

import com.beust.jcommander.ParameterException;
import static java.lang.Integer.parseInt;
import java.util.List;
import project.input.ArgsParser;
import project.settings.Types.FileType;
import project.settings.Types.FilterType;

public class Setup {
    
    private FilterType filter = FilterType.NONE;
    private FileType inputContainer = null;
    private FileType inputFile = null;
    private FileType outputFile = FileType.JPG; // CHECK
    private FileType outputContainer = FileType.ZIP; // CHECK
    
    private boolean batchMode = false;
    private boolean encoding = false;
    private boolean decoding = false;
    private boolean storing = true;
    
    private String inputFilePath;
    private String outputFilePath = "out";
    private String type;
    
    private int FPS = 0;
    private int binarizationValue = 0;
    private int averagingValue = 0;
    private int seekRange = 0;
    private int GOP = 0;
    private int quality = 0;  
    private int nTilesX = 0;
    private int nTilesY = 0;
    private int nPixelsPerTileX = 0;
    private int nPixelsPerTileY = 0;
    // frame image width and height
    private int width;  
    private int height;
    
    public Setup(ArgsParser parser){
        
        checkFilters(parser);
        checkMode(parser);
        checkInputFile(parser.getInputFile());
        
        //check and set tile values
        if (parser.isEncodeEnabled()){ 
            setTileValues(parser);
        }
        
        if(parser.getOutputFile() != null){
            checkOutputFile(parser.getOutputFile());
        }
        
    }
    
    private void checkFilters(ArgsParser parser){
        
        if(parser.isAverageFilterEnabled()){
            
            filter = FilterType.AVERAGE;
            averagingValue = parser.getAvgValue();
            
        }else if(parser.isBinarizeFilterEnabled()){
            
            filter = FilterType.BINARIZATION;
            binarizationValue = parser.getBinValue();
            
        }else if(parser.isNegativeFilterEnabled()){
            
            filter = FilterType.NEGATIVE;
            
        }else if(parser.isConvolutionalFilterEnabled()){
            
            filter = FilterType.CONVOLUTIONAL;
            type = parser.getConvolutionalFilterType();
        }
    }
    
    private void checkMode(ArgsParser parser){
        if(!(batchMode = parser.isBatchModeEnabled())){
            FPS = parser.getFps();
        }
    }
    
    private void checkOutputFile(String name){
        outputFilePath = name;
        if(FileType.isAcceptedFile(outputFilePath)){
            outputContainer = FileType.getFromFileName(outputFilePath);
        }
        
    }
    
    private void checkInputFile(String name){
        inputFilePath = name;
        if(FileType.isAcceptedFile(inputFilePath)){
            inputContainer = FileType.getFromFileName(inputFilePath);
        }
    }
    private void checkEncoding(ArgsParser parser){
        //TODO
    }
    
    private void checkDecoding(ArgsParser parser){
        //TODO
    }
    
    // get x and y tiles, or pixels per tile, and set local values
    private void setTileValues(ArgsParser parser){

        String first;
        String last;
        int temp1;
        int temp2;
        List<String> tiles = parser.getTiles();
        
        // if we have x and y number of tiles try to parse
        try{
            nTilesX= parseInt(tiles.get(0));
            nTilesY= parseInt(tiles.get(1));
            
         }catch(NumberFormatException e){
             
            //Parse was not possible. try to parse x and y pixels by tile. <num>px and <num>py
            // p.e. 12px and 12py
            //get last two characters of inpùt string ( px or py )
            first = tiles.get(0).substring(tiles.get(0).length() - 2);
            last = tiles.get(1).substring(tiles.get(1).length() - 2);
            
            try{                 
                 
                temp1 = parseInt(tiles.get(0).substring(0, tiles.get(0).length() - 2));
                temp2 = parseInt(tiles.get(1).substring(0, tiles.get(1).length() - 2));

                // input order not matter so <num>py could be at position 1 and <num>px at possition 1
                if (first.toUpperCase().equals("PX")&&last.toUpperCase().equals("PY")){

                    nPixelsPerTileX = temp1;
                    nPixelsPerTileY = temp2;

                }else if (first.toUpperCase().equals("PY")&&last.toUpperCase().equals("PX")){
                     
                     nPixelsPerTileX = temp2;
                     nPixelsPerTileY = temp1;
                     
                 }else{
                     // last two characters are not px and py!!
                     throw new ParameterException("Wrong value: " + tiles.get(0) +", " + tiles.get(1));
                 }   
             }catch(NumberFormatException e1){
                 // input value without two last charactesrs are not integer!!
                 throw new ParameterException("Wrong value: " + tiles.get(0) +", " + tiles.get(1));
             }
         }

    }

    public FilterType getFilter() {
        return filter;
    }

    public FileType getInputContainer() {
        return inputContainer;
    }

    public FileType getInputFile() {
        return inputFile;
    }

    public FileType getOutputFile() {
        return outputFile;
    }

    public FileType getOutputContainer() {
        return outputContainer;
    }

    public boolean isBatchMode() {
        return batchMode;
    }

    public boolean isEncoding() {
        return encoding;
    }

    public boolean isDecoding() {
        return decoding;
    }

    public boolean isStoring() {
        return storing;
    }

    public String getInputFilePath() {
        return inputFilePath;
    }

    public String getOutputFilePath() {
        return outputFilePath;
    }

    public int getFPS() {
        return FPS;
    }

    public int getBinarizationValue() {
        return binarizationValue;
    }

    public int getAveragingValue() {
        return averagingValue;
    }

    public int getSeekRange() {
        return seekRange;
    }

    public int getGOP() {
        return GOP;
    }

    public int getQuality() {
        return quality;
    }
    public String getConvolutionalFilterType(){
        return type;
    }
    
    public int getXTiles() {
        return nTilesX;
    }
    public int getYTiles() {
        return nTilesY;
    }
    public int getXPixelsPerTile() {
        return nPixelsPerTileX;
    }
    public int getYPixelsPerTile() {
        return nPixelsPerTileY;
    }
}
