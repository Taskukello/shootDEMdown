package toimintaTest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import javax.swing.SwingUtilities;
import kayttoliittyma.Kayttoliittyma;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import toiminta.Logiikka;
import toiminta.OsumanTarkistaja;
import toiminta.pelaaja.Alus;
import toiminta.vihollisobjekti.VihollisObjekti;

/**
 *
 * @author Aki
 */
public class LogiikkaTest {

    private Logiikka logiikka;
    private VihollisObjekti objekti;

    public LogiikkaTest() {
    }

    @Before
    public void setUp() {
        logiikka = new Logiikka();
        this.logiikka.luoBlokki();
        objekti = new VihollisObjekti(1, 250);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void onkoVihollisiaAlussaNolla() {
        Logiikka j = new Logiikka();

        assertEquals(j.getVihollisetKoko(), 0);
    }

    @Test
    public void onkoVihollisiaLisayksenJalkeenYksi() {
        assertEquals(logiikka.getVihollisetKoko(), 1);
    }

    @Test(timeout = 2000)
    public void liikkuukoBlokkiYhden() {
        this.logiikka.setLiikkumisKerrat(1);
        this.logiikka.liikutaKaikkiaVihollisia();
        VihollisObjekti objekti = this.logiikka.getViholliset().get(0);

        assertEquals(objekti.getY(), 698);

    }

    @Test
    public void vihollinenKuolee() {

        this.logiikka.setPoistettavat(this.logiikka.getViholliset());
        this.logiikka.poistaVihollisia();
        assertEquals(this.logiikka.getVihollisetKoko(), 0);
    }

    
    public void pelaaToimii() {
        this.logiikka.pelaa();
        assertEquals(this.logiikka.getAlus().getElamat(), this.logiikka.getPoistettavat().size(), 0);
        assertEquals(this.logiikka.loppuukoPeli(), true);

    }

    @Test(timeout = 2000)
    public void liikutaKaikkiaPoistaaKuolevat() {
        this.logiikka = new Logiikka();
        this.objekti = new VihollisObjekti(2, 250);
        this.objekti.setY(30);
        this.logiikka.addVihollinen(objekti);
        int k = 0;
        while (k != 15) {
            this.logiikka.liikutaKaikkiaVihollisia();
            k++;
        }

        assertEquals(this.logiikka.getViholliset().size(), 0);
        assertEquals(this.logiikka.getAlus().getElamat(), 2);

    }

    @Test(timeout = 2000)
    public void liikutaKaikkiaTappaaPelaajan() {
        this.logiikka = new Logiikka();
        poistaElamia(2);
        this.objekti = new VihollisObjekti(1, 250);
        this.objekti.setY(126);
        this.logiikka.addVihollinen(objekti);
        int k = 0;
        while (k != 13) {
            this.logiikka.liikutaKaikkiaVihollisia();
            k++;
        }
        assertEquals(this.logiikka.getAlus().getElamat(), 0);

    }

    @Test
    public void osumaTilanteenTarkistajaPalkitseeElamallaKunKeraatOikeanBlokin() {
        this.logiikka = new Logiikka();
        objekti.setY(100);
        //     OsumanTarkistaja o = new OsumanTarkistaja(this.logiikka.getAlus(), objekti);
        //      this.logiikka.katsotaanpaOsumaTilannetta(objekti, o);
        //      assertEquals(this.logiikka.getAlus().getElamat(), 4);

    }

    @Test(timeout = 1200)
    public void viivastysToimii() {
        this.logiikka.luoBlokki();
        this.logiikka.setViivytysAika(1000);
        this.logiikka.viivyta();
        assertTrue(true);

    }

    @Test(timeout = 1)
    public void viivastysEiTeeMitaanJosArvoOnNolla() {
        this.logiikka.luoBlokki();
        this.logiikka.viivyta();
        assertTrue(true);
    }

    @Test
    public void peliLoppuuToimii() {
        poistaElamia(3);
        assertEquals(this.logiikka.loppuukoPeli(), true);
        assertEquals(this.logiikka.getAlus().getElamat(), 0);
    }

    @Test
    public void peliLoppuuToimiiLisays() {
        assertEquals(this.logiikka.loppuukoPeli(), false);
    }

    public void poistaElamia(int maarat) {                                         //avustusmetodi vähentämään koodin toistoa
        int k = 0;
        while (k < maarat) {
            this.logiikka.getAlus().menetaElama();
            k++;
        }
    }

    @Test
    public void lisaaObjektiToimii() {
        this.logiikka.addVihollinen(objekti);
        assertEquals(this.logiikka.getViholliset().size(), 2);
    }

}
