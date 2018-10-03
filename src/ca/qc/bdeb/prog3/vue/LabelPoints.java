/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog3.vue;

import ca.qc.bdeb.prog3.modele.Joueur;
import ca.qc.bdeb.prog3.modele.Modele;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;

/**
 *
 * @author 1743379
 */
public class LabelPoints extends JLabel implements Observer {

    Modele modele;
    private Joueur joueur;
//    private int tabPoints[] = {5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65};
    
    private ArrayList<JLabel> listePoints=new ArrayList<JLabel>();
    
    private String texte;

    public LabelPoints(Modele modele, String texte, Joueur joueur) {
        this.joueur = joueur;
        this.texte = texte;
  
        this.modele = modele;
        modele.addObserver(this);
    }

//    public void setText(Joueur joueur) {
//        for (int i = 0; i < tabPoints.length; i++) {
//            texte =  texte + tabPoints[i];
//        }
//       texte=joueur.getCouleur()+texte+"points";
//    }

    @Override
    public void update(Observable o, Object arg) {
//Lorsque le calcul des points et du changement de couleur sera appeler, mettre ceci a jour



    }

}
