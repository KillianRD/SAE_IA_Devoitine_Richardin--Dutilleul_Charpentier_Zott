package ia.framework.common;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Classe représentant les étiquettes
 */
public class Etiquette {
    /**
     * Méthode permettant de charger les images
     *
     * @param tabImages le tableau d'image
     * @param fileName le nom du fichier
     *
     * @return le tableau des imagettes
     */
    public static Imagette[] chargerEtiquette(ArrayList<int[][]> tabImages, String fileName) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(Objects.requireNonNull(Etiquette.class.getResourceAsStream(fileName)));
        int typeFichier = dataInputStream.readInt();
        int nbElement = dataInputStream.readInt();

        Imagette[] imagette = new Imagette[nbElement];

        for (int n = 0; n < nbElement; n++) {
            imagette[n] = new Imagette(tabImages.get(n));
            imagette[n].setNumero(dataInputStream.readUnsignedByte());
        }

        return imagette;
    }
}

