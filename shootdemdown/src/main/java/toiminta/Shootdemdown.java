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
    private Kayttoliittyma liittyma;

    public Shootdemdown(Kayttoliittyma liittyma) {
        this.liittyma = liittyma;
    }

    public void pelaa() {
        //  while (true) {
        luoBlokki();
        liikutaKaikkia();

        // }
    }

    public void liikutaKaikkia() {
        int liikkumiskerrat = 30;
        while (liikkumiskerrat > 0) {
            for (VihollisObjekti objekti : viholliset) {
                objekti.liiku();
                liittyma.asetaHahmo(objekti);
                liittyma.luoKomponentit(liittyma.getFrame());

            }
            liikkumiskerrat--;
        }
    }

    public void luoBlokki() {
        ObjektinArpoja arpoja = new ObjektinArpoja();
        VihollisObjekti objekti = new VihollisObjekti(arpoja.arvoObjekti(), arpoja.arvoKoordinaatti());
        viholliset.add(objekti);

    }

}
