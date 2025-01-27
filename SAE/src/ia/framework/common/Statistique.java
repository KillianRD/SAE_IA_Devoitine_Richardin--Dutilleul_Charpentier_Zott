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
        double[][] inputs = problem.getInputsTest();
        double[][] outputDesired = problem.getOutputDesiredTest();
        int total = inputs.length;
        int correctCount = 0;

        for (int i = 0; i < total; i++) {
            double[] prediction = mlp.execute(inputs[i]);
            if (isCorrectPrediction(prediction, outputDesired[i])) {
                correctCount++;
            }
        }

        System.out.println("Nombre correct : " + correctCount + " / " + inputs.length);
        return (double) correctCount / total;
    }

    public String getStatistiquePerClasse() {
        double[][] inputs = problem.getInputsTest();
        double[][] outputDesired = problem.getOutputDesiredTest();
        int total = inputs.length;
        int numClasses = outputDesired[0].length;

        double[][] output = new double[numClasses][2];

        //Récupération des résultats
        for (int i = 0; i < total; i++) {
            double[] prediction = mlp.execute(inputs[i]);
            int expectedIndex = indexOfMax(outputDesired[i]);
            int predictedIndex = indexOfMax(prediction);

            output[expectedIndex][1]++;
            if (predictedIndex == expectedIndex) {
                output[expectedIndex][0]++;
            }
        }

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < numClasses; i++) {
            double correctCount = output[i][0];
            double totalCount = output[i][1];
            double percentageCorrect = (totalCount == 0) ? 0 : (correctCount / totalCount) * 100;

            res.append("----- Classe ").append(i).append(" -----\n");
            res.append("Pourcentage de réponses correctes : ").append(String.format("%.2f", percentageCorrect)).append("%\n");
            res.append("Correctes : ").append((int) correctCount).append(" / Total : ").append((int) totalCount).append("\n");
        }

        return res.toString();
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
