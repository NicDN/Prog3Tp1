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

    Modele modele;

    private JButton tabBouton[][] = new JButton[2][2];
    private JButton btn1 = new JButton(" 1 ");
    private JButton btn2 = new JButton(" 2 ");
    private JButton btn3 = new JButton(" 3 ");
    private JButton btn4 = new JButton(" 4 ");

    private JButton btn;
    public Quad(Modele modele) {
        this.modele = modele;
        modele.addObserver(this);

        creer();
//        creerEvents();
        this.setPreferredSize(new Dimension(30, 30));
        this.setLayout(new GridLayout(2, 2));
        this.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    private void creer() {
        
//        tabBouton[0][0]=btn2;
//        tabBouton[1][0]=btn3;
//        tabBouton[0][1]=btn1;
//        tabBouton[1][1]=btn4;
        

        this.add(btn2);
        this.add(btn3);
        this.add(btn1);
        this.add(btn4);

        btn1.setPreferredSize(new Dimension(15, 15));
        btn2.setPreferredSize(new Dimension(15, 15));
        btn3.setPreferredSize(new Dimension(15, 15));
        btn4.setPreferredSize(new Dimension(15, 15));

    }

    private void creerEvents() {
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
               
            }
        });
        
    }
}
