/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toiminta;

import toiminta.vihollisobjekti.ObjektinArpoja;
import toiminta.vihollisobjekti.VihollisObjekti;
import java.awt.List;
import java.util.ArrayList;
import javax.swing.SwingUtilities;
import kayttoliittyma.Kayttoliittyma;
import kayttoliittyma.NappaimistonKuuntelija;
import toiminta.pelaaja.Alus;
import toiminta.pelaaja.Ammus;

/**
 * luokka on pelin toiminan ydin. kaiken olennaisen liikkumisen ylläpitämisen ja
 * pisteiden ylläpitämisen -
 *
 * @author Aki
 */
public class Logiikka {

    private ArrayList<VihollisObjekti> viholliset = new ArrayList<VihollisObjekti>();
    private ArrayList<VihollisObjekti> poistettavat = new ArrayList<VihollisObjekti>();
    private ArrayList<Ammus> poistettavatAmmukset = new ArrayList<Ammus>();
    private ArrayList<Ammus> ammukset = new ArrayList<Ammus>();
    private Alus alus = new Alus();
    private int liikkumiskerrat = 42;
    private boolean kuoleekoPelaaja = true;
    private Kayttoliittyma liittyma;
    private int viivytysAika = 1000;
    private int pisteet = 0;
    private int osumat = 0;
    private long liikuntaRajotin = 0;
    private long odotusAika = 100;
    private int erikoisVihollinen = 0;

    public Logiikka(Kayttoliittyma liittyma) {
        this.liittyma = liittyma;

    }

    public Logiikka() {                                 //tämä ei liittymää luova on tässä testien takia
        this.viivytysAika = 0;
    }

