/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toiminta.pelaaja;

import java.awt.Graphics;

/**
 *
 * @author Aki
 */
public class Alus {

    private int x = 250;
    private int y = 100;
    private int elamat = 3;
    private int koko = 20;

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

    public boolean menetaElama() {
        this.elamat--;
        return this.elamat != 0;
    }

    public void lisaaElama() {
        if (this.elamat < 5) {
            this.elamat++;
        }
    }

    public int getElamat() {
        return elamat;
    }

    public void piirra(Graphics graphics) {
        graphics.fillRect(x, 600, koko, koko);
    }

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
}
