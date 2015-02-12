/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import toiminta.vihollisobjekti.VihollisObjekti;
import toiminta.pelaaja.Alus;
import toiminta.pelaaja.Ammus;

/**
 *
 * @author Aki
 */
public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private VihollisObjekti objekti;
    private ArrayList<VihollisObjekti> viholliset;
    private ArrayList<Ammus> ammukset;
    private Alus alus;
    private PiirtoAlusta piirtoalusta;
    private String pisteet = "0";
    private JLabel areaNorth = new JLabel(pisteet);

    public Kayttoliittyma() {

    }

    @Override
    public void run() {
        frame = new JFrame("Pelialusta");
        frame.setPreferredSize(new Dimension(520, 700));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());
        luoTietoPalkit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * luo ensimmäistä kertaa pelin alustan.
     *
     * @param container framen container
     */
    public void luoKomponentit(Container container) {

        piirtoalusta = new PiirtoAlusta(this.viholliset, this.ammukset, alus);

        container.add(piirtoalusta);

    }

    /**
     * päivittää käyttöliittymän kuvan pelaajalle.
     */
    public void paivita() {
        piirtoalusta.setAlus(alus);
        piirtoalusta.setAmmukset(ammukset);
        piirtoalusta.setViholliset(viholliset);
        piirtoalusta.repaint();

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * luo pelille luokan joka kuunteelee pelaajan näppäimistöä
     */
    public void luoNappaimistonKuuntelija() {
        frame.addKeyListener(new NappaimistonKuuntelija(alus, piirtoalusta));
    }

    /**
     * luo peliin pistepalkin jonka alkuarvo on 0
     *
     * @param container framen juttujotain
     */
    public void luoTietoPalkit(Container container) {

        container.add(areaNorth, BorderLayout.NORTH);

    }

    /**
     * päivittää pistepalkin pisteiden mukaiseksi
     *
     * @param o pisteiden määrä
     */
    public void paivitaPistePalkki(int o) {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(o);
        this.pisteet = sb.toString();
        this.areaNorth.setText(pisteet);

    }

    public void setAlus(Alus alus) {
        this.alus = alus;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setViholliset(ArrayList<VihollisObjekti> v) {
        this.viholliset = v;
    }

    public void setAmmukset(ArrayList<Ammus> a) {
        this.ammukset = a;
    }

}
