/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog3.vue;

import ca.qc.bdeb.prog3.modele.Joueur;
import ca.qc.bdeb.prog3.modele.Modele;
import java.awt.Color;
import java.awt.Font;

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

        boolean bool = modele.vérifierJoueurAcif();
        String couleur1 = vérifierCouleur(modele.getJoueur1());
        String couleur2 = vérifierCouleur(modele.getJoueur2());
       
        if (bool) {

            if (lblCouleur.getText() == couleur1) {
                lblCouleur.setFont(new Font("Times New Roman", Font.BOLD, 17));
            } else {
                lblCouleur.setFont(new Font("Times New Roman", Font.PLAIN, 17));
            }

        } else {
            if (lblCouleur.getText() == couleur2) {
                lblCouleur.setFont(new Font("Times New Roman", Font.BOLD, 17));
            } else {
                lblCouleur.setFont(new Font("Times New Roman", Font.PLAIN, 17));
            }
        }

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
