/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs475_sat_rehm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Chad Rehm
 */
public class CnfFormula {
	public ArrayList<Clause> clauses;
	
	public CnfFormula() {
		clauses = new ArrayList<>();
	}
	
	public void setClauses(ArrayList<String> clausesStrList) {
		List<Clause> clausesList = clausesStrList.stream().map(clauseStr -> {
			
			List<Literal> list = Arrays.asList(
				clauseStr.replaceAll("[()\\s]", "").split("v")).stream().map(literalStr -> {
					Literal literal = new Literal();
					
					boolean isNegative = literalStr.startsWith("n");
					if (isNegative) {
						literalStr = literalStr.substring(1);
					}
					
					literal.setName(literalStr);
					literal.setIsNegated(isNegative);
					
					return literal;
				}).collect(Collectors.toList());
			
			Clause clause = new Clause(new ArrayList<>(list));
			
			return clause;
		}).collect(Collectors.toList());
		
		this.clauses = new ArrayList<>(clausesList);
	}
	
	public String[] literals() {
		return new String[1];
	}

	
	public boolean verify(Assignment assignment) {
		return true;
	}
	
}
