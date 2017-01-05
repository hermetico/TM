/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.encoder;
import java.awt.image.BufferedImage;

public class Tile {

    private int x;
    private int y;
    private int width;
    private int height;
    private int index;
    
    private BufferedImage content;
    

    public Tile(int x, int y, int width, int height, BufferedImage fullImage){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.content = fullImage.getSubimage(x, y, width, height);
    }

    public int getX(){ return x; }

    public int getY(){ return y; }

    public int getIndex(){ return index; }

    public void setX(int x){ this.x = x; }

    public void setY(int y){ this.y = y; }

    public int computeDiference(Tile other){
        int diff = 0;
        for (int i = 0; i<width;i++){
            for(int j = 0; j<height; j++){
            //    diff += img;
            }
        }
        return diff;
    }
    
}
