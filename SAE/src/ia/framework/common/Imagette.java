package ia.framework.common;

/**
 * Classe représentant les imagettes
 */
public class Imagette {
    /**
     * Attribut représentant le tableau de pixels de l'imagette
     */
    private final double[][] donnees;

    /**
     * Attribut représentant le numéro de l'imagette
     */
    private double etiquette;

    /**
     * Constructeur de la classe Imagette
     */
    public Imagette(double[][] i, int etiquette) {
        this.donnees = i;
        this.etiquette = etiquette;
    }

    /**
     * Getter de l'attribut numero
     *
     * @return le numéro de l'imagette
     */
    public double getEtiquette() {
        return etiquette;
    }

    /**
     * Getter de l'attribut imagette
     *
     * @return le tableau de pixels de l'imagette
     */
    public double[][] getDonnees() {
        return donnees;
    }
}