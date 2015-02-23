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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import toiminta.pelaaja.Alus;

/**
 *kuuntelee nappaimistoa
 * @author Aki
 */
public class NappaimistonKuuntelija implements KeyListener {

    private Alus alus;
    private Component component;
    private long odotusaika = 0;
    private long ammusrajoitin = 500;
    boolean vasen = false;
    boolean oikea = false;
    boolean space = false;

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
            vasen = true;
        }
        if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
            space = true;
        }
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            oikea = true;
        }

        if (space == true) {
            if (vasen == true) {
                ammutaanko();
                alus.siirry(-10);
            }
            if (oikea == true) {
                ammutaanko();
                alus.siirry(10);
            }

            ammutaanko();

        }
        if (space == false) {
            if (vasen == true) {
                alus.siirry(-10);
            }
            if (oikea == true) {
                alus.siirry(10);
            }

        }

    }

    /**
     * avustaa metodia keyPressed() ampunmalla ammuksen HUOM! ammusta ei ammuta
     * jos ajat eivät täsmää
     */
    public void ammutaanko() {

        if (System.currentTimeMillis() - this.odotusaika > this.ammusrajoitin) {
            alus.luoAmmus(component.getGraphics());
            this.odotusaika = System.currentTimeMillis();
        }

    }

    @Override
    public void keyReleased(KeyEvent ke) {

        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            vasen = false;
        }
        if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
            space = false;
        }
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            oikea = false;
        }

    }

    public Long getAmmusRajotin() {
        return this.ammusrajoitin;
    }

    public void setAmmusRajotin(Long muutaAikaa) {
        this.ammusrajoitin = muutaAikaa;
    }
}
