package toimintaTest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import toiminta.Logiikka;
import toiminta.VihollisObjekti;

/**
 *
 * @author Aki
 */
public class LogiikkaTest {

    private Logiikka jokuNimi = new Logiikka();

    public LogiikkaTest() {
    }

    @Before
    public void setUp() {
        this.jokuNimi.luoBlokki();

    }

    @After
    public void tearDown() {
    }

    @Test
    public void onkoVihollisiaAlussaNolla() {
        Logiikka j = new Logiikka();
        assertEquals(j.getVihollisetKoko(), 0);
    }
    
    @Test
    public void onkoVihollisiaLisayksenJalkeenYksi(){
        
        assertEquals(jokuNimi.getVihollisetKoko(), 1);
    }
    
    @Test
    public void liikkuukoBlokkiYhden(){
        this.jokuNimi.setLiikkumisKerrat(1);
        this.jokuNimi.liikutaKaikkia();
        VihollisObjekti objekti = this.jokuNimi.getViholliset().get(0);
        
        assertEquals(objekti.getY() , 28);

    }
    
    @Test
    public void vihollinenKuolee(){
        this.jokuNimi.setLiikkumisKerrat(29);
        this.jokuNimi.liikutaKaikkia();
        assertEquals(this.jokuNimi.getVihollisetKoko() , 0);
    }
    
    @Test
    public void liikutaKaikkiaToimii(){
        this.jokuNimi.pelaa();
        assertEquals(this.jokuNimi.getAlus().getElamat(), 0);
        assertEquals(this.jokuNimi.loppuukoPeli(), true);
    }
    
    
}
