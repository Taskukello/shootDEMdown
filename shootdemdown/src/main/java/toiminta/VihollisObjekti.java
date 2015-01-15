/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toiminta;

import java.awt.Graphics;

/**
 *
 * @author Aki
 */
public class VihollisObjekti {

    private int x = 0;
    private int y = 29;
    private int muoto = 0;

    public VihollisObjekti(int muoto, int x) {
        this.x = x;
        this.muoto = muoto;
    }

    public void liiku() {
        y--;
        if (y == 0) {
        }

    }

    public int getY() {
        return this.y;
    }

    public int getX() {
        return this.x;
    }

    public void piirra(Graphics graphics) {
        graphics.fillOval(x, y, 5, 5);
    }

}
