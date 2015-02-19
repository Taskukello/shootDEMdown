/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toiminta;

import toiminta.vihollisobjekti.VihollisObjekti;
import toiminta.pelaaja.Alus;

/**
 * tarkistelee osumatilanteen objektin ja aluksen välillä, luokka on vielä hyvin
 * tyhjä mutta täyttyy jatkossa
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

    /**
     * tarkistaa millainen vihollinen on osunut alukseen elämää antava vai
     * tappava
     */
    public int osuma() {
        if (this.objekti.getMuoto() == 1) {
            this.alus.menetaElama();
            return 1;
        } else if (this.objekti.getMuoto() == 3) {
            this.alus.lisaaElama();
            return 3;

        } else {
            return 2;
        }

    }

}
