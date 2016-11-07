/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avcont4;


import LZ77.LZ77;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;


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
            
            parser.checkRelatedParameters();
            
            //System.out.println("Debug Mode: " + parser.getDebug());
            //System.out.println("Help Mode: " + parser.help);
            
        }catch(ParameterException e){
            System.out.println(e.getMessage());
            System.err.println(" Try --help or -h for help");
            System.exit(1);
        }
        
        
        
        //-----------------------Testing------------------
       
        // binario de 10000 cifras aleatorias
        String data = "";
        int numIter = 100001;
        
        for (int i=1; i<numIter; i++){ data += Math.round(Math.random()); }
        parser.setBinaryInput(data);
        //System.out.println("Data: " + data);
        System.out.println("Slide Window:\tInput Window:\tCompressed Length:\tDecompressed length:");
        for (int i =8; i< numIter; i*=2){
            for(int j= 8; j<= i; j*=2){
                parser.setSlideWindow(i);
                parser.setInputWindow(j);
                Main main = new Main(parser);
                main.run();
               
            }
        }
        
        //----------------------END TESTING----------------
        Main main = new Main(parser);
        main.run();
        
        
    }
    public Main(ArgParser args){
        this.args = args;
    }

    public void run(){
        
        
        String data = args.getBinaryInput();
        
        int inputWindowSize = args.getInputWindow();
        int slidingWindowSize = args.getSlideWindow();
        //System.out.println("SW: " + slidingWindowSize);
        //System.out.println("IW: " + inputWindowSize);
        LZ77 compressor = new LZ77();
        String compressed = compressor.compress(data, inputWindowSize, slidingWindowSize);
        String decompressed = compressor.decompress(compressed, inputWindowSize, slidingWindowSize);
        
        System.out.println(slidingWindowSize + "\t" + inputWindowSize + "\t" +  compressed.length() + "\t" + decompressed.length());
        
        
    }
    
}
