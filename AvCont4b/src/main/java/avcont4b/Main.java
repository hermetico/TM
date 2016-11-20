/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avcont4b;


import LZ77.LZ77;
import LZ77.utils.txtReader;
import static LZ77.utils.txtReader.loadDat;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author ferran
 */
public class Main {
    
    ArgParser args;
    
    public static void main(String[] args) {
        
       
        ArgParser parser = new ArgParser();
        JCommander jcomm;
        try{
            jcomm = new JCommander(parser, args);
            jcomm.setProgramName("AvCont4.jar");
            if (parser.help){ 
                jcomm.usage();
            }
           
            int size = txtReader.cargarTxt(parser.getFileName()).length();
            parser.checkRelatedParameters(size);
            
            //System.out.println("Debug Mode: " + parser.getDebug());
            //System.out.println("Help Mode: " + parser.help);
            
        }catch(ParameterException e){
            System.out.println(e.getMessage());
            System.err.println(" Try --help or -h for help");
            System.exit(1);
        }
        
        
        Main main = new Main(parser);
        
        
        
    }
    public Main(ArgParser args){
        this.args = args;
        if(args.getTest() != 0) test();
        else run();
    }

    public void run(){
        
       String data;
        // Load text file
        if(args.getMode().toUpperCase().equals("C")){
            data = txtReader.cargarTxt(args.getFileName()).toString();
        }else{
            data = loadDat(args.getFileName()).toString();
        }
            
        
        System.out.println("data:   " + data.length());
        
        LZ77 compressor = new LZ77();
        
        int inputWindowSize = args.getInputWindow();
        int slidingWindowSize = args.getSlideWindow();
        
        // check mode to compress or decompress input data
        
        String output;
        StringBuffer sbOutput;
        if ("c".equals(args.getMode())){
              output = compressor.compress(data, inputWindowSize, slidingWindowSize);  
        }else{
              output = compressor.decompress(data, inputWindowSize, slidingWindowSize);
              sbOutput = new StringBuffer(output);          
              output = txtReader.ASCIIbin2string(sbOutput).toString();
        }
        save2File(output);  
        System.out.println("Output: " + output.length());   
    }
    
    public void save2File(String output){
        
        int i = output.length();
        String fileName;
        File file;
        FileWriter fw;
        DataOutputStream os;
        try {
            if(args.getMode().toUpperCase().equals("C")){
                fileName = "out.dat";
            }else{ fileName = "out.txt"; }
            file = new File(fileName);
            fw = new FileWriter(file);
            fw.write(output);
            fw.flush();
            fw.close();
        } catch (FileNotFoundException ex) {        
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
   
    public void test(){
        String data = "";
        int binaryLength = this.args.getTest();
        String compressed;
        LZ77 compressor = new LZ77();
        Random rnd1 = new Random(7);
        for (int i=1; i <= binaryLength; i++){ 
            data += Math.round(rnd1.nextDouble()); 
        }

        System.out.println("Sliding Window: SW");
        System.out.println("Input Window: IW");
        System.out.println("Compressed length: CL");
        System.out.println("Decompressed length: DL");
        System.out.println("Compresion factor: CF");
        System.out.println();
        System.out.println("SW:\tIW:\tCL:\tDL:\tCF:");
        
        for (int  slidingWindow = 32; slidingWindow <= binaryLength; slidingWindow *= 2){ // sliding window
            for(int inputWindow = 32; (inputWindow <= slidingWindow ) && ( inputWindow + slidingWindow <= binaryLength ); inputWindow *= 2){ // inputWindow
                compressed = compressor.compress(data, inputWindow, slidingWindow);
                
                BigDecimal ratio;
                ratio = round(((float)binaryLength / (float)compressed.length()),2);
                
                System.out.print(slidingWindow + "\t" );
                System.out.print(inputWindow + "\t" );
                System.out.print(compressed.length()  + "\t" );
                System.out.print(binaryLength  + "\t" );
                System.out.print(ratio);
                System.out.println();                
               
            }
        }
        
    }
    
     /**
     * rounds number d to n decimalPlaces
     * @param d input number
     * @param decimalPlace number of decimals
     * @return number rounded to n decimal places
     */
    public static BigDecimal round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);       
        return bd;
    }
    
}
