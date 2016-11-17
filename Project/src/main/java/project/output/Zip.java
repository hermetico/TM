/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.output;
import project.processor.Buffer;
import project.settings.Types.FileType;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.*;
import javax.imageio.ImageIO;
public class Zip {
    
    public void zipData(Buffer<BufferedImage> buffer, String path, FileType fileType){
        
            path += "." + FileType.ZIP.toString();
        try {
            ZipOutputStream zout = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(path)));
            
            for(int i = 0; i < buffer.getSize(); i++){
                BufferedImage image = buffer.getIndex(i);
                InputStream in = toInputStream(image, fileType);
                zout.putNextEntry(new ZipEntry(i + "." + fileType.toString()));
                
                // pump data from file into zip file
		byte[] buf = new byte[1024];
		int len;
		while ((len = in.read(buf)) > 0) {
			zout.write(buf, 0, len);
		}
            }
            zout.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Zip.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Zip.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public InputStream toInputStream(BufferedImage image, FileType fileType){
        try {
            
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(image, fileType.toString(), os);
            
            return new ByteArrayInputStream(os.toByteArray());
        } catch (IOException ex) {
            Logger.getLogger(Zip.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
