/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.processor;

import project.processor.filters.Filter;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.zip.ZipEntry;
import project.player.Player;

public class FilterProcessor extends Processor{
    
    private Filter filter;
    private Buffer filteredBuffer;
    private Player filteredPlayer;

    public FilterProcessor(String path, Filter filter) {
        super(path);
        this.filter = filter;
        this.filteredBuffer = new Buffer<BufferedImage>(super.buffer.getSize());
        
    }
    
    public FilterProcessor(String path, Filter filter, Player original, Player filtered){
        this(path, filter);
        this.player = original;
        this.player.setBuffer(buffer);
        
        // second player to visualize the filter
        this.filteredPlayer = filtered;
        this.filteredPlayer.setBuffer(filteredBuffer);
    }
    
    
    public void processData() {
        
        // starts the player if there is any set up
        if(player != null){ 
            tr.trace("Starting player");
            player.playLoop();
            filteredPlayer.playLoop();
        }
        
        for(ZipEntry entry: entries){
            BufferedImage img;
            img = zp.unzipImageEntry(entry);
            
            //TODO:FIXME
            buffer.add(img);
            BufferedImage copy = new BufferedImage(img.getColorModel(), (WritableRaster) img.getData(), img.isAlphaPremultiplied(), null);
            
            filter.apply(copy);
            filteredBuffer.add(copy);
            
            if(cf.PROCESSING_COUNTERS)
                counters.addTimestamp();
        }
        if(cf.PROCESSING_COUNTERS)
            counters.flushCounters("Encoding", "fps");
    }
}
