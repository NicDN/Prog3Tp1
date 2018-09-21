/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog3.vue;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Nicolas
 */
public class Fenetre extends JFrame {

    private JPanel pnlPrincipal = new JPanel(new GridLayout(4, 0));
    private JPanel pnlJeu = new JPanel();

    private JLabel lblPointsHaut = new JLabel("Yellow 5 10 15 20 25 30 35 40 45 50 55 60 65 points ");
    private JLabel lblPointsBas = new JLabel("Blue 5 10 15 20 25 30 35 40 45 50 55 60 65 points ");
    private JLabel lblTitre = new JLabel("Hijara");

    private JMenuBar mnuBar = new JMenuBar();
    private JMenu mnuFichier = new JMenu("Fichier");
    private JMenu mnuAide = new JMenu("Aide");

    private JMenuItem mnuNouvellePartie = new JMenuItem("Nouvelle partie");
    private JMenuItem mnuOptions = new JMenuItem("Options");
    private JMenuItem mnuQuitter = new JMenuItem("Quitter");
    private JMenuItem mnuAPropos = new JMenuItem("À propos");

    public Fenetre() throws HeadlessException {
        setTitle("Hijara");
        setSize(400, 650);
        creer();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

    public void creer() {
        creerMenu();

        pnlPrincipal.add(lblTitre);
        pnlPrincipal.add(lblPointsHaut);

        creerTableJeu();

        pnlPrincipal.add(lblPointsBas);

        this.add(pnlPrincipal);
    }

    public void creerMenu() {
        mnuFichier.add(mnuNouvellePartie);
        mnuFichier.add(mnuOptions);
        mnuFichier.addSeparator();
        mnuFichier.add(mnuQuitter);

        mnuBar.add(mnuFichier);

        mnuAide.add(mnuAPropos);

        mnuBar.add(mnuAide);

        this.setJMenuBar(mnuBar);
    }

    public void creerEvents() {

    }

    public void creerEventsMenu() {

        mnuNouvellePartie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetPartie();
            }

        });

        mnuOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        mnuQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        
        mnuAPropos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Fenetre.this, "Nicolas Demers-Neuwirth "+" Remise le dimanche 14 octobre 2018 à minuit ");
            }
        });

        
    }

    private void creerTableJeu() {
        pnlPrincipal.add(pnlJeu);
    }

    private void resetPartie() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
