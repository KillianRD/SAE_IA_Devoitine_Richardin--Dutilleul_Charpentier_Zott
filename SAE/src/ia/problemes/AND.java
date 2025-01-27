package ia.problemes;

public class AND extends Problem {
    public AND(int batchSize) {
        super(batchSize);
    }

    @Override
    public void init(int batchSize) {
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
                {0},
                {0},
                {1}
        };

        outputDesiredTest = new double[][]{
                {0},
                {0},
                {0},
                {1}
        };
    }
}
