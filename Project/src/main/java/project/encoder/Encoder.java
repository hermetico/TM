package project.encoder;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import project.counters.Counters;
import project.input.Unzip;
import project.misc.Tracer;
import project.player.Player;
import project.processor.Buffer;
import project.settings.Configuration;
import project.settings.Setup;

public class Encoder {
    protected Configuration cf;
    protected Tracer tr;
    protected Unzip zp;

    protected Buffer<EncodedImage> buffer;
    protected Counters counters;
    protected Player player = null;
    protected Setup setup;

    public Encoder(Setup setup) {
        cf = Configuration.getInstance();
        tr =  Tracer.getInstance();
        counters = new Counters();
        this.setup = setup;
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
        
        // let's do a copy of the image
        BufferedImage encoded = new BufferedImage(image.getColorModel(), (WritableRaster) image.getData(), image.isAlphaPremultiplied(), null);
        // assume all images are I
        EncodedImage ei = new ImageI(encoded);
        buffer.add(ei);
    }
}
