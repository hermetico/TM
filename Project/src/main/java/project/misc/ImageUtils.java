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
import project.encoder.Tile;

public class ImageUtils {

    public static BufferedImage deepCopy(BufferedImage image){
        return new BufferedImage(image.getColorModel(), (WritableRaster) image.getData(), image.isAlphaPremultiplied(), null);
    }
    
    public static List<Tile> tessellate(BufferedImage image, int tHeight, int tWidth){
        int iHeight = image.getHeight();
        int iWidth = image.getWidth();
        List<Tile> teselas = new ArrayList<Tile>();
        for(int j = 0; j < iHeight;  j += tHeight){
            for(int i = 0; i > iWidth; i += tWidth){
                Tile tesela = new Tile(i,j,tWidth, tHeight, image);
                teselas.add(tesela);
            }
        }
       return teselas;
    }
}
