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
        
        LZ77 compressor = new LZ77();
        String compressed = compressor.compress(data, inputWindowSize, slidingWindowSize);
        String decompressed = compressor.decompress(compressed, inputWindowSize, slidingWindowSize);
        System.out.println(compressed);
        System.out.println("Decompressed: " + decompressed);
        
    }
    
}
