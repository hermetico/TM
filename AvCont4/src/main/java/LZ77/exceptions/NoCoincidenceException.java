/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LZ77.exceptions;

/**
 *
 * @author hermetico
 */
public class NoCoincidenceException extends Exception{
    public NoCoincidenceException(){super();}
    public NoCoincidenceException(String message){super(message);}
}
