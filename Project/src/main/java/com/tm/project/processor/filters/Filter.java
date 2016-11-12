/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.project.processor.filters;

import java.awt.image.BufferedImage;

public interface Filter {
    public void apply(BufferedImage image);
    
}
