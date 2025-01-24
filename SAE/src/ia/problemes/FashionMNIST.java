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
        ArrayList<Imagette> imagettesTrain = FileUtils.getImagettesFromFile(
                "/input/Fashion_MNIST/train-images-idx3-ubyte",
                "/input/Fashion_MNIST/train-labels-idx1-ubyte"
        );

        ArrayList<Imagette> imagettesTest = FileUtils.getImagettesFromFile(
                "/input/Fashion_MNIST/t10k-images-idx3-ubyte",
                "/input/Fashion_MNIST/t10k-labels-idx1-ubyte"
        );

        inputsTrain = new double[imagettesTrain.size()][784]; // 784 = 28 * 28 (taille des images)
        outputDesiredTrain = new double[imagettesTrain.size()][10]; // 10 sorties possibles

        for (int i = 0; i < imagettesTrain.size(); i++) {
            inputsTrain[i] = flattenMatrix(normalizeMatrix(imagettesTrain.get(i).getDonnees(), 255)); // coefficient pour normaliser les données entre 0 et 1.
            outputDesiredTrain[i][(int) imagettesTrain.get(i).getEtiquette()] = 1.0;
        }

        inputsTest = new double[imagettesTest.size()][784]; // 784 = 28 * 28 (taille des images)
        outputDesiredTest = new double[imagettesTest.size()][10]; // 10 sorties possibles

        for (int i = 0; i < imagettesTest.size(); i++) {
            inputsTest[i] = flattenMatrix(normalizeMatrix(imagettesTest.get(i).getDonnees(), 255)); // coefficient pour normaliser les données entre 0 et 1.
            outputDesiredTest[i][(int) imagettesTest.get(i).getEtiquette()] = 1.0;
        }
    }
}
