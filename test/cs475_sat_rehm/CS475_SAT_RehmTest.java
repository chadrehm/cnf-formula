/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs475_sat_rehm;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Chad Rehm
 */
public class CS475_SAT_RehmTest {
	
	public CS475_SAT_RehmTest() {
	}

	@Test
	public void test_setClauses() {
		CnfFormula cnfFormula = new CnfFormula();
		
		cnfFormula.setClauses(new ArrayList<>(Arrays.asList(
			" (x1 v x3 v x4) ",
			" (nx1 v x3) ",
			" (nx1 v x4 v nx2) ",
			" nx3 ", 
			" (x2 v nx4) "
		)));
		
	}
}
