package project.encoder;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import project.encoder.compare.Comparer;
import project.encoder.compare.MAD;
import project.encoder.search.FastPixelSearch;
import project.encoder.search.FastTileSearch;
import project.encoder.search.FullPixelSearch;
import project.encoder.search.FullTileSearch;
import project.encoder.search.Searcher;
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
    
    protected Searcher searcher;
    
    protected int GOP = 0;
    protected int quality = 0;
    protected int tWidth = 0;
    protected int tHeight = 0;
    protected int seekRange = 0;
    
    protected int frames = 0;
    protected Comparer comparer;
    //private Filter average;
    
    

    public Encoder(Setup setup) {
        cf = Configuration.getInstance();
        tr =  Tracer.getInstance();
        //average = new Average(4);
        this.setup(setup);
    }
    
    private void setup(Setup setup){
        this.setup = setup;
        
        this.GOP = setup.getGOP();
        this.quality = setup.getQuality();
        this.seekRange = setup.getSeekRange();
        this.tHeight = setup.getYPixelsPerTile();     
        this.tWidth = setup.getXPixelsPerTile();
        this.comparer = setup.getComparer();
        
        if(setup.isFast_search()){
            tr.trace("Using Fast search encoding");
            if(setup.isPixel_search()){
                tr.trace("Using Pixel search encoding");
                searcher = (Searcher) new FastPixelSearch(this.seekRange,this.tWidth, this.tHeight, this.quality, this.comparer);
            }else{
                tr.trace("Using Tile search encoding");
                searcher = (Searcher) new FastTileSearch(this.seekRange,this.tWidth, this.tHeight, this.quality, this.comparer);
            }
        }else{
            tr.trace("Using Full search encoding");
            if(setup.isPixel_search()){
                tr.trace("Using Pixel search encoding");
                searcher = (Searcher) new FullPixelSearch(this.seekRange,this.tWidth, this.tHeight, this.quality, this.comparer);
            }else{
                tr.trace("Using Tile search encoding");
                searcher = (Searcher) new FullTileSearch(this.seekRange,this.tWidth, this.tHeight, this.quality, this.comparer);
            }
        }
        
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
    
    public Buffer<EncodedImage> getBuffer(){
        return buffer;
    }
    
    public void encode(BufferedImage image){
        EncodedImage encoded;
        
        if(frames % GOP == 0){ // frames I
            encoded = new ImageI(ImageUtils.deepCopy(image));
        }else{ // frames P
        
            // As the project description indicates, we are going to implement
            // a DCT algorithm
            BufferedImage nImage = ImageUtils.deepCopy(image);
            List<Tile> teselas = ImageUtils.tessellate(nImage, tHeight, tWidth);

            
            List<DVector> vectors = new ArrayList<DVector>();
            // for each tesela of the image
            for(Tile wanted : teselas){
                
                // search and compare with the tiles of the previous frame
                Tile match = searcher.getMatch(wanted, previousFrame);
                 

                if(match != null){
                    // the vector is made with
                    int reference = wanted.getIndex();
                    int x = match.getX() - wanted.getX();
                    int y = match.getY() - wanted.getY();
                    
                    DVector displacement = new DVector(reference, x, y);
                    
                    // substract tesela
                    //ImageUtils.substractWantedTile(nImage, match, wanted);
                    ImageUtils.toMeanWantedTile(nImage, wanted);
                    //average.apply(nImage);
                    // adds vector
                    vectors.add(displacement);
                }
            }
                
            //tr.trace("Total of " + vectors.size() + " vectors");
            
            encoded = new ImageP(ImageUtils.deepCopy(nImage), vectors);
        }
        
        frames++;
        previousFrame = ImageUtils.deepCopy(image);
        buffer.add(encoded);
    }
    
}
