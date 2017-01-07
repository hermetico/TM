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
        int difference = 0;
        BufferedImage b = wanted.getContent();
        BufferedImage d = candidate.getContent();
        
        for (int y = 0; y < wanted.getWidth(); y++) {
            for (int x = 0; x < wanted.getHeight(); x++) {

                Color pixelB = new Color(b.getRGB(x, y));
                Color pixelD = new Color(d.getRGB(x, y));
                difference += Math.abs(pixelB.getRed() - pixelD.getRed())
                            + Math.abs(pixelB.getGreen() - pixelD.getGreen())
                            + Math.abs(pixelB.getBlue() - pixelD.getBlue());            
            }
        }
        int channels = 3;
        //get total number of pixels
        int pixelsOnTile = wanted.getWidth() * wanted.getHeight() * channels;
        // get mean absolute difference by pixel 
        int MAD = difference / pixelsOnTile;
        // normalize to 0-1 range
        double result = (double)MAD/255.0;
        return result;
    }
}
