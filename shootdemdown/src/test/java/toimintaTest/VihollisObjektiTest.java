/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toimintaTest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import toiminta.VihollisObjekti;

/**
 *
 * @author Aki
 */
public class VihollisObjektiTest {

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
        assertEquals(this.objekti.getY(), 29);
    }

    @Test
    public void antaaOikeanMuodon() {
        assertEquals(this.objekti.getMuoto(), 3);
    }
    
    @Test
    public void SetYToimii(){
        this.objekti.setY(5);
        assertEquals(this.objekti.getY(), 5);
    }

    @Test
    public void liikuPalauttaaFalseKunYNolla() {
        this.objekti.setY(0);
        if (this.objekti.liiku() == false){
            assertTrue(true);
        }else{
            fail("vihollisobjekti palautti true falsen sijaan.");
        }
    }
    
    @Test
    public void liikuPlauttaaTrueKunEiNolla(){
        if (this.objekti.liiku() == true){
            assertTrue(true);
        }else{
            fail("vihollisobjekti palautti false truen sijaan.");
        }
    }
}
