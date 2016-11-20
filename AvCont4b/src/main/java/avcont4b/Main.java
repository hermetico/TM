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
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


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
    public Main(ArgParser args) {
        this.args = args;
        if(args.getTest() != 0) test();
        else run();
    }

    public void run()  {
        
       String data;
       String output;
       StringBuffer sbOutput;
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
        
        
        if ("c".equals(args.getMode())){
              output = compressor.compress(data, inputWindowSize, slidingWindowSize);  
        }else{
              output = compressor.decompress(data, inputWindowSize, slidingWindowSize);
              sbOutput = new StringBuffer(output);          
              output = txtReader.ASCIIbin2string(sbOutput);
        }  
        try {       
            save2File(output);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Output: " + output.length());   
    }
    
    public void save2File(String output) throws FileNotFoundException {
        
        Writer out = null;
        int i = output.length();
        String fileName;
        if(args.getMode().toUpperCase().equals("C")){
                fileName = "out.dat";
            }else{ fileName = "out.txt"; }
        
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)));
            out.write(output);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {      
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
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
