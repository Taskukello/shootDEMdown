/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toimintaTest.pelaajaTest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import toiminta.pelaaja.Alus;

/**
 *testaa Alus luokan
 * @author Aki
 */
public class AlusTest {
    
    private Alus alus;
    
    public AlusTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        alus = new Alus();
        
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void aluksenKoordinaatitAluksiOikein() {
        assertEquals(this.alus.getX(), 250);
        assertEquals(this.alus.getY(), 100);
    }
    
    @Test
    public void aluksenXKoordinaattiMuuttuuOikeinOikealleMentaessaJaSetXToimii() {
        this.alus.siirry(10);
        assertEquals(this.alus.getX(), 260);
        this.alus.setX(100);
        assertEquals(this.alus.getX(), 100);
    }
    
    @Test
    public void AluskenXKoordinaattiMuuttuuOikeinVasemmalleMentaessa() {
        this.alus.siirry(-10);
        assertEquals(this.alus.getX(), 240);
    }
    
    @Test
    public void setYToimii() {                                          //*köh köh* turha *köh köh*
        this.alus.setY(200);
        assertEquals(this.alus.getY(), 200);
        
    }
    
    @Test
    public void menetaElamaToimii() {
        assertEquals(this.alus.menetaElama(), true);
        assertEquals(this.alus.getElamat(), 2);
    }
    
    @Test
    public void lisaaElamaToimii() {
        this.alus.lisaaElama();
        assertEquals(this.alus.getElamat(), 4);
    }
    
    @Test
    public void lisaaElamaEiYlitaArvoaViisi() {
        this.alus.lisaaElama();
        this.alus.lisaaElama();
        this.alus.lisaaElama();
        this.alus.lisaaElama();
        this.alus.lisaaElama();
        assertEquals(this.alus.getElamat(), 7);
    }
    
    @Test
    public void aluksenElamatAluksiKolme() {
        assertEquals(this.alus.getElamat(), 3);
    }
    
    @Test
    public void aluksenKokoOnAluksiOikea() {
        assertEquals(this.alus.getKoko(), 20);
    }
    
    @Test
    public void aluksenKokoMuuttuuOikein() {
        this.alus.setKoko(30);
        assertEquals(this.alus.getKoko(), 30);
        
    }
    
    @Test
    public void alusEiLiikuMaxXUlkopuolelle() {
        this.alus.siirry(260);
        assertEquals(this.alus.getX(), 500);
    }
    
    @Test
    public void alusEiliikuMinXUlkopuolelle() {
        this.alus.siirry(-260);
        assertEquals(this.alus.getX(), 0);
    }
    

}
