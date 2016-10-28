package jcommander.tests;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import utils.FileExists;
import utils.NumInRange;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hermetico
 */
public class Main {
    @Parameter(names={"--length", "-l"}, description="The length of something")
    private int length;
    @Parameter(names={"--pattern", "-p"}, description="The pattern of something")
    private int pattern;
    @Parameter(names="--debug", description="Enters debug mode")
    private boolean debug = false;
    @Parameter(names="--help", description="Help mode", help=true) 
    // help=true define caracteristicas de ayuda de la libreria
    // que muestran la ayuda por consola
    private boolean help = false;
    
    // parametro obligatorio
    @Parameter(
            names={"--inputZip", "-i"},
            required=true,
            validateWith = FileExists.class
    )
    private String name;
                // parametro obligatorio
    @Parameter(
            names={"--numero", "-n"},
            required=true,
            validateWith = NumInRange.class
    )
    private int num;
    JCommander jComm;
    
    public static void main(String ... args) {
        Main main = new Main(args);

    }
 
    public Main(String ... args){
        jComm = new JCommander(this);
        jComm.setProgramName("Awesome Encoder");
        
        try{
            jComm.parse(args);
            
            if (help){
                jComm.usage();
                System.exit(0);
            }
            System.out.printf("%d %d", length, pattern);
        }catch(ParameterException e) {
            System.out.println("Argument error: " + e.getMessage());
            jComm.usage();
            System.exit(1);
        }
    }


            
    
}
