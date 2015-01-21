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
import toiminta.ObjektinArpoja;

/**
 *
 * @author Aki
 */
public class ObjektinArpojaTest {

    private ObjektinArpoja arpoja;

    public ObjektinArpojaTest() {
    }

    @Before
    public void setUp() {
        arpoja = new ObjektinArpoja();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void meneekoArpaKoneRajojenYli() {                       
        int k = 0;
        while (k < 10000) {
            int r = arpoja.arpaKone(2);
            if (r < 0 || r > 2) {
                fail("Tulos meni raja-arvojen yli/ali");
                break;
            } else {
                k++;

            }

            if (k == 10000) {
                assertTrue(true);
            }
        }
    }

    @Test
    public void onkoObjektinArvoNollaAlussa() {
        assertEquals(arpoja.getObjektinArvo(), 0);

    }

    @Test
    public void onkoObjektinKoordinaattiAlussaNolla() {
        assertEquals(arpoja.getObjektinKoordinaatti(), 0);

    }

    @Test
    public void toimiikoObjektinKoordinaatinArpoja() {

        if (arpoja.arvoKoordinaatti() > 0 && arpoja.getObjektinKoordinaatti() < 20) {
            assertTrue(true);
        } else {
            fail("Arpoja antaa liian suuren tai pienen arvon!");

        }

    }

    @Test
    public void toimiikoObjektinArpoja() {
        if (arpoja.arvoObjekti() > 0 && arpoja.getObjektinArvo() < 4) {
            assertTrue(true);
        } else {
            fail("Arpoja antaa liian suuren tai pienen arvon!");

        }

    }
}
