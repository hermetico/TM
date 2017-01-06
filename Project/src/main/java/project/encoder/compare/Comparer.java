/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.encoder.compare;

import project.encoder.Tile;

public interface Comparer {
    double compare(Tile base, Tile destination);
}
