package com.example.aur_kit.IA.formulation;

/**
 * Class defining problem state. It represents a given problem stage or situation
 * and is described by certain information.
 * 
 * REPRESENTACION DEL PROBLEMA (PUZZLE)
 * TENDRA DOS INSTANCIAS: ESTADO INICIAL Y FINAL
 * 
 */
public class State {

	/**
	 * Information describing the problem state.
	 */
	private Object information;

	/**
	 * Constructor method.  A problem state is instantiated with the given information.
	 * 
	 * @param information
	 *            Object having the information that describes a problem state.
	 */
	public State(Object information) {
		this.information = information;
	}

	/**
	 * Returns the information describing a problem state.
	 * 
	 * @return Object with the information describing the state.
	 */
	public Object getInformacion() {
		return this.information;
	}

	/**
	 * Returns a text string containing the state information.
	 * Redefinition of method <i>toString()</i> from class Object.
	 * 
	 * @return String with the state information text.
	 */
	public String toString() {
		return this.information.toString();
	}

	/**
	 * Checks whether two states are the same. Redefinition of method
	 * <i>equals()</i> from class Object.
	 * 
	 * @param obj
	 *            Object against which the state is compared.
	 * @return boolean
	 *         <li><b>true</b> - if both states are described by the same information.</li>
	 *         <li><b>false</b> - otherwise.</li>
	 */
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof State) {
			return ((State) obj).information.equals(this.information);
		} else {
			return false;
		}
	}
}