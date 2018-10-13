package ca.qc.bdeb.prog3.vue;

import ca.qc.bdeb.prog3.modele.Modele;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Classe Fenetre
 *
 * @author Nicolas
 * @version 1,0
 */
public class Fenetre extends JFrame implements Observer {

    private ArrayList<Quad> listeQuad = new ArrayList();
    private int tabBoutonVue[][][] = new int[4][4][4];

    private JComboBox cboBoite;



    private JPanel pnlPrincipal1;
    private JPanel pnlJeu;
    private JPanel pnlPrincipal2;

    private PanelPoints pnlPointsHaut;
    private PanelPoints pnlPointsBas;
    private JLabel lblTitre;
    private JLabel lblTimer;

    private JMenuBar mnuBar;
    private JMenu mnuFichier;
    private JMenu mnuAide;

    private JMenuItem mnuNouvellePartie;
    private JMenuItem mnuOptions;
    private JMenuItem mnuQuitter;
    private JMenuItem mnuAPropos;

    private Modele modele;
    private Quad quad;

    private Timer tmr = new Timer(1000, new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            modele.chronometrer();

        }
    });

    public Fenetre(Modele modele) throws HeadlessException {

        this.modele = modele;
        modele.addObserver(this);

        initialiserComposantes();

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

    /**
     * Méthode qui crée l'interface graphique du jeu
     *
     * @see creerMenu();
     */
    public void creer() {
        tmr.start();
        creerMenu();

        pnlPrincipal2.add(lblTitre, BorderLayout.SOUTH);
        pnlPrincipal2.add(lblTimer, BorderLayout.EAST);
        this.add(pnlPrincipal2, BorderLayout.NORTH);

        pnlPrincipal1.add(pnlPointsHaut, BorderLayout.NORTH);

        creerTableJeu();

        pnlPrincipal1.add(pnlPointsBas, BorderLayout.SOUTH);

        this.add(pnlPrincipal1, BorderLayout.CENTER);
    }

    /**
     * Méthode qui inilisalise les composantes et les mets en mémoire
     */
    private void initialiserComposantes() {

        pnlPrincipal1 = new JPanel(new BorderLayout());
        pnlPrincipal2 = new JPanel(new BorderLayout());

        pnlJeu = new JPanel(new GridLayout(4, 4));
        pnlJeu.setPreferredSize(new Dimension(500, 500));

        pnlPointsHaut = new PanelPoints(modele, modele.getJoueur1());
        pnlPointsBas = new PanelPoints(modele, modele.getJoueur2());
        lblTitre = new JLabel("Hijara", JLabel.CENTER);
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

    /**
     * Méthode qui crée le menu du jeu
     */
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

    /**
     * Méthode qui réactive toute les 1ere case et désactive les autres
     */
    private void reset() {

        for (int i = 0; i < listeQuad.size(); i++) {
            for (int j = 0; j < 4; j++) {

                listeQuad.get(i).getListeBouton().get(j).setEnabled(false);

                if (listeQuad.get(i).getListeBouton().indexOf(listeQuad.get(i).
                        getListeBouton().get(j)) == 0) {

                    listeQuad.get(i).getListeBouton().get(j).setEnabled(true);
                }

            }

        }

        modele.resetPartie();
    }

    /**
     * Évènements sur les options du menu
     */
    public void creerEventsMenu() {

        mnuNouvellePartie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                reset();

            }

        });

        mnuOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                FenetreOptions fenetreOptions = new FenetreOptions(modele);

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
                JOptionPane.showMessageDialog(Fenetre.this, "Nicolas Demers-"
                        + "Neuwirth " + " Remise le dimanche 14 octobre 2018 à "
                        + "minuit ");
            }
        });

    }

    /**
     * Méthode qui crée les 16 quads du jeu
     */
    private void creerTableJeu() {

        for (int i = 0; i < 4; i++) {

            for (int j = 0; j < 4; j++) {

                quad = new Quad(modele, i, j);
                listeQuad.add(quad);
                pnlJeu.add(quad);
            }

        }
        pnlPrincipal1.add(pnlJeu, BorderLayout.CENTER);
    }

    @Override
    public void update(Observable o, Object arg) {

        lblTimer.setText("" + modele.getTexteTemps());

        if (modele.isIsDone()) {
            tmr.stop();
            String texte = null;
            if (modele.saCouleur() == null) {
                texte = "Partie nulle";

            } else if (modele.saCouleur() != null) {
                System.out.println("C'est fini " + modele.saCouleur());
                texte = "Le joueur " + modele.saCouleur() + " a gagné.";

            }
            int confirm = JOptionPane.showConfirmDialog(this, texte + "Voulez "
                    + "vous recommencer à jouer?", "Confirmation", JOptionPane.
                            YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) {

                reset();
                tmr.start();
            } else {
                System.exit(0);
            }

        }

    }

    /**
     * Méthode qui valide si le joueur veut réellement quitter le jeu
     */
    public void confirmerQuitter() {
        int confirm = JOptionPane.showOptionDialog(null, "Voulez vous fermer "
                + "l’application?", "Confirmation", JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE, null, null, null);

        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public Timer getTimer() {
        return tmr;
    }

}
