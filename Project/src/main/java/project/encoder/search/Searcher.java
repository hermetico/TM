/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.encoder.search;

import java.awt.image.BufferedImage;
import project.encoder.Tile;
import project.encoder.compare.Comparer;
import project.misc.Tracer;

public abstract class Searcher {
    protected Tracer tr = Tracer.getInstance();;
    protected int seekRange;
    protected int tWidth;
    protected int tHeight;
    
    protected double correlation;
    
    protected Comparer comparer;
    
    public Searcher(int seekRange,int tWidth, int tHeight, int quality, Comparer comparer){
        this.seekRange = seekRange;
        this.tWidth = tWidth;
        this.tHeight = tHeight;
        this.comparer = comparer;
        //TODO
        this.correlation = (double) quality;
    }
    
    public abstract void resetFrame(BufferedImage frame);
    public abstract Tile getMatch(Tile tessella);
    
}
