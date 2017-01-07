/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.input.entries;

import java.util.zip.ZipEntry;
import project.settings.Types;


public class PEntry extends Entry{
    private ZipEntry vectors;
        
    private PEntry(){
        this.fileType = Types.FileType.P;
    }
    
    public PEntry(ZipEntry content, ZipEntry vectors){
        this();
        this.content = content;
        this.vectors = vectors;
    }
    
    public ZipEntry getVectors(){
        return vectors;
    }
}
