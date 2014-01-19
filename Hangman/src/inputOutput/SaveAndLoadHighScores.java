package inputOutput;

import gui.GuiUtil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import main.HighScoreArrayList;

public class SaveAndLoadHighScores {
	
	public static void saveHighScores(HighScoreArrayList scores) {
		try {
			FileOutputStream save 		  = new FileOutputStream("highScore.txt");
			ObjectOutputStream serialSave = new ObjectOutputStream(save);
			
			// Save the object
			serialSave.writeObject(scores);
			
			// Close the files 
			serialSave.close();
			save.close();			
		} 
		catch (IOException e) {
			GuiUtil.showError("Cannot save", "Cannot save highscores");
		}
	}
	
	
	
	public static HighScoreArrayList readHighScores() {
		
		HighScoreArrayList highScores = new HighScoreArrayList();
		
		try {
			
			// Open the streams
			FileInputStream read 	 = new FileInputStream("highScore.txt");
			ObjectInputStream readIn = new ObjectInputStream(read);
			
			// Read the data
			highScores = (HighScoreArrayList) readIn.readObject();
			
			// Close the streams
			readIn.close();
			read.close();
		} 
		catch (IOException e) {
			GuiUtil.showError("Cannot Load", "Cannot load highscores");			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return highScores;
	}
}
