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
    protected List<Tile> previousTiles = null;
    protected int tWidth = 0;
    protected int tHeight = 0;
    
    public Decoder(Metadata data){
        this.tWidth = data.tWidth;
        this.tHeight = data.tHeight;
    }
    
    
    public void updatePreviousFrame(BufferedImage image){
        previousTiles = ImageUtils.tessellate(image, tHeight, tWidth);
    }
    
    
    public BufferedImage decode(BufferedImage frame, List<DVector> vectors){
        for(DVector vector: vectors){
            ImageUtils.addTile(frame, previousTiles.get(vector.getReference()), vector);
        }
        return frame;
    }
    
}
