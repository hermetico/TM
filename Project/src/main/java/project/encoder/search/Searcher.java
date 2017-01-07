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
        
        // We use quality 1 to 7 and correlation go from 0 (exact match) to 1 (maximal difference)
        // So using the formula: range = 0.15 - quality/40 will set this values:
        // Quality 1 => correlation < 0.13
        // Quality 2 => correlation < 0.11
        // Quality 3 => correlation < 0.09
        // Quality 4 => correlation < 0.07
        // Quality 5 => correlation < 0.05
        // Quality 6 => correlation < 0.03
        // Quality 7 => correlation < 0.01
        //correlation greater than this values means not matching tiles       
               
        this.correlation = 0.15 - ( (double) quality / 50.0);
        
    }
    
    public abstract void resetFrame(BufferedImage frame);
    public abstract Tile getMatch(Tile tessella);
    
}
