/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.encoder;

import java.awt.image.BufferedImage;
import java.util.List;
import project.settings.Types;

public class ImageP extends EncodedImage{
    
    private List<DVector> vectors;
        
    private ImageP(){
        this.fileType = Types.FileType.P;
    }
    
    public ImageP(BufferedImage image, List<DVector> vectors){
        this();
        this.image = image;
        this.vectors = vectors;
    }
    
    public List<DVector> getVectors(){
        return vectors;
    }
    
}
