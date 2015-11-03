package com.example.aur_kit.IA.search;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.example.aur_kit.IA.formulation.Operator;
import com.example.aur_kit.IA.formulation.Problem;
import com.example.aur_kit.IA.formulation.State;
import com.example.aur_kit.IA.search.log.SearchLog;
import com.example.aur_kit.IA.search.log.SolutionLog;



/**
 * Abstract class defining a search method.
 * This class is the result of applying the design pattern: Strategy
 */
public abstract class SearchMethod {

	/**
	 * Carries out a search process from a problem's initial state to its final state.
	 * 
	 * @param problem
	 *            Problem to be solved by means of a search method.
	 * @param initialState
	 *            problem's initial state.
	 * @return Node
	 *         <ul>
	 *         <li>If a solution is found, node will contain the problem's final state.</li>
	 *         <li>If a solution can't be found, node will contain null. </li>
	 *         </ul>
	 */
	public abstract Node search(Problem problem, State initialState);

	/**
	 * Expands the node's state therefore generating a list of successor nodes.
	 * Expansion takes place by invoking the problem's operators apply() method on the
	 * node's state.
	 * 
	 * @param node
	 *            node whose state is to be expanded.
	 * @param problem
	 *            problem to solve
	 * @param generatedStates
	 *            List<State> the nodes that are in the frontier of the search algorithm.
	 * @param expandedStates
	 *            List<State> the states that have been expended so far along the search process.            
	 * @return List<Node> containing the successor nodes.
	 */
	public List<Node> expand(Node node, Problem problem, List<State> generatedStates, List<State> expandedStates) {
		List<Node> successorNodes = new ArrayList<Node>();
		Node successorNode = null;
		State currentState = null;
		State successorState = null;

		//If neither node or problem are null
		if (node != null && problem != null) {
			//Get the node's state.
			currentState = node.getState();
			//Remove current state from the list of generated states.
			generatedStates.remove(currentState);
			//Insert current state to the list of generated states.
			expandedStates.add(currentState);
			//If the state is not null
			if (currentState != null) {
				//Go over the list of problem operators
				for (Operator operator : problem.getOperators()) {
					//Apply the operator to the current state
					successorState = operator.apply(currentState);
					//If the operator has been successfully applied and the resulting successor
					//state hadn't been previously generated nor expanded
					if (successorState != null && 
					    !generatedStates.contains(successorState) &&
					    !expandedStates.contains(successorState)) {
						//make a new node to contain the new successor state
						successorNode = new Node(successorState);
						successorNode.setOperator(operator.getName());
						successorNode.setParent(node);
						successorNode.setDepth(node.getDepth() + 1);
						//add the newly created node to the list of successor nodes.
						successorNodes.add(successorNode);
						//Insert current successor State to the list of generated states
						generatedStates.add(successorState);						
					}
				}
			}
		}
		
		return successorNodes;
	}

	/**
	 * Generates the list of names of the operators in the 
 	 * path from the root node to the current node.
	 * 
	 * This is a recursive method that builds the names list 
	 * on to the list passed within the parameter.
	 * 
	 * @param node
	 *            current node. Usually a node containing a final problem state.
	 * @param operatorNames
	 *            List<String> names of the operators making up the path
	 *            from the root node to the current node.
	 */
	public void solutionPath(Node node, List<String> operatorNames) {
		if (node == null || node.getParent() == null) {
			return;
		} else {
			operatorNames.add(0, node.getOperator());
			solutionPath(node.getParent(), operatorNames);
		}
	}
	
	/**
	 * Creates the solution log.
	 */
	@SuppressWarnings("resource")
	public void createSolutionLog(List<String> operators) {		
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("(dd-MM-yyyy@HH_mm_ss)", java.util.Locale.getDefault());
			//create log for the solution
			new SolutionLog("log/" + this.getClass().getSimpleName() + "_Solution " +
					        formatter.format(Calendar.getInstance().getTime()) + 
					        ".log", operators);
		} catch (Exception ex) {
			System.out.println("Unable to create the log file!");
		}
	}	
	
	/**
	 * Creates the search log.
	 * 
	 * @return SearchLog having the log of the search process.
	 */
	public SearchLog createLog() {
		SearchLog log = null;

		try {
			SimpleDateFormat formatter = new SimpleDateFormat("(dd-MM-yyyy@HH_mm_ss)",java.util.Locale.getDefault());
			//create log for the search process.
			log = new SearchLog("log/" + this.getClass().getSimpleName()+ " " +
					              formatter.format(Calendar.getInstance().getTime()) + 
					              ".log");
		} catch (Exception ex) {
			System.out.println("Unable to create the log file!");
		}

		return log;
	}

	/**
	 * Dumps the list of nodes into the search log.
	 * 
	 * @param log
	 *            SearchLog representing the log file.
	 * @param nodelist
	 *            list of nodes generated along the search process.
	 */
	public void writeLog(SearchLog log, List<Node> nodelist) {
		if (log != null) {
			log.writeLog(nodelist);
		}
	}

	/**
	 * Closes the log of the search process.
	 * 
	 * @param log
	 *            SearchLog representing the log file.
	 */
	public void closeLog(SearchLog log) {
		if (log != null) {
			log.closeLog();
		}
	}
}