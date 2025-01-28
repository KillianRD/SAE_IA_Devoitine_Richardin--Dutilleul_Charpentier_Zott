package ia.algo.jeux;

import ia.framework.common.Action;
import ia.framework.common.ActionValuePair;
import ia.framework.jeux.Game;
import ia.framework.jeux.GameState;
import ia.framework.jeux.Player;

public class AlphaBetaPlayer extends Player {
    private int profondeurMax;
    private int profondeur;
    /**
     * Represente un joueur
     *
     * @param g            l'instance du jeux
     * @param player_one   si joueur 1
     * @param valueOfParam
     */
    public AlphaBetaPlayer(Game g, boolean player_one, int valueOfParam) {
        super(g, player_one);
        name = "AlphaBeta";
        profondeurMax = valueOfParam;
    }

    @Override
    public Action getMove(GameState state) {
        ActionValuePair actionValuePair;
        profondeur = 0;
        if (player==PLAYER1){
            actionValuePair = maxValue(state, -Double.MAX_VALUE, Double.MAX_VALUE);
        } else {
            actionValuePair = minValue(state, -Double.MAX_VALUE, Double.MAX_VALUE);
        }
        return actionValuePair.getAction();
    }

    private ActionValuePair maxValue(GameState s, double alpha, double beta){
        this.incStateCounter();

        if (game.endOfGame(s) || profondeur >= profondeurMax){
            return new ActionValuePair(null, s.getGameValue());
        }
        double vMax = -Double.MIN_VALUE;
        Action cMax = null;
        GameState sprime;
        for(Action c : game.getActions(s)){
            sprime = (GameState) game.doAction(s, c);
            profondeur++;
            ActionValuePair vCprime = minValue(sprime, alpha, beta);
            if(vCprime.getValue() >= vMax){
                vMax = vCprime.getValue();
                cMax = c;

                if(vMax>alpha){
                    alpha = vMax;
                }
            }
            if(vMax>=beta){
                return new ActionValuePair(cMax, vMax);
            }
        }
        return new ActionValuePair(cMax, vMax);
    }

    private ActionValuePair minValue(GameState s, double alpha, double beta){
        this.incStateCounter();

        if (game.endOfGame(s) || profondeur >= profondeurMax){
            return new ActionValuePair(null, s.getGameValue());
        }
        double vMin = Double.MAX_VALUE;
        Action cMin = null;
        GameState sprime;
        for(Action c : game.getActions(s)){
            sprime = (GameState) game.doAction(s, c);
            profondeur++;
            ActionValuePair vCprime = maxValue(sprime, alpha, beta);
            if(vCprime.getValue() <= vMin){
                vMin = vCprime.getValue();
                cMin = c;

                if(vMin<beta){
                    beta = vMin;
                }
            }
            if(vMin<=alpha){
                return new ActionValuePair(cMin, vMin);
            }
        }
        return new ActionValuePair(cMin, vMin);
    }

}
