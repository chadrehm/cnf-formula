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
	public void test_setClauses_satisfiable() {
		Controller controller = new Controller(fc, frame, pane);
		
		controller.parseCnfFormulaInput("(x1 v x3 v x4) ^ (nx1 v x3) ^ " +
			"(nx1 v x4 v nx2) ^ nx3 ^ (x2 v nx4) ");
		
		assertNotNull(controller.satisfiable());
	}
	
	@Test
	public void test_setClauses_not_satisfialbe() {
		Controller controller = new Controller(fc, frame, pane);
		
		controller.parseCnfFormulaInput("(x1) ^ (nx1)");
		String satisfialbe = controller.satisfiable();
		
		assertNull(satisfialbe);
	}
	
	@Test
	public void test_setClauses_other_variables() {
		Controller controller = new Controller(fc, frame, pane);
	
		controller.parseCnfFormulaInput("(x v nz v y) ^ (nx v ny) ^ (ny)");
		String satisfialbe = controller.satisfiable();
		
		assertNotNull(satisfialbe);
	}
	
	@Test
	public void test_setClauses_big() {
		Controller controller = new Controller(fc, frame, pane);
	
		controller.parseCnfFormulaInput("(a v nb v c) ^ (na v nd) ^ (c v d v e v f)" +
			" ^ (nf v ne v g v nh) ^ (a v ng v ne v nt) ^ a");
		String satisfialbe = controller.satisfiable();
		
		assertNotNull(satisfialbe);
	}
	
	@Test
	public void test_setClauses_slow() {
		Controller controller = new Controller(fc, frame, pane);
	
		controller.parseCnfFormulaInput("a ^ b ^ c ^ d ^ e ^ f ^ g ^ h ^ i ^ u " +
			"^ j ^ k ^ l ^ m ^ nn ^ p ^ q ^ r ^ s ^ nt)");
		String satisfialbe = controller.satisfiable();
		
		assertNotNull(satisfialbe);
	}
	
		@Test
	public void test_setClauses_bad_input() {
		Controller controller = new Controller(fc, frame, pane);
	
		controller.parseCnfFormulaInput("(v)(a ^ b v^ c ^ d) ^ )");
		String satisfialbe = controller.satisfiable();
		
		assertNotNull(satisfialbe);
	}
}
