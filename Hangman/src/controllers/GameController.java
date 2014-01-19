package controllers;

import java.util.ArrayList;
import util.DifficultyEnum.Difficulty;

public class GameController {

	public String randomWord;
	public static String displayWord = "";
	public int correctLetters = 0;

	public boolean isWinner = false;
	
	private ArrayList<Integer> letterLocation;
	private String allSelection = "";

	public GameController(){}

	/**
	 * Get a random word
	 * @param difficulty
	 */
	public GameController(Difficulty difficulty) {
		randomWord = WordManager.getRandomWord(difficulty);

		// Get the underscores to display
		convertWordToHiddenFields();
		
		// Initilize the array
		letterLocation = new ArrayList<Integer>();
				
		// Debug
		System.out.println(randomWord);
	}
	
	
	
	/**
	 * Update the label if the letter or word is correct
	 * Changes is winner if fully correct
	 */
	public void checkWordOrLetterAndupdateGuiView(String selection) {
		
		// Add the selection to the string to check for later
		allSelection += selection;
		
		// Check if the users selection was correct
		if (doesWordContainLetter(selection)) {
			updateDisplayWord(selection);
			if (correctLetters == randomWord.length()) {
				isWinner = true;
			}
		}	
	}
	
	
	
	/**
	 * Check if the selection is in the word
	 */
	public boolean isSelectionCorrect(String selection) {
		if (randomWord.contains(selection)) {
			return true;
		}
		
		return false;
	}
	
	
	
	/**
	 * Check if the selection has already been made
	 */
	public boolean hasSelectionBeenMade(String selection) {
		if (allSelection.contains(selection)) {
			return true;
		}
		
		return false;
	}











	//////////////////////////////////////////////////////////////////////////
	/////////////////////////// Private /////////////////////////////////////

	
	
	
	/**
	 * Update the display word
	 */
	private void updateDisplayWord(String selection) {
				
		
		// Go through the word and change the letter if necessary
		for (int i=0; i<letterLocation.size(); i++){
			
			// Check if this location is guessed correct
			for (int k=0; k<randomWord.length(); k++) {
				if (letterLocation.get(i) == k){		// Get the letter location, Because I have a underscore then a space * 2 
					String temp = displayWord.substring(0, letterLocation.get(i)*2) + selection + displayWord.substring((letterLocation.get(i)*2)+1);
					displayWord = temp;
				}
			}
		}
		
		
		// Reset the array
		letterLocation.clear();		
	}
	

	

	/**
	 * Check if the random word contains the selected word or letter
	 * @param selection
	 * @return
	 */
	private boolean doesWordContainLetter(String selection) {

		int loc = -1;
		int letterCount = -1;
		
		// Check if the random word contains the selection
		if (randomWord.contains(selection)) {

			// If word was passed in check if it is right
			if (randomWord.equals(selection)) {
				isWinner = true;
				displayWord = selection;
				return true;
			}
			else {				

				do {

					// Location of the selection
					loc = randomWord.indexOf(selection, letterCount+1);

					if (loc != -1) {
						correctLetters ++;
						letterCount = loc;
						letterLocation.add(loc);
					}

				}while (loc != -1);
				return true;
			}
		}
		
		return false;
	}


	



	/**
	 * Convert the word to the underscores
	 */
	private void convertWordToHiddenFields() {
		for (int i=0; i<randomWord.length(); i++) {
			displayWord += "_ ";
		}
	}
	
	
	




}
