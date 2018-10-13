package ca.qc.bdeb.prog3.modele;

import java.awt.Color;

import java.util.Observable;

/**
 * Classe de gestion de la logistique du jeu
 *
 * @author Nicolas Demers-Neuwirth
 * @version 1,0
 */
public class Modele extends Observable {

    private boolean isDone = false;
    private int tabBoutonModele[][][] = new int[4][4][4];

    private int minute = 0, seconde = 0;

    private Joueur joueur1 = new Joueur(0, 1, Color.BLUE);
    private Joueur joueur2 = new Joueur(0, 2, Color.RED);
    private Joueur joueurActif = joueur1;
    private int cpt = 0;

    private int position = 0;
    private Color saCouleur;

    private String texteTemps = null;

    public Modele() {

    }

    private void majObserver() {
        setChanged();
        notifyObservers();
    }

    /**
     * Méthode que remet le tableau d'entier en trois dimensions à son état
     * initial, soit à 0 dans chaque case. La méthode réinitialise le compteur
     * de coup, les points et le timer à 0
     */
    public void resetPartie() {

        cpt = 0;
        joueurActif = joueur1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {

                    tabBoutonModele[i][j][k] = 0;
                }
            }

        }

        joueur1.ajouterPoints(0);
        joueur2.ajouterPoints(0);
        seconde = 0;
        minute = 0;
        isDone = false;

        majObserver();

    }

    /**
     * Méthode qui calcule le total des points des 3 options
     *
     * @param joueur qui représente le joueur actif qu'il faut calculer ses
     * points
     */
    public void calculerPoints(Joueur joueur) {
        double point10 = 0;
        double points15 = 0;

        double points20 = 0;
        try {
            point10 = calculerPoints10(joueur);
            points15 = calculerPoints15(joueur);

            points20 = calculerPoints20(joueur);
        } catch (IndexOutOfBoundsException ex) {

        }
        double pointsTot = point10 + points15 + points20;
        joueur.ajouterPoints(pointsTot);

        //majObserver();
    }

    /**
     * Méthode qui chronomètre en augmentant les minutes et les secondes
     */
    public void chronometrer() {
        seconde++;
        if (seconde > 59) {
            minute++;
            seconde = 0;
        }
        majObserver();
    }

    /**
     * Méthode qui change la couleur du joueur vérifie si elle n'est pas prise
     *
     * @param couleur couleur sélectionnée par le joueur actif
     * @return (true) si la couleur est déjà prise (false) si la couleur n'est
     * pas prise
     */
    public boolean changerCouleur(Color couleur) {

        saCouleur = joueurActif.getCouleur();

        joueurActif.setCouleur(couleur);
        boolean erreur = false;

        if (joueur1.getCouleur() == joueur2.getCouleur()) {
//            System.out.println(joueur1.getCouleur()+"  "+ joueur2.getCouleur());

            erreur = true;
            joueurActif.setCouleur(saCouleur);

        }
        majObserver();

        return erreur;
    }

    /**
     * Méthode qui calcul les points le l'option à 10 points. (4 pierres de même
     * couleur sur 4 numéros identiques et consécutifs - horizontalement,
     * verticalement ou en diagonale)
     *
     * @param joueur qui représente le joueur actif qu'il faut calculer les
     * points
     * @return le nombre de points marqué par le joueur
     */
    private double calculerPoints10(Joueur joueur) {

        double points = 0;

        for (int i = 0; i < tabBoutonModele.length; i++) {
            for (int j = 0; j < tabBoutonModele[i].length; j++) {

                for (int k = 0; k < tabBoutonModele[i][j].length; k++) {

                    if (tabBoutonModele[i][0][k] == joueur.getNumeroJoueur()
                            && tabBoutonModele[i][1][k] == joueur.getNumeroJoueur()
                            && tabBoutonModele[i][2][k] == joueur.getNumeroJoueur() 
                            && tabBoutonModele[i][3][k] == joueur.getNumeroJoueur()) {
                        //horizontal
                        points = points + 2.5;

                    }

                    if (tabBoutonModele[0][j][k] == joueur.getNumeroJoueur()
                            && tabBoutonModele[1][j][k] == joueur.getNumeroJoueur()
                            && tabBoutonModele[2][j][k] == joueur.getNumeroJoueur() 
                            && tabBoutonModele[3][j][k] == joueur.getNumeroJoueur()) {
//                        vertical
                        points = points + 2.5;

                    }

                    if (i == 0 && j == 0) {
                        //diagonale
                        if (tabBoutonModele[i][j][k] == joueur.getNumeroJoueur()
                                && tabBoutonModele[i + 1][j + 1][k] == joueur.
                                        getNumeroJoueur() && tabBoutonModele
                                [i + 2][j + 2][k] == joueur.getNumeroJoueur()
                                && tabBoutonModele[i + 3][j + 3][k] == joueur.
                                        getNumeroJoueur()) {
                            points = points + 10;
                        }
                    }

                    if (i == 0 && j == 3) {
                        //diagonale
                        if (tabBoutonModele[i][j][k] == joueur.getNumeroJoueur()
                                && tabBoutonModele[i + 1][j - 1][k] == joueur.
                                        getNumeroJoueur() && tabBoutonModele
                                [i + 2][j - 2][k] == joueur.getNumeroJoueur()
                                && tabBoutonModele[i + 3][j - 3][k] == joueur.
                                        getNumeroJoueur()) {
                            points = points + 10;
                        }
                    }

                }
            }
        }

        return points;

    }

    /**
     * Méthode qui calcul les points le l'option à 15 points. (4 pierres de même
     * couleur en ordre numérique horizontalement, verticalement ou en
     * diagonale)
     *
     * @param joueur qui représente le joueur actif qu'il faut calculer les
     * points
     * @return le nombre de points marqué par le joueur
     */
    private double calculerPoints15(Joueur joueur) {
        int k = 0;
        double points = 0;
        for (int i = 0; i < tabBoutonModele.length; i++) {
            for (int j = 0; j < tabBoutonModele[i].length; j++) {

                if ((tabBoutonModele[i][0][0] == joueur.getNumeroJoueur() && 
                        tabBoutonModele[i][1][1] == joueur.getNumeroJoueur() &&
                        tabBoutonModele[i][2][2] == joueur.getNumeroJoueur() &&
                        tabBoutonModele[i][3][3] == joueur.getNumeroJoueur()) || 
                        (tabBoutonModele[i][0][3] == joueur.getNumeroJoueur() && 
                        tabBoutonModele[i][1][2] == joueur.getNumeroJoueur() && 
                        tabBoutonModele[i][2][1] == joueur.getNumeroJoueur() && 
                        tabBoutonModele[i][3][0] == joueur.getNumeroJoueur())) {
//                         horizontal 4321 et 1234
                    points = points + 3.75;

                }

                if ((tabBoutonModele[0][j][0] == joueur.getNumeroJoueur() && 
                        tabBoutonModele[1][j][1] == joueur.getNumeroJoueur() &&
                        tabBoutonModele[2][j][2] == joueur.getNumeroJoueur() &&
                        tabBoutonModele[3][j][3] == joueur.getNumeroJoueur()) ||
                        (tabBoutonModele[0][j][3] == joueur.getNumeroJoueur() &&
                        tabBoutonModele[1][j][2] == joueur.getNumeroJoueur() && 
                        tabBoutonModele[2][j][1] == joueur.getNumeroJoueur() && 
                        tabBoutonModele[3][j][0] == joueur.getNumeroJoueur())) {
                    points = points + 3.75;
                }

                if (i == 0 && j == 0) {
                    //diagonale
                    if ((tabBoutonModele[i][j][0] == joueur.getNumeroJoueur() &&
                            tabBoutonModele[i + 1][j + 1][1] == joueur.
                                    getNumeroJoueur() && 
                            tabBoutonModele[i + 2][j + 2][2]
                            == joueur.getNumeroJoueur() && 
                            tabBoutonModele[i + 3][j + 3][3] == 
                            joueur.getNumeroJoueur()) || (tabBoutonModele[i][j][3]
                            == joueur.getNumeroJoueur() && 
                            tabBoutonModele[i + 1][j + 1][2] == joueur.
                                    getNumeroJoueur() && 
                            tabBoutonModele[i + 2][j + 2][1] == joueur.
                                    getNumeroJoueur() && 
                            tabBoutonModele[i + 3][j + 3][0] == joueur.
                                    getNumeroJoueur())) {
                        points = points + 15;
                    }
                }

                if (i == 0 && j == 3) {
                    //diagonale
                    if ((tabBoutonModele[i][j][0] == joueur.getNumeroJoueur() 
                            && tabBoutonModele[i + 1][j - 1][1] == joueur.
                                    getNumeroJoueur() && 
                            tabBoutonModele[i + 2][j - 2][2] == joueur.
                                    getNumeroJoueur() && 
                            tabBoutonModele[i + 3][j - 3][3] == joueur.
                                    getNumeroJoueur()) || (tabBoutonModele[i][j][3]
                            == joueur.getNumeroJoueur() && 
                            tabBoutonModele[i + 1][j - 1][2] == joueur.
                                    getNumeroJoueur() && 
                            tabBoutonModele[i + 2][j - 2][1] == joueur.
                                    getNumeroJoueur() &&
                            tabBoutonModele[i + 3][j - 3][0] == 
                            joueur.getNumeroJoueur())) {
                        points = points + 15;
                    }
                }

            }
        }

        return points;

    }

    /**
     * Méthode qui calcul les points le l'option à 20 points. (4 pierres de même
     * couleur sur un même carré.)
     *
     * @param joueur qui représente le joueur actif qu'il faut calculer les
     * points
     * @return le nombre de points marqué par le joueur
     */
    private int calculerPoints20(Joueur joueur) {
        int points = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                if ((tabBoutonModele[i][j][0] == joueur.getNumeroJoueur() && 
                        tabBoutonModele[i][j][1] == joueur.getNumeroJoueur() &&
                        tabBoutonModele[i][j][2] == joueur.getNumeroJoueur() &&
                        tabBoutonModele[i][j][3] == joueur.getNumeroJoueur())) {
                    points = points + 20;

                }

            }
        }
        return points;
    }

    /**
     * Méthode qui octroie une case cliqué par le joueur actif dans le tableau
     * de int en 3 dimensions
     *
     * @param positionX position horizontale du quad
     * @param positionY position verticale du quad
     * @param positionDansQuad position de la case cliqué dans le quad
     */
    public void placerMarqueur(int positionX, int positionY, int positionDansQuad) {

        tabBoutonModele[positionX][positionY][positionDansQuad] = joueurActif.
                getNumeroJoueur();

        cpt++;

        calculerPoints(joueurActif);
        if (cpt == 64) {

            isDone = true;
            saCouleur();

        } else {
            if (joueurActif == joueur1) {
                joueurActif = joueur2;
            } else if (joueurActif == joueur2) {
                joueurActif = joueur1;
            }
        }
        majObserver();
    }

    /**
     * Méthode qui vérifie si'il y a un gagnant et transforme sa couleur en
     * String
     *
     * @return null si aucun gagnant, string s'il y a un gagnant
     */
    public String saCouleur() {

        // vérifications ici
        String saCouleur = null;
        if (joueur1.getPoints() > joueur2.getPoints()) {

            if (joueur1.getCouleur() == Color.BLUE) {
                saCouleur = "Blue";
            } else if (joueur1.getCouleur() == Color.RED) {
                saCouleur = "Red";
            } else if (joueur1.getCouleur() == Color.GREEN) {
                saCouleur = "Green";
            } else if (joueur1.getCouleur() == Color.YELLOW) {
                saCouleur = "Yellow";
            }

        } else if (joueur1.getPoints() < joueur2.getPoints()) {

            if (joueur2.getCouleur() == Color.BLUE) {
                saCouleur = "Blue";
            } else if (joueur2.getCouleur() == Color.RED) {
                saCouleur = "Red";
            } else if (joueur2.getCouleur() == Color.GREEN) {
                saCouleur = "Green";
            } else if (joueur2.getCouleur() == Color.YELLOW) {
                saCouleur = "Yellow";
            }

        }

        return saCouleur;
    }

    /**
     * Méthode qui vérifier lequel des deux joueur est le joueur actif
     *
     * @return (true) si le joueur actif est le joueur1 (false) si c'est le
     * joueur2
     */
    public boolean vérifierJoueurAcif() {
        boolean bool = false;
        if (joueurActif == joueur1) {
            bool = true;
        }
        return bool;
    }

    public int getMinute() {
        return minute;
    }

    public int getSeconde() {

        return seconde;
    }

    public Joueur getJoueur1() {
        return joueur1;
    }

    public Joueur getJoueur2() {
        return joueur2;
    }

    public int[][][] getTabBoutonModele() {
        return tabBoutonModele;
    }

    public boolean isIsDone() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setSeconde(int seconde) {
        this.seconde = seconde;
    }

    public Joueur getJoueurActif() {
        return joueurActif;
    }

    public Color getSaCouleur() {
        return saCouleur;
    }

    public String getTexteTemps() {
        String textTemps = null;
        if (seconde < 10) {
            textTemps = minute + ":0" + seconde;

        } else if (seconde >= 10) {
            textTemps = minute + ":" + seconde;
        }
        return textTemps;
    }

}
