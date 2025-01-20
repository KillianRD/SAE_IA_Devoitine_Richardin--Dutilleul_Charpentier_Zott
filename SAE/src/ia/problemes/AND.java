package ia.problemes;

import ia.framework.MLP.MLP;

public class AND extends Problem {
    public AND(MLP mlp) {
        super(mlp);
    }

    @Override
    public void init() {
        inputs = new double[][]{
                {0, 0},
                {0, 1},
                {1, 0},
                {1, 1}
        };

        outputDesired = new double[][]{
                {0},
                {0},
                {0},
                {1}
        };
    }
}
