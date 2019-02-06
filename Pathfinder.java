package pathfinder.uninformed;
import java.util.*;
public class Pathfinder {

    public static ArrayList<String> solve (MazeProblem problem) {
        Queue<SearchTreeNode> frontier = new LinkedList<SearchTreeNode>();
        Map<String, MazeState> transitions = new HashMap<>();

        SearchTreeNode initialState = new SearchTreeNode(problem.INITIAL_STATE, null, null);
        SearchTreeNode current = initialState;

        frontier.add(initialState);

        while(!frontier.isEmpty()) {
            if(current.state.equals(problem.GOAL_STATE)) {
                return backThatThangUp(current, problem); // return path
            }
            transitions = problem.getTransitions(current.state);
            for(Map.Entry<String, MazeState> transition : transitions.entrySet()) {
                frontier.add(new SearchTreeNode(transition.getValue(), transition.getKey(), current));
            }
            frontier.remove();
            current = frontier.peek();
        }
        return null;
    }

    private static ArrayList<String> backThatThangUp(SearchTreeNode current, MazeProblem problem) {
        ArrayList<String> reversedPath = new ArrayList<String>();
        ArrayList<String> path = new ArrayList<String>();
        while(!current.state.equals(problem.INITIAL_STATE)) {
            reversedPath.add(current.action);
            current = current.parent;
        }
        for(int i = reversedPath.size()-1; i >= 0; i--) {
            path.add(reversedPath.get(i));
        }
        return path;
    }
}
class SearchTreeNode {

    MazeState state;
    String action;
    SearchTreeNode parent;

    SearchTreeNode (MazeState state, String action, SearchTreeNode parent) {
        this.state = state;
        this.action = action;
        this.parent = parent;
    }

}
