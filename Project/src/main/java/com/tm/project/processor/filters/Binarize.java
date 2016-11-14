/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.project.processor.filters;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author ferran
 */
public class Binarize implements Filter{
    int treshold;
    public Binarize(int treshold){
    this.treshold = treshold;
    }
    
    @Override
    public void apply(BufferedImage image) {
        int pixelInt;
        int value;
        Color from, to;
            for(int i = 0; i < image.getWidth(); i++){
                for (int j = 0; j<image.getHeight(); j++){
                    
                    pixelInt = image.getRGB(i,j);
                    
                    from = new Color(pixelInt);
                    value = (int)((from.getRed()+ from.getGreen() + from.getBlue())/3);
                    if(value<treshold){to = new Color(0,0,0);}
                    else{to = new Color(255,255,255);}      
                    image.setRGB(i, j, to.getRGB());
                }
            } 
    }
    
}
