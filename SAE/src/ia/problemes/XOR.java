package ia.problemes;

public class XOR extends Problem {
    public XOR(int batchSize) {
        super(batchSize);
    }

    @Override
    public void init() {
        inputsTrain = new double[][]{
                {0, 0},
                {0, 1},
                {1, 0},
                {1, 1}
        };

        inputsTest = new double[][]{
                {0, 0},
                {0, 1},
                {1, 0},
                {1, 1}
        };

        outputDesiredTrain = new double[][]{
                {0},
                {1},
                {1},
                {0}
        };

        outputDesiredTest = new double[][]{
                {0},
                {1},
                {1},
                {0}
        };
    }
}
