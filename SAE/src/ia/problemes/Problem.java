package ia.problemes;

import ia.framework.MLP.MLP;
import ia.framework.common.ArgParse;

import java.util.Arrays;


public abstract class Problem {
    protected MLP mlp;
    protected double[][] inputs;
    protected double[][] outputDesired;
    protected double errorThreshold = 0.01;

    public Problem(MLP mlp) {
        this.mlp = mlp;
        init();
    }

    public abstract void init();

    public void executeTraining(int nbIterations) {
        double erreur;
        int i = 0;

        while (i < nbIterations && (erreur = train()) >= errorThreshold) {
            if (ArgParse.DEBUG) {
                System.out.printf("--- Session d'entraînement n°%d ---%n", i);
                System.out.printf("Pourcentage d'erreur : %.4f%n", erreur);
            }
            i++;
        }

        for (int j = 0; j < inputs.length; j++) {
            if (ArgParse.DEBUG) {
                System.out.println("--- Sortie désirée : " + Arrays.toString(outputDesired[j]) + " ---");
            }
            double[] res = mlp.execute(inputs[j]);
            if (ArgParse.DEBUG) {
                System.out.print("Sortie reçu : ");
                for (double re : res) {
                    System.out.printf("%.2f ", re);
                }
                System.out.println();
            }
        }
    }


    private double train() {
        double erreur = 0;
        for (int i = 0; i < inputs.length; i++) {
            erreur += mlp.backPropagate(inputs[i], outputDesired[i]);
        }
        return erreur;
    }

    public int getSizeInput() {
        return inputs.length;
    }
}

