/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import toiminta.pelaaja.Alus;

/**
 *
 * @author Aki
 */
public class NappaimistonKuuntelija implements KeyListener {

    private Alus alus;
    private Component component;

    public NappaimistonKuuntelija(Alus alus, Component c) {
        this.alus = alus;
        this.component = c;
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            if (alus == null) {
                System.out.println("on null!");
            } else {
                alus.siirry(-10);
            }

        } else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (alus == null) {
                System.out.println("on null!");
            } else {
                alus.siirry(10);
            }
            component.repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {

    }
}
