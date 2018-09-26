package ca.qc.bdeb.prog3.modele;

import java.awt.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 1743379
 */
public class Joueur {

    private int points;
    private String nom;
    private Color couleur;

    public Joueur(int points, String nom, Color couleur) {
        this.points = points;
        this.nom = nom;
        this.couleur = couleur;
    }

  

    public void setCouleur(Color couleur) {
        this.couleur=couleur;
    }

    public Color getCouleur() {
        return couleur;
    }
    
   
    

}
