/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toiminta;

import java.awt.List;
import java.util.ArrayList;
import kayttoliittyma.Kayttoliittyma;
import toiminta.pelaaja.Alus;

/**
 *
 * @author Aki
 */
public class Logiikka {

    private ArrayList<VihollisObjekti> viholliset = new ArrayList<VihollisObjekti>();
    private ArrayList<VihollisObjekti> poistettavat = new ArrayList<VihollisObjekti>();
    private Alus alus = new Alus();
    private OsumanTarkistaja tarkista;
    private int liikkumiskerrat = 3;
    private boolean kuoleekoPelaaja = true;

    public Logiikka() {

    }

    public void pelaa() {
        while (kuoleekoPelaaja == true) {
            luoBlokki();
            System.out.println("aloitus");                                          //ei viimeisessä lopputuloksessa
            liikutaKaikkia();
            System.out.println("objekteja: " + this.viholliset.size());             //ei viimeisessä lopputuloksessa
        }
        System.out.println("Aluksesi tuhoutui!!");
    }

    public void liikutaKaikkia() {

        int liikkumiset = this.liikkumiskerrat;
        while (liikkumiset > 0) {
            for (VihollisObjekti objekti : viholliset) {
                this.tarkista = new OsumanTarkistaja(this.alus, objekti);

                objekti.liiku();
                System.out.println("y: " + objekti.getY());                         //ei viimeisessä lopputuloksessa
                if (katsotaanpaOsumaTilannetta(objekti) == true) {
                    break;
                }
            }
            liikkumiset--;
            if (!poistettavat.isEmpty()) {
                poistaVihollisia(poistettavat);
                poistettavat = new ArrayList<VihollisObjekti>();
            }

        }
    }

    public boolean katsotaanpaOsumaTilannetta(VihollisObjekti objekti) {
        if (objekti.getX() == 1) {
            tarkista.osuma();
            if (loppuukoPeli() == true) {
                return true;
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

    public void poistaVihollisia(ArrayList<VihollisObjekti> l) {
        int k = 0;
        for (VihollisObjekti o : l) {
            while (this.viholliset.size() > k) {
                VihollisObjekti tarkastettava = this.viholliset.get(k);

                if (tarkastettava.equals(o)) {
                    this.viholliset.remove(k);
                }
                k++;
            }

        }

    }

    public boolean loppuukoPeli() {
        if (this.alus.getElamat() == 0) {
            this.kuoleekoPelaaja = false;
            return true;
        } else {
            System.out.println("menetit elämän!");
            return false;
        }
    }

    public int getVihollisetKoko() {

        return this.viholliset.size();

    }

    public ArrayList<VihollisObjekti> getViholliset() {
        return this.viholliset;

    }
    
    public Alus getAlus(){
        return this.alus;
    }
}
