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
import project.processor.Processor;
import project.settings.Types.FileType;



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
        
        Tracer tracer = Tracer.getInstance();
        
        //String file = "Cubo.zip";
        
        Zip zipper = new Zip();
        //int fps = 24;
        
        //FilterProcessor pr = new FilterProcessor(parser.getInputFile(), new Negative());
        Processor pr = new Processor(parser.getInputFile(), new Player(parser.getFps()));
        tracer.trace("Starting processor");
        pr.processData();
            
        zipper.zipData(pr.getBuffer(), "Out.zip", FileType.JPG);

    }
    
}