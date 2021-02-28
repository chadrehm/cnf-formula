/**
 * @author Chad Rehm
 * @date 2/25/21
 * @description This class represents a cnf formula.
 */
package cs475_sat_rehm;

import java.util.ArrayList;

public class CnfFormula {
	private ArrayList<Clause> clauses;
	
	/**
	 * Constructor to instantiate new list of clauses
	 */
	public CnfFormula() {
		clauses = new ArrayList<>();
	}
	
	/**
	 * Setter for clauses 
	 * @param clauses
	 */
	public void setClauses(ArrayList<Clause> clauses) {
		this.clauses = clauses;
	}
	
	/**
	 * Given an assignment determine if the assignment is verifiable or not.
	 * This method has a O(n^2) with <n<m>> where n is the number of clauses
	 * and m being the number of literals per clause:
	 *	1. loop n until all inputs have been looped.
	 *    2. call "verify" on each n which will loop on every m. *This step is O(m)
	 *			3. return "true" or "false" from m verify.
	 *		4. if one m returns false reject.
	 *  5. if all n are verified to be true accept.
	 * 
	 * @param assignment
	 * @return
	 */
	public boolean verify(Assignment assignment) {
		boolean verified = true;
		
		for(Clause clause : clauses) {
			if(!clause.verify(assignment)) {
				verified = false;
				break;
			}
		}
		
		return verified;
	}
	
}
