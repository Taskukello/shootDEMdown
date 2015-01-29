/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import toiminta.vihollisobjekti.VihollisObjekti;
import toiminta.pelaaja.Alus;

/**
 *
 * @author Aki
 */
public class PiirtoAlusta extends JPanel {

    private Alus alus;
    private ArrayList<VihollisObjekti> viholliset;

    public PiirtoAlusta(ArrayList<VihollisObjekti> v, Alus alus) {
        super.setBackground(Color.WHITE);
        this.viholliset = v;
        this.alus = alus;

    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (this.viholliset != null || this.alus != null) {
            alus.piirra(graphics);
            for (VihollisObjekti objekti : viholliset) {
                objekti.piirra(graphics);
            }

        }

    }

}
