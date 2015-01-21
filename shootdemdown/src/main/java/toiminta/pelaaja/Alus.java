/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toiminta.pelaaja;

/**
 *
 * @author Aki
 */
public class Alus {

    private int x = 1;
    private int y = 0;
    private int elamat = 3;

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
    
    public void lisaaElama(){
        this.elamat++;
    }
    
    public int getElamat(){
        return elamat;
    }
}