    /**
     * viivyttää ohjelman toimintaa tarvitteasse kuten vuorojen välissä huomio.
     * kriittinen (tuntemattomista syistä) metodi käyttöliittymän luomisen
     * kannalta.
     */
    public void viivyta() {
        if (viivytysAika > 0) {
            try {
                Thread.sleep(viivytysAika);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * alustaa käyttöliittymän pelille huom! viivytysajan asettaminen tuhanteen
     * on välttämätön muuten käyttliittymä sekoaa.
     */
    public void valmisteleAlusta() {

        viivyta();
        this.liittyma.setAlus(alus);
        this.liittyma.luoNappaimistonKuuntelija();

    }

    /**
     * Metodi aloittaa pelin, ja ylläpitää vuoroja. Tarkennus. Vuoro tarkoittaa
     * tässä tapauksessa pelin vihollisen ja ammusten liikkumista.
     */
    public void pelaa() {

        while (kuoleekoPelaaja == true) {
            int k = this.liikkumiskerrat;
            luoBlokki();
            while (k >= 0) {
                liittymanPaivitys();
                if (System.currentTimeMillis() - this.liikuntaRajotin > this.odotusAika) {
                    liikutaKaikkiaVihollisia();
                    liikutaKaikkiaAmmuksia();
                    k--;
                    this.liikuntaRajotin = System.currentTimeMillis();
                }
            }
        }
        lopetaPeli();

    }
/**
 * lopettaa pelin ja antaa käyttöliittymälle komennon tuottaa loppunäkymän
 */
    public void lopetaPeli() {
        this.ammukset.removeAll(ammukset);
        this.viholliset.removeAll(ammukset);
        
        this.liittyma.luoLoppuNakyma(this.liittyma.getFrame().getContentPane());
    }

    /**
     * aloittaa uuden vuoron liikuttamalla jokaista olemassaolevaa vihollista
     * kun ehdot täyttyvät.
     */
    public void liikutaKaikkiaVihollisia() {
        int liikkumiset = 0;
        if (!this.viholliset.isEmpty()) {

            for (VihollisObjekti objekti : viholliset) {

                if (liikutaJaTarkistaKuolema(objekti) == true) {
                    break;
                }
            }
            poistaVihollisia();

        }

    }

    /**
     * liikuttaa kaikkia olemassaolevia ammuksia ja tarpeentullen tuhoaa ne.
     */
    public void liikutaKaikkiaAmmuksia() {
      this.ammukset = alus.getAmmukset();
        for (Ammus ammus : ammukset) {
            ammus.siirry();
            osuukoAmmus(ammus);
            if (ammus.getY() == 700) {
                this.poistettavatAmmukset.add(ammus);
            }
        }
        poistaAmmuksia();
        alus.setAmmukset(ammukset);

    }

    /**
     * tarkistaa koordinaattiin y=100 päässeen objektin, että törmääkö se
     * alukseen
     *
     * @param objekti joka on saavuttanut koordinaatin Y= 100 Huom! tämä metodi
     * vasta valmistelee itse tarkistuksen tarkistuksen itse suorittaa
     * katsotaanpaOsumaTilannetta()
     * @return palauttaa true jos objekti on osumassa alukseen.
     */
    public boolean liikutaJaTarkistaKuolema(VihollisObjekti objekti) {
        OsumanTarkistaja tarkista = new OsumanTarkistaja(this.alus, objekti);

        objekti.liiku();

        return katsotaanpaOsumaTilannetta(objekti, tarkista) == true;
    }

    /**
     * vuoron loputtua päivittää käyttöliittymän senhtekiseen tilaan.
     */
    public void liittymanPaivitys() {
        if (this.liittyma != null) {                                            //tämä if on puhtaasti testien takia.
            this.liittyma.setViholliset(this.viholliset);
            this.liittyma.setAmmukset(ammukset);
            this.liittyma.paivita();
        }
    }

    /**
     * Metodi tarkistaa ammuksen mahdollisen osuman verraten sen koordinaatteja
     * olemassaoleviin vihollisobjekteihin Huom! tämä metodi toistaiseksi myös
     * vastaa pisteiden lisäämisestä ja muusta mistä sen ei tulisi välittää
     * Huom2! ylimääräiset turhakkeet tullaan siirtämään uuteen metodiin.
     *
     * @param ammus ammus joka saattaa osua vihollisobjektiin
     */
    public void osuukoAmmus(Ammus ammus) {
        for (VihollisObjekti objekti : viholliset) {
            if (ammus.getY() == objekti.getY() - 20 || ammus.getY() < objekti.getY() && ammus.getY() > objekti.getY() - 20) {
                int x = ammus.getX();
                int objektix = objekti.getX();
                if (x == objektix || x > objektix && x < objektix + 20 || x < objektix && x + 5 > objektix) {
                    poistettavat.add(objekti);
                    poistettavatAmmukset.add(ammus);
                    pisteet++;
                    osumat++;
                    lisaaVaikeutta();
                    lisaaPistePaneeliin();

                }
            }
        }
    }

    /**
     * kun pelaaja on tuhonnut kolme vihollista peli lisää vaikeutta
     * vähentämällä liikkumiskertoja enne uuden vihollisen syntymistä Huom! jos
     * objektit liikkuvat enään 15 kertaa vuorossa (max 42) ei peli enään
     * vähennnä vuoroja Tämän jälkeen peli puolestaan aloittaa vähentämään
     * vuorojen välistä viivytysaikaa.
     */
    public void lisaaVaikeutta() {

        if (this.osumat == 2 && this.liikkumiskerrat == 42 && this.odotusAika > 22) {
            this.odotusAika = this.odotusAika - 2;
            this.osumat = 0;
        }
        if (this.osumat == 3 && this.liikkumiskerrat - 3 >= 20 && this.odotusAika == 22) {
            this.liikkumiskerrat = this.liikkumiskerrat - 3;
            this.osumat = 0;
        }

    }

    /**
     * tarkistaa osuuko vihollisobjekti alukseen ja määrittää annetaanko elämä
     * vai otetaanko
     *
     * @param objekti objekti joka ehkä osuu
     * @param tarkista luokka OsumanTarkistaja, joka kysyy onko objekti
     * elämääantava vai ei
     * @return palauttaa true jos alus on menettänyt jokaisen elämän.
     */
    public boolean katsotaanpaOsumaTilannetta(VihollisObjekti objekti, OsumanTarkistaja tarkista) {
        int ox = objekti.getX();
        int ax = alus.getX();

        if (objekti.getY() <= 120 && objekti.getY() > 80) {
            if (ox < ax && ox + objekti.getKoko() > ax || ox > ax && ox < ax + this.alus.getKoko() || ax == ox) {
                int k = tarkista.osuma();
                if (k == 2) {
                    lyhennaAmmuksenAmmuntaAikaa();
                }
                this.poistettavat.add(objekti);

                if (loppuukoPeli() == true) {
                    return true;
                }
            }
        }

        if (objekti.kuoleekoSeinaan() == true) {
            poistettavat.add(objekti);
            alus.menetaElama();
            if (loppuukoPeli() == true) {
                return true;
            }
        }

        return false;
    }

    /**
     * arpoo millainen vihollisobjekti syntyy ja luo sen rajoittaa
     * erikoisblokkien syntymistä
     *
     * @return return arvot ovat avustamaan testien toimintaa
     */
    public int luoBlokki() {
        ObjektinArpoja arpoja = new ObjektinArpoja();
        VihollisObjekti objekti;
        int k = 0;
        if (erikoisVihollinen == -5) {
            arpoja = new ObjektinArpoja(true);
            objekti = new VihollisObjekti(arpoja.arvoObjekti(), arpoja.arvoKoordinaatti());
            erikoisVihollinen--;
            erikoisVihollinen = 1;
            k = 1;
        } else if (erikoisVihollinen > 0) {
            objekti = new VihollisObjekti(1, arpoja.arvoKoordinaatti());
            erikoisVihollinen--;
            k = 2;
        } else {
            int muoto = arpoja.arvoObjekti();
            objekti = new VihollisObjekti(muoto, arpoja.arvoKoordinaatti());
            this.erikoisVihollinen--;
            k = 3;
            if (muoto == 2 || muoto == 3) {
                this.erikoisVihollinen = 3;
                k = 3;
            }

        }
        this.viholliset.add(objekti);
        return k;

    }

    /**
     * nopeuttaa ammusten laukasunopeutta kunnes rajotin on 100
     */
    public void lyhennaAmmuksenAmmuntaAikaa() {
        Long jokuHauskaNimi = this.liittyma.getNappaimistonKuuntelija().getAmmusRajotin();
        if (jokuHauskaNimi - 25 >= 100) {
            this.liittyma.getNappaimistonKuuntelija().setAmmusRajotin(jokuHauskaNimi - 25);

        }
    }

    /**
     * lisää käyttöliittymään pisteet
     */
    public void lisaaPistePaneeliin() {
        if (this.liittyma != null) {

            this.liittyma.paivitaPistePalkki(pisteet);
        }
    }

    /**
     * poistaa kuolleet viholliset pelistä.
     */
    public void poistaVihollisia() {
        this.viholliset.removeAll(this.poistettavat);
        this.poistettavat.clear();
    }

    /**
     * poistaa tuhoutuneet ammukset pelistä
     */
    public void poistaAmmuksia() {
        this.ammukset.removeAll(poistettavatAmmukset);
        this.poistettavatAmmukset.clear();
    }

    /**
     * tarkistaa että saavuttaako elämät nollan jolloin peli loppuu
     *
     * @return true jos pelaaja kuolee
     */
    public boolean loppuukoPeli() {
        if (this.alus.getElamat() == 0) {
            this.kuoleekoPelaaja = false;
            return true;
        } else {
            return false;
        }
    }

    public void setLiikkumisKerrat(int o) {
        this.liikkumiskerrat = o;
    }

    public int getVihollisetKoko() {

        return this.viholliset.size();

    }

    public ArrayList<VihollisObjekti> getViholliset() {
        return this.viholliset;

    }

    public Alus getAlus() {
        return this.alus;
    }

    public void setPoistettavat(ArrayList<VihollisObjekti> poistettavat) {
        this.poistettavat = poistettavat;
    }

    public void setViivytysAika(int aika) {
        this.viivytysAika = aika;
    }

    public ArrayList<VihollisObjekti> getPoistettavat() {
        return this.poistettavat;
    }
/**
 * lisää halutun vihollisen vihollis listalle
 * @param objekti haluttu vihollisobjekti.
 */
    public void addVihollinen(VihollisObjekti objekti) {
        this.viholliset.add(objekti);
    }

    public void setOsumat(int maara) {
        this.osumat = maara;
    }

    public int getOsumat() {
        return this.osumat;
    }

    public int getLiikkumisKerrat() {
        return this.liikkumiskerrat;
    }

    public int getPisteet() {
        return this.pisteet;
    }

    public ArrayList<Ammus> getAmmukset() {
        return this.ammukset;

    }

    public long getOdotusAika() {
        return odotusAika;
    }

    public void setOdotusAika(long odotus) {
        this.odotusAika = odotus;
    }

    public void setErikoisVihollinen(int arvo) {
        this.erikoisVihollinen = arvo;
    }

    public int getErikoisVihollinen() {
        return this.erikoisVihollinen;
    }

    public void setPisteet(int k) {
        this.pisteet = k;
    }




}
