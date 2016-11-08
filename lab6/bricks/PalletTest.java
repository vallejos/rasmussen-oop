

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class PalletTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class PalletTest
{
    /**
     * Default constructor for test class PalletTest
     */
    public PalletTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        Pallet p1 = new Pallet(10, 5);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void test_getWeight()
    {
        Pallet pallet1 = new Pallet(10, 5);
        assertEquals(198.5, pallet1.getWeight(), 0.1);
    }

    @Test
    public void test_getHeight()
    {
        Pallet pallet1 = new Pallet(10, 5);
        assertEquals(55, pallet1.getHeight(), 0.1);
    }
}


