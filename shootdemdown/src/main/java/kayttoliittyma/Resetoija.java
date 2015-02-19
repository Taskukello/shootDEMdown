/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;
import toiminta.Logiikka;

/**
 *
 * @author Aki
 */
public class Resetoija implements ActionListener {

    private Frame frame;

    public Resetoija(Frame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*
         frame.dispose();
         Kayttoliittyma kayttoliittyma = new Kayttoliittyma();

         SwingUtilities.invokeLater(kayttoliittyma);

         Logiikka peli = new Logiikka(kayttoliittyma);
         peli.valmisteleAlusta();
         peli.pelaa();
         */
    }

}
