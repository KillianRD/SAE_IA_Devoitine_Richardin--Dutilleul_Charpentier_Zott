package ia.algo.jeux;

import ia.framework.common.Action;
import ia.framework.common.ActionValuePair;
import ia.framework.jeux.Game;
import ia.framework.jeux.GameState;
import ia.framework.jeux.Player;

public class MinMaxPlayer extends Player {

    /**
     * Represente un joueur
     *
     * @param g            l'instance du jeux
     * @param player_one   si joueur 1
     * @param valueOfParam
     */
    public MinMaxPlayer(Game g, boolean player_one, int valueOfParam) {
        super(g, player_one);
        name = "Minmax";
    }

    @Override
    public Action getMove(GameState state) {
        ActionValuePair actionValuePair;
        if (player==PLAYER1){
            actionValuePair = maxValue(state);
        } else {
            actionValuePair = minValue(state);
        }
        return actionValuePair.getAction();
    }

    private ActionValuePair maxValue(GameState s){
        this.incStateCounter();

        if (game.endOfGame(s)){
            return new ActionValuePair(null, s.getGameValue());
        }
        double vMax = -Double.MIN_VALUE;
        Action aMax = null;
        GameState sprime;
        for(Action a : game.getActions(s)){
            sprime = (GameState) game.doAction(s, a);
            ActionValuePair vAprime = minValue(sprime);
            if(vAprime.getValue() >= vMax){
                vMax = vAprime.getValue();
                aMax = a;
            }
        }
        return new ActionValuePair(aMax, vMax);
    }

    private ActionValuePair minValue(GameState s){
        this.incStateCounter();

        if (game.endOfGame(s)){
            return new ActionValuePair(null, s.getGameValue());
        }
        double vMin = Double.MAX_VALUE;
        Action aMin = null;
        GameState sprime;
        for(Action a : game.getActions(s)){
            sprime = (GameState) game.doAction(s, a);
            ActionValuePair vAprime = maxValue(sprime);
            if(vAprime.getValue() <= vMin){
                vMin = vAprime.getValue();
                aMin = a;
            }
        }
        return new ActionValuePair(aMin, vMin);
    }
}
