/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.processor.filters;

import java.awt.Color;
import java.awt.image.BufferedImage;


public class Negative extends Filter{

    @Override
    
    // negative filter sets pixel value to  255 -pixel value
    public void apply(BufferedImage image) {
        
        int pixelInt;
        Color from, to;
            for(int i = 0; i < image.getWidth(); i++){
                for (int j = 0; j<image.getHeight(); j++){
                    
                    pixelInt = image.getRGB(i,j);
                    from = new Color(pixelInt);
                    to = new Color( 
                            255 - from.getRed(),
                            255 - from.getGreen(),
                            255 - from.getBlue()
                    );
                    image.setRGB(i, j, to.getRGB());
                }
            }
    }
    
}
