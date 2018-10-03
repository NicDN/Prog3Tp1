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

        for (int i = 0; i < 32; i++) {

            for (int j = 0; j < 2; j++) {
                if (j == 0) {
                    joueurActif = joueur1;
//                    calculerPoints(joueur1);
                } else if (j == 1) {
                    joueurActif = joueur2;
//                    calculerPoints(joueur2);
                }

            }
        }
    }

    public void resetPartie() {

//        Semble fonctionner pour l'instant
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    tabBoutonModele[i][j][k] = 0;
                }
            }

        }
        joueur1.setPoints(0);
        joueur2.setPoints(0);

        majObserver();
    }

    public void calculerPoints(Joueur joueur) {

        calculerPoints10(joueur);
        calculerPoints15(joueur);
       calculerPoints20(joueur);

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

    private void calculerPoints10(Joueur joueur) {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    if(){
//                        quatres coins
                    }else if(){
//                        horizontale
                    }else if(){
//                        vertical
                    }

                }
            }
        }
    }

    private void calculerPoints15(Joueur joueur) {

    }

    private void calculerPoints20(Joueur joueur) {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    if ((tabBoutonModele[i][j][k] == joueur.getNumeroJoueur() && tabBoutonModele[i][j][k + 1] == joueur.getNumeroJoueur() && tabBoutonModele[i][j][k + 2] == joueur.getNumeroJoueur() && tabBoutonModele[i][j][k + 3] == joueur.getNumeroJoueur())) {
                        joueur.ajouterPoints(20);

                    }
                }
            }
        }
    }

    public void placerMarqueur(int positionX, int positionY, int positionDansQuad) {

        joueurActif = joueur2;

        tabBoutonModele[positionX][positionY][positionDansQuad] = joueurActif.getNumeroJoueur();

        majObserver();
    }

    public int getMinute() {
        return minute;
    }

    public int getSeconde() {
        return seconde;
    }

    public Joueur getJoueur1() {
        return joueur1;
    }

    public Joueur getJoueur2() {
        return joueur2;
    }

    public int[][][] getTabBoutonModele() {
        return tabBoutonModele;
    }

}
