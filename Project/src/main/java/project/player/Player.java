/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.player;


import project.counters.FPSCounters;
import project.misc.Tracer;
import project.player.window.BaseWindow;
import project.processor.Buffer;
import project.settings.Configuration;
import project.settings.Parsers;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;

public class Player {
    private Configuration config = Configuration.getInstance();
    private Tracer tr = Tracer.getInstance();
    private  BaseWindow window;
    private Timer timer;
    private FPSCounters playerCounters;
    private Buffer buffer;
    private int FPS;
    
    private Player(){
        window = new BaseWindow();
        window.setVisible(true);
        timer  = new Timer();
        playerCounters = new FPSCounters();
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
        playerCounters.flushCounters();
        window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
    }
}
