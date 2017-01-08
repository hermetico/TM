/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.statistics;

import java.io.File;
import project.misc.Tracer;
import project.settings.Setup;


public class Statistics {
    private long sourceLength;
    private long targetLength;
    private File fSource;
    private File fTarget;
    private Setup setup;
    
    Tracer tr = Tracer.getInstance();
    
    public Statistics(Setup setup){
        this.setup = setup;
        fSource = new File(setup.getInputFilePath());
        sourceLength = fSource.length();
    }
    public void getResults(){
        setTargetSize();
        tr.trace("Source size: " + sourceLength);
        tr.trace("Target size: " + targetLength);
        long percent = 100 * (sourceLength - targetLength)/sourceLength;
        
        tr.trace("Compression ratio improvement: " + percent);
        // Warning, compare with uncompressed input file. 
        // TODO: save inputfile as jpg before compute results to load jpeg compressed input
    }
    public void setTargetSize(){
        
        fTarget = new File(setup.getOutputFilePath());
        targetLength = fTarget.length(); 
    }
}

