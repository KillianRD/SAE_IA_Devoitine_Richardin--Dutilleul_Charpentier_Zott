package ia.framework.common;

import ia.framework.MLP.MLP;
import ia.problemes.Problem;

/**
 * Classe représentant les statistiques
 */
public class Statistique {
    private final Problem problem;
    private final MLP mlp;

    public Statistique(Problem problem, MLP mlp) {
        this.problem = problem;
        this.mlp = mlp;
    }

    public double getStatistique() {
        double[][] inputs = problem.getInputs();
        double[][] outputDesired = problem.getOutputDesired();
        int total = inputs.length;
        int correctCount = 0;

        for (int i = 0; i < total; i++) {
            double[] prediction = mlp.execute(inputs[i]);
            if (isCorrectPrediction(prediction, outputDesired[i])) {
                correctCount++;
            }
        }

        System.out.println("Nombre correct : " +  correctCount + " / " + inputs.length);
        return (double) correctCount / total;
    }

    private boolean isCorrectPrediction(double[] prediction, double[] expected) {
        int predictedIndex = indexOfMax(prediction);
        int expectedIndex = indexOfMax(expected);
        return predictedIndex == expectedIndex;
    }

    private int indexOfMax(double[] array) {
        int index = 0;
        double max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
                index = i;
            }
        }
        return index;
    }
}
