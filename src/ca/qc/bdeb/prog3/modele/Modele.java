/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog3.modele;

import ca.qc.bdeb.prog3.vue.Fenetre;
import java.util.Observable;
import javax.swing.Timer;

/**
 *
 * @author Nicolas
 */
public class Modele extends Observable {
    
    private Joueur joueurActf;

    private int minute=0, seconde=0;

    public Modele() {
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

    public void resetPartie() {

    }


    public void chronometrer() {
        seconde++;
        if (seconde > 59) {
            minute++;
            seconde = 0;
        }
        majObsever();
    }

    public int getMinute() {
        return minute;
    }

    public int getSeconde() {
        return seconde;
    }

}
