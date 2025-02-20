package ia.algo.recherche;

import ia.framework.common.Action;
import ia.framework.common.State;
import ia.framework.recherche.HasHeuristic;
import ia.framework.recherche.SearchNode;
import ia.framework.recherche.SearchProblem;
import ia.framework.recherche.TreeSearch;

import java.util.Comparator;
import java.util.PriorityQueue;

public class GFS extends TreeSearch {
    /**
     * Crée un algorithme de recherche
     *
     * @param prob          Le problème à résoudre
     * @param initial_state L'état initial
     */
    public GFS(SearchProblem prob, State initial_state) {
        super(prob, initial_state);

        if (prob instanceof HasHeuristic) {
            System.exit(1);
        }
    }

    @Override
    public boolean solve() {
        // Initialisation de la liste de priorité
        frontier = new PriorityQueue<>(new Comparator<SearchNode>() {
            @Override
            public int compare(SearchNode o1, SearchNode o2) {
                return (int) (o1.getHeuristic() - o2.getHeuristic());
            }
        });
        frontier.add(SearchNode.makeRootSearchNode(initial_state));

        // Ensemble des états visités
        while (!frontier.isEmpty()) {
            SearchNode node = frontier.poll();
            State state = node.getState();

            if (problem.isGoalState(state)) {
                end_node = node;
                return true;
            }

            explored.add(state);

            for (Action action : problem.getActions(state)) {
                SearchNode child = SearchNode.makeChildSearchNode(problem, node, action);
                State childState = child.getState();

                if (!explored.contains(childState)) {
                    frontier.add(child);
                }
            }
        }

        return false;
    }
}
