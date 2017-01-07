/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.settings;

import com.beust.jcommander.ParameterException;
import java.awt.image.BufferedImage;
import static java.lang.Integer.parseInt;
import java.util.List;
import java.util.zip.ZipEntry;

import project.input.ArgsParser;
import project.input.Unzip;
import project.input.entries.Entry;
import project.misc.Tracer;
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
    // number of tiles of padded image, x -> width  y -> height
    private int nTilesX = 0;
    private int nTilesY = 0;
    // pixels per tile 
    private int nPixelsPerTileX = 0;
    private int nPixelsPerTileY = 0;
    
    // frame image width and height
    private int width = 0;  
    private int height = 0;
    // padded frame image dimensions
    private int padded_width = 0;
    private int padded_height = 0;
    // pad needed to get padded image from original one
    private int pad_left = 0;
    private int pad_rigth = 0;
    private int pad_top = 0;
    private int pad_bottom = 0;
    
    private Configuration cfg = Configuration.getInstance();
    private Tracer tr = Tracer.getInstance();
    
    public Setup(ArgsParser parser){
        
        checkFilters(parser);
        checkMode(parser);
        checkEncoding(parser);
        checkDecoding(parser);
        checkInputFile(parser.getInputFile());
        
        //check and set tile values
        if (parser.isEncodeEnabled()){ 
            setDimensions();
            getTileValues(parser);           
        }     
        if(parser.getOutputFile() != null){
            checkOutputFile(parser.getOutputFile());
        }
        
        this.GOP = parser.getGOP();
        this.seekRange = parser.getSeekRange();
        this.quality = parser.getQuality();
        setupLoopMode(parser.isLoop());
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
        this.encoding = parser.isEncodeEnabled();
    }
    
    private void checkDecoding(ArgsParser parser){
        this.decoding = parser.isDecodeEnabled();
    }
    
    // Read zip file, load images and get width and height from first image
    private void setDimensions(){
        Unzip zp = new Unzip(inputFilePath);

        BufferedImage img = zp.unzipImageEntry(zp.getEntries().get(0).getContent());
        width = img.getWidth();
        height = img.getHeight();
    }
    // Get x and y tiles, or pixels per tile
    private void getTileValues(ArgsParser parser){

        String first;
        String last;
        int temp1;
        int temp2;
        List<String> tiles = parser.getTiles();
        
        // if we have x and y number of tiles try to parse
        try{
            
            nTilesX = parseInt(tiles.get(0));
            nTilesY = parseInt(tiles.get(1));
            // check if values in range
            checkRange(nTilesX, 1, width);
            checkRange(nTilesY, 1, height);
            // image width and heigth must be exact multiple of tile number so we need to pad original image
            setPadFrame(nTilesX, nTilesY);
            // now we have number of tiles in x or y coord so we can set how many pixels define a tile
            setPixelsPerTile(nTilesX, nTilesY);
        }catch(NumberFormatException e){           
           //Parse was not possible. try to parse x and y pixels by tile. <num>px and <num>py
           

           try{                 
               // get substring without two last characters (px or py) and parse
               temp1 = parseInt(tiles.get(0).substring(0, tiles.get(0).length() - 2));
               temp2 = parseInt(tiles.get(1).substring(0, tiles.get(1).length() - 2));
               
               //get last two characters of input string ( px or py )
               first = tiles.get(0).substring(tiles.get(0).length() - 2);
               last = tiles.get(1).substring(tiles.get(1).length() - 2);
               
               // <num>px,<num>py may have been entered in reverse order, must check this
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
           // check if values in range 
           checkRange(nPixelsPerTileX, 1, width);
           checkRange(nPixelsPerTileY, 1, height);
           setPadFrame(nPixelsPerTileX, nPixelsPerTileY);
           // we know how many pixels has a tile so we can compute total number of tiles
           setNumberOfTiles(nPixelsPerTileX, nPixelsPerTileY);
        }catch(NullPointerException e){
            throw new ParameterException("You are encoding and we need to know the --nTiles paremeter, check it out!");
        }
        
        
    }
    
    //define Number of tiles just from tile width and height ( and padded image dimensions )
    private void setNumberOfTiles(int x, int y){  
        nTilesX = x/padded_width;
        nTilesY = y/padded_height;     
    }
    
    // define pixels per tile just from tile number
    private void setPixelsPerTile(int x, int y){
       nPixelsPerTileX = padded_width/x;
       nPixelsPerTileY = padded_height/y;
    }    
        
    private void checkRange(int value, int low, int high){
        if (value < low || value > high) {
                throw new ParameterException("Wrong value: " + value + "(Valid range:" + low + "-" + high + ")");
            }   
    }
    
    private void setPadFrame(int x, int y){
        // compute multiple of input nearest to width or height
        padded_width = (width/x + 1) * x;
        padded_height = (height/y + 1) * y;
        // left, rigth, top and bottom are the pixels we need 
        // to add to the original image.  
        pad_left = (padded_width - width)/2;
        pad_rigth = (padded_width - width) - pad_left;
        pad_top = (padded_height - height)/2;
        pad_bottom = (padded_height - height) - pad_top;     
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
    /*
    No estan devolviendo el valor esperado, cuando inserto px 
    la funcion que lo devuelve correctamente es getXTiles
    
    Las sobreescribo temporalmente
    public int getXPixelsPerTile() {
        return nPixelsPerTileX;
    }
    public int getYPixelsPerTile() {
        return nPixelsPerTileY;
    }
    */
    //TODO sustituir por las de arriba cuando funcionen
    public int getXPixelsPerTile() {
        return nTilesX;
    }
    public int getYPixelsPerTile() {
        return nTilesY;
    }
    
    
    private void setupLoopMode(boolean loop){
        cfg.LOOP_PLAY = loop;
        cfg.EXIT_ON_CLOSE_WINDOW = loop;
        if (loop) tr.trace("Loop mode enabled");
    }
}
