/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toiminta;

/**
 *
 * @author Aki
 */
public class vihollisObjekti {
    
    private int x = 0;
    private int y = 29;
    public vihollisObjekti(){
        
    }
    
    public void liiku(){
        y--;
        if (y == 0){
            elamaPois();
        }
        
    }
    
    public void elamaPois(){
        System.out.println("elama pois");
    }
    
    public int getY(){
        return this.y;
    }    
    
}
