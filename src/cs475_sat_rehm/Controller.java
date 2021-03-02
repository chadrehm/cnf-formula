/**
 * @author Chad Rehm
 * @date 1/27/21
 * @description This class controls the application.
 */
package cs475_sat_rehm;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Controller implements ActionListener{
	JFileChooser fc;
	JFrame frame;
	JOptionPane pane;
	Assignment assignment;
	CnfFormula cnfFormula;
	
	Controller(JFileChooser fc, JFrame frame, JOptionPane pane) {
		this.frame = frame;
		this.fc = fc;
		this.pane = pane;
		assignment = new Assignment();
		cnfFormula = new CnfFormula();
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		int returnVal = fc.showOpenDialog(frame);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			readFile(fc.getSelectedFile());
			handleAction();
		} else {
			System.out.println("Operation is CANCELLED :(");
		}
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	}
	
	/**
	 * After file is selected prompt user for an assignment, process input and
	 * display results to users.  Prompt user for additional assignments
	 */
	public void handleAction() {
		
		promptAssignment();
		boolean verified = cnfFormula.verify(assignment);
		showVerified(verified);

		String isSatisfiable = satisfiable();
		showSatisfiable(isSatisfiable);
	}
	
	/**
	 * return the first binary input that will satisfy the cnf formula or null.
	 * This method has a O(2^n).  For every variable the set of assignments that could
	 * be checked grows 2^n and each assignment has an O(m^2) running time.  Verifying
	 * assignment's clauses m with literals x:
	 *	1. loop over the assignment count which is 2 to the number of variables.
	 *		2. build a list of binary inputs to simulate the assignment.
	 *    3. crate the assignment.
	 *    4. loop on every clause m to check the cnf formula.
	 *		  5. call "verify" on each m which will also loop on every x. this step is O(m^2)
	 *			  6. return "true" or "false" from x verify.
	 *		  7. if one x returns false reject.
	 *		8. if all m are verified to be true accept.
	 *  9. if one assignment is found to accept the the cnf formula is satisfiable.
	 * @return
	 */
	public String satisfiable() {
		boolean verified;
		String[] variables = assignment.getVariables();
		int numberVariables = variables.length;
		int assignmentCount = (int)Math.pow(2, numberVariables);
		String psudoAssignment = null;
		
		for (int idx = 0; idx < assignmentCount; idx++) {
			StringBuilder psudoAssignmentSB = 
				new StringBuilder(Integer.toBinaryString(idx)).reverse();
			
			psudoAssignment = padRightZeros(psudoAssignmentSB, numberVariables);
			
			String [] assignmentValues = psudoAssignment.split("");
			
			for (int index = 0; index < numberVariables; index++) {
				assignment.setValue(variables[index], assignmentValues[index].equals("1"));
			}
			
			verified = cnfFormula.verify(assignment);
			if(verified) {
				break;
			}
			// Make sure that if no assignment verifies that psudoAssignment returns null
			psudoAssignment = null;
		}

		return psudoAssignment == null ? null : assignment.toString();
	}
	
	private String padRightZeros(StringBuilder inputString, int length) {
    if (inputString.length() >= length) {
        return inputString.toString();
    }
    while (inputString.length() < length) {
        inputString.append('0');
    }

    return inputString.toString();
	}
	
	/**
	 * Open and read file with valid cnf formula.
	 * @param file
	 */
	public void readFile(File file){
		String cnfFormulaString = null;

		try {
			BufferedReader brTest = new BufferedReader(new FileReader(file));
			cnfFormulaString = brTest.readLine();
		} catch (IOException exp) {
			System.out.println(exp.getMessage());
			System.exit(1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		if(cnfFormulaString != null) {
			parseCnfFormulaInput(cnfFormulaString);
		};
	}
	
	/**
	 * Take the incoming cnf formula and parse it for use by the application.
	 * This method has a O(n^2) with <n> being the input cnf formula:
	 *	1. split incoming string on ^
	 *  2. loop step 1 until all elements have been looped.
	 *    3. split incoming string on v
	 *    4. loop step 3 until all elements have been looped.
	 *      5. create literal.
	 *      6. add literal to Set of literals.
	 *    7. create clause.
	 * @param cnfFormulaString
	 */
	protected void parseCnfFormulaInput(String cnfFormulaString){
		HashSet<String> variablesSet = new HashSet<>();
		
		// Parse cnf formula and variables
		// Split user input on ^ to divide clauses
		List<Clause> clauses = Arrays.asList(cnfFormulaString.replaceAll("[()\\s]", "")
			.split("\\^")).stream().map(clauseStr -> {

			// Remove parentheses and space and split on v to divide literals
			List<Literal> list = Arrays.asList(clauseStr.split("v")).stream()
				.filter(literalStr -> !literalStr.trim().isEmpty())
				.map(literalStr -> {
					
					Literal literal = new Literal();
					literalStr = literalStr.trim();
					
					// Nagation values start with an "n", remove after read.
					boolean isNegative  = literalStr.startsWith("n");
					if (isNegative) {
						literalStr = literalStr.substring(1);
					}

					// Populate Literal and Variable set.
					literal.setName(literalStr);
					variablesSet.add(literalStr);
					literal.setIsNegated(isNegative);

					return literal;
				}).collect(Collectors.toList());

			Clause clause = new Clause(new ArrayList<>(list));

			return clause;
		}).collect(Collectors.toList());

		cnfFormula.setClauses( new ArrayList<>(clauses));

		String[] variables = new String[variablesSet.size()];

		assignment.setVariables(variablesSet.toArray(variables));
	} 

	private void promptAssignment() {
		AssignmentView view = new AssignmentView();
		view.setModel(assignment);
		view.setVisible(true);
	}

	
	/**
	 * Display if the assignment was verified.
	 * @param verified
	 */
	public void showVerified(boolean verified) {
		pane.showMessageDialog(frame, String.format("The assignment was %sverified.",
			verified ? "" : "not "));
	}
	
	/**
	 * Display if the assignment is satisfiable.
	 * @param inSatisfiable
	 */
	public void showSatisfiable(String inSatisfiable) {
		pane.showMessageDialog(frame, String.format("The cnf formula was %ssatisfiable.%s",
			inSatisfiable != null ? "" : "not ", 
			inSatisfiable == null ? "" : 
				String.format("\n %s was the first assignment to satisfy the formula.",
				inSatisfiable)));
	}
}
