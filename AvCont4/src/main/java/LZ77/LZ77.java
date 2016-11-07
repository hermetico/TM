/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LZ77;

import LZ77.exceptions.NoCoincidenceException;
import LZ77.utils.Pair;
import java.util.ArrayList;
import java.util.List;

public class LZ77 {

   
    /**
     * Compute and decompress previously LZ77 compressed binary number 
     * @param data binary number to decompress in string format
     * @param inputWindowSize input window used to compress data
     * @param slidingWindowSize slide window usedto compress data
     * @return data decompressed. String formatted binary number
     */
    public String decompress(String data, int inputWindowSize, int slidingWindowSize){ 
        
        
        // Compute digits size of each window 
        int slideWindowDigits = (int)(Math.log(slidingWindowSize)/Math.log(2));
        int inputWindowDigits = (int)(Math.log(inputWindowSize)/Math.log(2));
        
        // length and distance of current substring
        String currentL, currentD;
        int L,D ;
        
        String header, remainder;
        
        // init header has the size of the sliding window
        header = data.substring(0, slidingWindowSize);
        
        // reminder is the substring to decode
        remainder = data.substring(slidingWindowSize, data.length());
          
        // If reminder length is larger than sum of windows, decode step must be done.
        while (remainder.length() >= inputWindowSize){
            // read L and D
            currentL = remainder.substring(0, inputWindowDigits);
            currentD = remainder.substring(inputWindowDigits, inputWindowDigits + slideWindowDigits);
           
            // transcode L & D to int values
            L = Integer.parseInt(currentL, 2);
            // maxsize of L & D  coded as 0 value
            if (L == 0){ L = inputWindowSize;}
            
            D = Integer.parseInt(currentD, 2);
            if (D == 0){ D = slidingWindowSize;}
            
            // we must search substring of length L at distance D from end to start          
            // select substring to add to header and update header
            
            header += header.substring(header.length()-D , header.length()-D + L);        
       
            // update remainder
            remainder = remainder.substring(inputWindowDigits + slideWindowDigits, remainder.length());                  
        }
        // size of remaing substring is lower than sum of windows so add reamining unprocessed substring
        header = header + remainder;
        
        return header;
    }
    
    /**
     * Compress binary number in string format using LZ77 algorithm
     * @param data Binary number to compress formatted as string
     * @param inputWindowSize 
     * @param slidingWindowSize
     * @return compressed data. String formatted binary number. 
     */
    public  String compress(String data, int inputWindowSize, int slidingWindowSize){
        int initSlidingWindow = 0;
        int endSlidingWindow = slidingWindowSize;
        int initInputWindow = endSlidingWindow;
        int endInputWindow = initInputWindow + inputWindowSize;
        
        String header = "", remainder = "";
        List<String> pairs = new ArrayList<>();
        
        // creates the header based on the size of the sliding window
        header = data.substring(initSlidingWindow, slidingWindowSize);
        
        // creates the pairs
        for(int i = initSlidingWindow; endInputWindow <= data.length(); i++)
        {
            Pair current = longestPrefixIn(
                    data.substring(initSlidingWindow, endSlidingWindow), 
                    data.substring(initInputWindow, endInputWindow));
                
            if (current.length == 0){
                try{
                    throw new NoCoincidenceException("There are no coincidences!!");
                }catch(NoCoincidenceException e)
                {
                    System.out.println(e.getMessage());
                    current.length = 1;
                }
            }
            
            pairs.add(current.translatePieceWith(inputWindowSize, slidingWindowSize));
            
            // moves both windows
            initSlidingWindow += current.length;
            endSlidingWindow += current.length;   
            initInputWindow += current.length;
            endInputWindow += current.length;
        }
        
        // creates the remainder
        if (initInputWindow < data.length()){
            remainder = data.substring(initInputWindow);
        }
        
        // the result is an string
        return header + String.join("", pairs) + remainder;
    }
    
    /**
     * Returns a Pair with the position were the longest prefix of  b 
     * appears in a. if Pair.length == 0, there is no coincidence.
     * @param a
     * @param b
     * @return 
     */
    public Pair longestPrefixIn( String a, String b){
        if (a.length() == 0 || b.length() == 0) {
            return  new Pair();
        }

        int maxLen = 0, maxInit = 0;
        int bIndex = 0, bInit = 0;
        
        // loops looking for the longest prefix of b in a
        // if there are many we want the last one
        for(int aIndex = 0; aIndex < a.length(); aIndex++){
            if (bIndex == b.length()){
                // hemos visto una coincidencia en el paso anterior
                // y es la maxima posible, guardamos y reseteamos
                maxLen = b.length();
                maxInit = bInit;
                
                bIndex = 0;
                bInit = aIndex;
            }
            
            if( a.charAt(aIndex) == b.charAt(bIndex)){
                bIndex++;
            }else{
                // no hay coincidencia, comprobamos si hasta ahora teniamos
                // la mejor, luego reseteamos
                if(bIndex >= maxLen){
                    maxLen = bIndex;
                    maxInit = bInit;
                }
                // movemos b sin perder lo que hay por medio
                aIndex = bInit;
                bIndex = 0;
                bInit++;
            }
        }
        
        if(bIndex >= maxLen) 
        {
            maxLen =  bIndex;
            maxInit = bInit;
            
        }
        // es posible que al salir nos encontraramos con la maxima coincidencia
        return new Pair(maxLen, a.length() - maxInit);
    }
}