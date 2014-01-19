package main;

import java.io.Serializable;
import java.util.ArrayList;

public class HighScoreArrayList extends ArrayList<Integer> implements Serializable{

	public String toString() {
		String highScores = "HighScores: \n";
		
		for (int i=0; i<super.size(); i++) {
			highScores += i+1 + ": " + super.get(i) + "\n";
		}
		
		return highScores;
	}
	
	
	public String print() {
		return toString();
	}
	
}
