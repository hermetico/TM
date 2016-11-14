/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.project.processor;

import java.awt.image.BufferedImage;
import java.util.zip.ZipEntry;
import com.tm.project.processor.filters.Filter;

/**
 *
 * @author hermetico
 */
public class FilterProcessor extends Processor{
    
    private Filter filter;
    private int value;
    public FilterProcessor(String path, Filter filter) {
        super(path);
        this.filter = filter;
        
    }
   
    public FilterProcessor(String path, Filter filter, int value) {
        super(path);    
        this.filter = filter;
        this.value=value;
                
    }
    
    
    public void processData() {
        for(ZipEntry entry: entries){
            BufferedImage img;
            img = zp.unzipImageEntry(entry);
            
            filter.apply(img);
            buffer.add(img);
            
            if(cf.PROCESSING_COUNTERS)
                counters.addTimestamp();
        }
        if(cf.PROCESSING_COUNTERS)
            counters.flushCounters("Encoding", "fps");
    }
}
