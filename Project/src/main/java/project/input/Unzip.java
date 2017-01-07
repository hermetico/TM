/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.input;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import project.settings.Types.FileType;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import project.encoder.DVector;
import project.input.entries.Entry;
import project.input.entries.IEntry;
import project.input.entries.PEntry;
import project.misc.Tracer;
import project.settings.Configuration;
import project.settings.Metadata;

public class Unzip {
    String path;
    ZipFile zf = null;
    ZipEntry metadataEntry = null;
    Metadata metadata = null;
    List<Entry> allEntries;
    Configuration cf = Configuration.getInstance();
    Tracer tr =  Tracer.getInstance();
    public Unzip(String path){
        this.path = path;
    }
    
    public List<Entry> getEntries(){
        List<ZipEntry> imageEntries = new ArrayList<ZipEntry>();
        List<ZipEntry> vectorEntries = new ArrayList<ZipEntry>();
        
        String fileName;
        try{
            zf = new ZipFile(new File(this.path));
            Enumeration<? extends ZipEntry> entries = zf.entries();
            while(entries.hasMoreElements()){
                    ZipEntry entry = entries.nextElement();
                    if(!entry.isDirectory()){
                        fileName = entry.getName();
                        if(FileType.isAcceptedFile(fileName)){
                            FileType type = FileType.getFromFileName(fileName);
                            if (type == FileType.META){
                                metadataEntry = entry;
                            }else if(type == FileType.VEC){
                                vectorEntries.add(entry);
                            }else if(FileType.isAcceptedFrame(type)){
                                imageEntries.add(entry);
                            }
                        }
                    }
            }
            
            if(cf.SORT_INPUT_BY_NAME){
                tr.trace("Sorting input data by name");
                imageEntries = Sorter.sortEntriesByName(imageEntries);
                vectorEntries = Sorter.sortEntriesByName(vectorEntries);
            }
            
            // once we have all the ZipEntries, stores them as Entries
            allEntries = new ArrayList<Entry>();
            
            for(ZipEntry entry : imageEntries){
                fileName = entry.getName();
                FileType type = FileType.getFromFileName(fileName);

                Entry nEntry;
                        
                if(FileType.isPFrame(type)){
                    nEntry = (Entry) new PEntry(entry, vectorEntries.remove(0));
                }else{
                    nEntry = (Entry) new IEntry(entry);
                }
                
                allEntries.add(nEntry);
            }
            
            if(metadataEntry != null) parseMetadata();

        }catch (IOException ex) {
            System.err.println("Error reading the zip file");
            System.err.println(ex.getMessage());
                    
        }
        return allEntries;
    }
    

    public InputStream unzipEntry(ZipEntry entry){
        try {
            return zf.getInputStream(entry);
        } catch (IOException ex) {
            System.err.println("Error unzipping the entry");
            System.err.println(ex.getMessage());
        }
        return null;
    }
    
    public BufferedImage unzipImageEntry(ZipEntry entry){
        try {
            return ImageIO.read(unzipEntry(entry));
        } catch (IOException ex) {
            System.err.println("Error unzipping the image");
            System.err.println(ex.getMessage());
        }
        return null;
    }
    
    public List<DVector> unzipVectorsEntry(ZipEntry entry){

        try {
            List<DVector> vectors = new ArrayList<DVector>();
            InputStream data = unzipEntry(entry);
            BufferedReader in = new BufferedReader(new InputStreamReader(data));
            String separator = String.valueOf(cf.DATA_SEPARATOR);
            String line = null;
            
            while((line = in.readLine()) != null) {
                String[] values = line.split(separator);
                
                int reference = Integer.parseInt(values[0]);
                int x = Integer.parseInt(values[1]);
                int y = Integer.parseInt(values[2]);
                
                vectors.add(new DVector(reference, x, y));
            }
            
            return vectors;
            
        } catch (IOException ex) {
            Logger.getLogger(Unzip.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void parseMetadata() throws IOException{
        InputStream data = unzipEntry(metadataEntry);
        Properties props = new Properties();
        props.load( data );
        
        metadata = new Metadata();
        metadata.tHeight = Integer.parseInt(props.getProperty("tHeight"));
        metadata.tWidth = Integer.parseInt(props.getProperty("tWidth"));

    }
    
    public Metadata getMetadata(){
        return metadata;
    }
}
