/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import muistipeli.Kortit;
import muistipeli.Pelipohja;
import static org.junit.Assert.*;
import org.junit.*;

/**
 *
 * @author jarih
 */
public class KortitTest {
    
    /**
     * 
     */
    public KortitTest() {
    }

    /**
     * 
     * @throws Exception
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    /**
     * 
     * @throws Exception
     */
    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    /**
     * 
     */
    @Before
    public void setUp() {
    }
    
    /**
     * 
     */
    @After
    public void tearDown() {
    }

    /**
     * Test of kortinnumero method, of class Kortit.
     */
    @Test
    public void testKortinnumero() {
        System.out.println("kortinnumero");
        Kortit instance = null;
        int expResult = 0;
        int result = instance.kortinnumero();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pelipohja method, of class Kortit.
     */
    @Test
    public void testPelipohja() {
        System.out.println("pelipohja");
        Kortit instance = null;
        Pelipohja expResult = null;
        Pelipohja result = instance.pelipohja();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of onKaannetty method, of class Kortit.
     */
    @Test
    public void testOnKaannetty() {
        System.out.println("onKaannetty");
        Kortit instance = null;
        boolean expResult = false;
        boolean result = instance.onKaannetty();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of kaannaKuvaEsiin method, of class Kortit.
     */
    @Test
    public void testKaannaKuvaEsiin() {
        System.out.println("kaannaKuvaEsiin");
        Kortit instance = null;
        instance.kaannaKuvaEsiin();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of piilotaKuva method, of class Kortit.
     */
    @Test
    public void testPiilotaKuva() {
        System.out.println("piilotaKuva");
        Kortit instance = null;
        instance.piilotaKuva();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
