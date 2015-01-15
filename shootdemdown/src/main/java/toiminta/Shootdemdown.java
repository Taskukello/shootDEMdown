/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toiminta;

import java.awt.List;
import java.util.ArrayList;
import kayttoliittyma.Kayttoliittyma;

/**
 *
 * @author Aki
 */
public class Shootdemdown {

    private ArrayList<VihollisObjekti> viholliset = new ArrayList<VihollisObjekti>();
    private int liikkumiskerrat = 0;

    public Shootdemdown() {

    }

    public void pelaa() {
        //  while (true) {
        luoBlokki();

        // }
    }

    public void liikutaKaikkia() {
        ArrayList<VihollisObjekti> poistettavat = new ArrayList<VihollisObjekti>();
        int liikkumiset = this.liikkumiskerrat;

        int k = 0;

        for (VihollisObjekti objekti : viholliset) {
            while (liikkumiset > 0) {
                objekti.liiku();
                k++;
                liikkumiset--;

            }
            if (objekti.kuoleeko() == true) {
                poistettavat.add(objekti);
            }

        }
        if (!poistettavat.isEmpty()) {
            poistaVihollisia(poistettavat);
        }
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

    public int getVihollisetKoko() {
        if (this.viholliset == null) {
            return 0;
        } else {
            return this.viholliset.size();
        }
    }

    public ArrayList<VihollisObjekti> getViholliset() {
        return this.viholliset;

    }

}
