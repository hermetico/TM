/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.project.settings;

public class Configuration {
    private static Configuration INSTANCE = new Configuration();
    
    //booleans
    public final boolean TRACE = true;
    public final boolean SORT_INPUT_BY_NAME = true;
    public final boolean EXIT_ON_CLOSE_WINDOW = false;
    
    //ints
    public final int FIRST_DELAY = 200;
            
	
    private Configuration() {};
 
    public static Configuration getInstance() {
        return INSTANCE;
    }
}
