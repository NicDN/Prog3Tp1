/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog3.vue;

import ca.qc.bdeb.prog3.modele.Modele;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Nicolas
 */
public class Quad extends JPanel implements Observer {

    private int positionX, positionY;

    Modele modele;

    private ArrayList<Bouton> listeBouton = new ArrayList();

    public Quad(Modele modele, int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.modele = modele;
        modele.addObserver(this);

        creerBoutons();

        this.setPreferredSize(new Dimension(30, 30));
        this.setLayout(new GridLayout(2, 2));
        this.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    public void creerBoutons() {

        for (int i = 0; i < 4; i++) {
           
                Bouton btn = new Bouton(""+i,i);
                btn.setPreferredSize(new Dimension(15, 15));
                listeBouton.add(btn);
                this.add(btn);

                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Bouton btnClique = (Bouton) e.getSource();
                        modele.placerMarqueur(positionX, positionY,btnClique.getPositionDansQuad());
                    }
                });
            
        }

    }

}
