/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avcont4;


import LZ77.LZ77;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import java.util.Random;


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
        
        
        Main main = new Main(parser);
        
        
        
    }
    public Main(ArgParser args){
        this.args = args;
        if(args.getTest() != 0) test();
        else run();
    }

    public void run(){
        
        
        String data = args.getBinaryInput();
        
        int inputWindowSize = args.getInputWindow();
        int slidingWindowSize = args.getSlideWindow();
        System.out.println("SW: " + slidingWindowSize);
        System.out.println("IW: " + inputWindowSize);
        LZ77 compressor = new LZ77();
        String compressed = compressor.compress(data, inputWindowSize, slidingWindowSize);
        String decompressed = compressor.decompress(compressed, inputWindowSize, slidingWindowSize);
        System.out.print(slidingWindowSize + "\t" );
        System.out.print(inputWindowSize + "\t" );
        System.out.print(((float)decompressed.length() / (float)compressed.length()) + "\t" );
        System.out.print(compressed.length()  + "\t" );
        System.out.print(decompressed.length()  + "\n" );
      
        
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

        System.out.println("Slide Window:\tInput Window:\tCompressed Length:\tDecompressed length:\tCompression Factor:");
        //System.out.println("Slide Window:\tInput Window:\tCompression Factor:");
        for (int  slidingWindow = 32; slidingWindow <= binaryLength; slidingWindow *= 2){ // sliding window
            for(int inputWindow = 32; (inputWindow <= slidingWindow ) && ( inputWindow + slidingWindow <= binaryLength ); inputWindow *= 2){ // inputWindow
                compressed = compressor.compress(data, inputWindow, slidingWindow);
                
                System.out.print(slidingWindow + "\t" );
                System.out.print(inputWindow + "\t" );
                System.out.print(compressed.length()  + "\t" );
                System.out.print(binaryLength  + "\t" );
                System.out.print(((float)binaryLength / (float)compressed.length()) + "\n" );                
               
            }
        }
        
    }
    
}
