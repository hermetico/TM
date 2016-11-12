/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.project.processor;


public class Buffer <T>{
    
    private T[] buffer;
    private int size;
    private int end;
    private int init;
    private int elements;
    
    public Buffer(int size){
        buffer = (T[]) new Object[size];
        init = 0;
        end = 0;
        elements = 0;
        this.size = size;
        
    }
    
    
    public synchronized T get(){
        if (elements == 0) return null;
        T t = buffer[init++];
        init %= size;
        elements--;
        return t;
    }
    
    public synchronized void add(T t){
        buffer[end++] = t;
        end %= size;
        elements++;
    }
    
    public synchronized int getElements(){
        return elements;
    }
    public int getSize(){
        return this.size;
    }

    
}
