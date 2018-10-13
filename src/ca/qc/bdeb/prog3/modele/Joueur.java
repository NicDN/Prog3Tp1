package ca.qc.bdeb.prog3.modele;

import java.awt.Color;

/**
 * Classe joueur
 *
 * @author Nicolas Demers-Neuwirth
 * @version 1,0
 */
public class Joueur {

    private double points;
    private int numeroJoueur;
    private Color couleur;

    public Joueur(int points, int numeroJoueur, Color couleur) {
        this.points = points;
        this.numeroJoueur = numeroJoueur;
        this.couleur = couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    public Color getCouleur() {
        return couleur;
    }

    public int getNumeroJoueur() {
        return numeroJoueur;
    }

    public void ajouterPoints(double nombreAjouter) {
        this.points = nombreAjouter;
    }

    public int getPoints() {
        return (int) points;
    }

}
