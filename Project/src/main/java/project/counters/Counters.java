/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.counters;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import project.misc.Tracer;


public class Counters {
    private Tracer tr = Tracer.getInstance();
    protected List<Long> timestamps;
    
    public Counters(){
        timestamps = new ArrayList<Long>();
    }
    
    public void addTimestamp(){
        timestamps.add(System.nanoTime());
    }
    
    public void addTimestamp(long timestamp){
        timestamps.add(timestamp);
    }
    
    public void flushCounters(String name, String scale){
        if(timestamps.size() > 2){
            long last = timestamps.get(0);
            timestamps.remove(0);
            
            tr.trace("+ Resume of the " + name + ":");
            for(long stamp: timestamps){
                long difference = stamp - last;
                tr.trace(String.format(name +": %.3f" + scale +"\t", toFPS(difference)) + 
                        String.format("Difference with previous: %.3fms\n", toMillis(difference)));
                last = stamp;
            }
        }
        timestamps.clear();
    }

    public float toFPS(long num){
        float framerate = 1000;
        return framerate / toMillis(num);
        
    }
    
    public float toMillis(long num){
        return (float) TimeUnit.NANOSECONDS.toMillis(num);
    }
    
    
}
