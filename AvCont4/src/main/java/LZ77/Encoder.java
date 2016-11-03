/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LZ77;

import LZ77.exceptions.NoCoincidenceException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hermetico
 */
public class Encoder {

    
   
    //TODO
    public String decode(){ return "";}
    
    
    public  LZ77File encode(String data, int inputWindowSize, int slidingWindowSize){
        int initSlidingWindow = 0;
        int endSlidingWindow = slidingWindowSize;
        int initInputWindow = endSlidingWindow;
        int endInputWindow = initInputWindow + inputWindowSize;

        String header = "", remainder = "";
        List<String> pairs = new ArrayList<String>();
        
        // creates the header based on the size of the sliding window
        header = data.substring(0, slidingWindowSize);
        
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
            
            initSlidingWindow += current.length;
            endSlidingWindow += current.length;   
            initInputWindow += current.length;
            endInputWindow += current.length;
        }
        
        // creates the remainder
        if (initInputWindow < data.length()){
            remainder = data.substring(initInputWindow);
        }
        
        return new LZ77File(header, pairs.toArray(new String[0]), remainder, inputWindowSize, slidingWindowSize);
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

class Pair{
    
    public int length = 0;
    public int distance = 0;
    public Pair(){}
    public Pair(int length, int distance){
        this.length = length;
        this.distance = distance;
    }
    
    public boolean equalsTo(Pair other){
        return this.length == other.length && this.distance == other.distance;
    }
    
    /**
     * Translates the pair to a its binary representation
     * @param inputWindowSize
     * @param slidingWindowSize
     * @return 
     */
    public String translatePieceWith(int inputWindowSize, int slidingWindowSize){
        
        
        String _length = encodePiece(length, inputWindowSize);
        String _distance = encodePiece(distance, slidingWindowSize);
        
        return _length + _distance;
    }
    
    /**
     * Encodes a piece of the pair data based on maxSize
     * if pair == maxSize it returns all 0's
     * @param data
     * @param maxSize
     * @return 
     */
    public String encodePiece(int data, int maxSize){
        String binaryData = Integer.toBinaryString(data);
        String binaryMax = Integer.toBinaryString(maxSize - 1);
        String piece = "";
        
        // case where the piece should be all 0's
        if (binaryData.length() > binaryMax.length())
        {
            for(int i = 0; i < binaryMax.length(); i++) piece += "0";
        }else{
            piece = binaryData;
            for(int i = binaryData.length(); i < binaryMax.length(); i++)
                piece = "0" + piece;
        }
        return piece;
    }
    
    @Override
    public String toString(){
        return "(" + length + "," + distance + ")";
    }
}
