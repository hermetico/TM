package project.encoder;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.List;
import project.counters.Counters;
import project.input.Unzip;
import project.misc.ImageUtils;
import project.misc.Tracer;
import project.player.Player;
import project.processor.Buffer;
import project.settings.Configuration;
import project.settings.Setup;

public class Encoder {
    protected Configuration cf;
    protected Tracer tr;
    protected Setup setup;
    protected Player player = null;
    
    
    protected Buffer<EncodedImage> buffer;
    protected BufferedImage previousFrame;
    
    protected int GOP = 0;
    protected int quality = 0;
    protected int tWidth = 0;
    protected int tHeight = 0;
    protected int seekRange = 0;
    
    protected int frames = 0;
    

    public Encoder(Setup setup) {
        cf = Configuration.getInstance();
        tr =  Tracer.getInstance();
        this.setup(setup);
    }
    
    private void setup(Setup setup){
        this.setup = setup;
        
        this.GOP = setup.getGOP();
        this.quality = setup.getQuality();
        this.seekRange = setup.getSeekRange();
        this.tHeight = setup.getYPixelsPerTile();
        this.tWidth = setup.getXPixelsPerTile();
        
    }

    public void setPlayer(Player player){
        this.player = player;
        this.player.setBuffer(buffer);
    }
    
    public void setupEncoder(int bufferSize){
        this.buffer = new Buffer<EncodedImage>(bufferSize);
    
    }
    
    public void startUpEncoderPlayer(){
        if(player != null) this.player.playLoop();
    }
    
    public void encode(BufferedImage image){
        EncodedImage encoded;
        if(frames % GOP == 0){ // frames I
            encoded = new ImageI(ImageUtils.deepCopy(image));
            previousFrame = ImageUtils.deepCopy(image);
        }else{ // frames P
            // tessellate previous frame
            
            // for each tesela
                //search and compare
            
                // if result is positive
                    // get vector
                    // substract tesela
                    
            // TODO change to P
            encoded = new ImageI(ImageUtils.deepCopy(image));
        }
        buffer.add(encoded);
    }
    
}
