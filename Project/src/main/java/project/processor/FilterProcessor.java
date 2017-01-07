/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.processor;

import project.processor.filters.Filter;
import java.awt.image.BufferedImage;
import java.util.zip.ZipEntry;
import project.encoder.Encoder;
import project.encoder.ImageJPG;
import project.input.entries.Entry;
import project.player.Player;

/**
 *
 * @author hermetico 
 */
public class FilterProcessor extends Processor{
    
    private Filter filter = null;
    private Encoder encoder = null;

    public FilterProcessor(String path){
        super(path);
        
    }
            
    public FilterProcessor(String path, Filter filter) {
        super(path);
        this.filter = filter;
        
    }
    
     public FilterProcessor(String path,  Encoder encoder){
        super(path);
        this.encoder = encoder;
        this.encoder.setupEncoder(buffer.getSize());
        
    }
    
    public FilterProcessor(String path, Filter filter, Player player){
        this(path, filter);
        this.player = player;
        this.player.setBuffer(buffer);
        
    }
    
    public FilterProcessor(String path, Filter filter, Encoder encoder){
        this(path, filter);
        this.encoder = encoder;
        this.encoder.setupEncoder(buffer.getSize());
        
    }
    
    public FilterProcessor(String path, Filter filter, Player player, Encoder encoder){
        this(path, filter);
        this.player = player;
        this.player.setBuffer(buffer);
        this.encoder = encoder;
        this.encoder.setupEncoder(buffer.getSize());
        
    }
    
    public void processData() {
        
        // starts the player if there is any set up
        if(player != null){ 
            tr.trace("Starting player");
            player.playLoop();
            // if there is a player and a encoder, the encoder should have a player too
            if(encoder != null){
                tr.trace("Starting encoder player");
                encoder.startUpEncoderPlayer();
            }
            
        }

        for(Entry entry: entries){
            
            BufferedImage img, paddedImg, compressed;
            img = zp.unzipImageEntry(entry.getContent());
            
            // applies filter
            if(filter != null) 
                filter.apply(img);
            
            buffer.add(new ImageJPG(img));
            
            // encodes
            if(encoder != null) 
                encoder.encode(img);
            
            if(cf.PROCESSING_COUNTERS)
                counters.addTimestamp();
        }
        if(cf.PROCESSING_COUNTERS)
            counters.flushCounters("Encoding", "fps");
    }
    
}
