/**
 * @author Chad Rehm
 * @date 1/13/21
 * @description This is the programs view
 */
package cs475_sat_rehm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class View {
	View(){
		JFileChooser fc = new JFileChooser();
		
		JFrame.setDefaultLookAndFeelDecorated(true);
    JFrame frame = new JFrame("PDA Processor");
		frame.setPreferredSize(new Dimension(350,300));
		frame.setLocation(100, 100);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JTextArea  tarea = new JTextArea(10, 10);

		JOptionPane pane = new JOptionPane();
		
    JButton readButton = new JButton("Open File");
		Controller controller = new Controller(fc, frame, tarea, pane);
    readButton.addActionListener(controller);

    frame.getContentPane().add(tarea, BorderLayout.CENTER);
    frame.getContentPane().add(readButton, BorderLayout.PAGE_END);
    frame.pack();
    frame.setVisible(true);
	}
}
