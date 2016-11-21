package LZ77.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JDPrades 2009
 */
public class txtReader {

    /**
     * @param input cadena de caracteres (letras) a codificar en ASCII
     * @return output cadena de caracteres "1" y "0" con los códigos ASCII (8bits) de todas las letras de input
     */
    public static StringBuffer string2ASCIIbin(StringBuffer input) {
        int ASCIIrange = 256;
        StringBuffer output = new StringBuffer("");
        for (int i = 0; i < input.length(); ++i) {
            char c = input.charAt(i);
            int j = (int) c;
            output.append(int2bin(j, ASCIIrange - 1));
        //System.out.println(c + " = " + j + " = " + int2bin(j, 256-1) + " = " + (char)Integer.parseInt(int2bin(j, 256-1), 2));
        }

        return (output);
    }
    public static StringBuffer string2ASCIIbin2(StringBuffer input) {
    
        String data = input.toString();
        byte[] dataBytes = null;

        dataBytes = data.getBytes();

        StringBuffer output = new StringBuffer();
        for (byte b : dataBytes)
        {
           int val = b;
           for (int i = 0; i < 8; i++)
           {
              output.append((val & 128) == 0 ? 0 : 1);
              val <<= 1;
           }
        }

        return output; 
    }
    
    /**
     * @param input cadena de caracteres "1" y "0" con los códigos ASCII de todas las letras a decodificar
     * @return output cadena de caracteres (letras) decodificadas
     */
    public static String ASCIIbin2string(StringBuffer input) {
        int ASCIImodulo = 8;
        StringBuffer output = new StringBuffer("");
        for (int i = 0; i <= input.length() - ASCIImodulo; i = i + ASCIImodulo) {
            char c = (char) Integer.parseInt(input.substring(i, i + ASCIImodulo), 2);
            //System.out.println(input.substring(i, i + ASCIImodulo) + " = " + Integer.parseInt(input.substring(i, i + ASCIImodulo), 2) + " = " + c);
            output.append(c);
        }

        return output.toString();
    }

    /**
     * @param path ruta al fichero de texto
     * @return output cadena binaria correspondiente al texto en ASCII
     */
    public static StringBuffer cargarTxt(String path) {
        StringBuffer txt_data = new StringBuffer("");
        FileReader fr;

        try {
            fr = new FileReader(path);
            Scanner scn = new Scanner(fr);
            while (scn.hasNext()) {
                txt_data.append(scn.next() + " ");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(txtReader.class.getName()).log(Level.SEVERE, null, ex);
        }

        //System.out.println("Text d'entrada: " + txt_data);

        return (string2ASCIIbin2(txt_data));
    }
    
    
    /**
     * @param path ruta al fichero de texto
     * @return output cadena binaria correspondiente al texto en ASCII
     */
    public static StringBuffer loadDat(String path) {
        StringBuffer txt_data = new StringBuffer("");
        FileReader fr;

        try {
            fr = new FileReader(path);
            Scanner scn = new Scanner(fr);
            while (scn.hasNext()) {
                txt_data.append(scn.next() + " ");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(txtReader.class.getName()).log(Level.SEVERE, null, ex);
        }

        //System.out.println("Text d'entrada: " + txt_data);

        return txt_data;
    }

    /**
     * @param valor número entero a codificar en binario natural
     * @param maxval valor del màximo entero codificable (determina el número de bits con que se codificara valor)
     * @return output cadena binaria al código binario natural de valor
     */
    public static String int2bin(int valor, int maxval) {
        int numbits = getNumBits(maxval);
        String binstring = Integer.toBinaryString((1 << 31) | (valor));
        return (binstring.substring(binstring.length() - numbits));
    }

    /**
     * @param valor número entero
     * @return output número de bits necesarios para codificar entero en binario natural
     */
    public static int getNumBits(int valor) {
        return (Integer.toBinaryString(valor).length());
    }
}