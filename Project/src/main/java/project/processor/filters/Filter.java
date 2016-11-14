/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.processor.filters;

import java.awt.Color;
import java.awt.image.BufferedImage;

public abstract class Filter implements IFilter{
    
    public BufferedImage addPadding( BufferedImage image, int padding){
        BufferedImage padded;
        Color color;
        int pixelInt;
        // new padded image
        padded = new BufferedImage(image.getWidth()+2*padding, image.getHeight()+2*padding, image.getType());
        // set 0 to all values
        for(int i = 0; i < image.getWidth(); i++){
            for (int j = 0; j<image.getHeight(); j++){
                color = new Color(0,0,0);
                image.setRGB(i, j, color.getRGB());
                }         
            }
        // copy image values to padded image
        for(int i = 0; i < image.getWidth(); i++){
            for (int j = 0; j < image.getHeight(); j++){
                pixelInt = image.getRGB(i,j);
                color = new Color(pixelInt);
                padded.setRGB(i+padding, j+padding, color.getRGB());
                }         
            }
        return padded;
        
    }

    
}
