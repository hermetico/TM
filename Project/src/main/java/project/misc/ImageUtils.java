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
    
    /**
     * This functions substracts the match tile from the image based on the position of 
     * the wanted tile and a displacement vector
     * @param image
     * @param match
     * @param wanted
     * @param displacement 
     */
    public static void substractTile(BufferedImage image, Tile match, Tile wanted, DVector displacement){
        Color imagePixel, tilePixel;
        int r, g, b;
        BufferedImage tileImage = match.getContent();
        
        for(int y = 0; y < match.getHeight(); y++){ // tesela y coords
            for (int x = 0; x < match.getWidth(); x++){ // tesela x coords
                
                // image coords
                int imageX = wanted.getX() + x + displacement.getX();
                int imageY = wanted.getY() + y + displacement.getY();
                
                imagePixel = new Color(image.getRGB(imageX, imageY));
                tilePixel = new Color(tileImage.getRGB(x, y));
                
                r = imagePixel.getRed() - tilePixel.getRed();
                g = imagePixel.getGreen() - tilePixel.getGreen();
                b = imagePixel.getBlue() - tilePixel.getBlue();

                // checks no value is below 0
                if(r < 0) r = 0;
                if(g < 0) g = 0;
                if(b < 0) b = 0;

                
                //image.setRGB(imageX, imageY, new Color(0,0,0).getRGB());
                image.setRGB(imageX, imageY, new Color(r,g,b).getRGB());
            }
        }
    }
    
    /**
     * This function substracts the match tile from the image based on the position 
     * of the wanted tile
     * @param image
     * @param match
     * @param wanted 
     */
    public static void substractWantedTile(BufferedImage image, Tile match, Tile wanted){
        Color imagePixel, tilePixel;
        int r, g, b;
        BufferedImage tileImage = match.getContent();
        
        for(int y = 0; y < match.getHeight(); y++){ // tesela y coords
            for (int x = 0; x < match.getWidth(); x++){ // tesela x coords
                
                // image coords
                int imageX = wanted.getX() + x;
                int imageY = wanted.getY() + y;
                
                imagePixel = new Color(image.getRGB(imageX, imageY));
                tilePixel = new Color(tileImage.getRGB(x, y));
                
                r = imagePixel.getRed() - tilePixel.getRed();
                g = imagePixel.getGreen() - tilePixel.getGreen();
                b = imagePixel.getBlue() - tilePixel.getBlue();

                // checks no value is below 0
                if(r < 0) r = 0;
                if(g < 0) g = 0;
                if(b < 0) b = 0;

                
                image.setRGB(imageX, imageY, new Color(r,g,b).getRGB());
            }
        }
    }
    
     /**
     * This function changes the tile to a mean color
     * @param image
     * @param match
     * @param wanted 
     */
    public static void toMeanWantedTile(BufferedImage image, Tile wanted){
        int meanR = 0, meanG = 0, meanB = 0;
        Color pixel;
        BufferedImage tileImage = wanted.getContent();
        
        for(int y = 0; y < wanted.getHeight(); y++){ // tesela y coords
            for (int x = 0; x < wanted.getWidth(); x++){ // tesela x coords
                pixel = new Color(tileImage.getRGB(x, y));
                meanR += pixel.getRed(); 
                meanG += pixel.getGreen();
                meanB += pixel.getBlue();
            }
        }
        int size = wanted.getWidth() * wanted.getHeight();
        meanR /= size;
        meanG /= size;
        meanB /= size;
        
        for(int y = 0; y < wanted.getHeight(); y++){ // tesela y coords
            for (int x = 0; x < wanted.getWidth(); x++){ // tesela x coords
                // image coords
                int imageX = wanted.getX() + x;
                int imageY = wanted.getY() + y;
                image.setRGB(imageX, imageY, new Color(meanR,meanG,meanB).getRGB());
            }
        }
        
    }
    

    public static Color getMeanColor(BufferedImage image){
        int meanR = 0, meanG = 0, meanB = 0;
        Color pixel;
        
        for(int y = 0; y < image.getHeight(); y++){
            for (int x = 0; x < image.getWidth(); x++){
                pixel = new Color(image.getRGB(x, y));
                meanR += pixel.getRed(); 
                meanG += pixel.getGreen();
                meanB += pixel.getBlue();
            }
        }
        int size = image.getWidth() * image.getHeight();
        meanR /= size;
        meanG /= size;
        meanB /= size;
        
        return new Color(meanR,meanG,meanB);
    }
    
    public static void toBlackWantedTile(BufferedImage image, Tile wanted){
        for(int y = 0; y < wanted.getHeight(); y++){ // tesela y coords
            for (int x = 0; x < wanted.getWidth(); x++){ // tesela x coords
                // image coords
                int imageX = wanted.getX() + x;
                int imageY = wanted.getY() + y;
                image.setRGB(imageX, imageY, new Color(0,0,0).getRGB());
            }
        }
        
    }
    
    public static void toColorWantedTile(BufferedImage image, Tile wanted, Color color){
        int nColor = color.getRGB();
        for(int y = 0; y < wanted.getHeight(); y++){ // tesela y coords
            for (int x = 0; x < wanted.getWidth(); x++){ // tesela x coords
                // image coords
                int imageX = wanted.getX() + x;
                int imageY = wanted.getY() + y;
                image.setRGB(imageX, imageY, nColor);
            }
        }
        
    }
    
    public static void deblockingFilter(BufferedImage image, Tile position, int filterSide){
        int radius = filterSide / 2;
        for(int y = 0; y < position.getHeight(); y++){ // tesela y coords
            for (int x = 0; x < position.getWidth(); x++){ // tesela x coords
                // image coords
                int imageX = position.getX() + x;
                int imageY = position.getY() + y;
                
                // checks limits
                if(imageX < image.getWidth() - radius
                   && imageY < image.getHeight() - radius
                   && imageX > radius && imageY > radius){
                    image.setRGB(imageX, imageY,getMeanFilter(image, imageX, imageY, filterSide).getRGB());
                }
                
            }
        }        
    }
    
    public static void deblockingBorderFilter(BufferedImage image, Tile position, int filterSide){
        int radius = filterSide / 2;
        int x, y, imageX, imageY;
        for(y = 0; y < position.getHeight(); y++){
            // left border
            x = 0;
                // image coords
                imageX = position.getX() + x;
                imageY = position.getY() + y;
                
                // checks limits
                if(imageX < image.getWidth() - radius
                   && imageY < image.getHeight() - radius
                   && imageX > radius && imageY > radius){
                    image.setRGB(imageX, imageY,getMeanFilter(image, imageX, imageY, filterSide).getRGB());
                }
            // right border
            x = position.getWidth();
                // image coords
                imageX = position.getX() + x;
                // checks limits
                if(imageX < image.getWidth() - radius
                   && imageY < image.getHeight() - radius
                   && imageX > radius && imageY > radius){
                    image.setRGB(imageX, imageY,getMeanFilter(image, imageX, imageY, filterSide).getRGB());
                }
        }
        for (x = 0; x < position.getWidth(); x++){ 
            // top border
            y = 0;    
                imageX = position.getX() + x;
                imageY = position.getY() + y;
                
                // checks limits
                if(imageX < image.getWidth() - radius
                   && imageY < image.getHeight() - radius
                   && imageX > radius && imageY > radius){
                    image.setRGB(imageX, imageY,getMeanFilter(image, imageX, imageY, filterSide).getRGB());
                }
                        
            y = position.getHeight(); // bottom border    
                imageX = position.getX() + x;

                
                // checks limits
                if(imageX < image.getWidth() - radius
                   && imageY < image.getHeight() - radius
                   && imageX > radius && imageY > radius){
                    image.setRGB(imageX, imageY,getMeanFilter(image, imageX, imageY, filterSide).getRGB());
                }
                
            }
               
    }
    
    private static Color getMeanFilter(BufferedImage image, int x, int y, int filterSide){
        double meanR = 0, meanG = 0, meanB = 0;
        int size = filterSide * filterSide;
        int radius = filterSide / 2;
        meanR = 0;
        meanG = 0;
        meanB = 0;
        
        Color pixel;
        for(int j = -radius; j < radius; j++){
            for(int i = -radius; i < radius; i++){
                pixel = new Color(image.getRGB(x + i,  y + j));
                meanR += pixel.getRed(); 
                meanG += pixel.getGreen();
                meanB += pixel.getBlue();
            }
        }

        meanR /= size;
        meanG /= size;
        meanB /= size;
        return new Color((int)meanR,(int)meanG,(int)meanB);
    
    }
    /**
     * This functions adds the match tile into the image based in the position
     * of the match tile and a displacement
     * @param image
     * @param match
     * @param displacement 
     */
    public static void addTile(BufferedImage image, Tile match, DVector displacement){
        Color imagePixel, tilePixel;
        int r, g, b;
        BufferedImage tileImage = match.getContent();
        
        for(int y = 0; y < match.getHeight(); y++){ // tesela y coords
            for (int x = 0; x < match.getWidth(); x++){ // tesela x coords
                
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
    
    /**
     * Adds the tileImage onto the image based on x, y coordinates
     * @param image
     * @param subimage
     * @param x
     * @param y 
     */
    public static void addSubimage(BufferedImage image, BufferedImage tileImage, int iX, int iY){
        Color imagePixel, tilePixel;
        int r, g, b;
 
        for(int y = 0; y < tileImage.getHeight(); y++){ // tesela y coords
            for (int x = 0; x < tileImage.getWidth(); x++){ // tesela x coords
                
                // image coords
                int imageX = x + iX;
                int imageY = y + iY;
                
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
    
    
     /**
     * Composes the tileImage onto the image based on x, y coordinates
     * @param image
     * @param subimage
     * @param x
     * @param y 
     */
    public static void replaceSubimage(BufferedImage image, BufferedImage tileImage, int iX, int iY){
        Color imagePixel, tilePixel;
        int r, g, b;
 
        for(int y = 0; y < tileImage.getHeight(); y++){ // tesela y coords
            for (int x = 0; x < tileImage.getWidth(); x++){ // tesela x coords
                
                // image coords
                int imageX = x + iX;
                int imageY = y + iY;
                
                tilePixel = new Color(tileImage.getRGB(x, y));

                image.setRGB(imageX, imageY, new Color(tilePixel.getRed(),tilePixel.getGreen(),tilePixel.getBlue()).getRGB());
            }
        }
    }
    

}
