package ia.problemes;


public class OR extends Problem {
    public OR() {
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
                {1}
        };
    }
}
