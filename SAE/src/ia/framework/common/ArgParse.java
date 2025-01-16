package ia.framework.common;

import java.util.Arrays;

public class ArgParse {

    /**
     * Permet d'activer le mode DEBUG
     */
    public static boolean DEBUG = false;

    /**
     * Message d'aide
     */
    public static String msg = null;

    /**
     * Spécification du message d'aide
     *
     * @param usage message d'aide
     */
    public static void setUsage(String usage) {
        msg = usage;
    }

    /**
     * Affiche un message d'aide
     */
    public static void usage() {
        System.err.println(msg);
    }

    /**
     * Retourne la valeur du champ demandé
     *
     * @param args arguments de la ligne de commande
     * @param arg  argument qui nous intéresse
     * @return la valeur du champ demandé
     */
    public static String getArgFromCmd(String args[], String arg) {
        if (args.length > 0) {
            int idx = Arrays.asList(args).indexOf(arg);
            if (idx % 2 == 0 && idx < args.length - 1) return args[idx + 1];
            if (idx < 0) return null;

            usage();
        }
        return null;
    }

    /**
     * Vérifie l'existence d'une option
     *
     * @param args arguments de la ligne de commande
     * @param arg  option qui nous intéresse
     * @return vrai si l'option existe
     */
    public static boolean getFlagFromCmd(String args[], String arg) {
        int idx = Arrays.asList(args).indexOf(arg);
        return idx >= 0;
    }

    /**
     * Récupère la valeur qui nous intéresse
     *
     * @param args       arguments de la ligne de commande
     * @param arg        argument qui nous intéresse
     * @param defaultVal valeur par défault
     * @return récupère la valeur qui nous intéresse
     */
    public static int getValueOfParam(String args[], String arg, int defaultVal) {
        String s = getArgFromCmd(args, arg);

        if (s != null) {

            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException nfe) {
                usage();
                System.exit(1);
            }

        }
        return defaultVal;
    }

    /**
     * Gère les différentes options de debug
     * @param args arguments de la ligne de commande
     */
    public static void handleFlags(String args[]) {
        DEBUG = getFlagFromCmd(args, "-debug");
    }
}
