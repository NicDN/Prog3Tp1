/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog3.modele;

import ca.qc.bdeb.prog3.vue.Fenetre;
import java.awt.Color;

import java.util.Observable;
import javax.swing.JButton;

/**
 *
 * @author Nicolas
 */
public class Modele extends Observable {

    private int tabBoutonModele[][][] = new int[4][4][4];

    private Joueur joueurActif;

    private int minute = 0, seconde = 0;

    private Joueur joueur1 = new Joueur(0, 1, Color.BLUE);
    private Joueur joueur2 = new Joueur(0, 2, Color.RED);

    public Modele() {

    }

    private void majObserver() {
        setChanged();
        notifyObservers();
    }

    public void démarrer() {

//        for (int i = 0; i < 32; i++) {
//
//            for (int j = 0; j < 2; j++) {
//                if (j == 0) {
//                    joueurActif = joueurA;
//
//                } else if (j == 1) {
//                    joueurActif = joueurB;
//
//                }
//                
//                calculerPoints();
//            }
//        }
    }

    public void resetPartie() {

    }

    public void calculerPoints() {

    }

    public void chronometrer() {
        seconde++;
        if (seconde > 59) {
            minute++;
            seconde = 0;
        }
        majObserver();
    }

    public void changerCouleur(Color couleur) {
        joueurActif.setCouleur(couleur);
//        changerCouleurPlan(couleur);
        majObserver();
    }

    private void changerCouleurPlan(Color couleur) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public int getMinute() {
        return minute;
    }

    public int getSeconde() {
        return seconde;
    }

    public void placerMarqueur(int positionX, int positionY, int positionDansQuad) {
        joueurActif = joueur2;
        
        tabBoutonModele[positionX][positionY][positionDansQuad] = joueurActif.getNumeroJoueur();

        majObserver();
    }

    public int[][][] getTabBoutonModele() {
        return tabBoutonModele;
    }

    public Joueur getJoueur1() {
        return joueur1;
    }

    public Joueur getJoueur2() {
        return joueur2;
    }

}
