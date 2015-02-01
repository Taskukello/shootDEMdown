/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toiminta.vihollisobjekti;

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
        int prosentti = arpaKone(100);
        if (prosentti <= 70){
            this.objektinArvo = 1;
        }else if (prosentti <= 90 && prosentti > 70){
            this.objektinArvo = 2;
        }else{
            this.objektinArvo = 3;
        }
        return this.objektinArvo;

    }

    public int arvoKoordinaatti() {
        this.objektinKoordinaatti = arpaKone(48) *10;
        
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
