package com.example.aur_kit.IA.search.log;

import java.util.Collection;

import com.example.aur_kit.IA.search.Node;


/**
 * Class defining the log for the search process steps.
 */
public class SearchLog extends LogWriter {


	/**
	 * Constructor method.
	 * @param fileName String that is the name of the log file.
	 * @throws Exception Exception In case an error occurs while creating the log file.
	 */
	public SearchLog(String fileName) throws Exception {
		super(fileName);
	}

	/**
	 * The content of every node in the given list is written in the search log.
	 * 
	 * @param nodes
	 *            Collection<Node> List of nodes whose content is
	 *            to be written in the log.
	 */
	public void writeLog(Collection<Node> nodes) {
		try {
			this.write("( ");
			this.flush();
			boolean first = true;	
			
			for (Node node: nodes) {
				if (first) {
					this.write(this.nodeToString(node));
					first = false;
				} else {
					this.write(", " + this.nodeToString(node));
				}
				this.flush();
			}
			
			this.write(" )\r\n");
			this.flush();
		} catch (Exception ex) {
			System.err.println("% [SolutionLog] Error while writing the log: " + ex.getMessage());
		}
	}

	/**
	 * The content of the given node is converted into a String. The string
	 * contains the following elements: <b>"node's state:parent-node's state"</b>.
	 * 
	 * @param node
	 *            Node to be converted into a String.
	 * @return String containing the node's content.
	 */
	private String nodeToString(Node node) {
		String nodeStr = node.getState() + ":";

		if (node.getParent() != null) {
			nodeStr += node.getParent().getState();
		} else {
			nodeStr += "null";
		}

		return nodeStr;
	}
}