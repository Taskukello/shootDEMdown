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
import toiminta.pelaaja.Ammus;
import toiminta.vihollisobjekti.VihollisObjekti;

/**
 *
 * @author Aki
 */
public class LogiikkaTest {

    Kayttoliittyma kayttoliittyma = new Kayttoliittyma();

    private Logiikka logiikka;
    private VihollisObjekti objekti;

    public LogiikkaTest() {

    }

    @Before
    public void setUp() {
        SwingUtilities.invokeLater(kayttoliittyma);
        logiikka = new Logiikka(kayttoliittyma);
        this.logiikka.luoBlokki();
        objekti = new VihollisObjekti(1, 250);

    }

    @After
    public void tearDown() {
    }

    @Test
    public void onkoVihollisiaAlussaNolla() {
        Logiikka j = new Logiikka(this.kayttoliittyma);

        assertEquals(j.getVihollisetKoko(), 0);
    }

    @Test
    public void onkoVihollisiaLisayksenJalkeenYksi() {
        assertEquals(logiikka.getVihollisetKoko(), 1);
    }

    @Test(timeout = 2000)
    public void liikkuukoBlokkiYhden() {
        this.logiikka = new Logiikka();
        this.logiikka.luoBlokki();
        this.logiikka.setLiikkumisKerrat(1);
        this.logiikka.liikutaKaikkiaVihollisia();
        VihollisObjekti banaani = this.logiikka.getViholliset().get(0);

        assertEquals(banaani.getY(), 698);

    }

    @Test
    public void vihollinenKuolee() {

        this.logiikka.setPoistettavat(this.logiikka.getViholliset());
        this.logiikka.poistaVihollisia();
        assertEquals(this.logiikka.getVihollisetKoko(), 0);
    }

    @Test (timeout = 3000)
    public void pelaaToimii() {
        
        this.logiikka.valmisteleAlusta();
        this.logiikka.setViivytysAika(0);
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
        objekti = new VihollisObjekti(3, 250);
        objekti.setY(100);

        OsumanTarkistaja o = new OsumanTarkistaja(this.logiikka.getAlus(), objekti);
        this.logiikka.katsotaanpaOsumaTilannetta(objekti, o);
        assertEquals(this.logiikka.getAlus().getElamat(), 4);

    }

    @Test(timeout = 1200)
    public void viivastysToimii() {
        this.logiikka.luoBlokki();
        this.logiikka.setViivytysAika(1000);
        this.logiikka.viivyta();
        assertTrue(true);

    }

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

    @Test
    public void lisaaVaikeutta() {
        this.logiikka.setOsumat(3);
        this.logiikka.lisaaVaikeutta();
        assertEquals(this.logiikka.getOsumat(), 0);
        assertEquals(this.logiikka.getLiikkumisKerrat(), 37);
    }

    @Test
    public void lisaaVaikeuttaEiLisaaLiikaaVaikeutta() {
        this.logiikka.setOsumat(3);
        this.logiikka.setLiikkumisKerrat(16);
        this.logiikka.lisaaVaikeutta();
        assertEquals(this.logiikka.getOsumat(), 3);
        assertEquals(this.logiikka.getLiikkumisKerrat(), 16);
    }

    @Test
    public void eiLisaileHuvikseenVaikeutta() {
        this.logiikka.setOsumat(2);
        this.logiikka.setLiikkumisKerrat(76);
        this.logiikka.lisaaVaikeutta();
        assertEquals(this.logiikka.getOsumat(), 2);
        assertEquals(this.logiikka.getLiikkumisKerrat(), 76);
    }

    @Test
    public void sittenKunMutaatioTestitDerppaavatLisaaVaikeuttaMetdoin() {
        this.logiikka.setOsumat(3);
        this.logiikka.setLiikkumisKerrat(18);
        this.logiikka.lisaaVaikeutta();
        assertEquals(this.logiikka.getLiikkumisKerrat(), 18);
        assertEquals(this.logiikka.getOsumat(), 3);
        this.logiikka.setOsumat(3);
        this.logiikka.setLiikkumisKerrat(12);
        this.logiikka.lisaaVaikeutta();
        assertEquals(this.logiikka.getLiikkumisKerrat(), 12);
        assertEquals(this.logiikka.getOsumat(), 3);

    }

