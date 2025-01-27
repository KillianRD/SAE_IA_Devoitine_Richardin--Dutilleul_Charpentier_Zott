package ia.framework.common;

/**
 * Classe représentant les données
 */
public class Donnee {

    /**
     * Attribut représentant le tableau de pixels de l'imagette
     */
    private final Imagette[] imagette;

    /**
     * Constructeur de la classe Donnee
     *
     * @param i le tableau d'imagettes
     */
    public Donnee(Imagette[] i) {
        imagette = i;
    }

    /**
     * Getter de l'attribut imagette
     *
     * @return le tableau d'imagettes
     */
    public Imagette[] getImagette() {
        return imagette;
    }
}

