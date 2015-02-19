/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toiminta.vihollisobjekti;

import java.util.Random;

/**
 * hoitaa objektin luomisen edeltävät vaiheet
 *
 * @author Aki
 */
public class ObjektinArpoja {

    private int objektinArvo = 0;
    private int objektinKoordinaatti = 0;
    private int prosentti = 0;

    public ObjektinArpoja() {

    }

    /**
     * arpoo millainen objekti on (tappava, vai parantava)
     *
     * @return palauttaa mikä arvo on arvottu
     */
    public int arvoObjekti() {
        prosentti = arpaKone(100);
        maaritaObjekti();
        return this.objektinArvo;
    }

    /**
     * määrittää millainen objekti on kyseessä tarkennus. 1 = tappava, 2 =
     * bonuksen antava (ei olemassa vielä), 3 = parantava
     *
     * @return palauttaa objektin muodon
     */
    public int maaritaObjekti() {
        if (prosentti <= 70) {
            this.objektinArvo = 1;
        } else if (prosentti <= 90 && prosentti > 70) {
            this.objektinArvo = 2;
        } else {
            this.objektinArvo = 3;

        }
        return this.objektinArvo;

    }

    /**
     * määrittää objektin aloituspisteen x akselilla
     *
     * @return palauttaa x-koordinaatin
     */
    public int arvoKoordinaatti() {
        this.objektinKoordinaatti = arpaKone(48) * 10;

        return this.objektinKoordinaatti;

    }

    /**
     * hoitaa arvonnan
     *
     * @param rajaArvo kertoo mikä on maksimiarvo arvottavalle arvolle
     * @return palauttaa arvotun arvon
     */
    public int arpaKone(int rajaArvo) {
        Random ran = new Random();
        int r = ran.nextInt((rajaArvo - 1) + 1) + 1;
        return r;

    }

    public int getObjektinArvo() {
        return this.objektinArvo;

    }

    public int getObjektinKoordinaatti() {
        return this.objektinKoordinaatti;
    }

    public void setProsentti(int pro) {
        this.prosentti = pro;
    }

    public int getProsentti() {
        return this.prosentti;
    }

}
