/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.project.settings;

public class Parsers {

    public static long fpsToMillisDelay(int fps){
        return (long) 1000 / fps;
    }
}

