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

    public void majValeurs() {

        majObsever();
    }

    private void majObsever() {
        setChanged();
        notifyObservers();
    }

    public void démarrer() {

        for (int i = 0; i < 32; i++) {

            for (int j = 0; j < 2; j++) {
                if (j == 0) {

                } else if (j == 1) {

                }
            }
        }
        
       
        
    }
}
