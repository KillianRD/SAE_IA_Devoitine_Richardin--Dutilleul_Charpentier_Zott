package ia.framework.common;

import ia.framework.MLP.MLP;
import ia.problemes.Problem;

/**
 * Classe représentant les statistiques
 */
public class Statistique {
    private static Problem PROBLEM = null;
    private static MLP MLP = null;

    public Statistique(Problem problem, MLP mlp) {
        PROBLEM = problem;
        MLP = mlp;
    }

    public static double getStatistique() {
        double[][] inputs = PROBLEM.getInputsTest();
        double[][] outputDesired = PROBLEM.getOutputDesiredTest();
        int total = inputs.length;
        int correctCount = 0;

        for (int i = 0; i < total; i++) {
            double[] prediction = MLP.execute(inputs[i]);
            if (isCorrectPrediction(prediction, outputDesired[i])) {
                correctCount++;
            }
        }

        //System.out.println("Nombre correct : " + correctCount + " / " + inputs.length);
        return (double) correctCount / total;
    }

    public String getStatistiquePerClasse() {
        double[][] inputs = PROBLEM.getInputsTest();
        double[][] outputDesired = PROBLEM.getOutputDesiredTest();
        int total = inputs.length;
        int numClasses = outputDesired[0].length;

        double[][] output = new double[numClasses][2];

        //Récupération des résultats
        for (int i = 0; i < total; i++) {
            double[] prediction = MLP.execute(inputs[i]);
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

    private static boolean isCorrectPrediction(double[] prediction, double[] expected) {
        int predictedIndex = indexOfMax(prediction);
        int expectedIndex = indexOfMax(expected);
        return predictedIndex == expectedIndex;
    }

    private static int indexOfMax(double[] array) {
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
