/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.project;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.tm.project.input.ArgsParser;
import com.tm.project.misc.Tracer;
import com.tm.project.output.Zip;
import com.tm.project.player.Player;
import com.tm.project.processor.Buffer;
import com.tm.project.processor.FilterProcessor;
import com.tm.project.processor.filters.Average;
import com.tm.project.processor.filters.Binarize;
import com.tm.project.processor.Processor;
import com.tm.project.processor.filters.Negative;
import com.tm.project.settings.Types.FileType;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {

    
    
    public static void main(String[] args){
        Main main = new Main(args);
    }
    
    public Main(String[] args){
        ArgsParser parser = new ArgsParser();
        JCommander jcomm;
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
        
        /*
        FilterProcessor pr;
        
        if(parser.isNegativeFilterEnabled()){
            System.out.println("negative");
           pr = new FilterProcessor(parser.getInputFile(), new Negative());
        }else if (parser.isAverageFilterEnabled()){
            System.out.println("average");
           pr = new FilterProcessor(parser.getInputFile(), new Average(parser.getAvgValue()));
        }else if (parser.isBinarizeFilterEnabled()){
            System.out.println("Binarization");
           pr = new FilterProcessor(parser.getInputFile(), new Binarize(parser.getBinValue()));
        }else{
           pr = new FilterProcessor(parser.getInputFile(), new Negative());
        }
        */
        
        Processor pr = new Processor(parser.getInputFile(), new Player(parser.getFps()));
        tracer.trace("Starting processor");
        pr.processData();
            
        zipper.zipData(pr.getBuffer(), "Out.zip", FileType.JPG);

    }
    
}
