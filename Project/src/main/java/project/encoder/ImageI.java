/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.encoder;

import java.awt.image.BufferedImage;
import project.settings.Types.FileType;


public class ImageI extends EncodedImage{

    private ImageI(){
        this.fileType = FileType.I;
    }
    public ImageI(BufferedImage image){
        this();
        this.image = image;
    }
    
}
