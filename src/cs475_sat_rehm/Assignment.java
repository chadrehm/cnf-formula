/**
 * @author Chad Rehm
 * @date 2/25/21
 * @description This class represents an assignment for a cnf formula.
 */
package cs475_sat_rehm;

import java.util.HashMap;
import java.util.stream.Collectors;

public class Assignment {
	private HashMap<String, Boolean> assignments;
	private String[] variables;
	
	/**
	 * Constructor to instantiate new assignments map
	 */
	public Assignment() {
		assignments = new HashMap<>();
	}

	/**
	 * Getter for assignments
	 * @return
	 */
	public HashMap<String, Boolean> getAssignments() {
		return assignments;
	}

	/**
	 * Getter for variables
	 * @return
	 */
	public String[] getVariables() {
		return variables;
	}

	/**
	 * Setter for variables.
	 * This method has a O(n) with <n> :
	 *	1. loop n until all inputs have been looped.
	 * @param variables
	 */
	public void setVariables(String[] variables) {
		// Initialize assignments with false values
		for (String variable : variables) {
			assignments.put(variable, Boolean.FALSE);
		}
		
		this.variables = variables;
	}
	
	/**
	 * Getter for value
	 * @param value
	 * @return
	 */
	public boolean getValue(String value) {
		return assignments.get(value);
	}
	
	/**
	 * Setter for value
	 * @param var
	 * @param val
	 */
	public void setValue(String var, boolean val) {
		assignments.put(var, val);
	}
	
	public String toString() {
		return assignments.keySet().stream().map(assignment -> {
			return String.format("%s=%s", assignment, assignments.get(assignment));
		}).collect(Collectors.joining(",", "{", "}"));
	}
}
