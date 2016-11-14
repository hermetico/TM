/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import project.input.ArgsParser;
import project.misc.Tracer;
import project.output.Zip;
import project.player.Player;
import project.processor.FilterProcessor;
import project.processor.Processor;
import project.processor.filters.Average;
import project.processor.filters.Binarize;
import project.processor.filters.Filter;
import project.processor.filters.Negative;
import project.settings.Types.FileType;



public class Main {
    Tracer tr;
    
    
    public static void main(String[] args){
        Main main = new Main(args);
    }
    
    public Main(String[] args){
        ArgsParser parser = new ArgsParser();
        JCommander jcomm;
        tr = Tracer.getInstance();
        try{
            jcomm = new JCommander(parser, args);
           }catch(ParameterException e){
               System.out.println(e.getMessage());
               System.err.println();
               System.err.println("Try --help or -h for help");
               System.exit(1);
        }
        // checks if encode and/or decode mode is selected
        parser.checkMode();
        
        Tracer tracer = Tracer.getInstance();
 
        Zip zipper = new Zip();
        int fps = parser.getFps();
        
        
        
        
        //TODO something cleaner
        Filter filter = null;
        Processor pr;
        if(parser.isNegativeFilterEnabled()){
            tr.trace("negative");
            filter = new Negative();
        }else if (parser.isAverageFilterEnabled()){
            tr.trace("average");
            filter = new Average(parser.getAvgValue());
        }else if (parser.isBinarizeFilterEnabled()){
            tr.trace("Binarization");
            filter = new Binarize(parser.getBinValue());
        }
        
        
        // then batch mode or not
        if (!parser.isBatchModeEnabled()){
            Player pl = new Player(fps);
            if(filter == null){
                pr = new Processor(parser.getInputFile(), pl);
            }
            else{
                pr = new FilterProcessor(parser.getInputFile(), filter, pl);
            }
        }else{
            if(filter == null){
                pr = new Processor(parser.getInputFile());
            }
            else{
                pr = new FilterProcessor(parser.getInputFile(), filter);
            }
        }
        
        
        tracer.trace("Starting processor");
        pr.processData();
        
        tracer.trace("Zipping data");
        zipper.zipData(pr.getBuffer(), parser.getOutputFile(), FileType.JPG);

    }
    
}
