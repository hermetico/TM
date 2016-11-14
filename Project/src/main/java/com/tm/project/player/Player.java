/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.project.player;

import com.tm.project.misc.Counters;
import com.tm.project.misc.Tracer;
import com.tm.project.player.window.BaseWindow;
import com.tm.project.processor.Buffer;
import com.tm.project.settings.Configuration;
import com.tm.project.settings.Parsers;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;

public class Player implements Runnable{
    Configuration config = Configuration.getInstance();
    Tracer tr = Tracer.getInstance();
    BaseWindow window;
    Timer timer;
    Counters playerCounters;
    Buffer buffer;
    int fps;
    PlayerTask task;
    
    public Player(){
        window = new BaseWindow();
        window.setVisible(true);
        timer  = new Timer();
        playerCounters = new Counters();
    }
    
    public void set(Buffer buffer, int fps){
        this.fps = fps;
        this.buffer = buffer;
        this.task = new PlayerTask(this, window, buffer, playerCounters);
        
    }
    
    public void stop(){
        timer.cancel();
        timer.purge();
    };
    
    public void close(){
        this.stop();
        tr.trace("Exiting");
        window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
        playerCounters.flushFPS();
        System.exit(0);
    }

    @Override
    public void run() {
        timer.scheduleAtFixedRate((TimerTask) task, config.FIRST_DELAY, Parsers.fpsToMillisDelay(fps));
    }
    
    
}
