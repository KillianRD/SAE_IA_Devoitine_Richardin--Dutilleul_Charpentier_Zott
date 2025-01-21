package ia.problemes;

import ia.framework.MLP.MLP;

public class XOR extends Problem {
    public XOR() {
        super();
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
                {1},
                {1},
                {0}
        };
    }
}
