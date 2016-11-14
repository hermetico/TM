/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.processor;

import project.processor.filters.Filter;
import java.awt.image.BufferedImage;
import java.util.zip.ZipEntry;
import project.player.Player;

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
    
    public FilterProcessor(String path, Filter filter, Player player){
        this(path, filter);
        this.player = player;
        this.player.setBuffer(buffer);
        
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
            filter.apply(img);
            buffer.add(img);
            
            if(cf.PROCESSING_COUNTERS)
                counters.addTimestamp();
        }
        if(cf.PROCESSING_COUNTERS)
            counters.flushCounters("Encoding", "fps");
    }
}
