/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.processor.filters;

import java.awt.Color;
import java.awt.image.BufferedImage;

public abstract class Filter implements IFilter{

    
    /**
     * Adds rectangular frame padd of size padding
     * @param image: input image
     * @param left size of left pad in pixels
     * @param rigth size of rigth pad in pixels
     * @param top size of top pad in pixels
     * @param bottom size of bottom pad in pixels
     * @return  padded output image
     */
    public BufferedImage addPadding( BufferedImage image, int left, int rigth, int top, int bottom){
        
        
        Color color;
        int pixelInt;
        
        // new padded image
        BufferedImage padded = new BufferedImage(image.getWidth()+left+rigth, image.getHeight()+top+bottom, image.getType());
        
        // set 0 to all values
        for(int i = 0; i < padded.getWidth(); i++){
            for (int j = 0; j<padded.getHeight(); j++){
                color = new Color(0,0,0);
                image.setRGB(i, j, color.getRGB());
                }         
            }
        // copy image values to padded image
        for(int i = 0; i < image.getWidth(); i++){
            for (int j = 0; j < image.getHeight(); j++){
                pixelInt = image.getRGB(i,j);
                color = new Color(pixelInt);
                padded.setRGB(i+left, j+top, color.getRGB());
                }         
            }
        
        return padded;
        
    }
    /**
     * removes rectangular fixed size frame around image of size padding
     * @param image original padded input image
     * @param padding size of frame to remove
     * @return unpadded image
     */
    public BufferedImage removePadding( BufferedImage image, int padding){
        

        int i= padding;
        int i1 = padding;
        int i2 = image.getWidth()  - 2 * padding;
        int i3 = image.getHeight() - 2 * padding ;
        return image.getSubimage(i, i1, i2, i3);
    }
         
    // to remove variable pad size use image.getSubimage(x,y,width,height)
    
}
