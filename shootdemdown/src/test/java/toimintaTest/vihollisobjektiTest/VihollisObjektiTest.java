/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toimintaTest.vihollisobjektiTest;

import javax.swing.SwingUtilities;
import kayttoliittyma.Kayttoliittyma;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import toiminta.pelaaja.Alus;
import toiminta.vihollisobjekti.VihollisObjekti;

/**
 *testaa vihollisluokan
 * @author Aki
 */
public class VihollisObjektiTest {

    Kayttoliittyma kayttoliittyma = new Kayttoliittyma();
    private VihollisObjekti objekti;

    public VihollisObjektiTest() {

        objekti = new VihollisObjekti(3, 14);
    }

    @Before
    public void setUp() {
    }

    @Test
    public void antaaXArvonOikein() {
        assertEquals(this.objekti.getX(), 14);
    }

    @Test
    public void antaaYArvonOikein() {
        assertEquals(this.objekti.getY(), 700);
    }

    @Test
    public void antaaOikeanMuodon() {
        assertEquals(this.objekti.getMuoto(), 3);
    }

    @Test
    public void SetYToimii() {
        this.objekti.setY(5);
        assertEquals(this.objekti.getY(), 5);
    }

    @Test
    public void liikuToimii() {
        this.objekti.liiku();
        assertEquals(this.objekti.getY(), 698);
    }

    @Test
    public void palauttaaOikeanKoon() {
        assertEquals(this.objekti.getKoko(), 20);
    }

    @Test
    public void setKokoToimii() {
        this.objekti.setKoko(40);
        assertEquals(this.objekti.getKoko(), 40);
    }

    @Test
    public void kuoleekoSeinaanToimii() {
        this.objekti.setY(20);
        assertEquals(this.objekti.kuoleekoSeinaan(), true);
        this.objekti.setY(15);
        assertEquals(this.objekti.kuoleekoSeinaan(), true);
        this.objekti.setY(21);
        assertEquals(this.objekti.kuoleekoSeinaan(), false);
    }

}
