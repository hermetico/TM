/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.project.player;

import com.tm.project.misc.Counters;
import com.tm.project.player.window.BaseWindow;
import com.tm.project.processor.Buffer;
import java.awt.image.BufferedImage;
import java.util.TimerTask;


public class PlayerTask extends TimerTask  {
    BaseWindow window;
    Buffer buffer;
    Counters counters;
    Player player;
    int times;
    public PlayerTask( Player player, BaseWindow window, Buffer buffer, Counters counters){
        this.window = window;
        this.buffer = buffer;
        this.counters = counters;
        this.player = player;
        this.times = buffer.getSize();
            
    }
    
    @Override
    public void run() {
        if(times == 0 ) player.close();
        BufferedImage img = (BufferedImage) buffer.get();
        
        if(img != null){
            window.draw(img);
            times--;
            counters.addTimestamp();
        }
            
        
    }
    
    
}
