/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.encoder.search;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.IllegalFormatWidthException;
import java.util.List;
import project.encoder.Tile;
import project.encoder.compare.Comparer;
import project.misc.ImageUtils;

/**
 * Search among all the possible candidates and returns the best
 * if the result is better than the quality
 * @author hermetico
 */
public class FullTileSearch extends Searcher{
    List<Tile> candidates;
    BufferedImage frame;
    
    public FullTileSearch(int seekRange, int tWidth, int tHeight, int quality, Comparer comparer) {
        super(seekRange,tWidth, tHeight, quality, comparer);
    }

    /**
     * Resets the current frame in order to tessellate it
     * @param currentFrame 
     */
    @Override
    public void resetFrame(BufferedImage frame) {
        this.frame = frame;
        this.candidates = ImageUtils.tessellate(frame, tHeight, tWidth);
    }

    /**
     * Performs a full search
     * @param wanted
     * @return 
     */
    public Tile getMatch(Tile wanted){
        
        List<Tile> lastCandidates = new ArrayList<Tile>();
        int col = wanted.getCol();
        int row = wanted.getRow();
        

        // keeps only the candidates within bounds
        for(Tile candidate: candidates){
            int cCol = candidate.getCol();
            int cRow = candidate.getRow();
            
            // checks if is within bounds
            if( (Math.abs(row - cRow) <= seekRange ) &&
                (Math.abs(col - cCol) <= seekRange)){
                lastCandidates.add(candidate);
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
        // We use quality 1 to 5 and correlation go from 0 (exact match) to 1 (maximal difference)
        // So using the formula: range = 0.5 - quality/10 will set this values:
        // Quality 1 => correlation < 0.5
        // Quality 2 => correlation < 0.4
        // Quality 3 => correlation < 0.3
        // Quality 4 => correlation < 0.2
        // Quality 5 => correaltion < 0.1
        //correlation greater than this values means not matching tiles 
        double NormalizedValue = 0.5 - (double)correlation/10;   
        if (bestCorrelation < NormalizedValue){
            //tr.trace("New match from origin index " + wanted.getIndex() +" to previous " + bestCandidate.getIndex() +" with correlation " + bestCorrelation);
            return bestCandidate;
        }
        // if the result is worse than the specified correlation, returns null
        return null;
    }
    
    
}
