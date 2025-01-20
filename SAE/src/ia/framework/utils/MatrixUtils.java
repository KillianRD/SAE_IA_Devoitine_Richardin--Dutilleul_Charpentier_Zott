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
}
