/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog3.vue;

import ca.qc.bdeb.prog3.modele.Modele;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
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
public class Fenetre extends JFrame implements Observer {

    private JPanel pnlPrincipal;
    private JPanel pnlJeu;
    private JPanel pnlSecondaire;

    private JLabel lblPointsHaut;
    private JLabel lblPointsBas;
    private JLabel lblTitre;

    private JMenuBar mnuBar;
    private JMenu mnuFichier;
    private JMenu mnuAide;

    private JMenuItem mnuNouvellePartie;
    private JMenuItem mnuOptions;
    private JMenuItem mnuQuitter;
    private JMenuItem mnuAPropos;

    Modele modele;
    private Quad quad;

    public Fenetre(Modele modele) throws HeadlessException {

        this.modele = modele;

        déclarerComposantes();

        setTitle("Hijara");
        setSize(500, 850);
        creer();

        creerEventsMenu();
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                confirmerQuitter();
            }
        });

    }

    public void creer() {
        creerMenu();

        this.add(lblTitre, BorderLayout.CENTER);
        pnlPrincipal.add(lblPointsHaut, BorderLayout.NORTH);

        creerTableJeu();

        pnlPrincipal.add(lblPointsBas, BorderLayout.SOUTH);

        this.add(pnlPrincipal, BorderLayout.SOUTH);
    }

    private void déclarerComposantes() {
        //Je dois creer les composantes en mémoire dans cette méthode.
        pnlPrincipal = new JPanel(new BorderLayout());

        pnlJeu = new JPanel(new GridLayout(4, 4));
        pnlJeu.setPreferredSize(new Dimension(500, 500));
        lblPointsHaut = new JLabel("Yellow 5 10 15 20 25 30 35 40 45 50 55 60 65 points ");
        lblPointsBas = new JLabel("Blue 5 10 15 20 25 30 35 40 45 50 55 60 65 points ");
        lblTitre = new JLabel(new ImageIcon("hijara1.jpg"));

        mnuBar = new JMenuBar();
        mnuFichier = new JMenu("Fichier");
        mnuAide = new JMenu("Aide");
        mnuNouvellePartie = new JMenuItem("Nouvelle partie");
        mnuOptions = new JMenuItem("Options");
        mnuQuitter = new JMenuItem("Quitter");
        mnuAPropos = new JMenuItem("À propos");

    }

    public void creerMenu() {
        mnuFichier.add(mnuNouvellePartie);
        mnuFichier.add(mnuOptions);
        mnuFichier.addSeparator();
        mnuFichier.add(mnuQuitter);

        mnuBar.add(mnuFichier);

        mnuAide.add(mnuAPropos);

        mnuBar.add(mnuAide);

        this.add(mnuBar, BorderLayout.NORTH);

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
                confirmerQuitter();
            }
        });

        mnuAPropos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Fenetre.this, "Nicolas Demers-Neuwirth " + " Remise le dimanche 14 octobre 2018 à minuit ");
            }
        });

    }

    private void creerTableJeu() {

        for (int i = 0; i < 16; i++) {
            quad = new Quad(modele);
            pnlJeu.add(quad);
        }
        pnlPrincipal.add(pnlJeu, BorderLayout.CENTER);
    }

    

    @Override
    public void update(Observable o, Object arg) {

    }

    private void resetPartie() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void confirmerQuitter(){
        int confirm = JOptionPane.showOptionDialog(null, "Voulez vous fermer l’application?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
    }
}
