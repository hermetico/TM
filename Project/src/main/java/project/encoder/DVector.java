/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.encoder;

/**
 * Displacement Vector
 */
public class DVector {
    
    private int reference;
    private int x;
    private int y;

    public DVector(int reference, int x, int y) {
        this.reference = reference;
        this.x = x;
        this.y = y;
    }

    public int getReference() {
        return reference;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    
}
