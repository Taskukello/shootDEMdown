/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toiminta;

import java.util.ArrayList;
import javax.swing.SwingUtilities;
import kayttoliittyma.Kayttoliittyma;
import toiminta.vihollisobjekti.VihollisObjekti;

/**
 *
 * @author Aki
 */
public class PaaOhjelma {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Kayttoliittyma kayttoliittyma = new Kayttoliittyma();

        SwingUtilities.invokeLater(kayttoliittyma);
        Logiikka peli = new Logiikka(kayttoliittyma);

        peli.pelaa();
    }

}
