package jcommander.tests;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

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
    @Parameter(names={"--length", "-l"})
    int length;
    @Parameter(names={"--pattern", "-p"})
    int pattern;
 
    public static void main(String ... args) {
        Main main = new Main();
        new JCommander(main, args);
        main.run();
    }
 
    public void run() {
        System.out.printf("%d %d", length, pattern);
    }

            
    
}
