/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import toiminta.VihollisObjekti;

/**
 *
 * @author Aki
 */
public class Kayttoliittyma implements Runnable {

    private JFrame frame;
    private VihollisObjekti objekti;
    private PiirtoAlusta piirtoalusta = new PiirtoAlusta(objekti);

    public Kayttoliittyma() {
        ;
    }

    @Override
    public void run() {
        frame = new JFrame("Pelialusta");
        frame.setPreferredSize(new Dimension(300, 500));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    public void asetaHahmo(VihollisObjekti objekti) {
        this.objekti = objekti;
    }

    public void luoKomponentit(Container container) {
        container.add(piirtoalusta);
    }

    public void luoObjektit(Container container) {
       
    }

    public JFrame getFrame() {
        return frame;
    }
}
