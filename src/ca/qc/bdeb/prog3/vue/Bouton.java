/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog3.vue;

import ca.qc.bdeb.prog3.modele.Modele;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;

/**
 *
 * @author 1743379
 */
public class Bouton extends JButton implements Observer {

    Modele modele;

    private int positionDansQuad;
    private Color couleur;
    private int positionX, positionY;

    public Bouton(String texte, int positionX, int positionY, int positionDansQuad, Modele modele) {

        super(texte);
        this.positionX = positionX;
        this.positionY = positionY;
        this.modele = modele;
        modele.addObserver(this);
        setBackground(Color.WHITE);

        this.positionDansQuad = positionDansQuad;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    public int getPositionDansQuad() {
        return positionDansQuad;
    }

    @Override
    public void update(Observable o, Object arg) {

        int numeroJoueur = modele.getTabBoutonModele()[positionX][positionY][positionDansQuad];


        if (numeroJoueur == 1) {
            this.setBackground(modele.getJoueur1().getCouleur());
            this.setEnabled(false);
        } else if (numeroJoueur == 2) {
            this.setBackground(modele.getJoueur2().getCouleur());
            this.setEnabled(false);
        } else if (numeroJoueur == 0) {
            this.setBackground(Color.WHITE);
            this.setEnabled(true);
        }

    }
}