/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog3.vue;

import ca.qc.bdeb.prog3.modele.Joueur;
import ca.qc.bdeb.prog3.modele.Modele;
import java.awt.Color;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author 1743379
 */
public class PanelPoints extends JPanel implements Observer {

    Modele modele;
    private Joueur joueur;
//    private int tabPoints[] = {5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65};

    private ArrayList<JLabel> listePoints = new ArrayList<JLabel>();
    private JLabel lblCouleur = new JLabel();
    private JLabel lblPts = new JLabel("Points");

    public PanelPoints(Modele modele, Joueur joueur) {

        creerPanneau(joueur);
        this.joueur = joueur;

        this.modele = modele;
        modele.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
//Lorsque le calcul des points et du changement de couleur sera appeler, mettre ceci a jour
        for (int i = 0; i < listePoints.size(); i++) {
            if (joueur.getPoints() == Integer.parseInt(listePoints.get(i).getText())) {
                listePoints.get(i).setForeground(joueur.getCouleur());
            } else {
                listePoints.get(i).setForeground(null);
            }
        }

        String couleur = null;
        couleur = vérifierCouleur(joueur);

        lblCouleur.setText(couleur);
    }

    private void creerPanneau(Joueur joueur) {
        this.setLayout(new GridLayout(0, 15));

        String couleur = null;
        couleur = vérifierCouleur(joueur);

        lblCouleur.setText(couleur);
        this.add(lblCouleur);

        for (int i = 5; i <= 65; i += 5) {

            JLabel lbl = new JLabel("" + i);
            listePoints.add(lbl);
            this.add(lbl);

        }
        this.add(lblPts);
    }

    private String vérifierCouleur(Joueur joueur) {
        
        String couleur = null;
        if (joueur.getCouleur() == Color.BLUE) {
            couleur = "Blue";
        } else if (joueur.getCouleur() == Color.RED) {
            couleur = "Red";
        } else if (joueur.getCouleur() == Color.GREEN) {
            couleur = "Green";
        } else if (joueur.getCouleur() == Color.YELLOW) {
            couleur = "Yellow";
        }
        return couleur;
    }
}
