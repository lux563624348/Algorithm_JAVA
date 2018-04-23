import java.io.*;
import java.util.*;
/**
 * This is the template for a class that performs A* search on a given
 * rush hour puzzle with a given heuristic.  The main search
 * computation is carried out by the constructor for this class, which
 * must be filled in.  The solution (a path from the initial state to
 * a goal state) is returned as an array of <tt>State</tt>s called
 * <tt>path</tt> (where the first element <tt>path[0]</tt> is the
 * initial state).  If no solution is found, the <tt>path</tt> field
 * should be set to <tt>null</tt>.  You may also wish to return other
 * information by adding additional fields to the class.
 */
public class AStar {

    /** The solution path is stored here */
	public State path[]= new State[2];
	public State path_tem;
	public State initial_node_state;
	public State[] state_expand;
	//public State[] all_states;
	public int state_counting = 0;
	public Node initial_node;
    public Node[] new_nodes;
    public Puzzle puzzle_tem;
	//ArrayList<State> all_states = new ArrayList<State>();
	List<State> all_states = new ArrayList<State>();
    /**
     * This is the constructor that performs A* search to compute a
     * solution for the given puzzle using the given heuristic.
     */
    public AStar(Puzzle puzzle, Heuristic heuristic) {
		
		//puzzle.getInitNode();
		//System.out.println("No idea what to do" + puzzle.getSearchCount());
		
		//System.out.println("5th Car position:" + puzzle.getFixedPosition(1));
		
		
		//System.out.println("New Node");
		initial_node = puzzle.getInitNode();
		initial_node_state = initial_node.getState();
		//initial_node_state.print();
		
		all_states.add(initial_node_state);
		
		state_expand = initial_node_state.expand();
		
		//all_states.get(0).print();
		
		System.out.println("Expand State length: " + state_expand.length );
		
		
		for (int i = 0; i < state_expand.length ; i++){
			if ( state_expand[i].isGoal() == true ){
				System.out.println("Found one: " + path_tem.isGoal());
				break;}
			for ( int j = 0; j <= state_counting ; j++){
				if ( state_expand[i].equals(all_states.get(state_counting)) == false ){
					System.out.println("New one, Added");
					all_states.add(state_expand[i]);
					state_counting = state_counting + 1;
					all_states.get(state_counting).print();
					System.out.println("Saved States:" + state_counting);
					puzzle_tem = state_expand[i].getPuzzle();
					AStar new_search = new AStar( puzzle_tem, heuristic);
					}
			}
		}
	}
		
		
		/*
		
		//System.out.println("Depth of node:" + puzzle_nodes.getDepth() );
		// Gives all possible states of a node.
		//puzzle.incrementSearchCount(1);
		//new_nodes = initial_node.expand();
		System.out.println("heuristic:" + heuristic);
		
		for(int i = 0; i < 3 ; i++) {
			state_expand[i].print();
			//path_tem = new_nodes[i].getState();
			//System.out.println("New State:" + i);
			//path_tem.print();
			puzzle_tem = state_expand[i].getPuzzle();
			
			for( int j = 0; j < 1000 ; j ++){
			if ( state_expand[i].equals(initial_node_state) == false ){
				//AStar new_search = new AStar( puzzle_tem, heuristic);
				}
			}
			
			if ( state_expand[i].isGoal() == true ){
				System.out.println("Found one: " + path_tem.isGoal() );
				path[0] = initial_node.getState();
				//path[1] = puzzle_nodes.getState();
				//back_puzzle = path[0].getPuzzle();
				//path[0].print();
				break;
				}
			
			System.out.println("Above State Expand: ");
			state_expand = path_tem.expand();
			
			for ( int j = 0; j < state_expand.length ; j++ ){
				System.out.println("State Expand: " + j );
				state_expand[j].print();
				}
			
			// If found a solution, exit.
			if ( path_tem.isGoal() == true ){
				System.out.println("Found one: " + path_tem.isGoal() );
				path[0] = puzzle_nodes.getState();
				//path[1] = puzzle_nodes.getState();
				//back_puzzle = path[0].getPuzzle();
				//path[0].print();
				break;
				}
			*/
			
		
		//System.out.println("Is goal " + path[0].isGoal());
		
	// your code here
}
