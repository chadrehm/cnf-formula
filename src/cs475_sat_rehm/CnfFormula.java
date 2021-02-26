/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs475_sat_rehm;

import java.util.ArrayList;

/**
 *
 * @author Chad Rehm
 */
public class CnfFormula {
	public ArrayList<Clause> clauses;
	
	public CnfFormula() {
		clauses = new ArrayList<>();
	}
	
	public void setClauses(ArrayList<Clause> clauses) {
		this.clauses = clauses;
	}
	
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
