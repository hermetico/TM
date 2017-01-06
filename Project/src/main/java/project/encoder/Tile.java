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
    private int col;
    private int row;
    
    private BufferedImage content;
    

    public Tile(int x, int y, int width, int height, BufferedImage fullImage, int index, int col, int row){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.content = fullImage.getSubimage(x, y, width, height);
        this.index = index;
        this.col = col;
        this.row = row;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getIndex() {
        return index;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public BufferedImage getContent() {
        return content;
    }


    
}
