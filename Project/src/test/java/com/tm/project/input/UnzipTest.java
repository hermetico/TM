/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tm.project.input;

import project.input.Unzip;
import java.util.List;
import java.util.zip.ZipEntry;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hermetico
 */
public class UnzipTest {
    
    public UnzipTest() {
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
     * Test of getEntries method, of class Unzip.
     */
    @Test
    public void testGetEntries() {
        System.out.println("getEntries");
        Unzip instance = new Unzip("Cubo.zip");
        List<ZipEntry> entries = instance.getEntries();
        //assertEquals(expResult, result);
        assertFalse(entries.isEmpty());
        //for(ZipEntry entry: entries){
        //    System.out.println(entry.getName());
        //}
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