    public Ammus jarjesteleValmiiksi(int x, int y, int vx, int vy) {
        this.logiikka = new Logiikka();
        this.objekti = new VihollisObjekti(1, vx);
        objekti.setY(vy);
        this.logiikka.addVihollinen(objekti);
        Ammus ammus = new Ammus();
        ammus.setX(x);
        ammus.setY(y);
        return ammus;
    }

    @Test
    public void josAmmusSuoraanXSaatPisteen() {
        Ammus ammus = jarjesteleValmiiksi(250, 200, 250, 220);

        this.logiikka.osuukoAmmus(ammus);
        assertEquals(this.logiikka.getPisteet(), 1);
        assertEquals(this.logiikka.getOsumat(), 1);

    }

    @Test
    public void josAmmutKeskelleSaatPisteen() {
        Ammus ammus = jarjesteleValmiiksi(250, 200, 245, 215);

        this.logiikka.osuukoAmmus(ammus);
        assertEquals(this.logiikka.getPisteet(), 1);
        assertEquals(this.logiikka.getOsumat(), 1);

    }

    @Test
    public void josAmmusMelkeinOhiSaatPisteenJaPeliVaikeutuu() {
        Ammus ammus = jarjesteleValmiiksi(250, 200, 231, 210);
        this.logiikka.setOsumat(2);
        this.logiikka.osuukoAmmus(ammus);

        assertEquals(this.logiikka.getPisteet(), 1);
        assertEquals(this.logiikka.getOsumat(), 0);
        assertEquals(this.logiikka.getAlus().getAmmukset().size(), 0);
        assertEquals(this.logiikka.getLiikkumisKerrat(), 37);

    }

    @Test
    public void josAmmutMelkeinOhiVasemmaltaSaatPisteen() {
        Ammus ammus = jarjesteleValmiiksi(250, 200, 254, 205);
        this.logiikka.setOsumat(3);
        this.logiikka.osuukoAmmus(ammus);
        assertEquals(this.logiikka.getPisteet(), 1);
        assertEquals(this.logiikka.getOsumat(), 4);

    }

    @Test
    public void ohimenoTilanteetEivatAnnaPisteita() {
        Ammus ammus = jarjesteleValmiiksi(250, 200, 255, 220);
        this.logiikka.osuukoAmmus(ammus);
        assertEquals(this.logiikka.getPisteet(), 0);
        assertEquals(this.logiikka.getOsumat(), 0);

        ammus = jarjesteleValmiiksi(250, 200, 230, 220);
        this.logiikka.osuukoAmmus(ammus);
        assertEquals(this.logiikka.getPisteet(), 0);
        assertEquals(this.logiikka.getOsumat(), 0);

        ammus = jarjesteleValmiiksi(250, 200, 230, 200);
        this.logiikka.osuukoAmmus(ammus);
        assertEquals(this.logiikka.getPisteet(), 0);
        assertEquals(this.logiikka.getOsumat(), 0);

        ammus = jarjesteleValmiiksi(250, 200, 230, 221);
        this.logiikka.osuukoAmmus(ammus);
        assertEquals(this.logiikka.getPisteet(), 0);
        assertEquals(this.logiikka.getOsumat(), 0);

    }

    @Test
    public void ammmusLiikkuuOikein() {
        Ammus ammus = new Ammus();
        ammus.setY(100);
        this.logiikka = new Logiikka();
        this.logiikka.getAlus().addAmmus(ammus);
        this.logiikka.liikutaKaikkiaAmmuksia();
        assertEquals(this.logiikka.getAmmukset().get(0).getY(), 110);
        assertEquals(this.logiikka.getAlus().getAmmukset().get(0).getY(), 110);

    }

    @Test
    public void ammusTuhoutuuKunOsuuKattoon() {
        Ammus ammus = new Ammus();
        ammus.setY(690);
        this.logiikka = new Logiikka();
        this.logiikka.getAlus().addAmmus(ammus);

        this.logiikka.liikutaKaikkiaAmmuksia();
        assertEquals(this.logiikka.getAmmukset().size(), 0);

    }

}
