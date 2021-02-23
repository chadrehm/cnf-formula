/**
 * @author Chad Rehm
 * @data 1/11/21
 * @description CS475 Comp Theory assignment 1 - Build Pushdown Automata
 */
package cs475_sat_rehm;

import javax.swing.SwingUtilities;

public class CS475_SAT_Rehm {


	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					createAndShowGUI();
				} catch (Exception e) {
					System.out.println("An error occured");
				}
			}
		});
	}	
 
	/**
	 * Initialize the view
	 * @throws Exception
	 */
	public	 static void createAndShowGUI() throws Exception {
		new View();
	}
}
