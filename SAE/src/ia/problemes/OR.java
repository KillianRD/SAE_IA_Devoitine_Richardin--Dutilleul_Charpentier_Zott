package ia.problemes;


public class OR extends Problem {
    public OR(int batchSize) {
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
                {1}
        };

        outputDesiredTest = new double[][]{
                {0},
                {1},
                {1},
                {1}
        };
    }
}
