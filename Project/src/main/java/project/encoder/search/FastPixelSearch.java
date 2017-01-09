/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.encoder.search;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import project.encoder.Tile;
import project.encoder.compare.Comparer;


public class FastPixelSearch extends Searcher{
    public FastPixelSearch(int seekRange, int tWidth, int tHeight, int quality, Comparer comparer) {
        super(seekRange,tWidth, tHeight, quality, comparer);
    }

    @Override
    /**
     * Performs a fast search
     * @param wanted
     * @return 
     */
    public Tile getMatch(Tile wanted, BufferedImage candidateImage) {
        
        int wX = wanted.getX();
        int wY = wanted.getY();
        
        int minX = 0, minY = 0, maxX = candidateImage.getWidth(), maxY = candidateImage.getHeight();
        int cInitX, cInitY, cEndX, cEndY;

        // keeps only the candidates within bounds
        for (int y = -this.seekRange; y <= this.seekRange; y++){
            for (int x = -this.seekRange; x <= this.seekRange; x++){
                
                cInitX = wX + x;
                cInitY = wY + y;
                cEndX = cInitX + tWidth;
                cEndY = cInitY + tHeight;
                
                if( cInitX > minX && cInitY > minY && 
                    cEndX < maxX && cEndY < maxY){
                   Tile candidate = new Tile(cInitX, cInitY, candidateImage.getSubimage(cInitX, cInitY, tWidth, tHeight), wanted.getIndex(), -1, -1);
                   double candidateCorrelation = comparer.compare(wanted, candidate);
                   if (candidateCorrelation < correlation){
                       return candidate;
                   }
                }
            }
        }
        return null;
    }                  
}
