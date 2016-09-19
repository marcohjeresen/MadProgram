/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

/**
 *
 * @author markh_000
 */
public class numberutil {

    private double endpris;

    public numberutil() {
        endpris = 0.00;
        
    }
    
    public double getpris(int pris){
        endpris = pris;
        endpris = endpris / 100;
        return endpris;
    }
}
