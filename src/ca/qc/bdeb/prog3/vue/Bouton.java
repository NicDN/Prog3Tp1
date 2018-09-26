/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog3.vue;

import java.awt.Color;
import javax.swing.JButton;

/**
 *
 * @author 1743379
 */
public class Bouton extends JButton {

   private int positionDansQuad;
    private Color couleur;

    public Bouton(String texte, int positionDansQuad) {
        super(texte);
        this.positionDansQuad=positionDansQuad;
    }

  

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    public int getPositionDansQuad() {
        return positionDansQuad;
    }
    

}
