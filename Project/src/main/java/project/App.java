/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import project.encoder.Encoder;
import project.misc.Tracer;
import project.output.Zip;
import project.player.Player;
import project.processor.Processor;
import project.processor.ProcessorFactory;
import project.processor.filters.Filter;
import project.processor.filters.FilterFactory;
import project.settings.Setup;
import project.settings.Types.FileType;
import project.statistics.Statistics;

public class App {
    
    Tracer tr = Tracer.getInstance();   
    public App(Setup setup){
        
        run(setup);
    }
   
    public void run(Setup setup){
        
    
        
        ProcessorFactory prFactory = new ProcessorFactory();
        Processor pr,pr1;
        if(setup.isEncoding()){
            // Statistics generate compression results
            Statistics stats = new Statistics(setup);   
            FilterFactory flFactory = new FilterFactory();
            
            Filter fl = flFactory.createFilter(setup);
            Encoder enc = new Encoder(setup);
            pr = prFactory.createProcessor(setup, fl, enc);
            
            if(!setup.isBatchMode()){
                tr.trace("Creating player at "+ setup.getFPS() +" FPS" );
                pr.setPlayer(new Player(setup.getFPS(), "Original"));
                enc.setPlayer(new Player(setup.getFPS(), true, "Encoded"));
            }           
            tr.trace("Starting processor");
            stats.timeStart();// <--------------time start------------
            pr.processData();
            stats.timeEnd();  // <--------------time end--------------
            Zip zipper = new Zip();
            tr.trace("Zipping data");
            zipper.zipEncodedData(setup, enc.getBuffer(), setup.getOutputFilePath());
            stats.getResults();
            if(setup.isDecoding()){
                tr.trace("Starting decoder");
                setup.updateFilePath(setup.getOutputFilePath());           
                pr1 = prFactory.createDecoderProcessor(setup);
                pr1.processData();
            }
            
        }else{
            
            if(setup.isDecoding()){
                pr = prFactory.createDecoderProcessor(setup);          
            }else{
                tr.trace("Player mode enabled");
                FilterFactory flFactory = new FilterFactory();
                Filter fl = flFactory.createFilter(setup);
                pr = prFactory.createProcessor(setup, fl, null);
                tr.trace("Creating player at "+ setup.getFPS() +" FPS" );
                pr.setPlayer(new Player(setup.getFPS(), true, "Original"));              
            }
            pr.processData();
        }
        /*
             
        
       
        // Encoding reads from a zip full of jpegs
        if(setup.isEncoding() && !setup.isDecoding()){

            FilterFactory flFactory = new FilterFactory();
            ProcessorFactory prFactory = new ProcessorFactory();
            //Encoder encoder = new Encoder();

            Filter fl = flFactory.createFilter(setup);
            Encoder enc = new Encoder(setup);
            Processor pr = prFactory.createProcessor(setup, fl, enc);
            
            if(!setup.isBatchMode()){
                tr.trace("Creating player at "+ setup.getFPS() +" FPS" );
                pr.setPlayer(new Player(setup.getFPS(), "Original"));
                enc.setPlayer(new Player(setup.getFPS(), true, "Encoded"));
            }


            tr.trace("Starting processor");
            pr.processData();

            // always storing
            
            Zip zipper = new Zip();
            tr.trace("Zipping data");
            zipper.zipEncodedData(setup, enc.getBuffer(), setup.getOutputFilePath());
            
        }else if(setup.isEncoding() && setup.isDecoding()){
            FilterFactory flFactory = new FilterFactory();
            ProcessorFactory prFactory = new ProcessorFactory();
            //Encoder encoder = new Encoder();

            Filter fl = flFactory.createFilter(setup);
            Encoder enc = new Encoder(setup);
            Processor pr = prFactory.createProcessor(setup, fl, enc);

            tr.trace("Creating player at "+ setup.getFPS() +" FPS" );
            pr.setPlayer(new Player(setup.getFPS(), "Original"));
            enc.setPlayer(new Player(setup.getFPS(), "Encoded"));

            tr.trace("Starting processor");
            pr.processData();

            // always storing
            
            Zip zipper = new Zip();
            tr.trace("Zipping data");
            zipper.zipEncodedData(setup, enc.getBuffer(), setup.getOutputFilePath());
            
            tr.trace("Starting decoder");
            setup.updateFilePath(setup.getOutputFilePath());
            
            pr = prFactory.createDecoderProcessor(setup);
            pr.processData();
            
        }else if(setup.isDecoding() && !setup.isEncoding()){
            ProcessorFactory prFactory = new ProcessorFactory();
            
            Processor pr = prFactory.createDecoderProcessor(setup);
            pr.processData();
            
        }else{
            tr.trace("Player mode enabled");
            FilterFactory flFactory = new FilterFactory();
            ProcessorFactory prFactory = new ProcessorFactory();

            Filter fl = flFactory.createFilter(setup);
            Processor pr = prFactory.createProcessor(setup, fl, null);
            tr.trace("Creating player at "+ setup.getFPS() +" FPS" );
            pr.setPlayer(new Player(setup.getFPS(), true, "Original"));
            pr.processData();
        }
*/
    }      
}
