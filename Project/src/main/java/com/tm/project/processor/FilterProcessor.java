/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.project.processor;

import com.tm.project.processor.filters.Filter;
import java.awt.image.BufferedImage;
import java.util.zip.ZipEntry;

/**
 *
 * @author hermetico
 */
public class FilterProcessor extends Processor{
    
    private Filter filter;
    public FilterProcessor(String path, Filter filter) {
        super(path);
        this.filter = filter;
        
    }
    
    @Override
    public void run() {
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
