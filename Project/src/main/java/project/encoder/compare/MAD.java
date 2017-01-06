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
 * //TODO hacer otra
 */
public class MAD implements Comparer{

    @Override
    public double compare(Tile wanted, Tile candidate) {
        double correlation = 0.;
        BufferedImage b = wanted.getContent();
        BufferedImage d = candidate.getContent();
        
        for (int y = 0; y < wanted.getWidth(); y++) {
            for (int x = 0; x < wanted.getHeight(); x++) {

                Color pixelB = new Color(b.getRGB(x, y));
                Color pixelD = new Color(d.getRGB(x, y));
                double channels = Math.abs(pixelB.getRed() - pixelD.getRed())
                                + Math.abs(pixelB.getGreen() - pixelD.getGreen())
                                + Math.abs(pixelB.getBlue() - pixelD.getBlue());
                // we have 3 channels so we do the mean here
                channels /= 3.0;
                correlation += channels;
            }
        }
        return correlation / wanted.getWidth() * wanted.getHeight();
    }
    
}
