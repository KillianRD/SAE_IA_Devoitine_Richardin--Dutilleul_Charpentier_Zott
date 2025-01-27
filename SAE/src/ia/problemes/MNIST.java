package ia.problemes;

import ia.framework.common.Imagette;
import ia.framework.utils.FileUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ia.framework.utils.MatrixUtils.flattenMatrix;
import static ia.framework.utils.MatrixUtils.normalizeMatrix;

public class MNIST extends Problem {
    public MNIST(int batchSize) {
        super(batchSize);
    }

    @Override
    public void init(int batchSize) {
        ArrayList<Imagette> imagettesTrain = FileUtils.getImagettesFromFile(
                "/input/MNIST/train-images.idx3-ubyte",
                "/input/MNIST/train-labels.idx1-ubyte"
        );

        ArrayList<Imagette> imagettesTest = FileUtils.getImagettesFromFile(
                "/input/MNIST/t10k-images.idx3-ubyte",
                "/input/MNIST/t10k-labels.idx1-ubyte"
        );

        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < batchSize; i++) {
            indices.add(i);
        }
        Collections.shuffle(indices);

        inputsTrain = new double[batchSize][784]; // 784 = 28 * 28 (taille des images)
        outputDesiredTrain = new double[batchSize][10]; // 10 sorties possibles

        for (int i = 0; i < batchSize; i++) {
            inputsTrain[i] = flattenMatrix(normalizeMatrix(imagettesTrain.get(indices.get(i)).getDonnees(), 255)); // coefficient pour normaliser les données entre 0 et 1.
            outputDesiredTrain[i][(int) imagettesTrain.get(indices.get(i)).getEtiquette()] = 1.0;
        }

        inputsTest = new double[imagettesTest.size()][784]; // 784 = 28 * 28 (taille des images)
        outputDesiredTest = new double[imagettesTest.size()][10]; // 10 sorties possibles

        for (int i = 0; i < imagettesTest.size(); i++) {
            inputsTest[i] = flattenMatrix(normalizeMatrix(imagettesTest.get(i).getDonnees(), 255)); // coefficient pour normaliser les données entre 0 et 1.
            outputDesiredTest[i][(int) imagettesTest.get(i).getEtiquette()] = 1.0;
        }
    }
}
