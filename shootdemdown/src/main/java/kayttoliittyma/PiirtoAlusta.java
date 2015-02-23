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
import toiminta.pelaaja.Ammus;

/**
 *piirtää alustan ja hallitsee sitä
 * @author Aki
 */
public class PiirtoAlusta extends JPanel {
    
    private Alus alus;
    private ArrayList<VihollisObjekti> viholliset;
    private ArrayList<Ammus> ammukset;
    
    public PiirtoAlusta(ArrayList<VihollisObjekti> v, ArrayList<Ammus> ammus, Alus alus) {
        super.setBackground(Color.WHITE);
        this.ammukset = ammus;
        this.viholliset = v;
        this.alus = alus;
        
    }
    
    @Override
    protected void paintComponent(Graphics graphics) {
        Piirtaja piirtaja = new Piirtaja(graphics);
        super.paintComponent(graphics);
        if (this.viholliset != null) {
            
            piirtaja.piirraAlus(alus);
            piirtaja.piirraElamat(alus);
            for (VihollisObjekti objekti : viholliset) {
                piirtaja.piirraVihollisObjektit(objekti);
                
            }
            for (Ammus ammus : ammukset) {
                piirtaja.piirraAmmus(-1, -1, ammus);
            }
            
        }
        
    }

    public void setViholliset(ArrayList<VihollisObjekti> o) {
        this.viholliset = o;
    }
    
    public void setAlus(Alus a) {
        this.alus = a;
    }
    
    public void setAmmukset(ArrayList<Ammus> a) {
        this.ammukset = a;
    }
    
}
