/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs475_sat_rehm;

import java.util.HashMap;

/**
 *
 * @author Chad Rehm
 */
public class Assignment {
	private HashMap<String, Boolean> assignments;
	private String[] variables;
	
	public Assignment() {
		assignments = new HashMap<>();
	}

	public HashMap<String, Boolean> getAssignments() {
		return assignments;
	}

	public String[] getVariables() {
		return variables;
	}

	public void setVariables(String[] variables) {
		// Initialize assignments with false values
		for (String variable : variables) {
			assignments.put(variable, Boolean.FALSE);
		}
		
		this.variables = variables;
	}
	
	public boolean getValue(String value) {
		return assignments.get(value);
	}
	
	public void setValue(String var, boolean val) {
		assignments.put(var, val);
	}
}
