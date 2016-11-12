/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.project.processor;

import com.tm.project.input.Sorter;
import com.tm.project.input.Unzip;
import com.tm.project.misc.Counters;
import com.tm.project.settings.Configuration;
import com.tm.project.misc.Tracer;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.zip.ZipEntry;

public class Processor implements Runnable{
    protected Configuration cf;
    protected Tracer tr;
    protected Unzip zp;
    protected List<ZipEntry> entries;
    protected Buffer<BufferedImage> buffer;
    protected Counters counters;
    
    
    public Processor(String path) {
        cf = Configuration.getInstance();
        tr =  Tracer.getInstance();
        counters = new Counters();
        zp = new Unzip(path);
        entries = readZipData();
        buffer = new Buffer<BufferedImage>(entries.size());
        
    }

    
    private List<ZipEntry> readZipData(){
        List<ZipEntry> entries = zp.getEntries();
        
        if(cf.SORT_INPUT_BY_NAME){
            tr.trace("Sorting input data by name");
            entries = Sorter.sortEntriesByName(entries);
        }
        return entries;
        
    }
    
    public Buffer<BufferedImage> getBuffer(){
        return buffer;
    }
    
    @Override
    public void run() {
        for(ZipEntry entry: entries){
            BufferedImage img;
            img = zp.unzipImageEntry(entry);
            buffer.add(img);
            
            if(cf.PROCESSING_COUNTERS)
                counters.addTimestamp();
        }
        if(cf.PROCESSING_COUNTERS)
            counters.flushCounters("Encoding", "fps");
    }
}
