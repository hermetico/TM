/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.misc;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.List;
import project.encoder.Tile;

public class ImageUtils {

    public static BufferedImage deepCopy(BufferedImage image){
        return new BufferedImage(image.getColorModel(), (WritableRaster) image.getData(), image.isAlphaPremultiplied(), null);
    }
    
    public static List<Tile> tessellate(BufferedImage image, int tHeight, int tWidth){
        int iHeight = image.getHeight();
        int iWidth = image.getWidth();
        List<Tile> teselas = new ArrayList<Tile>();
        int index = 0;
        int col = 0;
        int row = 0;
        for(int j = 0; j  + tHeight <= iHeight;  j += tHeight){
            row++;
            col = 0;
            for(int i = 0; i + tWidth <= iWidth; i += tWidth){
                index++;
                col++;
                Tile tesela = new Tile(i,j,tWidth, tHeight, image, index, col, row);
                teselas.add(tesela);
            }
        }
       return teselas;
    }
    
    public static void substractTile(BufferedImage image, Tile match, Tile wanted){
        Color imagePixel, tilePixel;
        int r, g, b;
        BufferedImage tileImage = match.getContent();
        for(int i = 0; i < match.getWidth(); i++){
            for (int j = 0; j < match.getHeight(); j++){
                
                imagePixel = new Color(image.getRGB(wanted.getX() + i, wanted.getY() + j));
                tilePixel = new Color(tileImage.getRGB(i,j));
                r = imagePixel.getRed() - tilePixel.getRed();
                g = imagePixel.getGreen() - tilePixel.getGreen();
                b = imagePixel.getBlue() - tilePixel.getBlue();

                if(r < 0) r = 0;
                if(g < 0) g = 0;
                if(b < 0) b = 0;
                //image.setRGB(wanted.getX() + i, wanted.getY() + j, new Color(0,255,0).getRGB());
                image.setRGB(wanted.getX() + i, wanted.getY() + j, new Color(r,g,b).getRGB());
            }
        }
        
    }
}
