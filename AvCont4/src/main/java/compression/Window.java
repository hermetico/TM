/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compression;

/**
 *
 * @author ferran
 */
public class Window {
    private int start = 0;
    private int end = 0;
    
    
    public Window(){}
    public Window(int start, int end){
        this.start = start;
        this.end = end;
    }
    public void setStart(int s){start = s;}
    public void setEnd(int e){end = e;}
    public int getStart(){return start;}
    public int getEnd(){return end;}
}
