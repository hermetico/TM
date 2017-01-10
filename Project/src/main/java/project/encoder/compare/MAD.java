/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.encoder.compare;

import java.awt.Color;
import java.awt.image.BufferedImage;
import project.encoder.Tile;


public class MAD implements Comparer{
    
    @Override
    public double compare(Tile wanted, Tile candidate) {
        return compare(wanted.getContent(), candidate.getContent());
    }

    @Override
    public double compare(BufferedImage wanted, BufferedImage candidate) {
        int difference = 0;
        
        for (int y = 0; y < wanted.getHeight(); y++) {
            for (int x = 0; x < wanted.getWidth(); x++) {

                Color pixelB = new Color(wanted.getRGB(x, y));
                Color pixelD = new Color(candidate.getRGB(x, y));
                difference += Math.abs(pixelB.getRed() - pixelD.getRed())
                            + Math.abs(pixelB.getGreen() - pixelD.getGreen())
                            + Math.abs(pixelB.getBlue() - pixelD.getBlue());            
            }
        }
        
        int channels = 3;
        //get total number of pixels
        int pixelsOnTile = wanted.getWidth() * wanted.getHeight() * channels;
        
        // get mean absolute difference by pixel 
        double MAD = (double)difference / pixelsOnTile;
        // normalize to 0-1 range
        double result = (double)MAD/255.0;
        return result;
    }
    

}
