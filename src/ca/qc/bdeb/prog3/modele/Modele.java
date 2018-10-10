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

    private int minute = 0, seconde = 0;

    private Joueur joueur1 = new Joueur(0, 1, Color.BLUE);
    private Joueur joueur2 = new Joueur(0, 2, Color.RED);
    private Joueur joueurActif = joueur1;
    private int cpt = 0;

    private int position = 0;

    public Modele() {

    }

    private void majObserver() {
        setChanged();
        notifyObservers();
    }

    public void resetPartie() {

//       Ne fonctionne pas, continue de se souvenir lequel a ete cliquer
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {

                    tabBoutonModele[i][j][k] = 0;
                }
            }

        }
        joueur1.ajouterPoints(0);
        joueur2.ajouterPoints(0);

        majObserver();
    }

    public void calculerPoints(Joueur joueur) {
        double point10 = 0;
        double points15 = 0;

        double points20 = 0;
        try {
            point10 = calculerPoints10(joueur);
            points15 = calculerPoints15(joueur);

            points20 = calculerPoints20(joueur);
        } catch (IndexOutOfBoundsException ex) {

        }
        double pointsTot = point10 + points15 + points20;
        joueur.ajouterPoints(pointsTot);

        majObserver();
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

        Color saCouleur = joueurActif.getCouleur();
        joueurActif.setCouleur(couleur);

        for (int i = 0; i < tabBoutonModele.length; i++) {
            for (int j = 0; j < tabBoutonModele[i].length; j++) {
                for (int k = 0; k < tabBoutonModele[i][j].length; k++) {
                    if (tabBoutonModele[i][j][k] == 1) {
                         majObserver();
                        
                    } else if (tabBoutonModele[i][j][k] == 2) {
                         majObserver();
                       
                    }
                   
                }

            }
        }

//        changerCouleurPlan(couleur);
    }

    private void changerCouleurPlan(Color couleur) {

    }

    private double calculerPoints10(Joueur joueur) {

        double points = 0;

        for (int i = 0; i < tabBoutonModele.length; i++) {
            for (int j = 0; j < tabBoutonModele[i].length; j++) {

                for (int k = 0; k < tabBoutonModele[i][j].length; k++) {

                    if (tabBoutonModele[i][0][k] == joueur.getNumeroJoueur() && tabBoutonModele[i][1][k] == joueur.getNumeroJoueur() && tabBoutonModele[i][2][k] == joueur.getNumeroJoueur() && tabBoutonModele[i][3][k] == joueur.getNumeroJoueur()) {
//                         horizontal
                        points = points + 2.5;

                        //rentre 4 fois dans boucle
                    }

                    if (tabBoutonModele[0][j][k] == joueur.getNumeroJoueur() && tabBoutonModele[1][j][k] == joueur.getNumeroJoueur() && tabBoutonModele[2][j][k] == joueur.getNumeroJoueur() && tabBoutonModele[3][j][k] == joueur.getNumeroJoueur()) {
//                        vertical
                        points = points + 2.5;
                        //rentre 4 fois dans boucle
                    }

                    if (i == 0 && j == 0) {
                        //diagonale
                        if (tabBoutonModele[i][j][k] == joueur.getNumeroJoueur() && tabBoutonModele[i + 1][j + 1][k] == joueur.getNumeroJoueur() && tabBoutonModele[i + 2][j + 2][k] == joueur.getNumeroJoueur() && tabBoutonModele[i + 3][j + 3][k] == joueur.getNumeroJoueur()) {
                            points = points + 10;
                        }
                    }

                    if (i == 0 && j == 3) {
                        //diagonale
                        if (tabBoutonModele[i][j][k] == joueur.getNumeroJoueur() && tabBoutonModele[i + 1][j - 1][k] == joueur.getNumeroJoueur() && tabBoutonModele[i + 2][j - 2][k] == joueur.getNumeroJoueur() && tabBoutonModele[i + 3][j - 3][k] == joueur.getNumeroJoueur()) {
                            points = points + 10;
                        }
                    }

                }
            }
        }

        return points;

    }

    private double calculerPoints15(Joueur joueur) {
        int k = 0;
        double points = 0;
        for (int i = 0; i < tabBoutonModele.length; i++) {
            for (int j = 0; j < tabBoutonModele[i].length; j++) {

                if ((tabBoutonModele[i][0][0] == joueur.getNumeroJoueur() && tabBoutonModele[i][1][1] == joueur.getNumeroJoueur() && tabBoutonModele[i][2][2] == joueur.getNumeroJoueur() && tabBoutonModele[i][3][3] == joueur.getNumeroJoueur()) || (tabBoutonModele[i][3][0] == joueur.getNumeroJoueur() && tabBoutonModele[i][2][1] == joueur.getNumeroJoueur() && tabBoutonModele[i][1][2] == joueur.getNumeroJoueur() && tabBoutonModele[i][0][3] == joueur.getNumeroJoueur())) {
//                         horizontal 4321 gauche doite et 1234 droite gauche
                    points = points + 3.75;

                }
                if (tabBoutonModele[i][3][3] == joueur.getNumeroJoueur() && tabBoutonModele[i][2][2] == joueur.getNumeroJoueur() && tabBoutonModele[i][1][1] == joueur.getNumeroJoueur() && tabBoutonModele[i][0][0] == joueur.getNumeroJoueur()) {
//                         horizontal 4321 des deux sens
                    points = points + 3.75;

                }

            }
        }

        return points;
//pas fini
    }

    private int calculerPoints20(Joueur joueur) {
        int points = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                if ((tabBoutonModele[i][j][0] == joueur.getNumeroJoueur() && tabBoutonModele[i][j][1] == joueur.getNumeroJoueur() && tabBoutonModele[i][j][2] == joueur.getNumeroJoueur() && tabBoutonModele[i][j][3] == joueur.getNumeroJoueur())) {
                    points = points + 20;

                }

            }
        }
        return points;
    }

    public void placerMarqueur(int positionX, int positionY, int positionDansQuad, int positionBtnListe) {

        tabBoutonModele[positionX][positionY][positionDansQuad] = joueurActif.getNumeroJoueur();

        majObserver();

        cpt++;
        calculerPoints(joueurActif);
        if (cpt == 64) {
            terminerPartie();
        } else {
            if (joueurActif == joueur1) {
                joueurActif = joueur2;
            } else if (joueurActif == joueur2) {
                joueurActif = joueur1;
            }
        }

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

    private void terminerPartie() {

    }

}
