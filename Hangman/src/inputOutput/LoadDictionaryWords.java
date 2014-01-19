package inputOutput;

import gui.GuiUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import controllers.WordManager;


public class LoadDictionaryWords {
	
	/**
	 * Read in the dictionary words and sort them into 3 groups
	 * Easy, medium and hard
	 */
	public static void readInDictionaryWordsAndSort() {
		File dictWords = new File("dictWords.txt");

		// Instantiate the arrays to add words too
		WordManager.easy 	= new ArrayList<String>();
		WordManager.medium 	= new ArrayList<String>();
		WordManager.hard 	= new ArrayList<String>();
		
		try {
			Scanner readInput = new Scanner(dictWords);
			
			while (readInput.hasNext()) {
				String word = readInput.next();
				
				if (word.length() <= 4) {
					WordManager.easy.add(word);
				} 
				else if (word.length() <= 7) {
					WordManager.medium.add(word);
				}
				else {
					WordManager.hard.add(word);
				}
				
				
			}
			
			
			
		} catch (FileNotFoundException e) {
			GuiUtil.showError("Error", "Cannot read in words, Closing program");
			System.exit(0);
		}
	}
	
}
