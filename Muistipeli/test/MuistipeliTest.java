/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import muistipeli.Muistipeli;
import org.junit.*;

/**
 *
 * @author jarih
 */
public class MuistipeliTest {
    
    /**
     * 
     */
    public MuistipeliTest() {
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
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test

    /**
     * Test of teeUusiIkkuna method, of class Muistipeli.
     */
    @Test
    public void testTeeUusiIkkuna() {
        System.out.println("teeUusiIkkuna");
        Muistipeli instance = null;
        instance.teeUusiIkkuna();
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class Muistipeli.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Muistipeli.main(args);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
    
    
}
