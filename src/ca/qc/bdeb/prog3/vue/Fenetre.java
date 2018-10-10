/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog3.vue;

import ca.qc.bdeb.prog3.modele.Modele;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Nicolas
 */
public class Fenetre extends JFrame implements Observer {
 
    private int tabBoutonVue[][][] = new int[4][4][4];
     
    private JComboBox cboBoite;

    private JFrame fenetre2;

    private JPanel pnlPrincipal1;
    private JPanel pnlJeu;
    private JPanel pnlPrincipal2;

    private LabelPoints lblPointsHaut;
    private LabelPoints lblPointsBas;
    private JLabel lblTitre;
    private JLabel lblTimer;

    private JMenuBar mnuBar;
    private JMenu mnuFichier;
    private JMenu mnuAide;

    private JMenuItem mnuNouvellePartie;
    private JMenuItem mnuOptions;
    private JMenuItem mnuQuitter;
    private JMenuItem mnuAPropos;

    Modele modele;
    private Quad quad;

    private Timer timer = new Timer(1000, new ActionListener() {
//METTRE 0 !!
        @Override
        public void actionPerformed(ActionEvent e) {
            modele.chronometrer();

        }
    });

    public Fenetre(Modele modele) throws HeadlessException {

        this.modele = modele;
        modele.addObserver(this);

        déclarerComposantes();

        setTitle("Hijara");
        setSize(570, 850);
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
        démarrerTimer();
        creerMenu();

        pnlPrincipal2.add(lblTitre, BorderLayout.SOUTH);
        pnlPrincipal2.add(lblTimer, BorderLayout.EAST);
        this.add(pnlPrincipal2, BorderLayout.NORTH);

        pnlPrincipal1.add(lblPointsHaut, BorderLayout.NORTH);

        creerTableJeu();

        pnlPrincipal1.add(lblPointsBas, BorderLayout.SOUTH);

        this.add(pnlPrincipal1, BorderLayout.CENTER);
    }

    private void déclarerComposantes() {

        pnlPrincipal1 = new JPanel(new BorderLayout());
        pnlPrincipal2 = new JPanel(new BorderLayout());

        pnlJeu = new JPanel(new GridLayout(4, 4));
        pnlJeu.setPreferredSize(new Dimension(500, 500));

        lblPointsHaut = new LabelPoints(modele,modele.getJoueur1());
        lblPointsBas = new LabelPoints(modele,modele.getJoueur2());
        lblTitre = new JLabel("               Hijara");
        lblTitre.setFont(new Font("Elephant", Font.BOLD, 42));
        lblTitre.setPreferredSize(new Dimension(500, 50));
        lblTimer = new JLabel("0:00");

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


        pnlPrincipal2.add(mnuBar, BorderLayout.NORTH);
    }

    public void creerEvents() {

    }

    public void creerEventsMenu() {

        mnuNouvellePartie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modele.resetPartie();
            }

        });

        mnuOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                creerFenetreCouleur();

                String valeurSelectionee = (String) cboBoite.getSelectedItem();
                Color couleur = null;
                
                if (valeurSelectionee.equalsIgnoreCase("Vert")) {
                    couleur = Color.GREEN;

                } else if (valeurSelectionee.equalsIgnoreCase("Rouge")) {
                    couleur = Color.RED;
                } else if (valeurSelectionee.equalsIgnoreCase("Bleu")) {
                    couleur = Color.BLUE;
                } else if (valeurSelectionee.equalsIgnoreCase("Jaune")) {
                    couleur = Color.YELLOW;
                }
                
                modele.changerCouleur(couleur);
            }

            private void creerFenetreCouleur() {
                fenetre2 = new JFrame();
                fenetre2.setLayout(new GridLayout(2, 0));
                fenetre2.setTitle("Options");
                fenetre2.setSize(300, 150);

                JLabel lblCouleur = new JLabel("Sélectionnez une couleur:");
                String choixCouleur[] = {"Bleu", "Rouge", "Vert", "Jaune"};
                cboBoite = new JComboBox(choixCouleur);
                cboBoite.setPreferredSize(new Dimension(50, 20));

                fenetre2.add(lblCouleur);
                fenetre2.add(cboBoite);

                fenetre2.setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
                fenetre2.setVisible(true);
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

        for (int i = 0; i < 4; i++) {
            
            for(int j=0;j<4;j++){
                
                quad = new Quad(modele,i,j);
                pnlJeu.add(quad);
            }
     
        }
        pnlPrincipal1.add(pnlJeu, BorderLayout.CENTER);
    }

    @Override
    public void update(Observable o, Object arg) {
        lblTimer.setText(modele.getMinute() + ":" + modele.getSeconde());
        
    }

   

    public void confirmerQuitter() {
        int confirm = JOptionPane.showOptionDialog(null, "Voulez vous fermer l’application?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public void démarrerTimer() {
       // timer.start();
    }

    public Timer getTimer() {
        return timer;
    }

}
