/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import project.misc.Tracer;
import project.output.Zip;
import project.player.Player;
import project.processor.Processor;
import project.processor.ProcessorFactory;
import project.processor.filters.Filter;
import project.processor.filters.FilterFactory;
import project.settings.Setup;
import project.settings.Types.FileType;

public class App {
    
    Tracer tr = Tracer.getInstance();   
    public App(Setup setup){
        
        run(setup);
    }
    
    
    public void run(Setup setup){
        
        
        // por ahora siempre sera si
        if(setup.getInputContainer() == FileType.ZIP){
            //TODO Processor debe recibir una interfaz de datos
            // con algun metodo get o algun iterator
        }
        
        
        // Encoding reads from a zip full of jpegs
        if(setup.isEncoding()){

            FilterFactory flFactory = new FilterFactory();
            ProcessorFactory prFactory = new ProcessorFactory();
            //Encoder encoder = new Encoder();

            Filter fl = flFactory.createFilter(setup);
            Processor pr = prFactory.createProcessor(setup, fl);

            if(!setup.isBatchMode()){
                tr.trace("Creating player at "+ setup.getFPS() +" FPS" );
                pr.setPlayer(new Player(setup.getFPS()));
            }


            tr.trace("Starting processor");
            pr.processData();

            

            // Starting encoder
            if(setup.isEncoding()){
                tr.trace("Starting Encoder");
            }
            // TODO check this
            if(setup.isStoring()){
                if(setup.getOutputContainer() == FileType.ZIP){
                    Zip zipper = new Zip();
                    tr.trace("Zipping data");
                    zipper.zipData(pr.getBuffer(), setup.getOutputFilePath(), setup.getOutputFile());
                }
            }
        }
    }        
}
