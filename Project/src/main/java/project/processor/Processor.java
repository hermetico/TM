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
import project.encoder.EncodedImage;
import project.input.entries.Entry;

public abstract class Processor{
    protected Configuration cf;
    protected Tracer tr;
    protected Unzip zp;
    protected List<Entry> entries;
    protected Buffer<EncodedImage> buffer;
    protected Counters counters;
    protected Player player = null;
    
    
    
    public Processor(String path) {
        cf = Configuration.getInstance();
        tr =  Tracer.getInstance();
        counters = new Counters();
        zp = new Unzip(path);
        entries = zp.getEntries();
        buffer = new Buffer<EncodedImage>(entries.size());
        
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
    

    public Buffer<EncodedImage> getBuffer(){
        return buffer;
    }
    

    public abstract void processData();
    /*{

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

    }*/

    
}
