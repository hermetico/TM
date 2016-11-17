/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import project.input.ArgsParser;
import project.misc.Tracer;
import project.settings.Setup;



public class Main {
    Tracer tr;
    
    
    public static void main(String[] args){
        Main software = new Main(args);
    }
    
    public Main(String[] args){
        ArgsParser parser = new ArgsParser();
        JCommander jcomm;
        tr = Tracer.getInstance();
        try{
            jcomm = new JCommander(parser, args);
           }catch(ParameterException e){
               System.out.println(e.getMessage());
               System.err.println();
               System.err.println("Try --help or -h for help");
               System.exit(1);
        }
        // checks if encode and/or decode mode is selected
        parser.checkMode();
        
        // do rest of checks from the parser
        // TODO
        
        // Creates a setup for the app
        Setup setup = new Setup(parser);
        
        App app = new App(setup);
                
    }
    
}
