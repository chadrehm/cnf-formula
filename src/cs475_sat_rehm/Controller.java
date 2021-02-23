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
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Controller implements ActionListener{
	JFileChooser fc;
	JFrame frame;
	JTextArea  tarea;
	JOptionPane pane;
	
	Controller(JFileChooser fc, JFrame frame, JTextArea  tarea, JOptionPane pane) {
		this.tarea = tarea;
		this.frame = frame;
		this.fc = fc;
		this.pane = pane;
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
	 * display results to users.  Prompt user for additional input strings
	 */
	public void handleAction() {
		String inputString = promptInput();
		if (inputString != null) {	
			
		} else {
			pane.showMessageDialog(frame, "An input is required.");
		}
		
		String userContinue = promptContinue();
		
		if (userContinue != null && !userContinue.equals("n")) {
			handleAction();
		} else {
			System.exit(1);
		}
	}
	
	/**
	 * Open and read file then build Turing Machine
	 * @param file
	 */
	public void readFile(File file){
		String cnfFormulaString = null;
		CnfFormula cnfFormula = new CnfFormula();

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
			cnfFormula.setClauses(
				new ArrayList<>(Arrays.asList(cnfFormulaString.split("\\^"))));
		};
	}
	
	/**
	 * Open prompt and return input string
	 * @return
	 */
	public String promptInput() {
		return pane.showInputDialog(frame, "Enter input string.");
	}
	
	/**
	 *
	 * @return
	 */
	public String promptContinue() {
		return pane.showInputDialog(frame, "Enter another string? (type n to exit)");
	}
}
