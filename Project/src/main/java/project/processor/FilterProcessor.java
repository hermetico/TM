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
    protected Buffer<BufferedImage> compressedBuffer; //compressed output stream 
    public FilterProcessor(String path, Filter filter) {
        super(path);
        this.filter = filter;
        compressedBuffer = new Buffer<BufferedImage>(entries.size());
        
    }
    
    public FilterProcessor(String path, Filter filter, Player player){
        this(path, filter);
        this.player = player;
        this.player.setBuffer(buffer);
        compressedBuffer = new Buffer<BufferedImage>(entries.size());
        
    }
    
    
    public void processData() {
        
        // starts the player if there is any set up
        if(player != null){ 
            tr.trace("Starting player");
            player.playLoop();
        }
        
        int index = 0;
        for(ZipEntry entry: entries){
            
            BufferedImage img, paddedImg, compressed;
            img = zp.unzipImageEntry(entry);
            filter.apply(img);
            buffer.add(img);
            //TODO -- get padding from setup 
            paddedImg = filter.addPadding(img, 0, 0, 0, 0);
            if (index == 0){
                compressedBuffer.add(img);   
            }else{
            compressed = compressImg(paddedImg);
            compressed = filter.removePadding(compressed, index);
            //remove padding
            // TODO -- get values from setup
            //compressed = compressed.getSubimage(left, top, width, height);
            compressed = compressed.getSubimage(0, 0, 320, 240);
            compressedBuffer.add(compressed); 
            index++;
            // save reference frame every 5 frames; hardcoded, must be set as variable value
            index = index % 5;
  
        }
            
        if(cf.PROCESSING_COUNTERS)
            counters.addTimestamp();
        }
        if(cf.PROCESSING_COUNTERS)
            counters.flushCounters("Encoding", "fps");
    }
    
    private BufferedImage compressImg(BufferedImage img){
    // Not implemented yet, number of tiles required ( from setup )
    return img;
    }
}
