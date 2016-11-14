/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.processor.filters;

import java.awt.image.BufferedImage;

/**
 *
 * @author ferran
 */
public interface IFilter {
    
    public void apply(BufferedImage image);
    
}
