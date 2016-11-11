/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.project.settings;


public class Types{
    public enum Type {
        JPG("jpg"),
        PNG("png"),
        GIF("gif"), 
        BMP("bmp")
        ;
        private final String type;

        private Type(final String type){
            this.type = type;
        }

        @Override
        public String toString(){
            return type;
        }

        public boolean contains (String name){
            return this.equalsTo(name.substring(name.lastIndexOf(".") + 1));
        }
        
        public boolean equalsTo(String other){
            return other.equalsIgnoreCase(type);
        }
    }
    
    public static Type[] validTypes = {Type.JPG, Type.PNG, Type.GIF, Type.BMP};
    
    public static boolean isAccepted(String name){
        for(Type current: validTypes){
            if(current.contains(name)) return true;
        }
        return false;
    }
}
