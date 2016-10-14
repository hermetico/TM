/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abriImagen;

import java.awt.image.BufferedImage;
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
            fis.close();
       
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
}
