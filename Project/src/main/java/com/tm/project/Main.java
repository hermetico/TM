/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.project;

import com.tm.project.misc.Tracer;
import com.tm.project.player.Player;
import com.tm.project.processor.Buffer;
import com.tm.project.processor.Processor;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Main {

    
    
    public static void main(String[] args){
        Main main = new Main(args);
    }
    
    public Main(String[] args){
        Tracer tracer = Tracer.getInstance();
        String file = "Cubo.zip";
        int fps = 24;
        Processor pr = new Processor(file);
        Thread threadedProcessor;
        Thread threadedPlayer;
        
        Player pl = new Player();
        Buffer sharedBuffer = pr.getBuffer();
        
        try {

            threadedProcessor = new Thread(pr);
            threadedPlayer = new Thread(pl);
            pl.set(sharedBuffer, fps);
            
            tracer.trace("Starting processor");
            threadedProcessor.start();
            tracer.trace("Starting player");
            threadedPlayer.start();
            tracer.trace("Joining processor");
            threadedProcessor.join();
            tracer.trace("Joining player");
            threadedPlayer.join();
            tracer.trace("All finished");
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
