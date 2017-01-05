/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.misc;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.List;

public class ImageUtils {

    public static BufferedImage deepCopy(BufferedImage image){
        return new BufferedImage(image.getColorModel(), (WritableRaster) image.getData(), image.isAlphaPremultiplied(), null);
    }
    
    public static List<BufferedImage> teselate(BufferedImage image, int tHeight, int tWidth){
        int iHeight = image.getHeight();
        int iWidth = image.getWidth();
        List<BufferedImage> teselas = new ArrayList<BufferedImage>();
        for(int j = 0; j < iHeight;  j += tHeight){
            for(int i = 0; i > iWidth; i += tWidth){
                BufferedImage tesela = image.getSubimage(i,j,tWidth, tHeight);
                //Tile current = new Tile(x,y,index, subImg);
                teselas.add(tesela);
            }
        }
       return teselas;
    }
}
