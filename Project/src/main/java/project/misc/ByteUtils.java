/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.misc;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import project.encoder.DVector;


public class ByteUtils {
    
    private final String BIG_ENDIAN = "be";
    private final String LITTLE_ENDIAN = "le";
    private final int INT_LENGTH = 4;
    private final int SHORT_LENGTH = 2;
    
    public InputStream vectorsToInputStream(List<DVector> vectors)
    {
        List<Byte> buff = new ArrayList<Byte>();
        for(DVector vector : vectors)
        {
            vectorToByte(buff, vector);
        }
    
        // converting list to [], java is magic 
        byte[] result = new byte[buff.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = (byte) buff.get(i);
        }
        return new ByteArrayInputStream(result);        
        
    }
    
    public List<DVector> inputStreamToVectors(InputStream stream){
        
        byte[] bytes = inputStreamToByte(stream);
        int[] numbers = bytesToIntArray(bytes);
        List<DVector> vectors = new ArrayList<DVector>();
        
        for(int i = 0; i < numbers.length; ){
            vectors.add(new DVector(numbers[i++], numbers[i++], numbers[i++]));
            
        }
        return vectors;
    }
    
    private byte[] inputStreamToByte(InputStream is){

        // Define a size if you have an idea of it.
        ByteArrayOutputStream r = new ByteArrayOutputStream();
        byte[] read = new byte[512]; // Your buffer size.
        try {
            for (int i; -1 != (i = is.read(read)); r.write(read, 0, i));
            is.close();
        } catch (IOException ex) {
            Logger.getLogger(ByteUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r.toByteArray();
    }
    
    private int[] bytesToIntArray(byte[] bytes){
        int[] numbers = new int[bytes.length / SHORT_LENGTH];
        
        for(int i = 0, j = 0; i < bytes.length; j++){
            byte[] number = new byte[SHORT_LENGTH];
            number[0]  = bytes[i++];
            number[1]  = bytes[i++];
            //number[2]  = bytes[i++];
            //number[3]  = bytes[i++];
            
            numbers[j] = (int) bytesToShort16(number, BIG_ENDIAN);
            
        }
        return numbers;
    }
    
    private void vectorToByte(List<Byte> buff, DVector vector){
        
        //write_int32(buff, vector.getReference());
        //write_int32(buff, vector.getX());
        //write_int32(buff, vector.getY());
        write_short16(buff, (short)vector.getReference());
        write_short16(buff, (short)vector.getX());
        write_short16(buff, (short)vector.getY());
        
    }
 
    public  void write_int32(List<Byte> buff,  int number)
    {
        byte bytes[] = new byte[INT_LENGTH];
        int32ToBytes(number,bytes, BIG_ENDIAN);
        // try with short
        for(int i = 0; i < INT_LENGTH; i++)
        {
            buff.add(bytes[i]);
        }
    }
    
    public  void write_short16(List<Byte> buff,  short number)
    {
        byte bytes[] = new byte[SHORT_LENGTH];
        short16ToBytes(number,bytes, BIG_ENDIAN);
        // try with short
        for(int i = 0; i < SHORT_LENGTH; i++)
        {
            buff.add(bytes[i]);
        }
    }
    
        
    private int short16ToBytes(short number, byte bytes[], String endianess)
    {
        if(BIG_ENDIAN.equals(endianess.toLowerCase()))
        {
          bytes[0] = (byte)((number >> 8) & 0xFF);
          bytes[1] = (byte)(number & 0xFF);
        }
        else
        {
          bytes[0] = (byte)(number & 0xFF);
          bytes[1] = (byte)((number >> 8) & 0xFF);
         }
        return 4;
    }
    
    private int int32ToBytes(int number,byte bytes[], String endianess)
    {
        if(BIG_ENDIAN.equals(endianess.toLowerCase()))
        {
          bytes[0] = (byte)((number >> 24) & 0xFF);
          bytes[1] = (byte)((number >> 16) & 0xFF);
          bytes[2] = (byte)((number >> 8) & 0xFF);
          bytes[3] = (byte)(number & 0xFF);
        }
        else
        {
          bytes[0] = (byte)(number & 0xFF);
          bytes[1] = (byte)((number >> 8) & 0xFF);
          bytes[2] = (byte)((number >> 16) & 0xFF);
          bytes[3] = (byte)((number >> 24) & 0xFF);
        }
        return 4;
    }
 

    
    
    private int bytesToInt32(byte bytes[], String endianess)
    {
        int number;

        if(BIG_ENDIAN.equals(endianess.toLowerCase()))
        {
            number=((bytes[0] & 0xFF) << 24) | ((bytes[1] & 0xFF) << 16) |
            ((bytes[2] & 0xFF) << 8) | (bytes[3] & 0xFF);
        }
        else
        {
            number=(bytes[0] & 0xFF) | ((bytes[1] & 0xFF) << 8) |
            ((bytes[2] & 0xFF) << 16) | ((bytes[3] & 0xFF) << 24);
        }
        return number;
    }
    private short bytesToShort16(byte bytes[], String endianess)
    {
        short number;

        if(BIG_ENDIAN.equals(endianess.toLowerCase()))
        {
            number = (short) (((bytes[0] & 0xFF) << 8) | (bytes[1] & 0xFF));
        }
        else
        {
            number= (short) ((bytes[0] & 0xFF) | ((bytes[1] & 0xFF) << 8));
        }
        return number;
    }
}
