package main;

import controllers.MainCont;
import controllers.WordManager;
import inputOutput.LoadDictionaryWords;
import gui.GuiUtil;
import gui.View;

public class Main {
	
	public static void main(String[] args) {
		
		// Set the native look and feel
		GuiUtil.setNativeLookAndFeel();
		
		// Start the view
		View view = new View();
		
		// Start the controller
		MainCont cont = new MainCont(view);
		
		// Load the dictionary words
		LoadDictionaryWords.readInDictionaryWordsAndSort();
		
		
	}

}
