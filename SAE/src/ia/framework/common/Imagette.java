package ia.framework.common;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Classe représentant les imagettes
 */
public class Imagette {
    /**
     * Attribut représentant le tableau de pixels de l'imagette
     */
    private int[][] imagette;

    /**
     * Attribut représentant le numéro de l'imagette
     */
    private int numero;

    /**
     * Constructeur de la classe Imagette
     */
    public Imagette(int[][] i) {
        imagette = i;
    }

    /**
     * Méthode permettant de charger le tableau de pixels de l'imagette
     *
     * @param fileName le nom du fichier
     *
     * @return le tableau des imagettes
     */
    public ArrayList<int[][]> chargerTableau(String fileName) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(Objects.requireNonNull(getClass().getResourceAsStream(fileName)));
        int typeFichier = dataInputStream.readInt();
        int nbImages = dataInputStream.readInt();
        int nbLignes = dataInputStream.readInt();
        int nbColonnes = dataInputStream.readInt();
        imagette = new int[nbLignes][nbColonnes];
        ArrayList<int[][]> tabImages = new ArrayList<>();

        for (int n = 0; n < nbImages; n++) {
            for (int i = 0; i < nbLignes; i++) {
                for (int j = 0; j < nbColonnes; j++) {
                    imagette[i][j] = dataInputStream.readUnsignedByte();
                }
            }
            tabImages.add(imagette);
            imagette = new int[nbLignes][nbColonnes]; // On réinitialise l'imagette
        }

        return tabImages;
    }

    /**
     * Méthode permettant de transformer un tableau en une image sauvegardée sur le disque
     *
     * @param im le tableau de pixels de l'imagette
     */
    public void sauvegarderImage(int[][] im) throws IOException {
        BufferedImage image = new BufferedImage(im.length, im.length, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < im.length; i++) {
            for (int j = 0; j < im[i].length; j++) {
                Color color = new Color(im[i][j], im[i][j], im[i][j]);
                image.setRGB(j, i, color.getRGB());
            }
        }
        ImageIO.write(image, "png", new File("imagette.png"));
    }

    /**
     * Setter de l'attribut numero
     *
     * @param numero le numéro de l'imagette
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * Getter de l'attribut numero
     *
     * @return le numéro de l'imagette
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Getter de l'attribut imagette
     *
     * @return le tableau de pixels de l'imagette
     */
    public int[][] getImagette() {
        return imagette;
    }
}