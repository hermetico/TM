/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.misc;

import project.settings.Configuration;

public class Tracer {
    private static Tracer INSTANCE = new Tracer();
    private Configuration config = Configuration.getInstance();
    private boolean enabled = config.TRACE;
    
    private Tracer(){};
    
    public static Tracer getInstance() {
        return INSTANCE;
    }
    
    public void trace(String text){
        if(enabled) System.out.println(text);
    }
    public void enable(){
        enabled = true;
    }
    public void disable(){
        enabled = false;
    }
    public void toggle(){
        enabled = !enabled;
    }
    
}
