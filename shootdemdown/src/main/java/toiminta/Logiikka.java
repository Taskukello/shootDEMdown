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

/**
 *
 * @author Aki
 */
public class Logiikka {

    private ArrayList<VihollisObjekti> viholliset = new ArrayList<VihollisObjekti>();
    private ArrayList<VihollisObjekti> poistettavat = new ArrayList<VihollisObjekti>();
    private Alus alus = new Alus();
    private int liikkumiskerrat = 3;
    private boolean kuoleekoPelaaja = true;
    public Kayttoliittyma liittyma;
    private int viivytysAika = 1000;

    public Logiikka(Kayttoliittyma liittyma) {
        this.liittyma = liittyma;

    }

    public Logiikka() {
        this.viivytysAika = 0;
    }

    public void viivyta() {
        if (viivytysAika > 0) {
            try {
                Thread.sleep(viivytysAika);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void pelaa() {

        while (kuoleekoPelaaja == true) {
            viivyta();
            luoBlokki();
            liikutaKaikkia();
      //      System.out.println("objekteja: " + this.viholliset.size());             //ei viimeisessä lopputuloksessa

        }
        System.out.println("Aluksesi tuhoutui!!");                                  //tämäkin kai katoaa
    }

    public void liikutaKaikkia() {

        int liikkumiset = this.liikkumiskerrat;
        while (liikkumiset > 0 && !this.viholliset.isEmpty()) {
            for (VihollisObjekti objekti : viholliset) {
                OsumanTarkistaja tarkista = new OsumanTarkistaja(this.alus, objekti);

                objekti.liiku();

                if (katsotaanpaOsumaTilannetta(objekti, tarkista) == true) {
                    break;
                }
            }
            liikkumiset--;
     //       System.out.println("y: " + this.viholliset.get(0).getY() + " x: " + this.viholliset.get(0).getX());
            if (!poistettavat.isEmpty()) {
                poistaVihollisia();
                poistettavat = new ArrayList<VihollisObjekti>();
            }

        }

        liittymanPaivitys();
    }

    public void liittymanPaivitys() {
        if (this.liittyma != null) {                                            //tämä on puhtaasti testien takia.
            this.liittyma.asetaHahmot(this.viholliset, this.alus);
            this.liittyma.paivita();
        }
    }

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
            this.alus.menetaElama();
            if (loppuukoPeli() == true) {
                return true;
            }
        }

        return false;
    }

    public void luoBlokki() {
        ObjektinArpoja arpoja = new ObjektinArpoja();
        VihollisObjekti objekti = new VihollisObjekti(arpoja.arvoObjekti(), arpoja.arvoKoordinaatti());
        viholliset.add(objekti);

    }

    public void setLiikkumisKerrat(int o) {
        this.liikkumiskerrat = o;
    }

    public void poistaVihollisia() {
        this.viholliset.removeAll(this.poistettavat);

    }

    public boolean loppuukoPeli() {
        if (this.alus.getElamat() == 0) {
            this.kuoleekoPelaaja = false;
            return true;
        } else {
            System.out.println("menetit elämän!");                              //tämäkään ei tule olemaa lopullisessa versiossa
            return false;
        }
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
    
    public ArrayList<VihollisObjekti> getPoistettavat(){
        return this.poistettavat;
    }
    
    public void addVihollinen(VihollisObjekti objekti){
        this.viholliset.add(objekti);
    }
}
