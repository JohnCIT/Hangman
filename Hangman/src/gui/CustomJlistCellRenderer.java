package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.Renderer;

import com.sun.istack.internal.Pool.Impl;

public class CustomJlistCellRenderer extends DefaultListCellRenderer {
	
	
	public Component getListCellRendererComponent(JList list, Object value,	int index, boolean isSelected, boolean cellHasFocus) {
		Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		
		Color green = new Color(224, 92, 92);
		Color red 	= new Color(128, 224, 128);
				
		if (value.toString().length() > 1) {
			c.setBackground(green);
		}
		else {
			c.setBackground(red);
		}
	    
	    return this; 		
	}
		
}
