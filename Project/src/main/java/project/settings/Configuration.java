/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.settings;

import project.settings.Types.FileType;

public class Configuration {
    private static Configuration INSTANCE = new Configuration();
    
    //booleans
    public boolean TRACE = false;
    public final boolean SORT_INPUT_BY_NAME = true;
    
    public final boolean PROCESSING_COUNTERS = false;
    public final boolean LIVE_COUNTERS = false;
    public boolean LOOP_PLAY = true;
    public boolean EXIT_ON_CLOSE_WINDOW = LOOP_PLAY;
    
    //ints
    public final long FIRST_DELAY = 200;
    
    public final FileType CODEC_EXTENSION = FileType.ZIP;
    
    public final String MEDATATA_FILE = "metadata."  + FileType.META.toString();
    public final char DATA_SEPARATOR = ',';
    public final char END_LINE = '\n';
    private Configuration() {};
    
    public void enableTracer(){
        TRACE = true;
    }
 
    public static Configuration getInstance() {
        return INSTANCE;
    }
}
