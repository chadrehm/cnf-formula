/**
 * @author Chad Rehm
 * @date 1/27/21
 * @description This class controls the application.
 */
package cs475_sat_rehm;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	}
	
	/**
	 * After file is selected prompt user for input string, process input and
	 * display results to users.  Prompt user for additional assignments
	 */
	public void handleAction() {
		
		promptAssignment();
		boolean verified = cnfFormula.verify(assignment);
		showVerified(verified);

		String userContinue = promptContinue();
		if (userContinue != null && !userContinue.equals("n")) {
			handleAction();
		} else {
			satisfiable();
			System.exit(1);
		}
	}
	
	public void satisfiable() {
		boolean verified;
		String[] variables = assignment.getVariables();
		int numberVariables = variables.length;
		int assignmentCount = (int)Math.pow(2, numberVariables);
		
		for (int idx = 0; idx < assignmentCount; idx++) {
			StringBuilder psudoAssignmentSB = 
				new StringBuilder(Integer.toBinaryString(idx)).reverse();
			
			String psudoAssignment = padRightZeros(psudoAssignmentSB, numberVariables);
			
			String [] assignmentValues = psudoAssignment.split("");
			
			for (int index = 0; index < numberVariables; index++) {
				assignment.setValue(variables[index], assignmentValues[index].equals("1"));
			}
			
			verified = cnfFormula.verify(assignment);
			if(verified) {
				System.out.println("Verified with " + psudoAssignment);
			}
			
		}
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
	 * Open and read file then build Turing Machine
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
	
	protected void parseCnfFormulaInput(String cnfFormulaString) {
		HashSet<String> variablesSet = new HashSet<>();
		
		// Parse cnf formula and variables
		// Split user input on ^ to divide clauses
		List<Clause> clauses = Arrays.asList(cnfFormulaString.split("\\^"))
			.stream().map(clauseStr -> {

			// Remove parentheses and space and split on v to divide literals
			List<Literal> list = Arrays.asList(
				clauseStr.replaceAll("[()\\s]", "").split("v")).stream().map(literalStr -> {
					Literal literal = new Literal();

					// Nagation values start with an "n", remove after read.
					boolean isNegative = literalStr.startsWith("n");
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
	 *
	 * @return
	 */
	public String promptContinue() {
		return pane.showInputDialog(frame, "Try another assignment?\n"
			+ "Type n to find if formula is satisfiable and exit.");
	}
	
	public void showVerified(boolean verified) {
		pane.showMessageDialog(frame, String.format("The assignment was %sverified.",
			verified ? "" : "not "));
	}
}
