package ia.problemes;

import ia.framework.MLP.MLP;
import ia.framework.common.ArgParse;

public abstract class Problem {

    protected double[][] inputs;
    protected double[][] outputDesired;
    protected MLP mlp;

    public Problem(MLP mlp) {
        this.mlp = mlp;
        init();
    }

    public abstract void init();

    public void execute() {
        for (int i = 0; i < inputs.length; i++) {

            if (ArgParse.DEBUG)
                System.out.println("--- Sortie désirée : " + outputDesired[i][0] + " ---");

            double[] res = mlp.execute(inputs[i]);

            if (ArgParse.DEBUG) {
                System.out.print("Sortie reçu : ");

                for (double re : res) {
                    System.out.printf("%.2f ", re);
                }

                System.out.println();
            }
        }
    }
}
