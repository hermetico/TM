/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.counters;

public class FPSCounters extends Counters{
    
    public final String NAME = "FPS";
    public final String UNITS = "fps";
    
    public FPSCounters(){
        super();
    }
 
    public void flushCounters(){
        super.flushCounters(NAME, UNITS);
    }

}
