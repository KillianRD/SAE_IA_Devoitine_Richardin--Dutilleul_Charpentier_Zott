package ia.framework.MLP.transferfunction;

public class TanH implements TransferFunction {
    @Override
    public double evaluate(double value) {
        return Math.tanh(value);
    }

    @Override
    public double evaluateDer(double value) {
        return 1 - (value * value);
    }
}
