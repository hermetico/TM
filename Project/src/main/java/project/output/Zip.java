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
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.*;
import javax.imageio.ImageIO;
import project.encoder.DVector;
import project.encoder.EncodedImage;
import project.encoder.ImageP;
import project.settings.Configuration;
import project.settings.Setup;
public class Zip {
    
    Configuration cf = Configuration.getInstance();
    
    public void zipData(Buffer<EncodedImage> buffer, String path){
        
            path += "." + cf.CODEC_EXTENSION.toString();
        try {
            
            ZipOutputStream zout = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(path)));
            for(int i = 0; i < buffer.getSize(); i++){
                
                EncodedImage eImage = buffer.getIndex(i);
                BufferedImage image = eImage.getImage();
                FileType type = eImage.getFileType();
                
                InputStream in = imageToInputStream(image);
                zout.putNextEntry(new ZipEntry(i + "." + type.toString()));
                
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
    
    public void zipEncodedData(Setup setup, Buffer<EncodedImage> buffer, String path){
        
            path += "." + cf.CODEC_EXTENSION.toString();
        try {
            InputStream stream;
            
            ZipOutputStream zout = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(path)));
            
            // creates a metadata file
            stream = metadataToInputStream(setup);
            zout.putNextEntry(new ZipEntry(cf.MEDATATA_FILE));
            flushStream(zout, stream);
            
            for(int i = 0; i < buffer.getSize(); i++){
                
                EncodedImage eImage = buffer.getIndex(i);
                BufferedImage image = eImage.getImage();
                FileType type = eImage.getFileType();
                
                stream = imageToInputStream(image);
                zout.putNextEntry(new ZipEntry(i + "." + type.toString()));
                flushStream(zout, stream);
                
                // image is P type, need to store the vectors
                if(type == FileType.P){
                    // casting to P to get vectors
                    ImageP pImage = (ImageP) eImage;
                    stream = vectorsToInputStream(pImage.getVectors());
                    
                    zout.putNextEntry(new ZipEntry(i + "." + FileType.VEC.toString()));
                    flushStream(zout, stream);
                }
                

            }
            zout.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Zip.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Zip.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    
    
    public InputStream imageToInputStream(BufferedImage image){
        try {
            
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(image, FileType.JPG.toString(), os);
            
            return new ByteArrayInputStream(os.toByteArray());
        } catch (IOException ex) {
            Logger.getLogger(Zip.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public InputStream metadataToInputStream(Setup setup) throws IOException{
        
        Properties metadata = new Properties();
        
        // so far we only need tile size
        // tile size


        metadata.setProperty("tWidth", Integer.toString(setup.getXPixelsPerTile()));
        metadata.setProperty("tHeight", Integer.toString(setup.getYPixelsPerTile()));
        
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        metadata.store(output, null);
        return new ByteArrayInputStream(output.toByteArray());
    }
    
    public InputStream vectorsToInputStream(List<DVector> vectors){
        StringBuilder result = new StringBuilder();
        String separator = String.valueOf(cf.DATA_SEPARATOR);
        for(DVector vector : vectors)
        {
            result.append(vector.getReference() 
                    + separator 
                    + vector.getX() 
                    + separator 
                    + vector.getY() 
                    + String.valueOf(cf.END_LINE));
        }
        return new ByteArrayInputStream(result.toString().getBytes(StandardCharsets.UTF_8));
    
    }
    
    
    public void flushStream(ZipOutputStream zout, InputStream stream) throws IOException{

        // pump data from file into zip file
        byte[] buf = new byte[1024];
        int len;

        while (( len = stream.read(buf)) > 0) {
            zout.write(buf, 0, len);
        }

    }
}
