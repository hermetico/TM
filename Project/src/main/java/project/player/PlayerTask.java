/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.player;

import project.counters.FPSCounters;
import project.player.window.BaseWindow;
import project.processor.Buffer;
import java.awt.image.BufferedImage;
import java.util.TimerTask;
import project.settings.Configuration;


public class PlayerTask extends TimerTask  {
    BaseWindow window;
    Buffer buffer;
    FPSCounters counters;
    Player player;
    int times;
    Configuration cf;
    
    public PlayerTask( Player player, BaseWindow window, Buffer buffer, FPSCounters counters){
        this.window = window;
        this.buffer = buffer;
        this.counters = counters;
        this.player = player;
        this.times = buffer.getSize();
        cf = Configuration.getInstance();
            
    }
    
    @Override
    public void run() {
        
        if(times == 0) player.close();
        
        BufferedImage img = (BufferedImage) buffer.get();
        
        if(img != null){
            window.draw(img);
            times--;
            counters.addTimestamp();
        }
            
        
    }
    
    
}
