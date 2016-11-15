/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.processor;


import project.misc.Tracer;
import project.processor.filters.Filter;
import project.settings.Setup;

public class ProcessorFactory {
    Tracer tr = Tracer.getInstance();
    public Processor createProcessor(Setup setup, Filter filter){
        
        if(filter == null){
            tr.trace("Creating normal processor");
            return  new Processor(setup.getInputFilePath());
        }
        
        tr.trace("Creating normal processor with filter");
        return  new FilterProcessor(setup.getInputFilePath(), filter);
    }
    
}
