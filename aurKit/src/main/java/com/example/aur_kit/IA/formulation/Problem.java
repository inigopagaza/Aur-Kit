package com.example.aur_kit.IA.formulation;

import java.util.List;
import java.util.ArrayList;

/**
// * Class defining a problem's formulation for it to be solved by a search method.
 *  The problem is defined by:
 * <li>List of initial states.</li>
 * <li>List of final states.</li>
 * <li>List of operators.</li>
 */
public abstract class Problem {

	/**
	 * List containing the problem's initial states.
	 */
	private List<State> initialStates;

	/**
	 * List containing the problem's final states.
	 */
	private List<State> finalStates;

	/**
	 * List containing the problem's operators.
	 */
	private List<Operator> operators;

	/**
	 * Constructor method. Instantiates a problem making empty lists for the initial states,
	 * final states and operators.
	 */
	public Problem() {
		this.initialStates = new ArrayList<State>();
		this.finalStates = new ArrayList<State>();
		this.operators = new ArrayList<Operator>();
		
		this.createOperators();
	}
	
	/**
	 * Constructor method. Instantiates a problem making empty lists for the initial states,
	 * final states and operators. It also makes instances of State for
	 * the problem initial and final states respectively.
	 */	
	public Problem(Object initialEnvironment, Object finalEnvironment) {
		this.initialStates = new ArrayList<State>();
		this.finalStates = new ArrayList<State>();
		this.operators = new ArrayList<Operator>();
		
		if(initialEnvironment != null) {
			this.addInitialState(new State(initialEnvironment));
		}
		
		if(finalEnvironment != null) {
			this.addFinalState(new State(finalEnvironment));
		}

		this.createOperators();	
	}
	

	/**
	 * instantiation of operators
	 */
	protected abstract void createOperators();
	
		
	
	/**
	 * Adds a problem initial state to the list of initial states.
	 * 
	 * @param initialState
	 *            State that is one of the problem's initial states.
	 */
	public void addInitialState(State initialState) {
		if (initialState != null && !this.initialStates.contains(initialState)) {
			this.initialStates.add(initialState);
		}
	}

	/**
	 * Returns the problem's initial states list.
	 * 
	 * @return List<State> containing the problem's initial states.
	 */
	public List<State> getInitialStates() {
		return this.initialStates;
	}
	
	/**
	 * Adds a problem final state to the list of final states.
	 * 
	 * @param finalState
	 *            State that is one of the problem's final states.
	 */
	public void addFinalState(State finalState) {
		if (finalState != null && !this.finalStates.contains(finalState)) {
			this.finalStates.add(finalState);
		}
	}

	/**
	 * Returns the problem's final states list.
	 * 
	 * @return List<State> containing the problem's final states.
	 */
	
	public List<State> getFinalStates() {
		return this.finalStates;
	}

	/**
	 * Adds a problem operator to the list of operators.
	 * 
	 * @param operator
	 *            Operator that is one of the problem's operators.
	 */
	public void addOperator(Operator operator) {
		if (operator != null && !this.operators.contains(operator)) {
			this.operators.add(operator);
		}
	}

	/**
	 * Returns the problem's operators list.
	 * 
	 * @return List<Operator> containing the problem's operators.
	 */
	public List<Operator> getOperators() {
		return this.operators;
	}

	/**
	 * Checks whether a given state is one of the problem's final states by looking 
	 * for it inside the problem's final states list (using method contains). If final
	 * states of the problem are unknown, this method must be redefined. 
	 * 
	 * @param state
	 *            State containing the problem state to be checked.
	 * @return boolean
	 *         <li>true - if state is found in the list of final states</li>
	 *         <li>false - if state is not found in the list of final states.</li>
	 */
	public boolean isFinalState(State state) {
		if (state != null) {
			return this.finalStates.contains(state);
		} else {
			return false;
		}
	}
}