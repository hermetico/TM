/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.encoder.search;

import java.awt.image.BufferedImage;
import java.util.List;
import project.encoder.Tile;
import project.encoder.compare.Comparer;
import project.misc.ImageUtils;

public class FastTileSearch extends Searcher{
    
    public FastTileSearch(int seekRange, int tWidth, int tHeight, int quality, Comparer comparer) {
        super(seekRange,tWidth, tHeight, quality, comparer);
    }

    /**
     * Performs a search and returns the first that applies the correlation
     * @param wanted
     * @return 
     */
    public Tile getMatch(Tile wanted, BufferedImage candidateImage){
        List<Tile> candidates = ImageUtils.tessellate(candidateImage, tHeight, tWidth);
        int col = wanted.getCol();
        int row = wanted.getRow();

        // keeps only the candidates within bounds
        for(Tile candidate: candidates){
            int cCol = candidate.getCol();
            int cRow = candidate.getRow();
            
            // checks if is within bounds
            if( row - cRow <= seekRange &&
                col - cCol <= seekRange &&
                row - cRow <= 0 &&
                col - cCol <= 0){
                
                double candidateCorrelation = comparer.compare(wanted, candidate);
                if(candidateCorrelation < correlation){
                    return candidate;    
                }
                
            }
        }
        // if the result is worse than the specified correlation, returns null
        return null;
    }
    
    
}
