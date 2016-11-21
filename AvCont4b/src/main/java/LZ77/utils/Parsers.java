/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LZ77.utils;

import java.util.concurrent.TimeUnit;

public class Parsers {
    

    public static float nanoToMillis(long num){
        return (float) TimeUnit.NANOSECONDS.toMillis(num);
    }
    
}
