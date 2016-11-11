/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.project.input;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import com.tm.project.settings.Types;

public class Unzip {
    String path;
    ZipFile zf = null;
    
    public Unzip(String path){
        this.path = path;
    }
    
    
    public List<ZipEntry> getEntries(){
        List<ZipEntry>  list = new ArrayList<ZipEntry>();
        String fileName;
        try{
            zf = new ZipFile(new File(this.path));
            Enumeration<? extends ZipEntry> entries = zf.entries();
            while(entries.hasMoreElements()){
                    ZipEntry entry = entries.nextElement();

                    if(!entry.isDirectory()){
                        fileName = entry.getName();
                        if(Types.isAccepted(fileName))
                            list.add(entry);
                    }
            }

        }catch (IOException ex) {
            System.err.println("Error reading the zip file");
            System.err.println(ex.getMessage());
                    
        }
        return list;
    }     
}
