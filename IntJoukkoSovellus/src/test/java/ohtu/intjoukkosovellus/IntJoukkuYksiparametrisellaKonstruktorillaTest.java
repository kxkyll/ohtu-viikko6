
package ohtu.intjoukkosovellus;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class IntJoukkuYksiparametrisellaKonstruktorillaTest extends IntJoukkoTest {
    
    @Before
    @Override
    public void setUp() {
        ekaJoukko = new IntJoukko(3);
        ekaJoukko.lisaa(10);
        ekaJoukko.lisaa(3);
        tokaJoukko = new IntJoukko(3);
        tokaJoukko.lisaa(4);
        tokaJoukko.lisaa(3);
        tyhjaJoukko = new IntJoukko(3);
    }
    
    @Test
    public void luodaanJoukkoKapasiteetillaNollaJoukkoKayttaaOletuskokoa(){
        IntJoukko uusiJoukko = new IntJoukko(0);
        uusiJoukko.lisaa(3);
        assertEquals(1, uusiJoukko.mahtavuus());
    }
    
    // perii kaikki testit luokasta IntJoukkoTest
}
