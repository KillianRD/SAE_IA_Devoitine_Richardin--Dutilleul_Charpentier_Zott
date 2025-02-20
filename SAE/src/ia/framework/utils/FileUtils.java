package ia.framework.utils;

import ia.framework.common.Imagette;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class FileUtils {
    public static ArrayList<Imagette> getImagettesFromFile(String fileNameImage, String fileNameNameTag) {
        ArrayList<Imagette> imagettes = new ArrayList<>();

        DataInputStream disImage = new DataInputStream(Objects.requireNonNull(FileUtils.class.getResourceAsStream(fileNameImage)));
        DataInputStream disNameTag = new DataInputStream(Objects.requireNonNull(FileUtils.class.getResourceAsStream(fileNameNameTag)));

        try {

            // Lecture de la tête du fichier des images
            int typeFileImage = disImage.readInt();
            int nbImages = disImage.readInt();
            int nbLignes = disImage.readInt();
            int nbColonnes = disImage.readInt();

            // Lecture de la tête du fichier des étiquettes
            int typeFileNameTag = disNameTag.readInt();
            int nbEtiquettes = disNameTag.readInt();


            for (int i = 0; i < nbImages; i++) {
                imagettes.add(getNextImagette(nbLignes, nbColonnes, disImage, disNameTag));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return imagettes;
    }

    private static Imagette getNextImagette(int nbLignes, int nbColonnes, DataInputStream disI, DataInputStream disNT) throws IOException {
        double[][] imageData = new double[nbLignes][nbColonnes];
        int imageNameTag = disNT.readUnsignedByte();

        for (int ligne = 0; ligne < nbLignes; ligne++) {
            for (int colonne = 0; colonne < nbColonnes; colonne++) {
                imageData[ligne][colonne] = disI.readUnsignedByte();
            }
        }

        return new Imagette(imageData, imageNameTag);
    }

    public static void saveStatistique(String stats) {
        File file = new File("res/output/Statistiques.txt");

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(stats);
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
