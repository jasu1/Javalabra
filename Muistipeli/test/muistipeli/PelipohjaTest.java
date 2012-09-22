/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package muistipeli;

import javax.swing.JLabel;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author jarih
 */
public class PelipohjaTest {
    
    public PelipohjaTest() {
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
     * Test of uusiPeli method, of class Pelipohja.
     */
    @Test
    public void testUusiPeli() {
        System.out.println("uusiPeli");
        int merkkilaji = 0;
        Pelipohja instance = null;
        instance.uusiPeli(merkkilaji);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of onkoRatkaistu method, of class Pelipohja.
     */
    @Test
    public void testOnkoRatkaistu() {
        System.out.println("onkoRatkaistu");
        Pelipohja instance = null;
        boolean expResult = false;
        boolean result = instance.onkoRatkaistu();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of onkoAloitettu method, of class Pelipohja.
     */
    @Test
    public void testOnkoAloitettu() {
        System.out.println("onkoAloitettu");
        Pelipohja instance = null;
        boolean expResult = false;
        boolean result = instance.onkoAloitettu();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of naytaArvaukset method, of class Pelipohja.
     */
    @Test
    public void testNaytaArvaukset() {
        System.out.println("naytaArvaukset");
        Pelipohja instance = null;
        JLabel expResult = null;
        JLabel result = instance.naytaArvaukset();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of naytaParit method, of class Pelipohja.
     */
    @Test
    public void testNaytaParit() {
        System.out.println("naytaParit");
        Pelipohja instance = null;
        JLabel expResult = null;
        JLabel result = instance.naytaParit();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
