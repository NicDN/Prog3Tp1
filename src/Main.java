
import ca.qc.bdeb.prog3.modele.Modele;
import ca.qc.bdeb.prog3.vue.Fenetre;

/**
 * Classe main
 *
 * @author Nicolas Demers-Neuwirth
 * @version 1,0
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Modele modele = new Modele();
        Fenetre fenetre = new Fenetre(modele);

    }

}
