package com.example.aur_kit.IA.search.blind;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.example.aur_kit.IA.formulation.Problem;
import com.example.aur_kit.IA.formulation.State;
import com.example.aur_kit.IA.search.Node;
import com.example.aur_kit.IA.search.SearchMethod;


/**
 * This class defines the Breadth First Search Method. 
 * It is defined according to the design pattern <b>Singleton</b>.
 */
public class BreadthFS extends SearchMethod {

	/**
	 * In accordance with the pattern <b>Singleton</b>, 
	 * a single instance of the class will be created
	 */
	private static BreadthFS instance;
	
	/**
	 * Constructor method is private in accordance with the pattern <b>Singleton</b>
	 */
	private BreadthFS() {		
	}
	
	/**
	 * In accordance with the pattern <b>Singleton</b> a class method invokes the constructor
	 * and guarantees one single instantiation of the class.
	 * @return BreadthFS, the single instance of the class.
	 */
	public static BreadthFS getInstance() {
		if (instance == null) {
			instance = new BreadthFS();
		}
		
		return instance;
	}
	
	/**
	 * Carries out a search process from the initial state
	 * to the final state of the given problem.
	 * This method is defined according to the second version of the basic search algorithm
	 * which checks for repeated states (refer to the last algorithm studied in chapter 3).
	 * 
	 * @param problem
	 *            Problem to be solved by a search method.
	 * @param initialState
	 *            Problem's initial state. 
	 * @return Node
	 *         <ul>
	 *         <li>If a solution is found, Node contains the problem's final state</li>
	 *         <li>If the problem can't be solved, Node contains null.</li>
	 *         </ul>
	 */
	public Node search(Problem problem, State initialState) {
		//A queue is used to keep the nodes generated during the search process.
		Queue<Node> frontier = new LinkedList<Node>();
		//List of states generated during the search process. This is used to check for repeated states.
		List<State> generatedStates = new ArrayList<State>();
		//List of states expended during the search process. This is used to check for repeated states.
		List<State> expandedStates = new ArrayList<State>();
		//Queue's first node.
		Node firstNode = null;
		//successor nodes list.
		List<Node> successorNodes = null;		
		//flag to signal whether a solution has been found or not.
		boolean solutionFound = false;

		//Initialize the queue with a node that contains the problem's initial state.
		frontier.add(new Node(initialState));
		//The initial state is kept in the list of generated states.
		generatedStates.add(initialState);

		//Loop until a solution is found or the queue empties
		while (!solutionFound && !frontier.isEmpty()) {			
			//Remove the first node from the queue.
			firstNode = frontier.remove();
			
			//If the first node contains a problem's final state, then it's solved
			if (problem.isFinalState(firstNode.getState())) {
				//change the flag to signal that the problem is solved
				solutionFound = true;
			//If the first node doesn't contain a problem's final state
			} else {
				//expand the first node.
				successorNodes = super.expand(firstNode, problem, generatedStates, expandedStates);
				//If the expansion resulted in new successor nodes
				if (successorNodes != null && !successorNodes.isEmpty()) {
					//Add the new successor nodes to the queue of nodes.
					frontier.addAll(successorNodes);
				}
			}
		}

		// If the problem is solved
		if (solutionFound) {
			//The first node of the queue is returned as it contains the problem's final state
			return firstNode;
		//If the problem isn't solved
		} else {
			//null is returned
			return null;
		}
	}
}