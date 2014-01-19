package gui;

import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;

import net.miginfocom.swing.MigLayout;


public class CustomDialogComboBox extends JDialog implements ActionListener, ItemListener{

	
	JComboBox<String> combo;
	
	String[] list = {"Fantastic", "Ok", "Bad"};
	
	JLabel imageLab;
	
	JLabel feedBackMessage;
	ImageIcon happy = new ImageIcon("images/happy.jpg");
	ImageIcon med   = new ImageIcon("images/med.jpg");
	ImageIcon sad   = new ImageIcon("images/sad.jpg");
	
	
	public CustomDialogComboBox() {		
		
		// Set components up
		combo = new JComboBox<String>(list);
		feedBackMessage = new JLabel("What do you think of the game?");
		imageLab = new JLabel();
		
		// Set up layout
		setLayout(new FlowLayout());
		
		// Set the default image
		imageLab.setIcon(happy);
		
		// Add components to the dialog
		add(feedBackMessage);
		add(combo);
		add(imageLab);
		
		// Set the combo box action listener
		combo.addItemListener(this);
		
		// Display stuff
		pack();
		setSize(220, 400);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void itemStateChanged(ItemEvent e) {
		switch (combo.getSelectedIndex()) {
			case 0:
					imageLab.setIcon(happy);
				break;
			case 1:
					imageLab.setIcon(med);
				break;
			case 2:
					imageLab.setIcon(sad);
				break;

		default:
			break;
		}
	}

	
	public void actionPerformed(ActionEvent e) {
				
	}

}
