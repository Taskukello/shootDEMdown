/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toiminta.pelaaja;

import java.awt.Color;
import java.awt.Graphics;

/**
 * ylläpitää ammusta ja hoitaa sen koordinaatteja
 *-
 * @author Aki
 */
public class Ammus {

    private int x = 0;
    private int y = 110;
    private int leveys = 6;
    private int ammuksenPituus = 10;

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
        return y;
    }

    public int getLeveys() {
        return this.leveys;
    }
    
    public int getKorkeus(){
        return this.ammuksenPituus;
    }

    public void siirry() {
        if (this.y + 10 <= 700) {
            y = y + 10;
        }
    }

}
