
package ohtu.intjoukkosovellus;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class IntJoukkoKaksiparametrisellaKonstruktorillaTest extends IntJoukkoTest {
    
    
    @Before
    public void setUp() {
        ekaJoukko = new IntJoukko(4, 2);
        ekaJoukko.lisaa(10);
        ekaJoukko.lisaa(3);
        tokaJoukko = new IntJoukko(4,2);
        tokaJoukko.lisaa(4);
        tokaJoukko.lisaa(3);
        tyhjaJoukko = new IntJoukko(4,2);
    }
    
    @Test
    public void luodaanJoukkoNollaParametreillaJoukkoKayttaaOletusarvoja(){
        IntJoukko nollaJoukko = new IntJoukko(0,0);
        nollaJoukko.lisaa(2);
        nollaJoukko.lisaa(3);
        assertEquals(2, nollaJoukko.mahtavuus());
    }
    
    
}
