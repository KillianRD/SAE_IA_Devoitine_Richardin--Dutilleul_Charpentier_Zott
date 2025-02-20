package ia.problemes;

import ia.framework.MLP.MLP;
import ia.framework.common.ArgParse;
import ia.framework.common.Statistique;

public abstract class Problem {
    protected double[][] inputsTrain;
    protected double[][] inputsTest;
    protected double[][] outputDesiredTrain;
    protected double[][] outputDesiredTest;
    protected double errorThreshold = 0.1;
    protected int batchSize;

    public Problem(int batchSize) {
        if (batchSize < 0 || batchSize > 60_000) {
            batchSize = 60_000;
        }

        this.batchSize = batchSize;
        init();
    }

    public abstract void init();

    public void executeTraining(int nbIterations, MLP mlp) {
        double erreur;
        int i = 0;

        if (ArgParse.DEBUG) {
            System.out.printf("--- Session de test n°%d sans entraînement---%n", i);
            System.out.printf("Pourcentage : %.4f%n", Statistique.getStatistique());
        }

        //Entrainement
        while (i < nbIterations && (erreur = train(mlp)) >= errorThreshold) {
            if (ArgParse.DEBUG) {
                System.out.printf("--- Session d'entraînement n°%d ---%n", i);
                System.out.printf("Taux d'erreur : %.4f%n", erreur);
                System.out.printf("--- Session de test n°%d ---%n", i);
                System.out.printf("Pourcentage : %.4f%n", Statistique.getStatistique());
            }
            i++;
        }

        // Résultat après l'entrainement sur les données d'entrainement
        for (int j = 0; j < inputsTest.length; j++) {
            if (ArgParse.DEBUG) {
                //System.out.println("--- Sortie désirée : " + Arrays.toString(outputDesiredTest[j]) + " ---");
            }
            double[] res = mlp.execute(inputsTest[j]);
            /*if (ArgParse.DEBUG) {
                System.out.print("Sortie reçu : ");
                for (double re : res) {
                    System.out.printf("%.2f ", re);
                }
                System.out.println();
            }*/
        }
    }


    private double train(MLP mlp) {
        double erreur = 0;
        for (int i = 0; i < inputsTrain.length; i++) {
            erreur += mlp.backPropagate(inputsTrain[i], outputDesiredTrain[i]);
        }
        return erreur;
    }

    public double[][] getInputsTrain() {
        return inputsTrain;
    }

    public double[][] getInputsTest() {
        return inputsTest;
    }

    public double[][] getOutputDesiredTrain() {
        return outputDesiredTrain;
    }

    public double[][] getOutputDesiredTest() {
        return outputDesiredTest;
    }
}

