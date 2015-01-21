/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toiminta;

import toiminta.pelaaja.Alus;

/**
 *
 * @author Aki
 */
public class OsumanTarkistaja {

    private Alus alus;
    private VihollisObjekti objekti;

    public OsumanTarkistaja(Alus alus, VihollisObjekti o) {
        this.alus = alus;
        this.objekti = o;
    }

    public void osuma() {
        if (this.objekti.getMuoto() == 1) {
            this.alus.lisaaElama();
        } else if (this.objekti.getMuoto() == 2) {

            this.alus.menetaElama();
        }

    }
    
    
    

    public void osumaAntaaKivastiPisteita() {

    }

}
