/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.settings;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class Types{
    
    public enum FileType{
        JPG("jpg"),
        PNG("png"),
        GIF("gif"), 
        BMP("bmp"), 
        ZIP("zip")
        ;
        private final String fileType;
        private static final Map<String,FileType> ENUM_MAP;

        private FileType(final String type){
            this.fileType = type;
        }

        static {
            Map<String,FileType> map = new ConcurrentHashMap<String,FileType>();
            for (FileType instance : FileType.values()) {
                map.put(instance.toString().toLowerCase(),instance);
            }
            ENUM_MAP = Collections.unmodifiableMap(map);
        }
        
        // statics
        
        public static FileType get (String type) {
            return ENUM_MAP.get(type);
        }
        
        public static FileType getFromFileName(String fileName){
            return FileType.get(
                    fileName.substring(fileName.lastIndexOf(".") + 1));
        }
        
        public static boolean isAcceptedFile(String fileName){
            return FileType.isAcceptedFileType(
                    fileName.substring(fileName.lastIndexOf(".") + 1));
        }
        
        public static boolean isAcceptedFileType(String type){
            return ENUM_MAP.containsKey(type.toLowerCase());
        }
        
        
        
        @Override
        public String toString(){
            return fileType;
        }
        
        public boolean contains (String name){
            
            return this.equalsTo(name.substring(name.lastIndexOf(".") + 1));
        }
        
        public boolean equalsTo(String other){
            return other.equalsIgnoreCase(fileType);
        }
        

    }
    
    public enum FilterType{
        NEGATIVE("negative"),
        BINARIZATION("binarization"),
        AVERAGE("average"),
        NONE("NONE")
        ;
        
        private final String filterType;
        private static final Map<String,FilterType> ENUM_MAP;

        private FilterType(final String type){
            this.filterType = type;
        }

        static {
            Map<String,FilterType> map = new ConcurrentHashMap<String,FilterType>();
            for (FilterType instance : FilterType.values()) {
                map.put(instance.toString().toLowerCase(),instance);
            }
            ENUM_MAP = Collections.unmodifiableMap(map);
        }
        
        // statics
        
        public static FilterType get (String name) {
            return ENUM_MAP.get(name);
        }
        
        public static boolean isAcceptedFilter(String fileName){
            return FilterType.isAcceptedFilterType(
                    fileName.substring(fileName.lastIndexOf("-") + 1));
        }
        
        public static boolean isAcceptedFilterType(String type){
            return ENUM_MAP.containsKey(type.toLowerCase());
        }
        
        
        
        @Override
        public String toString(){
            return filterType;
        }
        
        public boolean contains (String name){
            
            return this.equalsTo(name.substring(name.lastIndexOf(".") + 1));
        }
        
        public boolean equalsTo(String other){
            return other.equalsIgnoreCase(filterType);
        }
        

    }
    
}
