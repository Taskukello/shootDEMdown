/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import toiminta.vihollisobjekti.VihollisObjekti;
import toiminta.pelaaja.Alus;

/**
 *
 * @author Aki
 */
public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private VihollisObjekti objekti;
    private ArrayList<VihollisObjekti> viholliset;
    private Alus alus;

    public Kayttoliittyma() {

    }

    @Override
    public void run() {
        frame = new JFrame("Pelialusta");
        frame.setPreferredSize(new Dimension(550, 750));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    public void paivita() {
        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    public void asetaHahmot(ArrayList<VihollisObjekti> v, Alus alus) {
        this.viholliset = v;
        this.alus = alus;
    }

    public void luoKomponentit(Container container) {
        PiirtoAlusta piirtoalusta = new PiirtoAlusta(this.viholliset, alus);
        container.add(piirtoalusta);

        frame.addKeyListener(new NappaimistonKuuntelija(alus, piirtoalusta));
    }

    public JFrame getFrame() {
        return frame;
    }
}
