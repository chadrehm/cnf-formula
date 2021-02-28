/**
 * @author Chad Rehm
 * @date 2/25/21
 * @description This class represents an clause for a cnf formula.
 */
package cs475_sat_rehm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Clause {
	private ArrayList<Literal> literals;
	
	/**
	 * Assign literals as a list to Clauses
	 * @param literals
	 */
	public Clause(ArrayList<Literal> literals) {
		this.literals = literals;
	}
	
	/**
	 * Given an assignment determine if the assignment is verifiable or not.
	 * This method has a O(n) with <n> where n is the number of literals:
	 *	1. loop n until all inputs have been looped.
	 *		2. accept if a literal results in a "true".
	 *  3. reject if no literal resulted in "true" before end of list.
	 * 
	 * @param assignment
	 * @return verified
	 */
	public boolean verify(Assignment assignment) {
		boolean verified = false;
		
		for(Literal literal : literals) {
			boolean value = literal.isIsNegated() 
				? !assignment.getValue(literal.getName()) 
				: assignment.getValue(literal.getName());
			
			if (value) {
				verified = true;
				break;
			}
		}
			
		return verified;
	}
	
	/**
	 * return an array of literals as strings.
	 * This method has a O(2n) with <n> where n is the number of literals:
	 *	1. Loop all literals and convert to strings.
	 *  2. Loop all literals and convert list to array.
	 * This has a total O(n).  One caveat that I'm assuming the built in Java operation
	 * time complexity.
	 * @return 
	 */
	public String[] literals() {
		List<String> literalsStrings = 
			literals.stream().map(literal -> literal.toString()).collect(Collectors.toList());
		return literalsStrings.toArray(new String[literals.size()]);
	}
}
