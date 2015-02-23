/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kayttoliittyma;

import java.awt.Color;
import java.awt.Graphics;
import toiminta.pelaaja.Alus;
import toiminta.pelaaja.Ammus;
import toiminta.vihollisobjekti.VihollisObjekti;

/**
 *piirtaa objektit pelikentälle
 * @author Aki
 */
public class Piirtaja {

    private Graphics graffat;
    private VihollisObjekti objekti;
    private Ammus ammus;

    public Piirtaja(Graphics g) {
        this.graffat = g;
    }

    /**
     * piirtää/siirtää ammusta käyttöliittymällä
     *
     * @param x -koordinaatti mistä ammus ammuttiin (ei muutu kun määritelty)
     * @param y -koordinaatti missä y koordinaatissa ammus tällä hetkin
     * sijaitsee
     * @param ammus piirtoAlustan lähettämä ammus
     */
    public void piirraAmmus(int x, int y, Ammus ammus) {
        this.ammus = ammus;
        int alkuX = ammus.getX();
        int alkuY = ammus.getY();
        if (x != -1) {
            alkuX = x + 7;
            alkuY = y;
        }
        graffat.setColor(Color.ORANGE);
        graffat.fillRect(alkuX, 700 - alkuY, 6, 10);

    }

    /**
     * piirtää aluksen käyttöliittymälle/ päivittää aluksen käyttöliittymälle
     *
     * @param alus piirtoalustan lähettämä alus
     */
    public void piirraAlus(Alus alus) {
        graffat.fillRect(alus.getX(), 600, alus.getKoko(), alus.getKoko());
    }

    /**
     * piirtää aluksen elämät näytölle
     *
     * @param alus piirtoalustan lähettämä alus
     */
    public void piirraElamat(Alus alus) {
        int x = 20;
        int y = 10;
        int elamat = 0;
        while (elamat < alus.getElamat()) {
            graffat.setColor(Color.red);
            graffat.fillOval(x, y, 20, 20);
            x = x + 40;
            elamat++;

        }
    }

    /**
     * piirtää/siirtää vihollisobjektin käyttöliittmällä
     *
     * @param objekti piirtoalustan lähettämä objekti
     */
    public void piirraVihollisObjektit(VihollisObjekti objekti) {

        if (objekti.getMuoto() == 1) {
            graffat.setColor(Color.BLACK);
            graffat.fillOval(objekti.getX(), 700 - objekti.getY(), objekti.getKoko(), objekti.getKoko());
        } else if (objekti.getMuoto() == 2) {
            graffat.setColor(Color.BLUE);
            graffat.fillOval(objekti.getX(), 700 - objekti.getY(), objekti.getKoko(), objekti.getKoko());
        } else if (objekti.getMuoto() == 3) {
            graffat.setColor(Color.RED);
            graffat.fillOval(objekti.getX(), 700 - objekti.getY(), objekti.getKoko(), objekti.getKoko());
        }

    }
}
