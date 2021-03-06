import java.util.ArrayList;
import java.util.List;

//import Heuristics.Heuristic;

/**
 * This is the template for a class that performs A* search on a given rush hour
 * puzzle with a given heuristic. The main search computation is carried out by
 * the constructor for this class, which must be filled in. The solution (a path
 * from the initial state to a goal state) is returned as an array of
 * <tt>State</tt>s called <tt>path</tt> (where the first element
 * <tt>path[0]</tt> is the initial state). If no solution is found, the
 * <tt>path</tt> field should be set to <tt>null</tt>. You may also wish to
 * return other information by adding additional fields to the class.
 */
public class BnB {

    /** The solution path is stored here */
    public State[] path;
    
    private SortableList<HNode> open = new SortableList<HNode>();
    private List<HNode> closed = new ArrayList<HNode>();
    
    private int curBest = 10000;
    private int solnCounter = 0;

    /**
     * This is the constructor that performs A* search to compute a
     * solution for the given puzzle using the given heuristic.
     */
    public BnB(Puzzle puzzle, Heuristic heuristic) {
    	
    	// Initialize root node w/ heuristics and path costs
    	int h = heuristic.getValue(puzzle.getInitNode().getState());
    	HNode root = new HNode(puzzle.getInitNode(), h);
    	
    	open.add(root);	// Add the root node to the open list
    	
    	while(!open.isEmpty()) {
    		
    		// Only performs sort if list was changed
    		open.sort();
    		
    		HNode current = open.remove(0);
    		
    		if (current.getState().isGoal()) {
    			solnCounter += 1;
    			if(current.getDepth() < curBest) {
    				// Update curBest
    				curBest = current.getDepth();
    				
	    			// Set the path array size to depth of goal state;
	    			// The +1 should be necessary to also include root node.
	    			path = new State[current.getDepth() + 1];
	    			
	    			// Set the current node to pathNode
	    			Node pathNode = current;
	    			
	    			// Get state for every node and store it in the path array,
	    			// then override current path node with its parent node until parent is null.
	    			while (pathNode != null) {
	    				path[pathNode.getDepth()] = pathNode.getState();
	    				pathNode = pathNode.getParent();
	    			}
    			}
    			
//    			// We found a solution, stop.
//    			return;
    		}
    		
    		// if current node is promising
    		if(current.getDepth() + 1 < curBest) {
    			closed.add(current);
    		
    		
    		
	    		for (Node successor : current.expand()) {
	
	    			h = heuristic.getValue(successor.getState());
	    			HNode hSuccessor = new HNode(successor, h);
	    			
	    			if (open.contains(hSuccessor)) {
	    				keepBetterNodeOnOpenList(hSuccessor);
	    			} else if (!closed.contains(hSuccessor)) {
	    				open.add(hSuccessor);
	    			}
	    		}
    		}

    	}
    	System.out.println("Total Solutions Found: " + solnCounter);

    }
    
    private void keepBetterNodeOnOpenList(HNode successor) {
    	HNode existing = open.get(successor);
    	
    	if (existing != null) {
    		if (existing.compareTo(successor) > 0) {
    			open.remove(existing);
    			open.add(successor);
    		}
    	}
    }

}