package ia.framework.common;

import ia.framework.MLP.MLP;

/**
 * Classe représentant les statistiques
 */
public class Statistique {
    private final MLP mlp;
    private final Donnee donnee;

    public Statistique(MLP m, Donnee d) {
        mlp = m;
        donnee = d;
    }

    public double tauxJuste() {
        int juste = 0;
        for (int i = 0; i < donnee.getImagette().length; i++) {
            //if (mlp.           (donnee.getImagette()[i]) == donnee.getImagette()[i].getNumero()) {
            //    juste++;
            //}
            System.out.printf("index courant : %d\n", i);
        }
        return (double) juste / donnee.getImagette().length;
    }

    public double tauxErreur() {
        int erreur = 0;
        for (int i = 0; i < donnee.getImagette().length; i++) {
            //if (mlp.     (donnee.getImagette()[i]) != donnee.getImagette()[i].getNumero()) {
            //    erreur++;
            //}
        }
        return (double) erreur / donnee.getImagette().length;
    }

}
