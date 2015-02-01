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
 *
 * @author Aki
 */
public class Logiikka {

    private ArrayList<VihollisObjekti> viholliset = new ArrayList<VihollisObjekti>();
    private ArrayList<VihollisObjekti> poistettavat = new ArrayList<VihollisObjekti>();
    private ArrayList<Ammus> poistettavatAmmukset = new ArrayList<Ammus>();
    private ArrayList<Ammus> ammukset;
    private Alus alus = new Alus();
    private int liikkumiskerrat = 40;
    private boolean kuoleekoPelaaja = true;
    private Kayttoliittyma liittyma;
    private int viivytysAika = 100;
    private int pisteet = 0;
    private int osumat = 0;

    public Logiikka(Kayttoliittyma liittyma) {
        this.liittyma = liittyma;

    }

    public Logiikka() {                                 //tämä ei liittymää luova on tässä testien takia
        this.viivytysAika = 0;
    }

    //-------------------------------------------------
    public void viivyta() {
        if (viivytysAika > 0) {
            try {
                Thread.sleep(viivytysAika);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void valmisteleAlusta() {
        this.viivytysAika = 1000;                                   //voi olla konekohtainen ongelma mutta ilman tätä ohjelma hajoaa 
        viivyta();
        this.liittyma.setAlus(alus);
        this.liittyma.luoNappaimistonKuuntelija();
    }

    //-------------------------------------------------
    public void pelaa() {
        this.viivytysAika = 100;
        while (kuoleekoPelaaja == true) {
            int k = this.liikkumiskerrat;
            luoBlokki();
            while (k >= 0) {
                liikutaKaikkiaVihollisia();
                viivyta();
                k--;
            }
        }
        System.out.println("Aluksesi tuhoutui!!");                                  //tämäkin kai katoaa
    }

    //-------------------------------------------------
    public void liikutaKaikkiaVihollisia() {
        int liikkumiset = 0;
        if (!this.viholliset.isEmpty()) {

            for (VihollisObjekti objekti : viholliset) {

                if (liikutaJaTarkistaKuolema(objekti) == true) {
                    break;
                }
            }
            liikutaKaikkiaAmmuksia();
        }

        poistaVihollisia();

        liittymanPaivitys();

    }
    
  
    //-------------------------------------------------

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
    //-------------------------------------------------

    public boolean liikutaJaTarkistaKuolema(VihollisObjekti objekti) {
        OsumanTarkistaja tarkista = new OsumanTarkistaja(this.alus, objekti);

        objekti.liiku();

        return katsotaanpaOsumaTilannetta(objekti, tarkista) == true;
    }

    //-------------------------------------------------
    public void liittymanPaivitys() {
        if (this.liittyma != null) {                                            //tämä if on puhtaasti testien takia.
            this.liittyma.setViholliset(this.viholliset);
            this.liittyma.setAmmukset(ammukset);
            this.liittyma.paivita();
        }
    }

    //---------------------------------------------------
    public void osuukoAmmus(Ammus ammus) {
        for (VihollisObjekti objekti : viholliset) {
            if (ammus.getY() == objekti.getY() - 20 || ammus.getY() < objekti.getY() && ammus.getY() > objekti.getY() - 20) {
                int x = ammus.getX();
                int objektix = objekti.getX();
                if (x == objektix || x > objektix && x < objektix + 14) {
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

    public void lisaaVaikeutta() {
        if (this.osumat == 3 && this.liikkumiskerrat > 16) {
            this.liikkumiskerrat = this.liikkumiskerrat - 3;
            this.osumat = 0;
        }
    }

    //---------------------------------------------------- 
    public boolean katsotaanpaOsumaTilannetta(VihollisObjekti objekti, OsumanTarkistaja tarkista) {
        int ox = objekti.getX();
        int ax = alus.getX();

        if (objekti.getY() == 100) {
            if (ox < ax && ox + objekti.getKoko() > ax || ox > ax && ox < ax + this.alus.getKoko() || ax == ox) {
                tarkista.osuma();
                this.poistettavat.add(objekti);

                if (loppuukoPeli() == true) {
                    return true;
                }
            }
        }

        if (objekti.kuoleeko() == true) {
            poistettavat.add(objekti);
            alus.menetaElama();
            if (loppuukoPeli() == true) {
                return true;
            }
        }

        return false;
    }

    //-------------------------------------------------
    public void luoBlokki() {
        ObjektinArpoja arpoja = new ObjektinArpoja();
        VihollisObjekti objekti = new VihollisObjekti(arpoja.arvoObjekti(), arpoja.arvoKoordinaatti());
        viholliset.add(objekti);

    }

    //-------------------------------------------------
    public void setLiikkumisKerrat(int o) {
        this.liikkumiskerrat = o;
    }

    public void lisaaPistePaneeliin() {
        this.liittyma.paivitaPistePalkki(pisteet);
    }

    //-------------------------------------------------
    public void poistaVihollisia() {
        this.viholliset.removeAll(this.poistettavat);
        this.poistettavat.clear();
    }

    //------------------------------------------------
    public void poistaAmmuksia() {
        this.ammukset.removeAll(poistettavatAmmukset);
        this.poistettavatAmmukset.clear();
    }

    //-------------------------------------------------
    public boolean loppuukoPeli() {
        if (this.alus.getElamat() == 0) {
            this.kuoleekoPelaaja = false;
            return true;
        } else {
            return false;
        }
    }

    //-------------------------------------------------
    public int getVihollisetKoko() {

        return this.viholliset.size();

    }

    //-------------------------------------------------
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

    public void addVihollinen(VihollisObjekti objekti) {
        this.viholliset.add(objekti);
    }
}
