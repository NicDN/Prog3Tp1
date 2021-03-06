package ca.qc.bdeb.prog3.vue;

import ca.qc.bdeb.prog3.modele.Modele;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.Timer;

/**
 * Classe de la Fenetre pour permet de changer la couleur d'un joueur
 *
 * @author Nicolas Demers-Neuwirth
 * @version 1,0
 */
public class FenetreOptions extends JFrame implements Observer {

    private JRadioButton optRouge;
    private JRadioButton optBleu;
    private JRadioButton optVert;
    private JRadioButton optJaune;
    private ButtonGroup groupeBoutons;
    private Modele modele;
    private Color couleur = null;
    private boolean erreur = false;
    private Timer tmr;

    public FenetreOptions(Modele modele) throws HeadlessException {
        this.modele = modele;
        modele.addObserver(this);

        setTitle("Sélectionnez une couleur:");
        setSize(300, 100);
        setLayout(new FlowLayout());
        initialiserComposantes();

        creer();
        creerEvents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Méthode qui créer l'interface de la fenetre
     */
    private void creer() {
        groupeBoutons.add(optRouge);
        groupeBoutons.add(optBleu);
        groupeBoutons.add(optVert);
        groupeBoutons.add(optJaune);

        this.add(optRouge);
        this.add(optBleu);
        this.add(optVert);
        this.add(optJaune);
    }

    /**
     * Méthode d'évènements sur les boutons radio de la fenètre
     */
    private void creerEvents() {
        optRouge.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                couleur = Color.RED;
                erreur = modele.changerCouleur(couleur);
            }
        });

        optVert.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                couleur = Color.GREEN;
                erreur = modele.changerCouleur(couleur);
            }
        });

        optJaune.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                couleur = Color.YELLOW;
                erreur = modele.changerCouleur(couleur);
            }
        });
        optBleu.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                couleur = Color.BLUE;
                erreur = modele.changerCouleur(couleur);
            }
        });

    }

    /**
     * Méthode qui initlialise les composantes et les mets en mémoroire
     */
    private void initialiserComposantes() {
        optRouge = new JRadioButton("Rouge", false);
        optBleu = new JRadioButton("Bleu", false);
        optVert = new JRadioButton("Vert", false);
        optJaune = new JRadioButton("Jaune", false);
        groupeBoutons = new ButtonGroup();
    }

    @Override
    public void update(Observable o, Object arg) {

        if (erreur) {

            erreur = false;

            JOptionPane.showMessageDialog(FenetreOptions.this, "Vous ne pouvez "
                    + "pas choisir la même couleur que l'autre joueur", "Erreur",
                    JOptionPane.OK_OPTION);

        }
    }

}
