/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zipReader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.imageio.ImageIO;

/**
 *
 * @author hermetico
 */
public class Main {
    
    public static void main( String[] args){
        ZipFile zf = null;
        try{
                File f = new File("Cubo.zip");
                zf = new ZipFile(f);

                Enumeration<? extends ZipEntry> entries = zf.entries();
                while(entries.hasMoreElements()){
                        ZipEntry entry = entries.nextElement(); // entry es un flux d'arxiu convencional java (inputStream)
                        // entry podria ser un directorio...
                        String imgName = entry.getName();
                        System.out.println(imgName);
                }
                zf.close();
        }   catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}