/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.processor;


public class Buffer <T>{
    
    private T[] buffer;
    private int size;
    private int end;
    private int init;
    private int bufferedElements;
    private int sizeBufferedElements;
    
    public Buffer(int size){
        buffer = (T[]) new Object[size];
        init = 0;
        end = 0;
        bufferedElements = 0;
        sizeBufferedElements = 0;
        this.size = size;
    }
    
    
    public synchronized T get(){
        if (bufferedElements == 0) return null;
        T t = buffer[init++];
        //init %= size;
        bufferedElements--;
        return t;
    }
    
    public synchronized void add(T t){
        buffer[end++] = t;
        bufferedElements++;
        sizeBufferedElements++;
    }
    
    public synchronized int getBufferedElements(){
        return bufferedElements;
    }
    
    public int getSize(){
        return this.size;
    }
    
    public synchronized T getIndex(int i){
        return buffer[i];
    }
    
    /**
     * Resets the buffer in case you want to loop
     * again over the data
     */
    public synchronized void resetGet(){
        init = 0;
        bufferedElements = end;
    }
    
    //// Extended functionalities
    /**
     * Returns T based on the counter, if counter is less than sizeBufferedElements
     * This allows to interact from different instances with the same buffer independently
     * @param counter
     * @return 
     */
    public synchronized T get(int counter){
        if (counter >= sizeBufferedElements) return null;
        T t = buffer[counter];
        return t;
    }
}
