/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visorZip;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import visorZip.utils.Unzip;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import javax.imageio.ImageIO;
import visorZip.utils.Sorter;

/**
 *
 * @author hermetico
 */
public class Main {
    Unzip unziper;
    Visor visor;
    int fps = 1000/30;
    public static void main(String[] args){
        Main app = new Main(args);
    }
    
    public Main(String[] args){
        for(String arg: args)
            System.out.println(arg);
        

            
        visor = new Visor();
        unziper = new Unzip("Cubo.zip");
        List<ZipEntry> entries = Sorter.sortEntriesByName(unziper.getPNG());
        //slideShow(1000, entries);


        // kind of video mode
        slideShow(fps, entries);

        this.unziper.close();

    }
    public void slideShow(int time, List<ZipEntry> files)
    {
        try {
            visor.setVisible(true);
            for(ZipEntry entry:files){
                BufferedImage img = null;
                InputStream input;
                img = ImageIO.read(this.unziper.getInputStream(entry));
                visor.show(img);
                Thread.sleep(time);
            }
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(abriImagen.Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
                Logger.getLogger(abriImagen.Main.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
