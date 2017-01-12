/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import static java.lang.System.exit;
import project.input.ArgsParser;
import project.misc.Tracer;
import project.settings.Setup;


public class Main {
    Tracer tr;

    public static void main(String[] args){
        new Main(args);
    }
    
    public Main(String[] args){
        ArgsParser parser = new ArgsParser();
        JCommander jcomm;     
        
        try{
            jcomm = new JCommander(parser, args);
            if (parser.help){ jcomm.usage(); exit(0);}
           }catch(ParameterException e){
               System.out.println(e.getMessage());
               System.err.println();
               System.err.println("Try --help or -h for help");
               System.exit(1);
        }
              
        // Creates a setup for the app
        Setup setup = new Setup(parser);
        App app = new App(setup);
    }
    
}
