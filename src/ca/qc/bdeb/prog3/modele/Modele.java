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

//        calculerPoints10(joueur);
//        calculerPoints15(joueur);
//        calculerPoints20(joueur);
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
//                    if (tabBoutonModele[i][j][k] == 1) {
//                        
//                        joueurActif.setCouleur(couleur);
//                    } else if (tabBoutonModele[i][j][k] == 2) {
//                        
//                        joueurActif.setCouleur(couleur);
//                    }
                    
                }
                
            }
        }

//        changerCouleurPlan(couleur);
        majObserver();
    }
    
    private void changerCouleurPlan(Color couleur) {
        
    }
    
    private void calculerPoints10(Joueur joueur) {
        
        for (int i = 0; i < tabBoutonModele.length; i++) {
            for (int j = 0; j < tabBoutonModele[i].length; j++) {
                
                for (int k = 0; k < tabBoutonModele[i][j].length; k++) {
                    if ((tabBoutonModele[0][0][k] == joueur.getNumeroJoueur() && tabBoutonModele[0][3][k] == joueur.getNumeroJoueur() && tabBoutonModele[3][0][k] == joueur.getNumeroJoueur() && tabBoutonModele[3][3][k] == joueur.getNumeroJoueur())) {
                        //quatres coins
                        joueur.ajouterPoints(10);
                        
                    }  
                    
                    if (tabBoutonModele[i][0][k] == joueur.getNumeroJoueur() && tabBoutonModele[i][1][k] == joueur.getNumeroJoueur() && tabBoutonModele[i][2][k] == joueur.getNumeroJoueur() && tabBoutonModele[i][3][k] == joueur.getNumeroJoueur()) {
//                         horizontal
                        joueur.ajouterPoints(10);
                    }  
                    
                    if (tabBoutonModele[0][j][k] == joueur.getNumeroJoueur() && tabBoutonModele[1][j][k] == joueur.getNumeroJoueur() && tabBoutonModele[2][j][k] == joueur.getNumeroJoueur() && tabBoutonModele[3][j][k] == joueur.getNumeroJoueur()) {
//                        vertical
                        joueur.ajouterPoints(10);
                    }
                    
                    if(i==0&&j==0){
                        if(tabBoutonModele[i][j][k]==joueur.getNumeroJoueur()&&tabBoutonModele[i+1][j+1][k]==joueur.getNumeroJoueur()&&tabBoutonModele[i+2][j+2][k]==joueur.getNumeroJoueur()&&tabBoutonModele[i+3][j+3][k]==joueur.getNumeroJoueur()){
                            
                        }
                    }
                    if(i==0&&j==3){
//                        if(tabBoutonModele[i][j][k]==joueur.getNumeroJoueur()&&tabBoutonModele[i+1][j+1][k]==joueur.getNumeroJoueur()&&tabBoutonModele[i+2][j+2][k]==joueur.getNumeroJoueur()&&tabBoutonModele[i+3][j+3][k]==joueur.getNumeroJoueur()){
//                            modifier cet algorithme
//                        }
                    }
                    if(i==3&&j==0){
                        
                    }
                    if(i==3&&j==3){
                        
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
    
    public void placerMarqueur(int positionX, int positionY, int positionDansQuad, int positionBtnListe) {

// Grosse validation
//if(position==positionBtnListe-1){
//    
//}
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
