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
import com.tm.project.processor.filters.Negative;
import com.tm.project.settings.Types.Type;
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
        
        Tracer tracer = Tracer.getInstance();
        
        //String file = "Cubo.zip";
        
        Zip zipper = new Zip();
        //int fps = 24;
        FilterProcessor pr = new FilterProcessor(parser.getInputFile(), new Negative());
        Thread threadedProcessor;
        Thread threadedPlayer;
        
        Player pl = new Player();
        Buffer sharedBuffer = pr.getBuffer();
        
        try {

            threadedProcessor = new Thread(pr);
            threadedPlayer = new Thread(pl);
            pl.set(sharedBuffer, parser.getFps());
            
            tracer.trace("Starting processor");
            threadedProcessor.start();
            tracer.trace("Starting player");
            threadedPlayer.start();
            tracer.trace("Joining processor");
            threadedProcessor.join();
            tracer.trace("Joining player");
            threadedPlayer.join();
            tracer.trace("All finished");
            zipper.zipData(sharedBuffer, "Out.zip", Type.JPG);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
