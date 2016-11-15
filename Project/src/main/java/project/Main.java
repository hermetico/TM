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
import project.settings.Setup;
import project.settings.Types.FileType;



public class Main {
    Tracer tr;
    
    
    public static void main(String[] args){
        Main software = new Main(args);
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
        
        // do rest of checks from the parser
        // TODO
        
        // Creates a setup for the app
        Setup setup = new Setup(parser);
        App app = new App(setup);
                
    }
    
}
