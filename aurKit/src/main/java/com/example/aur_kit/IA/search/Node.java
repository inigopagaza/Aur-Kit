package com.example.aur_kit.IA.search;

import com.example.aur_kit.IA.formulation.State;

/**
 * Class that represents a search node.
 */
public class Node implements Comparable<Node> {

	/**
	 * State contained within node.
	 */
	private State state;

	/**
	 * Name of operator that generated the state
	 */
	private String operator;

	/**
	 * Node's parent node.
	 */
	private Node parent;

	/**
	 * Node's depth: how far down the search tree it's been generated.
	 */
	private int depth;

	/**
	 * Real cost of the action path from the root node to node.
	 * The default value is zero.
	 */
	private double g = 0d;
	
	/**
	 * Estimated cost from node to the goal node.
	 * The default value is zero.
	 */
	private double h = 0d;

	/**
	 * Constructor method. Creates a new node to keep the state passed as parameter.
	 * g and h take value zero; the parent node and operator name: <b>valor null</b>.
	 * 
	 * @param state
	 *            state kept in the node.
	 */
	public Node(State state) {
		this.state = state;
	}

	/**
	 * Returns the state kept within the node.
	 * 
	 * @return State contained within the node.
	 */
	public State getState() {
		return this.state;
	}

	/**
	 * Changes the name of the operator that generated the state kept within the node.
	 * 
	 * @param operator
	 *            String that is the new operator's name.
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * Returns the name of the operator that generated the state kept in the node.
	 * 
	 * @return operator String that is the operator's name.
	 */
	public String getOperator() {
		return this.operator;
	}

	/**
	 * Changes the parent node.
	 * 
	 * @param parent
	 *            node that is the new parent node.
	 */
	public void setParent(Node parent) {
		this.parent = parent;
	}

	/**
	 * Returns the parent node.
	 * 
	 * @return node containing the parent node.
	 */
	public Node getParent() {
		return this.parent;
	}

	/**
	 * Changes the value of the depth at which the node was generated.
	 * 
	 * @param depth
	 *            int that is the new depth.
	 */
	public void setDepth(int depth) {
		this.depth = depth;
	}

	/**
	 * Returns the depth at which the node was generated.
	 * 
	 * @return int that is the node's depth.
	 */
	public int getDepth() {
		return this.depth;
	}
	
	/**
	 * Changes the real cost of the action path from the root node to node.
	 * 
	 * @param cost
	 *            double that is new accumulated real cost.
	 */
	public void setG(double cost) {
		this.g = cost;
	}	
	
	/**
	 * Returns the real cost of the action path from the root node to node.
	 * 
	 * @return double that is the new real cost.
	 */
	public double getG() {
		return this.g;
	}	

	/**
	 * Changes the the estimated cost from node to the goal node.
	 * 
	 * @param heuristic
	 *            double that is the new estimated cost.
	 */
	public void setH(double heuristic) {
		this.h = heuristic;
	}
	
	/**
	 * Returns the estimated cost from node to the goal node.
	 * 
	 * @return double that is the estimated cost
	 */
	public double getH() {
		return this.h;
	}	

	/**
	 * Converts the node's content into a String.
	 * 
	 * @return String describing the node's content.
	 */
	public String toString() {
		String tab = "";
		// Tabulating the node's content is accomplished by adding two empty spaces 
		// per depth unit.
		for (int i = 0; i < this.depth; i++) {
			tab += "  ";
		}
		return tab + "Tree level:" + this.depth + " / g(n): " + this.g + 
		       "/ h(n): " + this.h + " # [" + this.state + "]";
	}

	/**
	 * Compares the node against an object.
	 * 
	 * @param object
	 *            Object against which the node is compared.
	 * @return
	 *            <ul>
	 *            <li><b>true</b> - if object passed within parameter is a node and 
	 *            contains the same state as the present node </li>
	 *            <li><b>false</b> - if object passed within parameter is not a node
	 *            or is a node but doesn't contain the same state as present node </li>
	 *            </ul>
	 */
	public boolean equals(Object object) {
		if (object != null && object instanceof Node) {
			return this.state.equals(((Node) object).state);
		} else {
			return false;
		}
	}

	/**
	 * Compares the present node against the node passed within the parameter
	 * taking into account only their evaluation function values.
	 * The method sort() from Collections uses this compareTo() definition to
	 * sort nodes in <b>ascending order</b>.
	 * 
	 * <b>For descending order sorting, swap the return values "1" and "-1"</b>.
	 * @param node
	 *            node against which the present node is compared.
	 * @return int
	 *         <ul>
	 *         <li><b>-1</b> - if the evaluation function value of the present node 
	 *         is greater than that of the node passed within the parameter.</li>
	 *         <li><b>1</b> - if the evaluation function value of the present node 
	 *         is lower than that of the node passed within the parameter.</li>
	 *         <li><b>0</b> - if the evaluation function values of the two nodes
	 *         are the same or an error occurred while comparing </li>
	 *         </ul>
	 */
	public int compareTo(Node node) {
		if (node != null && node instanceof Node) {
			if ( (node.g + node.h) > (this.g + this.h) ) {
				return 1;
			} else if ( (node.g + node.h) < (this.g + this.h) ) {
				return -1;
			}
		}

		return 0;
	}
}