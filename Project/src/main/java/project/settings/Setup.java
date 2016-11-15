/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.settings;

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
    private boolean storing = false;
    
    private String inputFilePath;
    private String outputFilePath;
    
    private int FPS = 0;
    private int binarizationValue = 0;
    private int averagingValue = 0;
    private int nTiles = 0;
    private int seekRange = 0;
    private int GOP = 0;
    private int quality = 0;
    
    public Setup(ArgsParser parser){
        
        checkFilters(parser);
        checkMode(parser);
        checkInputFile(parser.getInputFile());
        
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

    public int getnTiles() {
        return nTiles;
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
    
    
}
