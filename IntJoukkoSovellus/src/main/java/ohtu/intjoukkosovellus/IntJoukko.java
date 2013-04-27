package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int OLETUSKAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] joukonAlkiot;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        joukonAlkiot = new int[OLETUSKAPASITEETTI];
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        this();
        if (kapasiteetti > 0) {
            joukonAlkiot = new int[kapasiteetti];
        }

    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        this();
        if (kapasiteetti > 0) {
            joukonAlkiot = new int[kapasiteetti];
        }
        if (kasvatuskoko > 0) {
            this.kasvatuskoko = kasvatuskoko;
        }
    }

    public boolean lisaa(int luku) {
        if (kuuluu(luku)) {
            return false;
        }
        if (alkioidenLkm < joukonAlkiot.length) {
            joukonAlkiot[alkioidenLkm] = luku;
            alkioidenLkm++;
            return true;
        }
        kasvataTauluJaLisaaLuku(luku);
        return true;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == joukonAlkiot[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        boolean poistettu = false;
        poistettu = poistaLukuTaulukosta(luku);
        if (poistettu) {
            siirraLuvut();
            return true;
        }
        return false;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        System.arraycopy(vanha, 0, uusi, 0, vanha.length);

    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    
        public String toString() {
        String alkiotMerkkijonona = "{";
        if (alkioidenLkm < 1) {
            return alkiotMerkkijonona + "}";
        }
        for (int i = 0; i < alkioidenLkm; i++) {
            if (i < alkioidenLkm - 1) {
                alkiotMerkkijonona += joukonAlkiot[i] + ", ";
            } else {
                alkiotMerkkijonona += joukonAlkiot[i];
            }
        }
        return alkiotMerkkijonona + "}";
    }

    private void kasvataTauluJaLisaaLuku(int luku) {
        int isompiTaulu[] = new int[alkioidenLkm + OLETUSKASVATUS];
        kopioiTaulukko(joukonAlkiot, isompiTaulu);
        joukonAlkiot = new int[alkioidenLkm + OLETUSKASVATUS];
        kopioiTaulukko(isompiTaulu, joukonAlkiot);
        joukonAlkiot[alkioidenLkm] = luku;
        alkioidenLkm++;
    }

    private void siirraLuvut() {
        int apu;
        for (int j = 0; j < alkioidenLkm - 1; j++) {
            if (joukonAlkiot[j] == 0) {
                apu = joukonAlkiot[j];
                joukonAlkiot[j] = joukonAlkiot[j + 1];
                joukonAlkiot[j + 1] = apu;
            }
        }
        alkioidenLkm--;
    }

    private boolean poistaLukuTaulukosta(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == joukonAlkiot[i]) {
                joukonAlkiot[i] = 0;
                return true;
            }
        }
        return false;
    }
    
    public static IntJoukko erotus(IntJoukko ekaJoukko, IntJoukko tokaJoukko) {
        IntJoukko erotusJoukko = new IntJoukko();
        int[] ekaJoukkoTaulu = ekaJoukko.toIntArray();
        int[] tokaJoukkoTaulu = tokaJoukko.toIntArray();
        for (int i = 0; i < ekaJoukkoTaulu.length; i++) {
            erotusJoukko.lisaa(ekaJoukkoTaulu[i]);
        }
        for (int i = 0; i < tokaJoukkoTaulu.length; i++) {
            erotusJoukko.poista(i);
        }
        return erotusJoukko;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko yhdisteJoukko = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    yhdisteJoukko.lisaa(bTaulu[j]);
                }
            }
        }
        return yhdisteJoukko;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdisteJoukko = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            yhdisteJoukko.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            yhdisteJoukko.lisaa(bTaulu[i]);
        }
        return yhdisteJoukko;
    }

    
    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        System.arraycopy(joukonAlkiot, 0, taulu, 0, taulu.length);
        return taulu;
    }
    
}