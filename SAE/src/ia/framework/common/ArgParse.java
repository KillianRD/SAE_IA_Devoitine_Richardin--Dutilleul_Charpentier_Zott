package ia.framework.common;

import ia.framework.MLP.MLP;
import ia.framework.MLP.transferfunction.Sigmoide;
import ia.framework.MLP.transferfunction.TanH;
import ia.problemes.*;

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
    public static String getArgFromCmd(String[] args, String arg) {
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
    public static boolean getFlagFromCmd(String[] args, String arg) {
        int idx = Arrays.asList(args).indexOf(arg);
        return idx >= 0;
    }

    /**
     * Récupère la valeur qui nous intéresse (Integer)
     *
     * @param args       arguments de la ligne de commande
     * @param arg        argument qui nous intéresse
     * @param defaultVal valeur par défault
     * @return récupère la valeur qui nous intéresse
     */
    public static int getValueOfParam(String[] args, String arg, int defaultVal) {
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
     * Récupère la valeur qui nous intéresse (Tableau d'integer)
     *
     * @param args       arguments de la ligne de commande
     * @param arg        argument qui nous intéresse
     * @param defaultVal valeur par défault
     * @return récupère la valeur qui nous intéresse
     */
    public static int[] getValueOfParam(String[] args, String arg, int[] defaultVal) {
        String s = getArgFromCmd(args, arg);

        if (s != null) {
            s = s.replaceAll("^\\{|}$", "");
            String[] tab = s.split(",");

            try {
                return Arrays.stream(tab)
                        .map(String::trim)
                        .mapToInt(Integer::parseInt)
                        .toArray();

            } catch (NumberFormatException nfe) {
                usage();
                System.exit(1);
            }

        }

        return defaultVal;
    }

    /**
     * Récupère la valeur qui nous intéresse (Double)
     *
     * @param args       arguments de la ligne de commande
     * @param arg        argument qui nous intéresse
     * @param defaultVal valeur par défault
     * @return récupère la valeur qui nous intéresse
     */
    public static double getValueOfParam(String[] args, String arg, double defaultVal) {
        String s = getArgFromCmd(args, arg);

        if (s != null) {

            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException nfe) {
                usage();
                System.exit(1);
            }

        }
        return defaultVal;
    }

    /**
     * Gère les différentes options de debug
     *
     * @param args arguments de la ligne de commande
     */
    public static void handleFlags(String[] args) {
        DEBUG = getFlagFromCmd(args, "-debug");
    }

    /**
     * Récupère le nom de la fonction d'activation depuis la ligne de commande
     *
     * @param args arguments de la ligne de commande
     * @return nom de la fonction d'activation
     */
    public static String getActivationFuncFromCmd(String[] args) {
        return getArgFromCmd(args, "-func");
    }

    public static String getProbFromCmd(String[] args) {
        return getArgFromCmd(args, "-prob");
    }

    public static MLP makeMLP(String[] args, String functionName) {
        if (functionName == null) {
            functionName = "sigmoid";
        }

        switch (functionName) {
            case "sigmoid":
                return new MLP(getValueOfParam(args, "-layers", new int[]{2, 2, 1}),
                        getValueOfParam(args, "-lr", 0.1),
                        new Sigmoide());
            case "tanh":
                return new MLP(getValueOfParam(args, "-layers", new int[]{2, 2, 1}),
                        getValueOfParam(args, "-lr", 0.1),
                        new TanH());
            default:
                System.out.println("Fonction d'activation inconnue");
                usage();
                System.exit(1);
        }

        return null;
    }

    public static Problem makeProblem(String[] args, String problemName) {
        if (problemName == null) {
            problemName = "OR";
        }

        switch (problemName) {
            case "OR":
                return new OR(0);
            case "AND":
                return new AND(0);
            case "XOR":
                return new XOR(0);
            case "MNIST":
                return new MNIST(getValueOfParam(args, "-batchSize", 60000));
            case "FashionMNIST":
                return new FashionMNIST(getValueOfParam(args, "-batchSize", 60000));
            default:
                System.out.println("Problème inconnu");
                usage();
                System.exit(1);
        }

        return null;
    }

    public static Statistique makeStats(String[] args, MLP mlp, Problem p) {
        if(getFlagFromCmd(args, "-stats"))
            return new Statistique(p, mlp);
        return null;
    }
}
