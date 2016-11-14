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
    
    /*
    public void addTimestamp(){
        long current = System.nanoTime();
        long difference;
        StringBuilder text = new StringBuilder(100);
        if(!timestamps.isEmpty()){
            long last = timestamps.get(timestamps.size() - 1);
            difference = current-last;
            
            text.append("\r")
                .append(String.format(NAME + ": %.3f" + UNITS, toFPS(difference)))
                .append(String.format("\tDifference with previous: %.3fms", toMillis(difference)));
            
            System.out.print(text);
        }
        timestamps.add(current);
    }
    */

}
