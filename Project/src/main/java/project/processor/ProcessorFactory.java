/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.processor;


import project.encoder.Encoder;
import project.misc.Tracer;
import project.player.Player;
import project.processor.filters.Filter;
import project.processor.filters.FilterFactory;
import project.settings.Setup;

public class ProcessorFactory {
    Tracer tr = Tracer.getInstance();
    FilterFactory flFactory = new FilterFactory();
    public Processor createProcessor(Setup setup, Filter filter, Encoder encoder){
        

        if(filter == null){
            tr.trace("Creating processor with encoder");
            //return  new Processor(setup.getInputFilePath());
            return  new FilterProcessor(setup.getInputFilePath(), encoder);
        }
        
        
        
        tr.trace("Creating processor with filter and encoder");
        return  new FilterProcessor(setup.getInputFilePath(), filter, encoder);
    }
    
    public Processor createDecoderProcessor(Setup setup){
        Filter fl = flFactory.createFilter(setup);
        if(setup.isBatchMode()){
            return new FilterDecoderProcessor(setup.getInputFilePath(), fl);
            
        }
        return new FilterDecoderProcessor(setup.getInputFilePath(), new Player(setup.getFPS(),true, "Decoded"), fl);
        
    }
    
}
