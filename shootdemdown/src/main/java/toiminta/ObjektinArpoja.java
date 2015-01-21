/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toiminta;

import java.util.Random;

/**
 *
 * @author Aki
 */
public class ObjektinArpoja {

    private int objektinArvo = 0;
    private int objektinKoordinaatti = 0;

    public ObjektinArpoja() {

    }

    public int arvoObjekti() {
        this.objektinArvo = arpaKone(3);
        return this.objektinArvo;

    }

    public int arvoKoordinaatti() {
        this.objektinKoordinaatti = arpaKone(20);
        return this.objektinKoordinaatti;

    }

    public int arpaKone(int rajaArvo) {
        Random ran = new Random();
        int r = ran.nextInt((rajaArvo - 1) + 1) + 1;
        return r;

    }

    public int getObjektinArvo() {
        return this.objektinArvo;

    }

    public int getObjektinKoordinaatti() {
        return this.objektinKoordinaatti;
    }
    
   

}
