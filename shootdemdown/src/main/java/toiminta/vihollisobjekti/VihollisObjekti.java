/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toiminta.vihollisobjekti;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Aki
 */
public class VihollisObjekti {

    private int x = 0;
    private int y = 700;
    private int muoto = 0;
    private int koko = 20;

    public VihollisObjekti(int muoto, int x) {
        this.x = x;
        this.muoto = muoto;
    }

    public void liiku() {

            y = y - 2;
    }

    public int getY() {
        return this.y;
    }

    public int getX() {
        return this.x;
    }

    public int getMuoto() {
        return this.muoto;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean kuoleeko() {
        return this.y <= 0;
    }

    public boolean piirra(Graphics graphics) {
        if (this.muoto == 1) {
            graphics.setColor(Color.BLACK);
            graphics.fillOval(x, 700 - y, koko, koko);
            return true;
        } else if (this.muoto == 2) {
            graphics.setColor(Color.BLUE);
            graphics.fillRect(x, 700 - y, koko, koko);
            return true;
        } else if (this.muoto == 3) {
            graphics.setColor(Color.RED);
            graphics.fillRect(x, 700 - y, koko, koko);
            return true;
        } else {
            return false;
        }

    }

    public void setKoko(int koko) {
        this.koko = koko;
    }

    public int getKoko() {
        return this.koko;
    }
}