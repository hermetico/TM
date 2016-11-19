/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LZ77.utils;


public class Pair {
    
    public int length = 0;
    public int distance = 0;

    public Pair() {
    }

    public Pair(int length, int distance) {
        this.length = length;
        this.distance = distance;
    }

    public boolean equalsTo(Pair other) {
        return this.length == other.length && this.distance == other.distance;
    }

    /**
     * Translates the pair to a its binary representation
     * @param inputWindowSize
     * @param slidingWindowSize
     * @return
     */
    public String translatePieceWith(int inputWindowSize, int slidingWindowSize) {
        String _length = encodePiece(length, inputWindowSize);
        String _distance = encodePiece(distance, slidingWindowSize);
        return _length + _distance;
    }

    /**
     * Encodes a piece of the pair data based on maxSize
     * if pair == maxSize it returns all 0's
     * @param data
     * @param maxSize
     * @return
     */
    public String encodePiece(int data, int maxSize) {
        String binaryData = Integer.toBinaryString(data);
        String binaryMax = Integer.toBinaryString(maxSize - 1);
        String piece = "";
        // case where the piece should be all 0's
        if (binaryData.length() > binaryMax.length()) {
            for (int i = 0; i < binaryMax.length(); i++) {
                piece += "0";
            }
        } else {
            piece = binaryData;
            for (int i = binaryData.length(); i < binaryMax.length(); i++) {
                piece = "0" + piece;
            }
        }
        return piece;
    }

    @Override
    public String toString() {
        return "(" + length + "," + distance + ")";
    }
    
}
