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

    private double points;
   private int numeroJoueur;
    private Color couleur;

    public Joueur(int points, int numeroJoueur, Color couleur) {
        this.points = points;
        this.numeroJoueur=numeroJoueur;
        this.couleur = couleur;
    }

  

    public void setCouleur(Color couleur) {
        this.couleur=couleur;
    }

    public Color getCouleur() {
        return couleur;
    }

    public int getNumeroJoueur() {
        return numeroJoueur;
    }
    public void ajouterPoints(double nombreAjouter){
        this.points=nombreAjouter;
    }

    public int getPoints() {
        return (int) points;
    }
     

    
    

   
    
   
    

}
