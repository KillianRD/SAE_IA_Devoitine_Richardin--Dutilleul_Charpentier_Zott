import ia.framework.MLP.MLP;
import ia.framework.common.ArgParse;
import ia.framework.common.Statistique;
import ia.framework.utils.FileUtils;
import ia.problemes.Problem;

public class LancerRecherche {
    public static void main(String[] args) {

        ArgParse.setUsage
                ("""
                        Utilisation :

                        java LancerRecherche [-prob problem] [-batchSize int] [-nbIterations int] [-func functionActivatation]\
                         [-layers {int}] [-lr double] [-debug] [-stats]

                        -prob : Le nom du problem {OR, AND, XOR, MNIST, FashionMNIST}. Par défaut OR
                        -batchSize : Taille des données d'entrainement (utile pour MNIST et Fashion MNIST). Par défaut 60 000
                        -nbIterations : nombre d'itérations pour l'entrainement du mlp
                        -func : fonction d'activation {sigmoid, tanh}. Par défault sigmoid
                        -layers : le nombre de neurones du réseau par couche. Par défaut {2, 2, 1}
                        -lr : taux d'apprentissage. Par défaut 0.1
                        -debug : Rendre bavard (mettre à la fin)
                        -stats : Permet de rendre les résultats du MLP sur un problème
                        """
                );


        ArgParse.handleFlags(args);

        String prob_name = ArgParse.getProbFromCmd(args);
        String func_name = ArgParse.getActivationFuncFromCmd(args);
        int nbIteration = ArgParse.getValueOfParam(args, "-nbIterations", 10);

        MLP mlp = ArgParse.makeMLP(args, func_name);
        Problem p = ArgParse.makeProblem(args, prob_name);
        Statistique s = ArgParse.makeStats(args, mlp, p);

        p.executeTraining(nbIteration, mlp);

        if (s != null) {
            FileUtils.saveStatistique(s.getStatistiquePerClasse());
        }
    }
}
