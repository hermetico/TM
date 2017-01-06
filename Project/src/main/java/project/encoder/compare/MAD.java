/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.encoder.compare;

import java.awt.Color;
import java.awt.image.BufferedImage;
import project.encoder.Tile;

/**
 * Mean of absolute differences implementation
 */
public class MAD implements Comparer{

    @Override
    public double compare(Tile base, Tile destination) {
        double correlation = 0.;
        BufferedImage b = base.getContent();
        BufferedImage d = destination.getContent();
        
        for (int x = 0; x < base.getWidth(); x++) {
            for (int y = 0; y < base.getHeight(); y++) {

                Color pixelB = new Color(b.getRGB(x, y));
                Color pixelD = new Color(b.getRGB(x, y));
                double channels = Math.abs(pixelB.getRed() - pixelD.getRed())
                                + Math.abs(pixelB.getGreen() - pixelD.getGreen())
                                + Math.abs(pixelB.getBlue() - pixelD.getBlue());
                // we have 3 channels so we do the mean here
                channels /= 3.0;
                correlation += channels;
            }
        }
        return correlation / base.getWidth() * base.getHeight();
    }
    
}
