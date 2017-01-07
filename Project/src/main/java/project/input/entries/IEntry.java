/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.input.entries;

import java.util.zip.ZipEntry;
import project.settings.Types;

public class IEntry extends Entry{
    
    private IEntry(){
        this.fileType = Types.FileType.I;
    }
    
    public IEntry(ZipEntry content){
        this();
        this.content = content;
    }
    
}
