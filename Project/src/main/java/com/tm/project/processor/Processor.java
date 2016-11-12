/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.project.processor;

import com.tm.project.input.Sorter;
import com.tm.project.input.Unzip;
import com.tm.project.settings.Configuration;
import com.tm.project.misc.Tracer;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.zip.ZipEntry;

public class Processor implements Runnable{
    Configuration cf;
    Tracer tr;
    Unzip zp;
    List<ZipEntry> entries;
    Buffer<BufferedImage> buffer;
    
    
    public Processor(String path) {
        cf = Configuration.getInstance();
        tr =  Tracer.getInstance();
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
    
    public int lengthData(){
        return entries.size();
    }
    
    public Buffer<BufferedImage> getBuffer(){
        return buffer;
    }
    

    @Override
    public void run() {
        for(ZipEntry entry: entries){
            BufferedImage img;
            img = zp.unzipImageEntry(entry);
            // exclusion zone
            buffer.add(img);
            
        }
    }
    
    
}
