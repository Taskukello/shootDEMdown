/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toiminta.pelaaja;

import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

/**
 * ylläpitää aluksen liikettä sen kuntoa. hoitaa ammuksia
 *
 * @author Aki -
 */
public class Alus {

    private Component component;
    private int x = 250;
    private int y = 100;
    private int elamat = 3;
    private int koko = 20;
    private ArrayList<Ammus> ammukset = new ArrayList<Ammus>();

    public Alus() {

    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return this.x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return this.y;
    }

    /**
     * poistaa elämiä alukselta kunnes elämiä on nolla
     *
     * @return palauttaa true jos elämiä on vielä jäljellä
     */
    public boolean menetaElama() {
        this.elamat--;
        return this.elamat != 0;
    }

    /**
     * lisää elämiä alukselle kun kiltti vihollinen on napattu Huom! elämät ovat
     * rajoitettu viiteen
     */
    public void lisaaElama() {
        if (this.elamat < 7) {
            this.elamat++;
        }
    }

    public int getElamat() {
        return elamat;
    }

    /**
     * muuttaa aluksen koordinaatteja kun nuolinappeja painetaan
     *
     * @param x kuinka paljon alus siirtyy/ mihin suuntaan tarkennus.
     * negatiivinen arvo vasemmalle, positiivinen oikealle
     */
    public void siirry(int x) {

        if (this.x >= 0 && x < 0) {
            int t = this.x + x;
            if (t < 0) {
                this.x = 0;
            } else {
                this.x = this.x + x;
            }
        } else if (this.x <= 500 && x > 0) {
            int t = this.x + x;
            if (t > 500) {
                this.x = 500;
            } else {
                this.x = this.x + x;
            }
        }
    }

    public void setKoko(int koko) {
        this.koko = koko;
    }

    public int getKoko() {
        return this.koko;
    }

    
     public ArrayList<Ammus> getAmmukset() {
     return this.ammukset;
     }

     public void setAmmukset(ArrayList<Ammus> ammus) {
     this.ammukset = ammus;
     }

     public void addAmmus(Ammus ammus) {
     this.ammukset.add(ammus);
     }
     
}
