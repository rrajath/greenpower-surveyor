/**
 * 
 */
package com.uic.gpsurveyor.gui;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 * @author rajath
 *
 */
public class MainGUI extends JFrame{

	public static void main(String args[])
	{
		JFrame frame = new JFrame("SpringLayout");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = frame.getContentPane();
		frame.setVisible(true);

		SpringLayout layout = new SpringLayout();
		contentPane.setLayout(layout);
		//		setBounds(100, 100, 450, 300);

		String[] labels = {"Name: ", "Fax: ", "Email: ", "Address: "};
		int numPairs = labels.length;

		//		JPanel p = new JPanel(new SpringLayout());
		for (int i = 0; i < numPairs; i++) {
			JLabel l = new JLabel(labels[i], JLabel.TRAILING);
			contentPane.add(l);
			JTextField textField = new JTextField(10);
			l.setLabelFor(textField);
			contentPane.add(textField);
		}

	}
}
