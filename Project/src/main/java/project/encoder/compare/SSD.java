/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.encoder.compare;

import java.awt.Color;
import java.awt.image.BufferedImage;
import static java.lang.Math.pow;
import project.encoder.Tile;


public class SSD implements Comparer{
    
    @Override
    public double compare(Tile wanted, Tile candidate) {
        return compare(wanted.getContent(), candidate.getContent());
    }

    @Override
    public double compare(BufferedImage wanted, BufferedImage candidate) {
        double difference = 0;
        for (int y = 0; y < wanted.getHeight(); y++) {
            for (int x = 0; x < wanted.getWidth(); x++) {

                Color pixelB = new Color(wanted.getRGB(x, y));
                Color pixelD = new Color(candidate.getRGB(x, y));
                // These are squared differences
                difference += (pixelB.getRed() - pixelD.getRed()) * (pixelB.getRed() - pixelD.getRed())
                            + (pixelB.getGreen() - pixelD.getGreen()) * (pixelB.getGreen() - pixelD.getGreen())
                            + (pixelB.getBlue() - pixelD.getBlue()) * (pixelB.getBlue() - pixelD.getBlue());            
            }
        }
        difference = pow(difference,0.5);
        int channels = 3;
        //get total number of pixels
        int pixelsOnTile = wanted.getWidth() * wanted.getHeight() * channels;
        // get mean absolute difference by pixel 
        double SSD = (double)difference / pixelsOnTile;
        // normalize to 0 -1 range
        double result = (double)SSD/255.0;
       
        return result;
    }
}
