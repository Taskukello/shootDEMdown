/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
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
        if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
            alus.luoAmmus(component.getGraphics());
        }

        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {

            alus.siirry(-10);
            alus.piirra(component.getGraphics());

        } else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            alus.siirry(10);
            alus.piirra(component.getGraphics());

        }

    }


    @Override
    public void keyReleased(KeyEvent ke) {

    }
}
