/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog3.modele;

import java.util.Observable;

/**
 *
 * @author Nicolas
 */
public class Modele extends Observable {

    public Modele() {
    }
    
    public void majValeurs(){
        
        
        
        
        majObsever();
    }

    private void majObsever() {
        setChanged();
        notifyObservers();
    }
}
