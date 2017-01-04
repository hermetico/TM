/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.processor;

import project.input.Sorter;
import project.input.Unzip;
import project.counters.Counters;
import project.settings.Configuration;
import project.misc.Tracer;
import project.player.Player;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.zip.ZipEntry;

public class Processor{
    protected Configuration cf;
    protected Tracer tr;
    protected Unzip zp;
    protected List<ZipEntry> entries;
    protected Buffer<BufferedImage> buffer;
    
    protected Counters counters;
    protected Player player;
    
    
    public Processor(String path) {
        cf = Configuration.getInstance();
        tr =  Tracer.getInstance();
        counters = new Counters();
        zp = new Unzip(path);
        entries = readZipData();
        buffer = new Buffer<BufferedImage>(entries.size());
        
        this.player = null;
        
    }
    
    public Processor(String path, Player player){
        this(path);
        this.player = player;
        this.player.setBuffer(buffer);
        
    }
    
    public void setPlayer(Player player){
        this.player = player;
        this.player.setBuffer(buffer);
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
    
    public void processData() {
        // starts the player if there is any set up
        if(player != null){ 
            tr.trace("Starting player");
            player.playLoop();
        }
        
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
