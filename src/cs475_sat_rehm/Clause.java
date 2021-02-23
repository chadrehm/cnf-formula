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
public class Clause {
	private ArrayList<Literal> literals;
	
	public Clause(ArrayList<Literal> literals) {
		this.literals = literals;
	}
	
	public boolean verify(Assignment assignment) {
		return true;
	}
	
	public String[] literals() {
		return new String[1];
	}
}
