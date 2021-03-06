/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.statistics;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import javax.imageio.ImageIO;
import project.input.Unzip;
import project.misc.Tracer;
import project.settings.Setup;


public class Statistics {
    private long sourceLength = 0;
    private long sourceCompressedLength = 0;
    private long targetLength = 0;
    private long time_start, time_end;
    
    private Setup setup; 
    
    Tracer tr = Tracer.getInstance();
    
    public Statistics(Setup setup){
        this.setup = setup;
        sourceLength = getSize(setup.getInputFilePath());      
        source2JPG();
        sourceCompressedLength = getSize(compressedFileName(setup.getInputFilePath(),"_jpg.zip"));
        
    }
    // use start, end and getTime() to check how many time spends a task
    public void timeStart(){
        time_start = System.currentTimeMillis();
    }
    public void timeEnd(){
        time_end = System.currentTimeMillis();
    }
    public long getTime(){
        return time_end - time_start;
    }
    //prints message at str + time spent
    public void printTime(String str){
        System.out.println(str +" "+ round((double)(getTime()/1000.0), 2) + "s");
    }
   
    public void getResults(){
        targetLength = getSize(setup.getOutputFilePath());
        double percentUncompressed = round(100.0 * (sourceLength - targetLength)/sourceLength, 2);
        double percentCompressed = round(100.0 * ( sourceCompressedLength - targetLength)/sourceCompressedLength, 2);
        
        if (setup.isTesting()){
            System.out.print(percentUncompressed + "\t");
            System.out.print(percentCompressed + "\t" );
            System.out.println(round((double)(getTime()/1000.0), 2));
        }else{

            System.out.println("Source uncompressed size: " + sourceLength);
            System.out.println("Source compressed size(jpg): " + sourceCompressedLength );
            System.out.println("Target size: " + targetLength);

            System.out.println("Improvement over uncompressed file: " + percentUncompressed + "%");
            System.out.println("Improvement over compressed file(jpg): " + percentCompressed + "%");
            // prints message + time spent
            printTime("the task has taken");
        }
       
    }
    
    public void getSynthResults(){
        targetLength = getSize(setup.getOutputFilePath());
        double jpgSize = sourceCompressedLength / 1000000.;
        double targetSize = targetLength / 1000000.;
        double percentCompressed = round(100.0 * ( sourceCompressedLength - targetLength)/sourceCompressedLength, 2);
        double spent = round((double)(getTime()/1000.0), 2);
        System.out.format("%.2f\t",jpgSize);
        System.out.format("%.2f\t", targetSize);
        System.out.format("%.1f\t",percentCompressed);
        System.out.format("%.0f", spent);
        
        
    }
    
    private long getSize(String fileName){
        File f = new File(fileName);
        return f.length(); 
        
    }
    private void source2JPG() {
        String path = setup.getInputFilePath();
        try {
            
            ZipFile zipFile = new ZipFile(path);           
            File zipOut = new File(compressedFileName(path,"_jpg.zip"));
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipOut));
            
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while(entries.hasMoreElements()){
         
                ZipEntry entry = entries.nextElement();
                
                String fileName = entry.getName();             
                InputStream stream = zipFile.getInputStream(entry);
                
                if(entry.isDirectory()){                  
                    ZipEntry e = new ZipEntry(fileName);
                    out.putNextEntry(e);
                    out.closeEntry();
                    
                }else{
                    BufferedImage img = ImageIO.read(stream);
                    
                    
                    BufferedImage copy = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
                    copy.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
                    
                    
                    ZipEntry e = new ZipEntry(compressedFileName(fileName, ".jpg"));
                    
                    out.putNextEntry(e);
                    ImageIO.write(copy, "jpg", out);   
                    out.closeEntry();
                    out.flush();
                }
            }         
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    // read name.ext and tail and returns name.tail
    private String compressedFileName(String fileName, String tail){
        String str = fileName.substring(0, fileName.lastIndexOf("."));
        return str + tail;
    }
    public static double round(double value, int places) {
    if (places < 0) throw new IllegalArgumentException();

    BigDecimal bd = new BigDecimal(value);
    bd = bd.setScale(places, RoundingMode.HALF_UP);
    return bd.doubleValue();
}
}

