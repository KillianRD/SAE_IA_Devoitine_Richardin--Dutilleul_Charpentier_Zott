package ia.framework.utils;

public class MatrixUtils {
    public static double[] flattenMatrix(double[][] input) {
        double[] res = new double[input.length * input.length];

        int i = 0;

        for (double[] doubles : input) {
            for (double aDouble : doubles) {
                res[i] = aDouble;
                i++;
            }
        }

        return res;
    }

    public static double[][] normalizeMatrix(double[][] input, double coefficient) {
        double[][] res = new double[input.length][input.length];

        for (int l = 0; l < input.length; l++) {
            for (int c = 0; c < input[0].length; c++) {
                res[l][c] = input[l][c] / coefficient;
            }
        }

        return res;
    }
}
