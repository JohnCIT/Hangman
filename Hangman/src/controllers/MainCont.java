package controllers;

import inputOutput.SaveAndLoadHighScores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import main.HighScoreArrayList;
import gui.CustomDialogComboBox;
import gui.GuiUtil;
import gui.View;

public class MainCont {
	
	View view;
	GameController gameCont;
	
	public String[] listDisplay = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	public String[] alpha 		= {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	
	int whatToDraw = 0;
	int hangManDrawn = 12;
	int points = 0;

	final int EASY_POINTS = 20;
	final int MED_POINTS  = 40;
	final int HARD_POINTS = 60;
	
	public MainCont() {}
	
	HighScoreArrayList highScore;
			
	
	public MainCont(View view) {
		this.view = view;
		
		highScore = new HighScoreArrayList();
				
		// Set the default state for the GUI
		setListView();
		setHighScores();
				
		// Hook up the action listeners
		view.startGameAction	(new StartGameAction());
		view.guessLetterAction	(new LetterListSelection());
		view.guessWordAction	(new GuessWordAction());
		view.quitAction			(new QuitAction());
		view.saveActionListener (new SaveAction());
		view.loadActionListener	(new LoadAction());
		view.feedBackAction		(new FeedBack());
	}
	
	
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////// Private ///////////////////////////////////////////////////////////////
	
	/**
	 * Set up the default state
	 */
	private void setListView() {
		view.setJlist(listDisplay);
	}
	
	
	/**
	 * Reset the displayArray
	 */
	private void resetDisplayArray() {
		for (int i=0; i<alpha.length; i++) {
			listDisplay[i] = alpha[i];
		}
	}
	
	
	/**
	 * Set up the high score display
	 */
	private void setHighScores() {
		String display = "Current Score: " + points + "\n\n";
		display += highScore.toString();
		view.setScoreText(display);
	}
	
	
	/**
	 * Add the points corresponding to the difficulty
	 */
	private void addPointsDependingOnDifficulty() {
		
		switch (view.getSelectedDifficulty()) {
		case EASY:
				points += EASY_POINTS;
			break;
			
		case MEDIUM:
				points += MED_POINTS;
			break;
			
		case HARD:
				points += HARD_POINTS;
			break;
			
			default:
				points += 0;
		}
	}
	
	
	/**
	 * The steps taken when the game is won
	 */
	private void winGame() {
		GuiUtil.showHelpMessage("Winner!", "You win!");
		
		// Add up the points											
		addPointsDependingOnDifficulty();
	
		// Reset the view
		setHighScores();
		
		// Reset the view
		view.setStatusStartButton(true);
		view.setDifficultyRadStatus(true);
		view.setLetterPickingStatus(false);
		view.setGuessWordStatus(false);
	}
	
	
	/**
	 * The steps taken when the game is lost
	 */
	private void loseGame() {
		GuiUtil.showHelpMessage("You lose", "You lost, try again\nThe Word was " + gameCont.randomWord);
		gameCont.displayWord = gameCont.randomWord;
		view.setLetterPickingStatus(false);
		view.setStatusStartButton(true);
		view.setDifficultyRadStatus(true);
		view.setGuessWordStatus(false);
		
		// Set the points into the array and rest them
		highScore.add(points);
		points = 0;
		
		// Reset the view for the scores.
		setHighScores();
	}
	
	
	/**
	 * Draw a hangman piece
	 */
	private void drawHangmanPiece() {
		whatToDraw ++;
		view.hangManPanWhatToDraw(whatToDraw);
		view.refreshHangManPanel();
	}
	
	
	private boolean isGameLost() {
		if (whatToDraw >= hangManDrawn) {
			return true;
		}
		
		return false;
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////// Classes for the actionListeners ///////////////////////////////////////////
	
	/**
	 * what occurs when the user presses the start game button
	 * @author such
	 *
	 */
	class StartGameAction implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			// Reset the word
			gameCont.displayWord = "";
			
			// Start the game controller, Get the difficulty from the view
			gameCont = new GameController(view.getSelectedDifficulty());
			
			// Display the underscores representative of the word
			view.refreshHangManPanel();
			
			// Disable the start button and the radio group
			view.setStatusStartButton(false);
			view.setDifficultyRadStatus(false);
			
			// Enable the selection of letters and words
			view.setLetterPickingStatus(true);
			view.setGuessWordStatus(true);
			
			// Set the hang man panel 0
			whatToDraw = 0;
			view.hangManPanWhatToDraw(whatToDraw);		
			
			// Reset the list display
			resetDisplayArray();
			setListView();	
		}		
	}
	


	/**
	 * When a letter is selected from  the list
	 */
	class LetterListSelection implements MouseListener {

		public void mouseClicked(MouseEvent e) {

			// Only do this if there is something selected
			if (view.getLetterSelection() != -1) {

				// Check the selection and update the word on screen
				String letterSelection = alpha[view.getLetterSelection()];
								
				// If the selected value has not been picked before add to the List display and update the Jlist
				// The list should only have letters until it is picked,  then Picked is added so it is no longer a single value
				if (view.getListValueSelected().length() == 1) {
					listDisplay[view.getLetterSelection()] = view.getListValueSelected() + " Picked";
					setListView();
					
					// Check if the selection is contained in the word. if not draw a section of the hang man
					if (!gameCont.isSelectionCorrect(letterSelection)) {
						drawHangmanPiece();
					}
										
					// if the variable is equal to the max drawing of the hangman they lose
					if (isGameLost()) {
						loseGame();
					}
				}
				
				
				// Check if the selection has been made
				if (!gameCont.hasSelectionBeenMade(letterSelection)) {
					gameCont.checkWordOrLetterAndupdateGuiView(letterSelection);
					view.refreshHangManPanel();		
					if(gameCont.isWinner) {
						winGame();
					}
				}
				
			}
		}

		
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}	
	}
	
	
	
	/**
	 * Steps when a key is typed into the guess word field
	 *
	 */
	public class GuessWordAction implements KeyListener {

		public void keyTyped(KeyEvent e) {}
		public void keyPressed(KeyEvent e) {}

		public void keyReleased(KeyEvent e) {
			
			// Check if the lengths are equal, This way I know if the they have typed enough characters
			if (view.getGuessedWord().length() == gameCont.randomWord.length()) {
				
				// Check if the guess is correct, if not reset the field and draw a hangman section
				if (view.getGuessedWord().equalsIgnoreCase(gameCont.randomWord)) {
					winGame();
					view.setGuessWordField("");
				}
				else {
					drawHangmanPiece();					
					view.setGuessWordField("");
					if (isGameLost()) {
						loseGame();
					}
				}
			}
			
		}
		
	}
	
	
	
	/**
	 * quit and save the high score
	 */
	public class QuitAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			SaveAndLoadHighScores.saveHighScores(highScore);
			System.exit(0);
		}
		
	}
	
	
	
	/**
	 * Save the high score array
	 */
	public class SaveAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			highScore.add(points);
			SaveAndLoadHighScores.saveHighScores(highScore);		
		}
		
	}
	
	
	
	/**
	 * load the high score array
	 */
	public class LoadAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			highScore = SaveAndLoadHighScores.readHighScores();
			setHighScores();
		}
		
	}
	
	
	/**
	 * Make screen for feed back
	 */
	public class FeedBack implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new CustomDialogComboBox();
		}
		
	}
	
	
	
	
	
	

}
