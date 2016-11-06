/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LZ77;

import LZ77.utils.Pair;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class EncoderTest {
    
    public EncoderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of decompress method, of class LZ77.
     */

    @org.junit.Test
    public void testDecode() {
        System.out.println("decompress");
        String data = "11010001001010110100111011";
        int inputWindowSize = 2;
        int slidingWindowSize = 4;
        LZ77 instance = new LZ77();
        String expResult = "1101110010100111";
        String result = instance.decompress(data, inputWindowSize, slidingWindowSize);
        
        assertEquals(expResult, result);
        
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    
    /**
     * Test of compress method, of class LZ77.
     */
    @org.junit.Test
    @SuppressWarnings("empty-statement")
    public void testEncode() {
        System.out.println("Compress");
        String data = "1101110010100111";
        int inputWindowSize = 2;
        int slidingWindowSize = 4;
        LZ77 instance = new LZ77();
        String result = instance.compress(data, inputWindowSize, slidingWindowSize);
        String expected = "11010001001010110100111011";
        
        assertEquals(result, expected);

        
        inputWindowSize = 4;
        slidingWindowSize = 4;
        instance = new LZ77();
        result = instance.compress(data, inputWindowSize, slidingWindowSize);
        expected = "11011100010110111010101111";

        
        assertEquals(result, expected);
    }


    /**
     * Test of longestCommonSubstring method, of class LZ77.
     */
    @org.junit.Test
    public void testLongestCommonSubstring() {
        System.out.println("longestPrefixIn");
        String a = "aababa";
        String b = "bab";
        LZ77 instance = new LZ77();
        Pair expResult = new Pair(3, 4);
        Pair result = instance.longestPrefixIn(a, b);
        assertTrue(expResult.equalsTo(result));
        
        
        a = "bbaab";
        b = "aab";
        expResult = new Pair(3, 3);
        result = instance.longestPrefixIn(a, b);
        assertTrue(expResult.equalsTo(result));
        
        a = "aaaa";
        b = "bbbb";
        expResult = new Pair(0, 0);
        result = instance.longestPrefixIn(a, b);
        assertTrue(expResult.equalsTo(result));
        
        a = "baabbaab";
        b = "aabb";
        expResult = new Pair(4, 7);
        result = instance.longestPrefixIn(a, b);
        assertTrue(expResult.equalsTo(result));

        a = "aabb";
        b = "aabb";
        expResult = new Pair(4, 4);
        result = instance.longestPrefixIn(a, b);
        assertTrue(expResult.equalsTo(result));
        
        a = "1100";
        b = "10";
        expResult = new Pair(2, 3);
        result = instance.longestPrefixIn(a, b);
        System.out.println(result);
        assertTrue(expResult.equalsTo(result));

    }
     /**
     * Test of testtranslate method, of class LZ77.
     */
    @org.junit.Test
    public void testTranslatePieceWith() {
        System.out.println("translatePieceWith");
        Pair instance = new Pair(1,1);
        String expResult = "01001";
        String result =  instance.translatePieceWith(4,8);
        assertEquals(expResult, result);
        
        instance = new Pair(3,8);
        expResult = "11000";
        result =  instance.translatePieceWith(4,8);
        assertEquals(expResult, result);

        instance = new Pair(4,5);
        expResult = "00101";
        result =  instance.translatePieceWith(4,8);
        assertEquals(expResult, result);        

    }
     /**
     * Test of testEncodePiece method, of class LZ77.
     */
    @org.junit.Test
    public void testEncodePiece() {
        System.out.println("encodePiece");
        Pair instance = new Pair(1,1);
        
        String expResult = "01";
        String result =  instance.encodePiece(1,4);
        
        assertEquals(expResult, result);

        expResult = "00";
        result =  instance.encodePiece(4,4);
        
        assertEquals(expResult, result);

        expResult = "11";
        result =  instance.encodePiece(3,4);
        
        assertEquals(expResult, result);
    }
    
}
