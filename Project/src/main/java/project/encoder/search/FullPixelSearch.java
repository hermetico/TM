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

public class FullPixelSearch extends Searcher{
    
    public FullPixelSearch(int seekRange, int tWidth, int tHeight, int quality, Comparer comparer) {
        super(seekRange,tWidth, tHeight, quality, comparer);
    }

    /**
     * Performs a full search
     * @param wanted
     * @return 
     */
    public Tile getMatch(Tile wanted, BufferedImage candidateImage){
        
        List<Tile> lastCandidates = new ArrayList<Tile>();
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
                   lastCandidates.add(candidate);
                }
            }
        }

        Tile bestCandidate = null;
        double bestCorrelation = 0;

        // loops through the last candidates to get the best one
        for(Tile candidate: lastCandidates){
            
            double candidateCorrelation = comparer.compare(wanted, candidate);

            if(bestCandidate == null || candidateCorrelation < bestCorrelation){
                bestCandidate = candidate;
                bestCorrelation = candidateCorrelation;
            }

        }

        
        if (bestCorrelation < correlation){
            return bestCandidate;
        }
        // if the result is worse than the specified correlation, returns null
        return null;
    }
    
    
}