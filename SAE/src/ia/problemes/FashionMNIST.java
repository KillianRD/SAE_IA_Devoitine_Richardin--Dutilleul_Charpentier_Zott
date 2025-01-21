package ia.problemes;


import ia.framework.common.Imagette;
import ia.framework.utils.FileUtils;

import java.util.ArrayList;

import static ia.framework.utils.MatrixUtils.flattenMatrix;
import static ia.framework.utils.MatrixUtils.normalizeMatrix;

public class FashionMNIST extends Problem {
    public FashionMNIST() {
        super();
    }

    @Override
    public void init() {
        ArrayList<Imagette> imagettes = FileUtils.getImagettesFromFile(
                "/input/Fashion_MNIST/train-images-idx3-ubyte",
                "/input/Fashion_MNIST/train-labels-idx1-ubyte"
        );

        inputs = new double[imagettes.size()][784]; // 784 = 28 * 28 (taille des images)
        outputDesired = new double[imagettes.size()][10]; // 10 sorties possibles

        for (int i = 0; i < imagettes.size(); i++) {
            inputs[i] = flattenMatrix(normalizeMatrix(imagettes.get(i).getDonnees(), 255)); // coefficient pour normaliser les données entre 0 et 1.
            outputDesired[i][(int) imagettes.get(i).getEtiquette()] = 1.0;
        }
    }
}
