/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visorImagen;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


/**
 *
 * @author hermetico
 */
public class Main {
        public static void main( String[] args){
        BufferedImage img = null;
        try{
            FileInputStream fis = new FileInputStream("test-image.jpg");
            img = ImageIO.read(fis);
            System.out.println("width: " + img.getWidth() + ", height: " + img.getHeight());
            
            Visor vis = new Visor(img);
            vis.setVisible(true);
            
            // does a deep copy of the image
            BufferedImage copy = new BufferedImage(img.getColorModel(), (WritableRaster) img.getData(), img.isAlphaPremultiplied(), null);
            Visor vis2 = new Visor(alterPixels(copy));
            vis2.setVisible(true);
            
            fis.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(abriImagen.Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
                Logger.getLogger(abriImagen.Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
        
        public static BufferedImage alterPixels(BufferedImage img){
            int pixelInt, r,g,b;
            for(int i = 0; i<img.getWidth(); i++){
                for (int j = 0; j<img.getHeight(); j++){
                    
                    pixelInt = img.getRGB(i,j);
                    r = new Color(pixelInt).getRed();
                    g = 0;
                    b = 0;

                    img.setRGB(i,j, new Color(r,g,b).getRGB());
                }
            }
           return img; 
        }
}
