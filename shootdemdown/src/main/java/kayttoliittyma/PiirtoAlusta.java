/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import javax.swing.JPanel;
import toiminta.VihollisObjekti;

/**
 *
 * @author Aki
 */
public class PiirtoAlusta extends JPanel {

    private VihollisObjekti blokki;

    public PiirtoAlusta(VihollisObjekti blokki) {
        super.setBackground(Color.WHITE);
        this.blokki = blokki;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        blokki.piirra(graphics);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paintComponent(graphics);
        blokki.piirra(graphics);
    }

}
