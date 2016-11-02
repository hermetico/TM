/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compression;

import java.util.List;

/**
 *
 * @author ferran
 */
public class Compressor {
    
    // Input binary stream
    private String binaryInput;
    // Input window
    private String inputWindow;
    // Slide window
    private String slideWindow;    
    // flag checks if compress method has been executed
    boolean uncompressed = true;
    
    // Output compressed binary stream stored as list of strings
    //private List<String> output;
    private String output;
   
    
    // constructor
    
    public Compressor(String binaryChain, String inputWindow, String slideWindow){
        binaryInput = binaryChain;
        this.inputWindow = inputWindow;
        this.slideWindow = slideWindow;
       
    }
    
    
    public String compress(){
        if (!uncompressed){
            return output;
        }
            
        // Initialize current input window
        String ciw;
        ciw = b.substring(i.length(), s.length());
        //Initialize current slide window
        String csw;
        csw = b.substring(0, s.length());
     return b;
        }
    } 
    
    public int[] maxSubstring(String s1, String s2){
        
        
        // search max substring of s1 that can be found in reversed s2
        int[] result = new int[2] ;
        // length of max common subString.
        int L = 0;
        // distance of max common subString in reversed string.
        int D = 0;
        // temp distance, needed to iterate.
        int tempD = 0;
        
        // check 0 length
        if (s1.length()== 0 || s2.length() == 0){
            result[0] = L;
            result[1] = D;
            return result;
        }
        // reverse s2 for convenience
        String rs2 = new StringBuffer(s2).reverse().toString();
        // find long match
        for (int i = 0; i< s1.length(); i++){
            tempD = rs2.indexOf(s1.substring(0, i));
            if (tempD!= -1){
                L = i;
                D = tempD;
            }else{
                break;
            }
        }
        
       result[0] = L ;
       result[1] = D ;
       return result;
    }
}