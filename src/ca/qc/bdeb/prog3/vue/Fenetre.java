/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.qc.bdeb.prog3.vue;

import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author Nicolas
 */
public class Fenetre extends JFrame{
    
    private JPanel pnlPrincipal=new JPanel();
    private JPanel pnlJeu=new JPanel();
    private JLabel lblPointsHaut=new JLabel();
    private JLabel lblPointsBas=new JLabel();
    private JLabel lblTitre=new JLabel();
    
    
    private JMenuBar mnuBar=new JMenuBar();
    private JMenu mnuFichier=new JMenu("Fichier");
    private JMenu mnuAide=new JMenu("Aide");
    
    private JMenuItem mnuNouvellePartie=new JMenuItem("Nouvelle partie");
    private JMenuItem mnuOptions=new JMenuItem("Options");
    private JMenuItem mnuQuitter=new JMenuItem("Quitter");
    private JMenuItem mnuAPropos=new JMenuItem("À propos");
    
    

    public Fenetre() throws HeadlessException {
        setTitle("Hijara");
        setSize(400,650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
    }
    
    public void creer(){
        
    }
    
    public void creerMenu(){
        mnuFichier.add(mnuNouvellePartie);
        mnuFichier.add(mnuOptions);
        mnuFichier.addSeparator();
        mnuFichier.add(mnuQuitter);
        
        mnuBar.add(mnuFichier);
    }
    
    public void creerEvents(){
        
    }
    public void creerEventsMenu(){
        
    }
 
    
}
