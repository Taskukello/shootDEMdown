/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toimintaTest.pelaajaTest;

import javax.swing.SwingUtilities;
import kayttoliittyma.Kayttoliittyma;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import toiminta.pelaaja.Ammus;

/**
 *Testaa Ammus luokan
 * @author Aki
 */
public class AmmusTest {

    private Ammus ammus;

    public AmmusTest() {

    }

    @Before
    public void setUp() {
        this.ammus = new Ammus();
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void ammuksenKoordinaatitOvatOikein() {
        assertEquals(ammus.getX(), 0);
        assertEquals(ammus.getY(), 110);
    }

    @Test
    public void ammuksenKoordinaatinSetteritToimii() {
        ammus.setX(10);
        ammus.setY(10);
        assertEquals(ammus.getX(), 10);
        assertEquals(ammus.getY(), 10);
    }

    @Test
    public void ammusSiirtyyKovinKiltisti() {
        ammus.siirry();
        assertEquals(ammus.getY(), 120);
    }

    @Test
    public void getKokoAntaaOikeanArvon() {
        assertEquals(ammus.getLeveys(), 6);
    }

    @Test
    public void eiSiirryLiikaa() {
        ammus.setY(700);
        ammus.siirry();
        assertEquals(ammus.getY(), 700);
        ammus.setY(690);
        ammus.siirry();
        assertEquals(ammus.getY(), 700);
        ammus.setY(695);
        ammus.siirry();
        assertEquals(ammus.getY(), 695);
    }

}
