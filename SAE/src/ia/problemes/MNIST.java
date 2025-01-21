package ia.problemes;

import ia.framework.MLP.MLP;
import ia.framework.common.Imagette;
import ia.framework.utils.FileUtils;
import ia.framework.utils.MatrixUtils;

import java.util.ArrayList;

public class MNIST extends Problem {
    public MNIST(MLP mlp) {
        super(mlp);
    }

    @Override
    public void init() {
        ArrayList<Imagette> imagettes = FileUtils.getImagettesFromFile(
                "/input/MNIST/train-images.idx3-ubyte",
                "/input/MNIST/train-labels.idx1-ubyte"
        );


        inputs = new double[imagettes.size()][784]; // 784 = 28 * 28 (taille des images)
        outputDesired = new double[imagettes.size()][10]; // 10 sorties possibles

        for (int i = 0; i < imagettes.size(); i++) {
            inputs[i] = MatrixUtils.flattenMatrix(imagettes.get(i).getDonnees());
            outputDesired[i][(int) imagettes.get(i).getEtiquette()] = imagettes.get(i).getEtiquette();
        }
    }
}
