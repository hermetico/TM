/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.encoder.search;

import java.awt.image.BufferedImage;
import project.encoder.Tile;

public abstract class Searcher {
    private int seekRange;
    private int iWidth;
    private int iHeight;
    private int tWidth;
    private int tHeight;
    
    private BufferedImage currentFrame;
    private int xCoord;
    private int yCoord;
    
    public Searcher(int seekRange, int iWidth, int iHeight, int tWidth, int tHeight){
        this.seekRange = seekRange;
        this.iWidth = iWidth;
        this.iHeight = iHeight;
        this.tWidth = tWidth;
        this.tHeight = tHeight;
    }
    
    public Tile next(){
        return new Tile(xCoord, yCoord, tWidth, tHeight, currentFrame);
    }
    
    public abstract void reset(int xCoord, int yCoord, BufferedImage currentFrame);
    public abstract boolean anyMore();
    public abstract void displace();
    
}
