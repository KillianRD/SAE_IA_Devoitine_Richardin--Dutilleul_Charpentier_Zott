import ia.framework.MLP.MLP;
import ia.framework.common.ArgParse;
import ia.problemes.Problem;

public class LancerRecherche {
    public static void main(String[] args) {

        ArgParse.setUsage
                ("Utilisation :\n\n"
                        + "java LancerRecherche [-prob problem] [-func functionActivatation]"
                        + " [-layers {int}] [-lr double] [-debug]\n\n"
                        + "-prob : Le nom du problem {OR, AND, XOR, MNIST, FashionMNIST}. Par défaut OR\n"
                        + "-func : fonction d'activation {sigmoid, tanh}. Par défault sigmoid\n"
                        + "-layers : le nombre de neurones du réseau par couche. Par défaut 2 couches de taille 2 neurones\n"
                        + "-lr : taux d'apprentissage. Par défaut 0.1\n"
                        + "-debug    : Rendre bavard (mettre à la fin)\n"
                );

        ArgParse.handleFlags(args);

        String prob_name = ArgParse.getProbFromCmd(args);
        String func_name = ArgParse.getActivationFuncFromCmd(args);

        MLP mlp = ArgParse.makeMLP(args, func_name);
        Problem p = ArgParse.makeProblem(args, prob_name, mlp);

        p.execute();
    }
}
