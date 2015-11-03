package com.example.aur_kit.IA.search.log;

import java.util.List;

/**
 * Class defining the log for a problem's solution. This is the String of 
 * the names of the operators that constitute the solution to the problem. 
 */
public class SolutionLog extends LogWriter {

	/**
	 * Constructor method.
	 * @param fileName String that is the name of the log file.
	 * @param operators List<String> containing the operators' names.
	 * @throws Exception Exception In case an error occurs while creating the log.
	 */
	public SolutionLog(String fileName, List<String> operators) throws Exception {
		super(fileName);
		this.write(operators);
		this.closeLog();
	}

	/**
	 * Writes the content of the list of operator names in the log file.
	 * 
	 * @param operators
	 *            List<String> containing the names of the operators 
	 *            that make up the solution path.
	 */
	private void write(List<String> operators) {
		try {
			if (operators != null && operators.size() > 0) {
				for (String operator : operators) {
					this.write(" - " + operator + "\r\n");
				}
			} else {
				this.write("SOLUTION NOT FOUND!!");
			}
			this.flush();
		} catch (Exception ex) {
			System.err.println("% [SolutionLog] Error while writing the log: " + ex.getMessage());
		}
	}
}