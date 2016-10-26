/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visorZip.utils;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 *
 * @author hermetico
 */
public class Unzip {
    // The path were the zip file is located
    String path = null;
    ZipFile zf = null;
    
    public Unzip(String path){
        this.path = path;
    }
    
    public Unzip(){};
    
    
    public void logContent(){
        try{
            System.out.println("Content of " + this.path);
            zf = new ZipFile(new File(this.path));

            Enumeration<? extends ZipEntry> entries = zf.entries();
            while(entries.hasMoreElements()){
                    ZipEntry entry = entries.nextElement(); // entry es un flux d'arxiu convencional java (inputStream)
                    // entry podria ser un directorio...
                    String fileName = entry.getName();
                    String isDirectory = entry.isDirectory()? "Folder": "File";
                    System.out.println(isDirectory + ":" + fileName);
            }
            zf.close();
            zf = null;
        }catch (IOException ex) {
            Logger.getLogger(zipReader.Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<ZipEntry> getPNG(){
        List<ZipEntry>  list = new ArrayList<>();
        String type = "png";
        String fileName;
        try{
            zf = new ZipFile(new File(this.path));
            Enumeration<? extends ZipEntry> entries = zf.entries();
            while(entries.hasMoreElements()){
                    ZipEntry entry = entries.nextElement();

                    if(!entry.isDirectory()){
                        fileName = entry.getName();
                        if(isType(fileName, type))
                            list.add(entry);
                    }
            }

        }catch (IOException ex) {
            Logger.getLogger(zipReader.Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    } 
    
    /**
     * Checks if the input zip file exists
     * @return 
     */
    public boolean exists(){
        File f = new File(this.path);
        return (f.exists() && !f.isDirectory());
    }
    
    private boolean isType(String path, String type){
        String fileType = path.substring(path.lastIndexOf(".") + 1);
        return fileType.equalsIgnoreCase(type);
    }
    
    public void setPath(String path){ this.path = path;}
    public String getPath(){return this.path;}
    public ZipFile getZip(){return this.zf;}
    public void close(){
        try{
            this.zf.close();
        }catch (IOException ex) {
            Logger.getLogger(zipReader.Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public InputStream getInputStream(ZipEntry entry){
        try {
            return this.zf.getInputStream(entry);
        } catch (IOException ex) {
            Logger.getLogger(Unzip.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
 }
