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
        
        
        // We use quality 1 to 5 and correlation go from 0 (exact match) to 1 (maximal difference)
        // So using the formula: range = 0.5 - quality/10 will set this values:
        // Quality 1 => correlation < 0.5
        // Quality 2 => correlation < 0.4
        // Quality 3 => correlation < 0.3
        // Quality 4 => correlation < 0.2
        // Quality 5 => correaltion < 0.1
        //correlation greater than this values means not matching tiles 
        
                        
        this.correlation = 0.5 - ( (double) quality / 10.0);
    }
    
    public abstract void resetFrame(BufferedImage frame);
    public abstract Tile getMatch(Tile tessella);
    
}
