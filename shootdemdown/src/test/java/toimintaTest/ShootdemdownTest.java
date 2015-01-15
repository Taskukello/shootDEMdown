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
import toiminta.Shootdemdown;
import toiminta.VihollisObjekti;

/**
 *
 * @author Aki
 */
public class ShootdemdownTest {

    private Shootdemdown jokuNimi = new Shootdemdown();

    public ShootdemdownTest() {
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
        Shootdemdown j = new Shootdemdown();
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
    
    
}
