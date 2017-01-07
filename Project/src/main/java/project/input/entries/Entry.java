/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.input.entries;

import java.util.zip.ZipEntry;
import project.settings.Types.FileType;

public abstract class Entry {
    protected FileType fileType;
    protected ZipEntry content;
    
    public ZipEntry getContent(){return content;}
    
    public FileType getFileType(){return fileType;}
    
}
