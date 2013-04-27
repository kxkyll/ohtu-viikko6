package ohtu.intjoukkosovellus;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class IntJoukkoTest {

    public IntJoukko ekaJoukko;
    public IntJoukko tokaJoukko;
    public IntJoukko tyhjaJoukko;

    @Before
    public void setUp() {
        ekaJoukko = new IntJoukko();
        ekaJoukko.lisaa(10);
        ekaJoukko.lisaa(3);
        tokaJoukko = new IntJoukko();
        tokaJoukko.lisaa(4);
        tokaJoukko.lisaa(3);
        tyhjaJoukko = new IntJoukko();
    }

    @Test
    public void lukujaLisattyMaara() {
        ekaJoukko.lisaa(4);
        assertEquals(3, ekaJoukko.mahtavuus());
    }

@Test
    public void tyhjanJoukonTulostusToimii() {
        
        assertEquals(0, tyhjaJoukko.mahtavuus());
        assertEquals("{}", tyhjaJoukko.toString());
    }

    
    @Test
    public void samaLukuMeneeJoukkoonVaanKerran() {
        ekaJoukko.lisaa(10);
        ekaJoukko.lisaa(3);
        assertEquals(2, ekaJoukko.mahtavuus());
    }

    @Test
    public void vainLisatytLuvutLoytyvat() {
        assertTrue(ekaJoukko.kuuluu(10));
        assertFalse(ekaJoukko.kuuluu(5));
        assertTrue(ekaJoukko.kuuluu(3));
    }

    @Test
    public void poistettuEiOleEnaaJoukossa() {
        ekaJoukko.poista(3);
        assertFalse(ekaJoukko.kuuluu(3));
        assertEquals(1, ekaJoukko.mahtavuus());
    }
    
    @Test
    public void palautetaanOikeaTaulukko() {
        int[] odotettu = {3, 55, 99};
        
        ekaJoukko.lisaa(55);
        ekaJoukko.poista(10);
        ekaJoukko.lisaa(99);

        int[] vastaus = ekaJoukko.toIntArray();
        Arrays.sort(vastaus);
        assertArrayEquals(odotettu, vastaus);
    }
    
    
    @Test
    public void toimiiKasvatuksenJalkeen(){
        int[] lisattavat = {1,2,4,5,6,7,8,9,11,12,13,14};
        for (int luku : lisattavat) {
            ekaJoukko.lisaa(luku);
        }
        assertEquals(14, ekaJoukko.mahtavuus());
        assertTrue(ekaJoukko.kuuluu(11));
        ekaJoukko.poista(11);
        assertFalse(ekaJoukko.kuuluu(11));
        assertEquals(13, ekaJoukko.mahtavuus());
    }
    
    @Test
    public void leikkausToimii(){
        IntJoukko leikkausJoukko;
        leikkausJoukko = IntJoukko.leikkaus(ekaJoukko, tokaJoukko);
        System.out.println("leikkausjoukko: "  +leikkausJoukko.toString());
        
        assertTrue(leikkausJoukko.kuuluu(3));
        assertEquals(1,leikkausJoukko.mahtavuus());
    }
    
    @Test
    public void erotusToimii(){
        IntJoukko erotusJoukko;
        erotusJoukko = IntJoukko.erotus(ekaJoukko, tokaJoukko);
               
        assertTrue(erotusJoukko.kuuluu(10));
        assertEquals(1,erotusJoukko.mahtavuus());
    }
    
    @Test
    public void toStringToimii(){
        assertEquals("{10, 3}", ekaJoukko.toString());
    }
}
