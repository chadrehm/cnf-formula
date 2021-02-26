/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs475_sat_rehm;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Chad Rehm
 */
public class CS475_SAT_RehmTest {
	JFileChooser fc;
	JFrame frame;
	JOptionPane pane;
	Assignment assignment;
	CnfFormula cnfFormula;
	
	public CS475_SAT_RehmTest() {
	}

	@Test
	public void test_setClauses() {
		Controller controller = new Controller(fc, frame, pane);
		
		controller.parseCnfFormulaInput("(x1 v x3 v x4) ^ (nx1 v x3) ^ " +
			"(nx1 v x4 v nx2) ^ nx3 ^ (x2 v nx4) ");
		
		controller.satisfiable();
	}
}
