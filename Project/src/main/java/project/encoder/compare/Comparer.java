/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.encoder.compare;

import java.awt.image.BufferedImage;
import project.encoder.Tile;

public interface Comparer {
    boolean compare(Tile tesela, BufferedImage image, int xOffset, int yOffset, int quality);
}
