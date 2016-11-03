/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LZ77;

import java.util.Arrays;



public class LZ77File {
    private String header;
    private String[] coincidences;
    private String remainder;
    private int inputWindowSize;
    private int slidingWindowSize;
    
    public LZ77File(){
        inputWindowSize = 0;
        slidingWindowSize = 0;
        header = "";
        coincidences = new String[0];
        remainder = "";
    }
    
    public LZ77File(String header, String[] coincidences, String remainder, 
            int inputWindowSize, int slidingWindowSize){
        this.header = header;
        this.coincidences = Arrays.copyOf(coincidences, coincidences.length);
        this.remainder = remainder;
        this.inputWindowSize = inputWindowSize;
        this.slidingWindowSize = slidingWindowSize;
    }
    
    public String getHeader(){return header;}
    public String[]getCoincidences(){return Arrays.copyOf(coincidences, coincidences.length);}
    public String getRemainder(){return remainder;}
}
