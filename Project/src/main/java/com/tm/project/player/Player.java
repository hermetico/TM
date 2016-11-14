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

public class Player {
    private Configuration config = Configuration.getInstance();
    private Tracer tr = Tracer.getInstance();
    private  BaseWindow window;
    private Timer timer;
    private Counters playerCounters;
    private Buffer buffer;
    private int FPS;
    
    private Player(){
        window = new BaseWindow();
        window.setVisible(true);
        timer  = new Timer();
        playerCounters = new Counters();
    }
    
    public Player(int fps){
        this();
        this.FPS = fps;
    }
    
    public void setBuffer(Buffer buffer){
        this.buffer = buffer;
    }

    public void setFPS(int fps){
        this.FPS = fps;
    }
    
    public void playLoop(){
        timer.scheduleAtFixedRate(
                (TimerTask) new PlayerTask(this, window, buffer, playerCounters), 
                config.FIRST_DELAY, Parsers.fpsToMillisDelay(FPS));
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
}
