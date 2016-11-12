/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.project.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Counters {
    
    List<Long> timestamps;
    
    public Counters(){
        timestamps = new ArrayList<Long>();

    }
    
    public void addTimestamp(){
            timestamps.add(System.nanoTime());
    }
    
    public void flushCounters(){
        if(timestamps.size() > 2){
            long last = timestamps.get(0);
            timestamps.remove(0);
            
            System.out.println("+ Resume of the counters:");
            for(long stamp: timestamps){
                long difference = stamp - last;
                System.out.format("FrameRate: %.3fms\t", toFPS(difference));
                System.out.format("Difference with previous: %.3fms\n", toMillis(difference));
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
