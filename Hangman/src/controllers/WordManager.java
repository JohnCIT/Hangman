package controllers;

import java.util.ArrayList;


import util.DifficultyEnum.Difficulty;
import util.RandomNumbers;

public class WordManager {
	
	public static ArrayList<String> easy;
	public static ArrayList<String> medium;
	public static ArrayList<String> hard;
	
	
	/**
	 * Pass in the difficulty and get a random word based on the difficulty
	 * @param diffi
	 * @return
	 */
	public static String getRandomWord(Difficulty diffi) {
		
		// Get a random word from the arrays depending on difficulty
		String ranWord;
		int minRange = 0;
		
		
		
		switch (diffi) {
			case EASY:
					ranWord = easy.get( RandomNumbers.getRandomNumberInRange(minRange, 	 easy.size()) );
				break;
				
			case MEDIUM:
					ranWord = medium.get( RandomNumbers.getRandomNumberInRange(minRange, medium.size()) );
				break;
				
			case HARD:
					ranWord = hard.get( RandomNumbers.getRandomNumberInRange(minRange,   hard.size()) );
				break;
				
				default:	
					ranWord = easy.get( RandomNumbers.getRandomNumberInRange(minRange,  easy.size()) );
					break;
		}
		
		// Put the word to upper case
		ranWord = ranWord.toUpperCase();
		
		return ranWord;
	}
	
	

}
