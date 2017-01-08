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
import project.encoder.DVector;
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
        BufferedImage data;
        for(int j = 0; j  + tHeight <= iHeight;  j += tHeight){
            col = 0;
            for(int i = 0; i + tWidth <= iWidth; i += tWidth){
                data = image.getSubimage(i, j, tWidth, tHeight);
                Tile tesela = new Tile(i,j, data, index, col, row);
                teselas.add(tesela);
                index++;
                col++;
            }
            row++;
        }
       return teselas;
    }
    
    public static void substractTile(BufferedImage image, Tile match, DVector displacement){
        Color imagePixel, tilePixel;
        int r, g, b;
        BufferedImage tileImage = match.getContent();
        
        for(int y = 0; y < match.getWidth(); y++){ // tesela y coords
            for (int x = 0; x < match.getHeight(); x++){ // tesela x coords
                
                // image coords
                int imageX = match.getX() + x + displacement.getX();
                int imageY = match.getY() + y + displacement.getY();
                
                imagePixel = new Color(image.getRGB(imageX, imageY));
                tilePixel = new Color(tileImage.getRGB(x, y));
                
                r = imagePixel.getRed() - tilePixel.getRed();
                g = imagePixel.getGreen() - tilePixel.getGreen();
                b = imagePixel.getBlue() - tilePixel.getBlue();

                // checks no value is below 0
                if(r < 0) r = 0;
                if(g < 0) g = 0;
                if(b < 0) b = 0;
                
                image.setRGB(imageX, imageY, new Color(0,0,0).getRGB());
                //image.setRGB(imageX, imageY, new Color(r,g,b).getRGB());
            }
        }
    }
    
    public static void addTile(BufferedImage image, Tile match, DVector displacement){
        Color imagePixel, tilePixel;
        int r, g, b;
        BufferedImage tileImage = match.getContent();
        
        for(int y = 0; y < match.getWidth(); y++){ // tesela y coords
            for (int x = 0; x < match.getHeight(); x++){ // tesela x coords
                
                // image coords
                int imageX = match.getX() + x + displacement.getX();
                int imageY = match.getY() + y + displacement.getY();
                
                imagePixel = new Color(image.getRGB(imageX, imageY));
                tilePixel = new Color(tileImage.getRGB(x, y));
                
                r = imagePixel.getRed() + tilePixel.getRed();
                g = imagePixel.getGreen() + tilePixel.getGreen();
                b = imagePixel.getBlue() + tilePixel.getBlue();

                // checks no value is above 255
                if(r > 255) r = 255;
                if(g > 255) g = 255;
                if(b > 255) b = 255;
                
                image.setRGB(imageX, imageY, new Color(r,g,b).getRGB());
            }
        }
    }
    

}
