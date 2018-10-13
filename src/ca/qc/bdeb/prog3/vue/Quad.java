package ca.qc.bdeb.prog3.vue;

import ca.qc.bdeb.prog3.modele.Modele;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * Classe Quad
 *
 * @author Nicolas Demers-Neuwirth
 * @version 1,0
 */
public class Quad extends JPanel implements Observer {

    private int positionX, positionY;

    private Modele modele;

    private ArrayList<Bouton> listeBouton = new ArrayList();
    private int positionBtnListe = 0;

    public Quad(Modele modele, int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.modele = modele;
        modele.addObserver(this);

        creerBoutons(positionX, positionY);

        this.setPreferredSize(new Dimension(30, 30));
        this.setLayout(new GridLayout(2, 2));
        this.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    /**
     * Méthode qui créer les les quatres boutons à l'intérieur du quad
     *
     * @param positionX position horizontale du quad
     * @param positionY position verticale du quad
     */
    public void creerBoutons(int positionX, int positionY) {

        for (int i = 0; i < 4; i++) {

            Bouton btn = new Bouton("" + (i + 1), positionX, positionY, i, modele);
            if (i == 0) {
                btn.setEnabled(true);
            } else {
                btn.setEnabled(false);
            }

            btn.setPreferredSize(new Dimension(15, 15));
            listeBouton.add(btn);

            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Bouton btnClique = (Bouton) e.getSource();
                    positionBtnListe = listeBouton.indexOf(btnClique);

                    modele.placerMarqueur(positionX, positionY, btnClique.
                            getPositionDansQuad());
                    System.out.println("x,y,z:" + positionX + "/" + positionY + 
                            "/" + btnClique.getPositionDansQuad());

                    try {
                        listeBouton.get(positionBtnListe + 1).setEnabled(true);
                    } catch (IndexOutOfBoundsException ex) {

                    }
                }
            });

        }
        this.add(listeBouton.get(1));
        this.add(listeBouton.get(2));
        this.add(listeBouton.get(0));
        this.add(listeBouton.get(3));

    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public ArrayList<Bouton> getListeBouton() {
        return listeBouton;
    }

}
