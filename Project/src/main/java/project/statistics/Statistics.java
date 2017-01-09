/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.statistics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import javax.imageio.ImageIO;
import project.encoder.EncodedImage;
import project.input.Unzip;
import project.input.entries.Entry;
import project.misc.Tracer;
import project.output.Zip;
import project.processor.Buffer;
import project.settings.Setup;
import project.settings.Types;


public class Statistics {
    private long sourceLength;
    private long targetLength;
    private File fSource;
    private File fTarget;
    private Setup setup;
    
    Tracer tr = Tracer.getInstance();
    
    public Statistics(Setup setup){
        this.setup = setup;
        fSource = new File(setup.getInputFilePath());
        sourceLength = fSource.length();
        input2JPG();
    }
    public void getResults(){
        setTargetSize();
        tr.trace("Source size: " + sourceLength);
        tr.trace("Target size: " + targetLength);
        long percent = 100 * (sourceLength - targetLength)/sourceLength;
        
        tr.trace("Compression ratio improvement: " + percent);
        // Warning, compare with uncompressed input file. 
        // TODO: save inputfile as jpg before compute results to load jpeg compressed input
    }
    public void setTargetSize(){
        
        fTarget = new File(setup.getOutputFilePath());
        targetLength = fTarget.length(); 
    }
    public void saveJPGinputFile() throws IOException {
        
           
    }
    public void input2JPG() {
        String path = setup.getInputFilePath();
        try {
            
            ZipFile zipFile = new ZipFile(path);
            File zipOut = new File(path + "_jpg.zip");
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipOut));
            
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while(entries.hasMoreElements()){
                ZipEntry entry = entries.nextElement();
                String fileName = entry.getName();
                InputStream stream = zipFile.getInputStream(entry);
                BufferedImage img = ImageIO.read(stream);
                ZipEntry e = new ZipEntry(fileName);
                out.putNextEntry(e);
                ImageIO.write(img, "jpg", out);
                
            }
        } catch (IOException ex) {
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

