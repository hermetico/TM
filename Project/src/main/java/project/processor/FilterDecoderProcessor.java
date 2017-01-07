/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.processor;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.zip.ZipEntry;
import project.decoder.Decoder;
import project.encoder.DVector;
import project.encoder.Encoder;
import project.encoder.ImageJPG;
import project.input.entries.Entry;
import project.input.entries.PEntry;
import project.player.Player;
import project.processor.filters.Filter;
import project.settings.Metadata;
import project.settings.Types;
import project.settings.Types.FileType;

public class FilterDecoderProcessor extends Processor{

    private Filter filter = null;
    private Metadata metadata;
    private Decoder decoder;
    
    public FilterDecoderProcessor(String path){
        super(path);
        metadata = zp.getMetadata();
        this.decoder = new Decoder(metadata);
        
    
    }
    
    public FilterDecoderProcessor(String path, Filter filter){
        this(path);
        this.filter = filter;
    }
    
    public FilterDecoderProcessor(String path, Player player){
        this(path);
        this.player = player;
        this.player.setBuffer(buffer);
    }
    
    
    public FilterDecoderProcessor(String path, Player player, Filter filter){
        this(path, player);
        this.filter = filter;
    }
    
    public void processData() {
        
        // starts the player if there is any set up
        if(player != null){ 
            tr.trace("Starting player");
            player.playLoop();
            
        }

        for(Entry entry: entries){
            
            BufferedImage img;
            img = zp.unzipImageEntry(entry.getContent());
            
            if (FileType.isPFrame(entry.getFileType())){
                List<DVector> vectors;
                vectors = zp.unzipVectorsEntry(((PEntry)entry).getVectors());   
                img = decoder.decode(img, vectors);
            }
            decoder.updatePreviousFrame(img);
            // applies filter
            if(filter != null) 
                filter.apply(img);
            
            buffer.add(new ImageJPG(img));

            if(cf.PROCESSING_COUNTERS)
                counters.addTimestamp();
        }
        if(cf.PROCESSING_COUNTERS)
            counters.flushCounters("Encoding", "fps");
    }
    
}
