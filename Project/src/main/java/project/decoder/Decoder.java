/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.decoder;

import java.awt.image.BufferedImage;
import java.util.List;
import project.encoder.DVector;
import project.encoder.Tile;
import project.misc.ImageUtils;
import project.misc.Tracer;
import project.settings.Configuration;
import project.settings.Metadata;

public class Decoder {
    protected Configuration cf;
    protected Tracer tr;
    protected BufferedImage previousFrame = null;
    protected int tWidth = 0;
    protected int tHeight = 0;
    protected List<Tile> sampleFrameTiles = null;
    
    
    public Decoder(Metadata data){
        this.tWidth = data.tWidth;
        this.tHeight = data.tHeight;
    }
    
    
    public void updatePreviousFrame(BufferedImage image){
        previousFrame = image;
        if (sampleFrameTiles == null){
            // tessellates a frame to get coordinates from the beginning
            sampleFrameTiles = ImageUtils.tessellate(image, tHeight, tWidth);
        }
    }
    
    
    public BufferedImage decode(BufferedImage frame, List<DVector> vectors){
        for(DVector vector: vectors){
            // gets wanted position within the frame from the tile based on the reference
            Tile wantedTile = sampleFrameTiles.get(vector.getReference());
            
            // position of the wanted tile in the image
            int x = wantedTile.getX() + vector.getX();
            int y = wantedTile.getY() + vector.getY();
            BufferedImage wantedSubimage = previousFrame.getSubimage(x, y, tWidth, tWidth);
            
            // adds the subimage
            ImageUtils.replaceSubimage(frame, wantedSubimage, wantedTile.getX(), wantedTile.getY());
        }
        
        return frame;
    }
    
}
