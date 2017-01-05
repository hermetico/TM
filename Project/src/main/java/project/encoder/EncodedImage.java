/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.encoder;

import java.awt.image.BufferedImage;
import project.settings.Types.FileType;

public abstract class EncodedImage{
    protected FileType fileType;
    protected BufferedImage image;
    
    public BufferedImage getImage(){return image;}
    
    public FileType getFileType(){return fileType;}
    
}
